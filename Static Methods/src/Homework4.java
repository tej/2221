import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Homework4 {

    /**
     * Checks whether the given point (xCoord, yCoord) is inside the circle of
     * radius 1.0 centered at the point (1.0, 1.0).
     *
     * @param xCoord
     *            the x coordinate of the point
     * @param yCoord
     *            the y coordinate of the point
     * @return true if the point is inside the circle, false otherwise
     */
    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        return Math.sqrt(
                Math.pow(xCoord - 1, 2.0) + Math.pow(yCoord - 1, 2.0)) <= 1;
    }

    /**
     * Generates n pseudo-random points in the [0.0,2.0) x [0.0,2.0) square and
     * returns the number that fall in the circle of radius 1.0 centered at the
     * point (1.0, 1.0).
     *
     * @param n
     *            the number of points to generate
     * @return the number of points that fall in the circle
     */
    private static int numberOfPointsInCircle(int n) {
        int ptsInInterval = 0, ptsInSubinterval = 0;
        /*
         * Generate points and count how many fall in [0.0,2.0),[0.0,2.0)
         * interval
         */
        Random rnd = new Random1L();
        while (ptsInInterval < n) {
            /*
             * Generate pseudo-random number in [0.0,1.0) interval
             */
            double x = rnd.nextDouble() * 2.0;
            double y = rnd.nextDouble() * 2.0;
            /*
             * Increment total number of generated points
             */
            ptsInInterval++;
            /*
             * Check if point is in [0.0,0.5) interval and increment counter if
             * it is
             */
            if (pointIsInCircle(x, y)) {
                ptsInSubinterval++;
            }
        }
        return ptsInSubinterval;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        /*
         * Ask user for number of points to generate
         */
        out.print("Number of points: ");
        int n = in.nextInteger();

        double estimate = (100.0 * 4 * numberOfPointsInCircle(n)) / n;
        out.println("Estimate of percentage: " + estimate + "%");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
