package leetcode.easy;

import leetcode.medium.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteNNodesAfterMNodesOfALinkedList_1474 {
    public ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode cur = head;
        while (cur != null) {
            // skip m nodes
            for (int i = 1; i < m && cur != null; i++) {
                cur = cur.next;
            }
            if (cur == null) {
                break;
            }
            // delete n nodes
            ListNode deleteStart = cur.next;
            for (int i = 0; i < n && deleteStart != null; i++) {
                deleteStart = deleteStart.next;
            }
            cur.next = deleteStart;
            cur = deleteStart;
        }
        return head;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        ListNode node;
        node = ListNode.createTillN(13);
        assertEquals("1, 2, 6, 7, 11, 12", deleteNodes(node, 2, 3).toString());
        node = ListNode.createTillN(11);
        assertEquals("1, 5, 9", deleteNodes(node, 1, 3).toString());
        node = ListNode.createTillN(6);
        assertEquals("1, 3, 5", deleteNodes(node, 1, 1).toString());
    }
}
