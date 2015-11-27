package matrix;

import org.apache.commons.math.fraction.Fraction;

import java.util.ArrayList;

/**
 * Created by gnupinguin on 27.11.15.
 */
public class BorderMatrix extends FractionMatrix {
    public BorderMatrix(Fraction[][] data) {
        super(data);
        makeBorder();
    }

    public BorderMatrix(Integer[][] data) {
        super(data);
        makeBorder();
    }
    private void makeBorder(){
        ArrayList<Fraction> rowsValues = new ArrayList<Fraction>();
        ArrayList<Fraction> columnValues = new ArrayList<Fraction>();

        for (int i = 0; i < matrix.get(0).size(); i++){
            rowsValues.add(new Fraction(i+1));
        }
        for (int j = 0; j < matrix.size(); j++){
            columnValues.add(new Fraction(j+1));
        }

        matrix.set(0, rowsValues);
        for (int i = 1; i < matrix.size(); i++){
            matrix.get(i).set(0, columnValues.get(i-1));
        }
    }

    @Override
    public void set(int i, int j, Integer value) {
        super.set(i + 1, j + 1, value);
    }

    @Override
    public void set(int i, int j, Fraction value) {
        super.set(i + 1, j + 1, value);
    }

    @Override
    public Fraction get(int i, int j) {
        return super.get(i + 1, j + 1);
    }

    public int getVerticalPosition(int i){
        return super.get(i,0).getNumerator();
    }
    public int getHorizontalPosition(int i){
        return super.get(0,i).getNumerator();
    }

    @Override
    public int getColumnDimension() {
        return super.getColumnDimension() - 1;
    }

    @Override
    public int getRowDimension() {
        return super.getRowDimension() -1;
    }

    @Override
    public Fraction[] getRow(int i) {
        ArrayList<Fraction> ans = matrix.get(i);
        ans.remove(0);
        return ans.toArray(new Fraction[getRowDimension()]);
    }

    public Fraction[] getColumn(int j){
        Fraction[] ans = new Fraction[getColumnDimension()];
        for (int i = 1; i < getColumnDimension(); i++){
            ans[i-1] = matrix.get(i).get(j);
        }
        return ans;
    }
    public void print(String message){
        System.out.println("----------------");
        System.out.println(message);

        for (int i = 0; i < getColumnDimension() + 1; i++){
            for (int j = 0; j< getRowDimension() + 1; j++){
                System.out.print(matrix.get(i).get(j) +  "\t");
            }

            System.out.println();
        }
        System.out.println("----------------");
    }

}
