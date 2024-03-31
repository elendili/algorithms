package leetcode.medium;

import leetcode.top_interview_questions.LinkedListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*

https://leetcode.com/problems/add-two-numbers-ii/

You are given two non-empty linked lists representing two non-negative integers.
The most significant digit comes first and each of their nodes contain a single digit.
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbersII {

    // without list node reversion
    public LinkedListNode addTwoNumbers(LinkedListNode l1, LinkedListNode l2) {
        // get length
        int[] a1 = toArray(l1);
        int[] a2 = toArray(l2);
        if (a1.length < a2.length) {
            int[] tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        // sum
        for (int i = 0, carried = 0; i < a1.length; i++) {
            a1[i] += carried;
            if (i < a2.length) {
                a1[i] += a2[i];
            }
            if (a1[i] > 9) {
                a1[i] %= 10;
                carried = 1;
            } else {
                carried = 0;
            }
        }
        return toListNode(a1);
    }

    int length(LinkedListNode n) {
        int out = 0;
        while (n != null) {
            out++;
            n = n.next;
        }
        return out;
    }

    int[] toArray(LinkedListNode n) {
        int l = length(n);
        int[] out = new int[l + 1];
        int i = out.length - 2;
        while (n != null) {
            out[i--] = n.val;
            n = n.next;
        }
        return out;
    }

    LinkedListNode toListNode(int[] a) {
        int i = a.length;
        while (a[--i] == 0 && i>0) ;
        LinkedListNode out = new LinkedListNode(), c = out;
        for (; i > -1; i--) {
            c.val = a[i];
            if (i > 0) {
                c.next = new LinkedListNode();
            }
            c = c.next;
        }
        return out;
    }

    @Test
    public void test1() {
        LinkedListNode a = LinkedListNode.from(9);
        LinkedListNode b = LinkedListNode.from(9);
        LinkedListNode expected = LinkedListNode.from(1, 8);
        Assertions.assertEquals(expected, addTwoNumbers(a, b));
    }

    @Test
    public void test2() {
        LinkedListNode a = LinkedListNode.from(9, 9);
        LinkedListNode b = LinkedListNode.from(9);
        LinkedListNode expected = LinkedListNode.from(1, 0, 8);
        Assertions.assertEquals(expected, addTwoNumbers(a, b));
    }

    @Test
    public void test0() {
        LinkedListNode a = LinkedListNode.from(0);
        LinkedListNode b = LinkedListNode.from(0);
        LinkedListNode expected = LinkedListNode.from(0);
        Assertions.assertEquals(expected, addTwoNumbers(a, b));
    }

    @Test
    public void test() {
        LinkedListNode a = LinkedListNode.from(7, 2, 4, 3);
        LinkedListNode b = LinkedListNode.from(5, 6, 4);
        LinkedListNode expected = LinkedListNode.from(7, 8, 0, 7);
        Assertions.assertEquals(expected, addTwoNumbers(a, b));
    }
}
