package leetcode.medium;

import leetcode.top_interview_questions.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/reorder-list/
 */
public class ReorderList {
    /*
    L0 → L1 → … → Ln - 1 → Ln
    Reorder the list to be on the following form:

    L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
     */
    public void reorderList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode n = head;
        while (n != null) {
            list.add(n);
            n = n.next;
        }

        int count = list.size();
        int l = 1, r = count - 1;
        ListNode end = head;
        while (l <= r) {
            end.next = list.get(r--);
            end = end.next;
            if (l <= r) {
                end.next = list.get(l++);
                end = end.next;
            }
        }
        end.next = null;
    }

    @Test
    public void test0() {
        ListNode ln = new ListNode(4);
        reorderList(ln);
        Assertions.assertEquals("[4]", listNodeToString(ln));
    }

    @Test
    public void test1() {
        ListNode ln = new ListNode(4);
        ln = new ListNode(3, ln);
        reorderList(ln);
        Assertions.assertEquals("[3, 4]", listNodeToString(ln));
    }

    @Test
    public void test() {
        ListNode ln = new ListNode(4);
        ln = new ListNode(3, ln);
        ln = new ListNode(2, ln);
        ln = new ListNode(1, ln);
        reorderList(ln);
        Assertions.assertEquals("[1, 4, 2, 3]", listNodeToString(ln));
    }

    @Test
    public void test2() {
        ListNode ln;
        ln = new ListNode(5);
        ln = new ListNode(4, ln);
        ln = new ListNode(3, ln);
        ln = new ListNode(2, ln);
        ln = new ListNode(1, ln);
        reorderList(ln);
        Assertions.assertEquals("[1, 5, 2, 4, 3]", listNodeToString(ln));
    }


    String listNodeToString(ListNode ln) {
        List<Integer> list = new ArrayList<>();
        ListNode n = ln;
        while (n != null) {
            list.add(n.val);
            n = n.next;
        }
        return list.toString();
    }


}




