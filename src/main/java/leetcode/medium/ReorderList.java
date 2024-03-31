package leetcode.medium;

import leetcode.top_interview_questions.LinkedListNode;
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
    public void reorderList(LinkedListNode head) {
        List<LinkedListNode> list = new ArrayList<>();
        LinkedListNode n = head;
        while (n != null) {
            list.add(n);
            n = n.next;
        }

        int count = list.size();
        int l = 1, r = count - 1;
        LinkedListNode end = head;
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
        LinkedListNode ln = new LinkedListNode(4);
        reorderList(ln);
        Assertions.assertEquals("[4]", listNodeToString(ln));
    }

    @Test
    public void test1() {
        LinkedListNode ln = new LinkedListNode(4);
        ln = new LinkedListNode(3, ln);
        reorderList(ln);
        Assertions.assertEquals("[3, 4]", listNodeToString(ln));
    }

    @Test
    public void test() {
        LinkedListNode ln = new LinkedListNode(4);
        ln = new LinkedListNode(3, ln);
        ln = new LinkedListNode(2, ln);
        ln = new LinkedListNode(1, ln);
        reorderList(ln);
        Assertions.assertEquals("[1, 4, 2, 3]", listNodeToString(ln));
    }

    @Test
    public void test2() {
        LinkedListNode ln;
        ln = new LinkedListNode(5);
        ln = new LinkedListNode(4, ln);
        ln = new LinkedListNode(3, ln);
        ln = new LinkedListNode(2, ln);
        ln = new LinkedListNode(1, ln);
        reorderList(ln);
        Assertions.assertEquals("[1, 5, 2, 4, 3]", listNodeToString(ln));
    }


    String listNodeToString(LinkedListNode ln) {
        List<Integer> list = new ArrayList<>();
        LinkedListNode n = ln;
        while (n != null) {
            list.add(n.val);
            n = n.next;
        }
        return list.toString();
    }


}




