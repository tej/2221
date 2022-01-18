import java.util.Comparator;

import components.queue.Queue;
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
public final class ProgramWithIO {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIO() {
    }

    /**
     *  * Removes and returns the minimum value from {@code q} according to the
     *  * ordering provided by the {@code compare} method from {@code order}.  *
     *  * @param q  *            the queue  * @param order  *           
     * ordering by which to compare entries  * @return the minimum value from
     * {@code q}  * @updates q  * @requires <pre>
      * q /= empty_string  and
      *  [the relation computed by order.compare is a total preorder]
      * </pre>  * @ensures <pre>
      * perms(q * <removeMin>, #q)  and
      *  for all x: string of character
      *      where (x is in entries (q))
      *    ([relation computed by order.compare method](removeMin, x))
      * </pre>  
     */
    private static String removeMin(Queue<String> q, Comparator<String> order) {
        Queue<String> tempQueue = q.newInstance();
        int len = q.length();
        String minVal = q.dequeue();
        tempQueue.enqueue(minVal);
        len--;
        while (len > 0) {
            String tempString = q.dequeue();
            tempQueue.enqueue(tempString);
            if (order.compare(tempString, minVal) < 0) {
                minVal = tempString;
            }
            len--;
        }
        return minVal;
    }

    /**
     * Sorts {@code q} according to the ordering provided by the {@code compare}
     * method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to sort
     * @updates q
     * @requires [the relation computed by order.compare is a total preorder]
     * @ensures q = [#q ordered by the relation computed by order.compare]
     */
    public static void sort(Queue<String> q, Comparator<String> order) {
        Queue<String> temp = q.newInstance();
        for (int i = q.length(); i < q.length(); i--) {
            String min = removeMin(q, order);
            temp.enqueue(min);
        }
        q.transferFrom(temp);
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
        in.close();
        out.close();
    }

}
