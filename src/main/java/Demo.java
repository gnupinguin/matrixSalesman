import matrix.FractionMatrix;
import org.apache.commons.math.fraction.Fraction;

/**
 * Created by gnupinguin on 27.11.15.
 */
public class Demo {
    public static void main(String[] args) {
        Integer[][] a = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Fraction f = new Fraction(1, 2);
        Fraction f1 = new Fraction(1);
        System.out.println(f.compareTo(f1));
    }
}
