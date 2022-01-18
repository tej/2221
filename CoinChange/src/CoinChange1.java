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
public final class CoinChange1 {

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
        int currentChange = 0;
        int Dollars = 0, halfDollars = 0, Quarters = 0, Dimes = 0, Nickles = 0,
                Pennies = 0;
        output.println("Enter the number you want change for:");
        currentChange = input.nextInteger();

        if (currentChange >= 100) {
            Dollars = currentChange / 100;
            currentChange = currentChange % 100;

        }
        if (currentChange >= 50) {
            halfDollars = currentChange / 50;
            currentChange = currentChange % 50;
        }
        if (currentChange >= 25) {
            Quarters = currentChange / 25;
            currentChange = currentChange % 25;
        }
        if (currentChange >= 10) {
            Dimes = currentChange / 10;
            currentChange = currentChange % 10;
        }
        if (currentChange >= 5) {
            Nickles = currentChange / 5;
            currentChange = currentChange % 5;
        }
        if (currentChange >= 1) {
            Pennies = currentChange / 1;
        }
        output.println(Dollars);
        output.println(halfDollars);
        output.println(Quarters);
        output.println(Dimes);
        output.println(Nickles);
        output.println(Pennies);

        input.close();
        output.close();
    }

}
