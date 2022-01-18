import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
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
public final class Hailstone1 {

    private Hailstone1() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {
        //output first in series

        out.print(n);
        int sizeOfSeries = 1;
        NaturalNumber max = new NaturalNumber1L(0);
        //temp NN to change and print
        NaturalNumber tempNN = new NaturalNumber1L(n);
        NaturalNumber temp1 = new NaturalNumber1L(1);
        while (!tempNN.equals(temp1)) {

            int checkVar = tempNN.divideBy10();
            tempNN.multiplyBy10(checkVar);

            if (checkVar % 2 == 0) {
                tempNN.divide(new NaturalNumber1L(2));
            } else {
                tempNN.multiply(new NaturalNumber1L(3));
                tempNN.add(new NaturalNumber1L(1));
            }
            if (tempNN.compareTo(max) > 0) {
                max.copyFrom(tempNN);
            }

            //print updated temp NN
            out.print(", " + tempNN);
            sizeOfSeries++;
        }
        out.println("\nlength of series: " + sizeOfSeries);
        out.println("max value in series: " + max);
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
        out.println("Enter number");
        String inNum = in.nextLine();

        generateSeries(new NaturalNumber1L(inNum), out);
        in.close();
        out.close();
    }

}
