package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLinkedLists {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length,
                Comparator.comparingInt(ln -> ln.val));
        for (ListNode ln : lists) {
            if (ln != null) {
                pq.add(ln);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (!pq.isEmpty()) {
            tail.next = pq.poll();
            tail = tail.next;

            if (tail.next != null) {
                pq.add(tail.next);
            }
        }
        return dummy.next;
    }

    ListNode fromList(int... values) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        for (int i = 0; i < values.length; i++) {
            tail.next = new ListNode(values[i]);
            tail = tail.next;
        }
        return dummy.next;
    }

    String toString(ListNode ln) {
        StringBuilder out = new StringBuilder();
        while (ln != null) {
            out.append(ln.val);
            ln = ln.next;
            if (ln != null) {
                out.append("->");
            }
        }
        return out.toString();
    }

    @Test
    public void test() {
        Assertions.assertEquals("1->1->2->2->3->3",
                toString(mergeKLists(new ListNode[]{fromList(1, 2, 3),fromList(2),fromList(1, 3)})));
    }
}
