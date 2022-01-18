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
public final class CoinChange2 {

    public static void change(int n, int[] arr, SimpleWriter output) {

        int[] finalValues = { 0, 0, 0, 0, 0, 0 };

        for (int i = 0; i < arr.length; i++) {
            while (n > arr[i]) {
                n = n - arr[i];
                finalValues[i]++;
            }

        }
        output.println("Dollars: " + finalValues[0] + "\nHalf Dollars: "
                + finalValues[1] + "\nQuarters: " + finalValues[2] + "\nDimes: "
                + finalValues[3] + "\nNickles: " + finalValues[4]
                + "\nPennies: " + finalValues[5]);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        int[] coinValues = { 100, 50, 25, 10, 5, 1 };

        output.println("Enter the number you want change for:");
        change(input.nextInteger(), coinValues, output);

        input.close();
        output.close();
    }

}
