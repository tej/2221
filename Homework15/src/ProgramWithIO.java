import components.queue.Queue;
import components.queue.Queue1L;
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
     * Reports the smallest integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return the smallest integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * min is in entries(q) and
     * for all x: integer
     *     where (x is in entries(q))
     *   (min <= x)
     * </pre>
     */
    private static int min(Queue<Integer> q) {
        int min = q.dequeue();
        q.enqueue(min);

        for (int i = 0; i < q.length(); i++) {
            int temp = q.dequeue();
            if (temp < min) {
                min = temp;

            }

            q.enqueue(temp);
        }
        return min;

    }

    /*
     * HOMEWORK QUESTIONS FOR 1: i. because the queue needs to have values in it
     * so the Kernel methods can be called ii. if this line was not included the
     * queue could be changed in the function to add values to q that weren't
     * originally there
     */

    /**
     * Reports an array of two {@code int}s with the smallest and the largest
     * integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return an array of two {@code int}s with the smallest and the largest
     *         integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * { minAndMax[0], minAndMax[1] } is subset of entries(q) and
     * for all x: integer
     *     where (x in in entries(q))
     *   (minAndMax[0] <= x <= minAndMax[1])
     * </pre>
     */
    private static int[] minAndMax(Queue<Integer> q) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < q.length(); i++) {
            int temp = q.dequeue();
            if (temp < min) {
                min = temp;

            } else if (temp > max) {
                max = temp;
            }
            q.enqueue(temp);
        }
        int[] arrOfMinMax = { min, max };

        return arrOfMinMax;
    }

    // Noah's Ark minAndMax
    /**
     * Reports an array of two {@code int}s with the smallest and the largest
     * integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return an array of two {@code int}s with the smallest and the largest
     *         integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * { minAndMax[0], minAndMax[1] } is subset of entries(q) and
     * for all x: integer
     *     where (x in in entries(q))
     *   (minAndMax[0] <= x <= minAndMax[1])
     * </pre>
     */
    private static int[] minAndMaxNoah(Queue<Integer> q) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < q.length(); i++) {
            int temp1 = q.dequeue();
            int temp2 = q.dequeue();

            q.enqueue(temp1);
            q.enqueue(temp2);
        }
        int[] arrOfMinMax = { min, max };

        return arrOfMinMax;
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

        Queue<Integer> test = new Queue1L();
        test.enqueue(3);
        test.enqueue(1);
        test.enqueue(4);
        test.enqueue(4);
        test.enqueue(0);

        out.print(min(test));

        int[] result = minAndMax(test);

        out.print("Min: " + result[0] + " Max: " + result[1]);

        in.close();
        out.close();
    }

}
