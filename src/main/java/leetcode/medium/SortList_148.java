package leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/sort-list
 */
public class SortList_148 {
    // recursive merge sort
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = getMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        ListNode out = merge(left, right);
        return out;
    }

    ListNode getMid(ListNode head) {
        if(head==null){
            return head;
        }
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        while (fastPointer.next!=null && fastPointer.next.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        ListNode out = slowPointer.next;
        slowPointer.next = null;
        return out;
    }

    private final ListNode dummyHead = new ListNode();

    ListNode merge(ListNode left, ListNode right) {
        ListNode cur = dummyHead;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if (left != null) {
            cur.next = left;
        }
        if (right != null) {
            cur.next = right;
        }
        ListNode head = dummyHead.next;
        dummyHead.next = null;
        return head;
    }

    @org.junit.jupiter.api.Test
    public void test0() {
        assertEquals(null, sortList(null));
    }

    @org.junit.jupiter.api.Test
    public void test4() {
        assertEquals("1, 2, 3, 4",
                sortList(ListNode.createFromNumbers(4, 2, 1, 3)).toString());
    }

    @org.junit.jupiter.api.Test
    public void test1() {
        assertEquals("1", sortList(ListNode.createFromNumbers(1)).toString());
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals("1, 2", sortList(ListNode.createFromNumbers(2, 1)).toString());
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        assertEquals("1, 2, 3", sortList(ListNode.createFromNumbers(3, 2, 1)).toString());
    }

    @org.junit.jupiter.api.Test
    public void test5() {
        assertEquals("1, 2, 3, 4, 5",
                sortList(ListNode.createFromNumbers(5,1,2,4,3)).toString());
    }
    @org.junit.jupiter.api.Test
    public void test6() {
        assertEquals("1, 2, 3, 4, 5, 6",
                sortList(ListNode.createFromNumbers(6,5,1,2,4,3)).toString());
    }
}
