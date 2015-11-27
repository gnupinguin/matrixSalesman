package matrix;

import org.apache.commons.math.fraction.Fraction;
import org.apache.commons.math.stat.Frequency;

import java.util.ArrayList;

/**
 * Created by gnupinguin on 27.11.15.
 */
public class FractionMatrix implements Cloneable{
    protected ArrayList< ArrayList<Fraction>> matrix = new ArrayList<ArrayList<Fraction>>();

    public FractionMatrix(Fraction[][] data){
        for (int i =  0; i < data.length; i++){
            matrix.add(new ArrayList<Fraction>());
            for (int j = 0; j < data[i].length; j++){
                matrix.get(i).add(data[i][j]);
            }
        }
    }
    public FractionMatrix(Integer[][] data){
        for (int i =  0; i < data.length; i++){
            matrix.add(new ArrayList<Fraction>());
            for (int j = 0; j < data[i].length; j++){
                matrix.get(i).add(new Fraction(data[i][j]));
            }
        }
    }
    public Fraction[][] getData(){
        Fraction[][] ans = new Fraction[getColumnDimension()][getRowDimension()];
        for (int i = 0; i < getColumnDimension();i++){
            ans[i] = matrix.get(i).toArray(ans[i]);
        }
        return ans;
    }
    public int getRowDimension(){
        return matrix.get(0).size();
    }
    public int getColumnDimension(){
        return matrix.size();
    }

    public Fraction get(int i, int j){
        return matrix.get(i).get(j);
    }
    public void set(int i, int j, Fraction value){
        matrix.get(i).set(j, value);
    }
    public void set(int i, int j, Integer value){
        matrix.get(i).set(j, new Fraction(value));
    }

    public Fraction[] getRow(int i){
        return matrix.get(i).toArray(new Fraction[getRowDimension()]);
    }
    public Fraction[] getColumn(int j){
        Fraction[] ans = new Fraction[getColumnDimension()];
        for (int i = 0; i < getColumnDimension(); i++){
            ans[i] = matrix.get(i).get(j);
        }
        return ans;
    }

    public void dropRow(int index){
            matrix.remove(index);
    }
    public void dropColumn(int index){
        for (int i = 0; i< getColumnDimension(); i++){
            matrix.get(i).remove(index);
        }
    }
    public void VerticalConcat(FractionMatrix m){
        FractionMatrix m1 = m.clone();
        for (int i = 0; i < m1.getColumnDimension(); i++){
            matrix.add(m1.matrix.get(i));
        }
    }

    public void HorizontalConcat(FractionMatrix m){
        FractionMatrix m1 = m.clone();
        for (int i = 0; i < getColumnDimension(); i++){
            matrix.get(i).addAll(m1.matrix.get(i));
        }
    }

   /* public void add(FractionMatrix m){
        for (int i = 0; i < getColumnDimension(); i++){
            for (int j = 0; j < getRowDimension(); j++){
                if (get(i,j) == null || m.get(i, j) == null)
                    matrix.get(i).set(j, null);
                else set(i, j, get(i, j).add(m.get(i, j)));
            }
        }
    }*/
    /*public void addNum(Integer num) {
        for (int i = 0; i < getColumnDimension(); i++) {
            for (int j = 0; j < getRowDimension(); j++) {
                if (get(i, j) == null || num == null)
                    matrix.get(i).set(j, null);
                else set(i, j, get(i, j).add(num));
            }
        }
    }*/

    /*public void product(FractionMatrix m){

    }*/

    @Override
    public FractionMatrix clone() {
            return new FractionMatrix(getData());
    }

    public void print(String message){
        System.out.println("----------------");
        System.out.println(message);

        for (int i = 0; i < getColumnDimension(); i++){
            for (int j = 0; j< getRowDimension(); j++){
                System.out.print(matrix.get(i).get(j) +  "\t");
            }

            System.out.println();
        }
        System.out.println("----------------");
    }
}
