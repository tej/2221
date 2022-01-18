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
public final class Newton1 {

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        double r = x;
        while (!(Math.abs(r * r - x) / x < 0.00001)) {
            r = (r + x / r) / 2;
        }
        return r;
    }

// <h>this is main</h>
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        String rerun = "";

        do {
            out.println(
                    "Enter a number you want to estimate the square root of: ");
            out.println(sqrt(in.nextDouble()));
            out.println("Do you want to recalculate?: ");
            rerun = in.nextLine();
        } while (rerun.equalsIgnoreCase("Y"));

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
