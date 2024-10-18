package leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RotateList_61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode c = head;
        int length = 1;
        while (c.next != null) {
            c = c.next;
            length++;
        }
        k = (k % length); // when k is more than length
        int stepsToNewHead = length - k;
        ListNode tail = c;
        // loop
        tail.next = head;
        // shift head and tail forward
        for (int i = 0; i < stepsToNewHead; i++) {
            tail = tail.next;
            head = head.next;
        }
        // break circular loop
        tail.next = null;
        return head;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals("2, 0, 1", rotateRight(ListNode.createFromNumbers(0, 1, 2), 4).toString());
        assertEquals("4, 5, 1, 2, 3", rotateRight(ListNode.createTillN(5), 2).toString());
        assertEquals("1, 2, 3", rotateRight(ListNode.createTillN(3), 0).toString());
        assertEquals("3, 1, 2", rotateRight(ListNode.createTillN(3), 1).toString());
        assertEquals("2, 3, 1", rotateRight(ListNode.createTillN(3), 2).toString());
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals("2, 3, 1", rotateRight(ListNode.createTillN(3), 2000000000).toString());
    }
}
