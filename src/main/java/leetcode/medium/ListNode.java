package leetcode.medium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListNode {
    int val;
    ListNode next;

    static ListNode createTillN(int n) {
        ListNode head = new ListNode(1);
        ListNode cur = head;
        for (int i = 2; i <= n; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        System.out.println(head);
        return head;
    }

    static ListNode createFromNumbers(List<Integer> numbers) {
        ListNode head = new ListNode(numbers.get(0));
        ListNode cur = head;
        for (int i = 1; i < numbers.size(); i++) {
            cur.next = new ListNode(numbers.get(i));
            cur = cur.next;
        }
        return head;
    }

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = this;
        Set<ListNode> visited = new HashSet<>();
        do {
            sb.append(cur.val);
            cur = cur.next;
            if (cur != null) {
                sb.append(", ");
            }
        } while (cur != null && visited.add(cur));
        if (cur != null) {
            sb.append(", !");
        }
        return sb.toString();
    }
}
