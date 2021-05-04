package Algorithms_R_Sedgewick;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/*
1.3.37 Josephus problem. In the Josephus problem from antiquity,
N people are in dire straits and agree to the following strategy
to reduce the population. They arrange themselves in a circle
(at positions numbered from 0 to N–1) and proceed around the circle,
eliminating every Mth person until only one person is left.
Legend has it that Josephus figured out where to sit to avoid being eliminated.
Write a Queue client Josephus that takes M and N from the command line and prints
out the order in which people are eliminated (and thus would show Josephus
where to sit in the circle).

% java Josephus 2 7
1 3 5 0 4 2 6
 */
public class _1_3_37_JosephusProblem {

    public List<Integer> indexesToExecute(int jump, int peopleCount) {
        if (peopleCount < 2) {
            return asList(0);
        }
        jump = jump % peopleCount; // make jump smaller
        class Node {
            int index;
            Node next;
        }
        Node last, first = new Node();
        { // create linked circle
            first.index = 0;
            Node prev = first;
            for (int i = 1; i < peopleCount; i++) {
                Node n = new Node();
                n.index = i;
                prev.next = n;
                prev = n;
            }
            last = prev;
            last.next = first;
        }
        List<Integer> out = new ArrayList<>(peopleCount);
        {// iterate over circle and remove element
            Node prev = last, cur = last;
            for (; peopleCount > 0; peopleCount--) {
                // jump
                for (int i = 0; i < jump; i++) {
                    prev = cur;
                    cur = cur.next;
                }
                // remove cur
                out.add(cur.index);
                prev.next = cur.next;
            }
        }
        return out;
    }

    @Test
    public void testSimple() {
        Assertions.assertEquals(asList(0, 1, 2),
                indexesToExecute(1, 3));
        Assertions.assertEquals(asList(1, 0, 2),
                indexesToExecute(2, 3));
        Assertions.assertEquals(asList(1, 3, 2, 0),
                indexesToExecute(2, 4));
    }

    @Test
    public void test() {
        Assertions.assertEquals(asList(1, 3, 5, 0, 4, 2, 6),
                indexesToExecute(2, 7));
    }
}
