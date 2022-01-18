import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hailstone4 {

    /**
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
        String num = "";
        do {
            out.println("Enter a Positive Integer");
            num = in.nextLine();
        } while (!FormatChecker.canParseInt(num)
                || !(Integer.parseInt(num) > 0));

        return Integer.parseInt(num);

    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out,
            SimpleReader in) {
        out.print(n);
        int len = 0;
        int max = 0;
        while (n > 1) {
            if (max < n) {
                max = n;
            }
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = n * 3 + 1;
            }

            out.print(", " + n);
            len++;
        }
        out.print("\nLength of series:" + len);
        out.print("\nThe Maximum number in the series is: " + max);

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
        String reLoop = "";
        do {
            generateSeries(getPositiveInteger(in, out), out, in);
            out.println("\nDo you want to calculate another series");
            reLoop = in.nextLine();
        } while (reLoop.equalsIgnoreCase("y"));
        in.close();
        out.close();
    }
}
