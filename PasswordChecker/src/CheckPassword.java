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
public final class CheckPassword {
    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param s
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String s, SimpleWriter out) {
        String tempCheck = "";
        if (!(s.length() > 7)) {
            out.println("The password must be at least 8 characters long");
        } else {
            if (containsUpperCaseLetter(s)) {
                tempCheck += "U";
            }
            if (containsLowerCaseLetter(s)) {
                tempCheck += "L";
            }
            if (containsDigits(s)) {
                tempCheck += "N";
            }
            if (tempCheck.length() <= 2) {
                if (tempCheck.contains("U")) {

                }
                if (tempCheck.contains("L")) {

                }
                if (tempCheck.contains("N")) {

                }
                if (tempCheck.contains("S")) {

                }
            } else {
                out.print("valid");
            }
        }
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String s) {
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                upperCase = true;
            }
        }
        return upperCase;

    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains a lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String s) {
        boolean lowerCase = false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                lowerCase = true;
            }
        }
        return lowerCase;
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains a number letter, false otherwise
     */
    private static boolean containsDigits(String s) {
        boolean isDigit = false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                isDigit = true;
            }
        }
        return isDigit;
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
        String password = "";
        do {
            out.println("Enter Password: ");
            password = in.nextLine();
            checkPassword(password, out);
        } while (!password.equals(""));
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
