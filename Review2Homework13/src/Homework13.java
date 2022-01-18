import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Homework13 {

    private static boolean hasDigit(NaturalNumber n, int digit,
            SimpleWriter out) {

        boolean found = false;

        int lastDigit = n.divideBy10();

        out.println(lastDigit + " n:" + n);

        if (lastDigit == digit) {
            found = true;

        } else if (!n.isZero()) {
            found = hasDigit(n, digit, out);
        }

        n.multiplyBy10(lastDigit);

        return found;

    }

    /**
     * Returns the product of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to multiply
     * @return the product of the digits of {@code n}
     * @clears n
     * @ensures productOfDigits1 = [product of the digits of n]
     */
    private static NaturalNumber productOfDigits1(NaturalNumber n) {
        int product = 1;
        while (!n.isZero()) {
            product *= n.divideBy10();
        }
        n.clear();
        return new NaturalNumber2(product);
    }

    /**
     * Returns the product of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to multiply
     * @return the product of the digits of {@code n}
     * @ensures productOfDigits2 = [product of the digits of n]
     */
    private static NaturalNumber productOfDigits2(NaturalNumber n) {
        String num = n.toString();
        int sum = 1;
        char[] nNarr = num.toCharArray();
        for (char i : nNarr) {
            sum += Integer.parseInt(String.valueOf(i));
        }
        return new NaturalNumber2(sum);
    }

    /**
     * Reports the value of {@code n} as an {@code int}, when {@code n} is small
     * enough.
     *
     * @param n
     *            the given {@code NaturalNumber}
     * @return the value
     * @requires n <= Integer.MAX_VALUE
     * @ensures toInt = n
     */
    private static int toInt(NaturalNumber n) {
        assert n.canConvertToInt() : "Violation of: n <= Integer.MAX_VALUE";
        return n.toInt();
    }

    /**
     * Reports whether the given tag appears in the given {@code XMLTree}.
     *
     * @param xml
     *            the {@code XMLTree}
     * @param tag
     *            the tag name
     * @return true if the given tag appears in the given {@code XMLTree}, false
     *         otherwise
     * @ensures <pre>
     * findTag =
     *    [true if the given tag appears in the given {@code XMLTree}, false otherwise]
     * </pre>
     */
    private static boolean findTag(XMLTree xml, String tag) {
        if (xml.label().equals(tag)) {
            return true;
        }
        for (int i = 0; i < xml.numberOfChildren(); i++) {
            XMLTree child = xml.child(i);
            if (findTag(child, tag)) {
                return true;
            }
        }

        return false;

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
        NaturalNumber num = new NaturalNumber2(1212);
        out.println(hasDigit(num, 3, out));
        out.println(num);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
