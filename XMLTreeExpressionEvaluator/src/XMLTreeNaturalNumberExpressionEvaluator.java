import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Tej D.
 *
 */
public final class XMLTreeNaturalNumberExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNaturalNumberExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        NaturalNumber result = new NaturalNumber2(0);

        if (exp.label().equals("number")) {
            int value = Integer.parseInt(exp.attributeValue("value"));
            result = new NaturalNumber2(value);
        } else {

            NaturalNumber left = new NaturalNumber2(evaluate(exp.child(0)));
            NaturalNumber right = new NaturalNumber2(evaluate(exp.child(1)));

            if (exp.label().equals("plus")) {
                left.add(right);
                result = left;
            }
            if (exp.label().equals("minus")) {
                if (left.compareTo(right) < 1) {
                    // I tried to import reporter but it disappears when I try to add it to imports
                    components.utilities.Reporter.fatalErrorToConsole(
                            "Error. Natural Number cannot be less than 0.");
                }
                left.subtract(right);
                result = left;
            }
            if (exp.label().equals("times")) {
                left.multiply(right);
                result = left;
            }
            if (exp.label().equals("divide")) {
                if (right.isZero()) {
                    // I tried to import reporter but it disappears when I try to add it to imports
                    components.utilities.Reporter.fatalErrorToConsole(
                            "Error. Cannot divide by zero.");
                }
                left.divide(right);
                result = left;
            }
        }

        return result.toInt();

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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}