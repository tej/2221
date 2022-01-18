import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Tej
 *
 */
public final class Glossary {

    //queue of sorted terms
    private static Queue<String> wordsSorted = new Queue1L<>();

    //map of words and definitions
    public static Map<String, String> glossary = new Map1L<>();

    //set for seperators
    private static Set<Character> seperators = new Set1L<>();
    private static Set<String> wordSet = new Set1L<>();

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        String ret = "";
        char element = text.charAt(position);
        if (separators.contains(text.charAt(position))) {
            ret += element;
        } else {
            while (!separators.contains(text.charAt(position))
                    && position < text.length()) {
                ret += element;
                position++;
                if (position < text.length()) {
                    element = text.charAt(position);
                }

            }
        }

        return ret;
    }

    //alphabetical order comparator
    private static class alphabeticalOrder implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }

    }

    //Print html headers
    public static void printHeaders(SimpleWriter out) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Glossary</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Glossary</h2>");
        out.println("<hr />");
        out.println("<h3>Index</h3>");
        out.println("<ul>");

    }

    /*
     * Prints the HTML closing tags for the Glossary index.
     */
    public static void printFooters(SimpleWriter out) {
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");

    }

    //create map and set and set of word and def
    public static void createMap(String name, SimpleReader in) {
        SimpleReader file = new SimpleReader1L(name);
        while (!file.atEOS()) {

            String tempWord = file.nextLine();
            String tempDef = file.nextLine();
            boolean multiLine = true;
            while (multiLine) {
                if (!file.atEOS()) {

                    String tempDefLines = file.nextLine();
                    if (tempDefLines.contentEquals("")) {
                        multiLine = false;
                    } else {
                        tempDef += " " + tempDefLines;
                    }
                } else {
                    multiLine = false;
                }
            }
            glossary.add(tempWord, tempDef);
            wordsSorted.enqueue(tempWord);
            wordSet.add(tempWord);

        }
        file.close();
    }

    /*
     * Use map key to create glossary home page output using simplewriter to
     * index.html
     *
     *
     */
    public static void createGlossaryHomePage(Map<String, String> gloss,
            String folderName) {
        SimpleWriter indexPath = new SimpleWriter1L(folderName + "/index.html");

        printHeaders(indexPath);

        int len = wordsSorted.length();
        for (int i = 0; i < len; i++) {
            String word = wordsSorted.front();
            indexPath.print("<li><a href=\"" + word + ".html\">" + word
                    + "</a></li>\n");
            wordsSorted.rotate(1);
            createWordHTML(glossary, folderName, word);
        }
        printFooters(indexPath);
        indexPath.close();
    }

    //html page for each word
    public static void createWordHTML(Map<String, String> glossary,
            String folderName, String word) {
        SimpleWriter termPath = new SimpleWriter1L(
                folderName + "/" + word + ".html");
        termPath.print("<html><head><title>" + word
                + "</title></head><body><h2><b><i><font color=\"red\">" + word
                + "</font></i></b></h2>\r\n" + "<blockquote>");
        //check definition for words that need to be linked
        String def = glossary.value(word);
        int currentPos = 0;
        while (currentPos < def.length() - 1) {
            String nextWord = nextWordOrSeparator(def, currentPos, seperators);
            currentPos += nextWord.length();
            if (wordSet.contains(nextWord)) {
                termPath.print("<a href=\"" + nextWord + ".html\">" + nextWord
                        + "</a>");
            } else {
                termPath.print(nextWord);
            }

        }
        termPath.print("</blockquote>\r\n" + "<hr />");

        termPath.print("<p>Return to <a href=\"index.html\">index</a>.</p>\r\n"
                + "</body>\r\n" + "</html>");
        termPath.close();
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
        Comparator<String> lexo = new alphabeticalOrder();
        seperators.add(',');
        seperators.add('.');
        seperators.add(' ');
        out.print("Enter name of file: ");
        String fileName = in.nextLine();
        out.print("Enter name of folder: ");
        String folderName = in.nextLine();

        createMap(fileName, in);

        wordsSorted.sort(lexo);

        createGlossaryHomePage(glossary, folderName);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
