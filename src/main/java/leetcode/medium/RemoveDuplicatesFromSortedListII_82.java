package leetcode.medium;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/?envType=study-plan-v2&envId=top-interview-150
 */
public class RemoveDuplicatesFromSortedListII_82 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode d = new ListNode();
        d.next = head;
        ListNode p = d;

        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }

            if (p.next == cur) {
                p = p.next;
            } else {
                p.next = cur.next;
            }

            cur = cur.next;
        }


        return d.next;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals("1, 2, 5", deleteDuplicates(ListNode.createFromNumbers(asList(1, 2, 3, 3, 4, 4, 5))).toString());
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals("2, 3", deleteDuplicates(ListNode.createFromNumbers(asList(1, 1, 1, 2, 3))).toString());
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        assertEquals("1", deleteDuplicates(ListNode.createFromNumbers(asList(1, 2, 2))).toString());
        assertEquals("2", deleteDuplicates(ListNode.createFromNumbers(asList(1, 1, 2))).toString());
    }

    @org.junit.jupiter.api.Test
    public void test5() {
        assertEquals(null, deleteDuplicates(ListNode.createFromNumbers(asList(2, 2))));
        assertEquals(null, deleteDuplicates(ListNode.createFromNumbers(asList(1, 1, 2, 2))));
        assertEquals(null, deleteDuplicates(ListNode.createFromNumbers(asList(1, 1, 2, 2, 3, 3))));
        assertEquals(null, deleteDuplicates(ListNode.createFromNumbers(asList(1, 1, 2, 2, 3, 3, 4, 4))));
    }
    @org.junit.jupiter.api.Test
    public void test6() {
        assertEquals("2", deleteDuplicates(ListNode.createFromNumbers(asList(1, 1, 2, 3, 3))).toString());
        assertEquals("2", deleteDuplicates(ListNode.createFromNumbers(asList(1, 1, 1, 2, 3, 3, 3))).toString());
    }
}
