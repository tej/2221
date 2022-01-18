import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Tej Dungarani
 *
 */
public final class ABCDGuesser1 {

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        String posNum = "";
        do {
            out.println("Enter a Positive Double for μ: ");
            posNum = in.nextLine();
        } while (!FormatChecker.canParseDouble(posNum)
                | Double.parseDouble(posNum) < 0);
        return Double.parseDouble(posNum);

    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        String posNum = "";
        do {
            out.println("Enter a Positive Double: ");
            posNum = in.nextLine();
        } while (!FormatChecker.canParseDouble(posNum)
                | Double.parseDouble(posNum) == 1.0);
        return Double.parseDouble(posNum);
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
        double w = 0, x = 0, y = 0, z = 0, u = 0;

        int a = 0, b = 0, c = 0, d = 0;

        double aFinal = 0, bFinal = 0, cFinal = 0, dFinal = 0;

        double aprox = 0;

        double[] deJager = { -5, -4, -3, -2, -1, -1 / 2, -1 / 3, -1 / 4, 0,
                1 / 4, 1 / 3, 1 / 2, 1, 2, 3, 4, 5 };

        double difference = (Math.pow(w, deJager[0]) * Math.pow(x, deJager[0])
                * Math.pow(y, deJager[0]) * Math.pow(z, deJager[0])) - u;

        u = getPositiveDouble(in, out);
        out.println("Enter W");
        w = getPositiveDoubleNotOne(in, out);

        out.println("Enter X");
        x = getPositiveDoubleNotOne(in, out);

        out.println("Enter Y");
        y = getPositiveDoubleNotOne(in, out);

        out.println("Enter Z");
        z = getPositiveDoubleNotOne(in, out);
        while (d <= 16) {
            while (c <= 16) {
                while (b <= 16) {
                    while (a <= 16) {

                        aprox = (Math.pow(w, deJager[a])
                                * Math.pow(x, deJager[b])
                                * Math.pow(y, deJager[c])
                                * Math.pow(z, deJager[d])) - u;
                        if (Math.abs(aprox) < Math.abs(difference)) {
                            difference = aprox;
                            aFinal = deJager[a];
                            bFinal = deJager[b];
                            cFinal = deJager[c];
                            dFinal = deJager[d];

                        }
                        a++;
                    }
                    b++;
                    a = 0;
                }
                c++;
                b = 0;
            }
            d++;
            c = 0;
        }
        out.println("Estimate of u: " + (difference + u));
        out.println("Error:" + Math.abs((difference / u) * 100));
        out.println("a: " + aFinal);
        out.println("b: " + bFinal);
        out.println("c: " + cFinal);
        out.println("d: " + dFinal);
        in.close();
        out.close();
    }

}
