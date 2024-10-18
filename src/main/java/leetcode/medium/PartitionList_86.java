package leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartitionList_86 {
    public ListNode partition(ListNode head, int x) {
        ListNode mockHead = new ListNode(Integer.MIN_VALUE);
        mockHead.next = head;
        ListNode c = mockHead;
        ListNode rightPartitionHead = null;
        ListNode leftPartitionTail = null;
        ListNode prev = null;
        while (c != null) {
            if (rightPartitionHead == null && c.val >= x) {
                rightPartitionHead = c;
                leftPartitionTail = prev;
            }
            ListNode tmpNext = c.next;
            ListNode tmpPrev = prev;
            if (rightPartitionHead != null && c.val < x) {
                // shift to before rightPartitionHead
                c.next = rightPartitionHead;
                leftPartitionTail.next = c;
                leftPartitionTail = leftPartitionTail.next;
                tmpPrev.next = tmpNext;
                prev = tmpPrev;
                c = tmpNext;
            } else {
                prev = c;
                c = tmpNext;
            }
            // mockHead -> 3 -> 2 -> 1 -> null
            // mockHead -> 2 -> 3 -> 1 -> null
            // mockHead -> 2 -> 1 -> 3 -> null

        }
        return mockHead.next;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals("1, 2, 2, 4, 3, 5", partition(ListNode.createFromNumbers(1, 4, 3, 2, 5, 2), 3).toString());
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        assertEquals("2, 1, 3", partition(ListNode.createFromNumbers(3, 2, 1), 3).toString());
        assertEquals("1, 3, 2", partition(ListNode.createFromNumbers(3, 2, 1), 2).toString());

        assertEquals("3, 2, 1", partition(ListNode.createFromNumbers(3, 2, 1), 0).toString());
        assertEquals("3, 2, 1", partition(ListNode.createFromNumbers(3, 2, 1), 1).toString());
        assertEquals("3, 2, 1", partition(ListNode.createFromNumbers(3, 2, 1), 4).toString());
    }

    @org.junit.jupiter.api.Test
    public void test6() {
        assertEquals("4, 3, 2, 1", partition(ListNode.createFromNumbers(4, 3, 2, 1), 0).toString());
        assertEquals("4, 3, 2, 1", partition(ListNode.createFromNumbers(4, 3, 2, 1), 1).toString());
        assertEquals("1, 4, 3, 2", partition(ListNode.createFromNumbers(4, 3, 2, 1), 2).toString());
        assertEquals("2, 1, 4, 3", partition(ListNode.createFromNumbers(4, 3, 2, 1), 3).toString());
        assertEquals("3, 2, 1, 4", partition(ListNode.createFromNumbers(4, 3, 2, 1), 4).toString());
        assertEquals("4, 3, 2, 1", partition(ListNode.createFromNumbers(4, 3, 2, 1), 5).toString());
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals("1, 2", partition(ListNode.createFromNumbers(2, 1), 2).toString());
        assertEquals("2, 1", partition(ListNode.createFromNumbers(2, 1), 1).toString());
    }
    @org.junit.jupiter.api.Test
    public void test4() {
        assertEquals("2", partition(ListNode.createFromNumbers(2), 1).toString());
        assertEquals("2", partition(ListNode.createFromNumbers(2), 2).toString());
        assertEquals("2", partition(ListNode.createFromNumbers(2), 3).toString());
    }
    @org.junit.jupiter.api.Test
    public void test5() {
        assertEquals(null, partition(ListNode.createFromNumbers(), 1));
    }
}
