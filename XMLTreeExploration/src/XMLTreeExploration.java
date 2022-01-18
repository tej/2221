import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree2;

/**
 * Put a short phrase describing the program here.
 *
 * @author Tej
 *
 */
public final class XMLTreeExploration {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */

    /**
     * Output information about the middle child of the given {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose middle child is to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires <pre>
     * [the label of the root of xt is a tag]  and
     * [xt has at least one child]  and  out.is_open
     * </pre>
     * @ensures <pre>
     * out.content = #out.content * [the label of the middle child
     *  of xt, whether the root of the middle child is a tag or text,
     *  and if it is a tag, the number of children of the middle child]
     * </pre>
     */
    private static void printMiddleNode(XMLTree xt, SimpleWriter out) {
        int middleChild = xt.numberOfChildren() / 2;
        out.println("Middle Child label: " + xt.child(middleChild).label());
        out.println(xt.child(middleChild).isTag());
        out.println(xt.child(middleChild).numberOfChildren());

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
        XMLTree xml = new XMLTree2(
                "http://web.cse.ohio-state.edu/software/2221/web-sw1/"
                        + "extras/instructions/xmltree-model/columbus-weather.xml");

        // out.println(xml.toString());
        xml.display();
        if (xml.isTag()) {
            out.println("its a tag");
        } else {
            out.println("it's text");
        }
        out.println(xml.label());

        XMLTree results = xml.child(0);
        XMLTree channel = results.child(0);
        out.println("channel has " + channel.numberOfChildren() + " Children");

        XMLTree title = channel.child(1);

        XMLTree titleText = title.child(0);

        out.println(titleText.label());

        out.println(xml.child(0).child(0).child(1).child(0).label());

        XMLTree astronomy = channel.child(10);
        if (astronomy.hasAttribute("sunset")
                & astronomy.hasAttribute("midday")) {
            out.print("it has the attributes sunset and midday");
            astronomy.attributeValue("sunset");
            astronomy.attributeValue("midday");
        } else {
            out.println("it aint got it");
        }

        printMiddleNode(channel, out);

        in.close();
        out.close();
    }

}
