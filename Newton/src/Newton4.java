import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Newtons method in Java
 *
 * @author Tej Dungarani
 *
 */
public final class Newton4 {

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x, SimpleWriter out, double epsilon) {
        if (x > 0) {
            double r = x;

            while (!(Math.abs(r * r - x) / x < epsilon)) {
                r = (r + x / r) / 2;
            }
            return r;
        } else {
            out.println("You entered 0 or a negative number that won't work");
            return 0;
        }
    }

// <h>this is main</h>
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        String rerun = "";
        double xParam = 0;
        double epsilonParam = 0;
        do {
            out.println(
                    "Enter a number you want to estimate the square root of: ");
            out.println("Enter epsilon: ");
            xParam = in.nextDouble();
            epsilonParam = in.nextDouble();
            out.println(sqrt(xParam, out, epsilonParam));
        } while (xParam > 0);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
