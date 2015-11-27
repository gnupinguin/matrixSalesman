
import javafx.util.Pair;
import matrix.BorderMatrix;
import org.apache.commons.math.fraction.Fraction;

import java.util.ArrayList;

/**
 * Created by gnupinguin on 24.11.15.
 */
public class Salesman {
    public static final Fraction INF = null;
    private BorderMatrix matrix= null;

    public Salesman(Integer[][] matrix){
        if (!isSalesmanMatrix(matrix))
            throw new RuntimeException("Error: No salesman's matrix.");
        this.matrix  = new BorderMatrix(matrix);

    }

    public void getWay(){
        while (matrix.length != 1) {
            if (matrix.length == 2)
                System.out.println();
            printMatrix(matrix, "new iteration");
            rowsReduction(minRowsElts());
            columnsReduction(minColumnsElts(matrix));

            printMatrix(matrix, "after reduction");

            Double[][] marksMatrix = copy(matrix);
            ArrayList<Pair<Integer, Integer>> zeroCoordinates = new ArrayList<Pair<Integer, Integer>>();

            //find positions with zero-values
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] != null && matrix[i][j] == 0) {
                        zeroCoordinates.add(new Pair<Integer, Integer>(i, j));
                    }
                }
            }
            //do marks
            for (Pair<Integer, Integer> p : zeroCoordinates) {
                Double aa;
                marksMatrix[p.getFirst()][p.getSecond()] = aa =
                        add(minColumnElt(matrix, p.getSecond(), p.getFirst()), minRowElt(matrix, p.getFirst(), p.getSecond()));
                System.out.println(aa);
            }
            printMatrix(marksMatrix, "final marks!!!!!");
            //find max mark
            Double maxMark = -1d;
            Pair<Integer, Integer> coordinatesMaxMarks = null;
            for (Pair<Integer, Integer> p : zeroCoordinates) {
                if (greater(marksMatrix[p.getFirst()][p.getSecond()], maxMark)) {
                    maxMark = marksMatrix[p.getFirst()][p.getSecond()];
                    coordinatesMaxMarks = p;
                }
            }

            matrix[coordinatesMaxMarks.getFirst()][coordinatesMaxMarks.getSecond()] = INF;//!!!!!!!!!!!!1
            matrix[coordinatesMaxMarks.getSecond()][coordinatesMaxMarks.getFirst()] = INF;

            //find place where is two INF-values
            Pair<Integer, Integer> pos = deleteRowAndColumn(coordinatesMaxMarks.getFirst(), coordinatesMaxMarks.getSecond());


            System.out.println(pos.getFirst() + " " + pos.getSecond());
        }
    }

    public static boolean isSalesmanMatrix(Integer[][] m){
        if (m.length != m[0].length)
            return false;
        for (int i = 0; i < m.length; i++){
            if (m[i][i] != null)
                return false;
        }
        return true;
    }

    //return vector with min elements every row
    private Fraction[] minRowsElts(){
        Fraction[] ans = new Fraction[matrix.getRowDimension()];
        for(int i = 0; i < matrix.getRowDimension(); i++) {
            ans[i] = minRowElt(matrix, i);
        }

        return ans;
    }

    private Double[] minColumnsElts(Double[][] m){
        Double[] ans = new Double[m.length];
        for(int j = 0; j < m.length; j++) {
            ans[j] = minColumnElt(m, j);
        }

        return ans;
    }

    private Fraction minColumnElt(Fraction[][] m, int pos, int excludeRowIndex){
        Fraction min = INF;
        int i;
        for (i = 0; min == INF && i< m.length; i++) {
            if (i == excludeRowIndex) continue;
            if (m[i][pos] != INF)
                min = m[i][pos];
        }

        for (; i < m.length; i++) {
            if (i == excludeRowIndex) continue;
            if (m[i][pos] != INF && m[i][pos].compareTo(min) == -1 ) {
                min = m[i][pos];
            }
        }
        return min;
    }

    private Fraction minColumnElt(int pos){
        return minColumnElt(pos, -1);
    }

    private Double minRowElt(int pos, int excludeColumnIndex){

        Fraction min = INF;
        int i;
        for (i = 0; min == INF && i< matrix.getRowDimension(); i++) {
            if (i == excludeColumnIndex) continue;
            min = matrix.get(pos, i);
        }

        for (; i < m[pos].length; i++) {
            if (i == excludeColumnIndex)
                continue;
            if (m[pos][i] != INF && m[pos][i] < min) {
                min = m[pos][i];
            }
        }
        return min;
    }

    private Double minRowElt(Double[][] m, int pos){
        return minRowElt(m, pos, -1);
    }

    private void rowsReduction( Double[] column){
        for (int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++)
                if (matrix[i][j] != INF && column[i] != INF)
                    matrix[i][j] -= column[i];
        }
    }

    private void columnsReduction(Double[] row){
        for (int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++)
                if (matrix[j][i] != INF && row[i] != INF)
                    matrix[j][i] -= row[i];
        }
    }

    private Pair<Integer, Integer> deleteRowAndColumn(int rowPosition, int columnPosition){
        //delete row
        for (int i = rowPosition; i < matrix.length-1; i++  )
            matrix[i] = matrix[i+1];

        //delete column
        Double[][] ans = new Double[matrix.length-1][matrix.length-1];
        for (int i = 0; i < ans.length; i++){
            for (int j = 0; j < columnPosition; j++){
                ans[i][j] = matrix[i][j];
            }
            for (int j = columnPosition; j < ans.length ; j++){
                ans[i][j] = matrix[i][j+1];
            }
        }
        matrix = ans;
        printMatrix(matrix, "end variant");
        Pair<Integer, Integer> segment = new Pair<Integer, Integer>(rowsNums.get(rowPosition), columnsNums.get(columnPosition));
        rowsNums.remove(rowPosition);
        columnsNums.remove(rowPosition);

        return segment;
    }


    public static Double[][] copy(Double[][] m){
        Double[][] ans = new Double[m.length][m.length];
        for (int i = 0; i < m.length; i++)
            ans[i] = m[i].clone();
        return ans;
    }

    public Double add(Double a, Double b){
        if (a == INF || b == INF)
            return INF;
        return a+b;
    }
    public boolean greater(Double a, Double b) {
        if (a == null)
            return true;
        if (b == null)
            return false;
        return a > b;
    }
}



