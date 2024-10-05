package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/reverse-linked-list-ii/editorial/?envType=study-plan-v2&envId=top-interview-150
 */
public class ReverseLinkedListII_92 {
    public ListNode reverseBetween(ListNode head, int l, int r) {
        assert l <= r: "l should be less/equal than r";
        ListNode left = null;
        ListNode right = head;
        while (right != null && l > 1) {
            left = right;
            right = right.next;
            l--;
            r--;
        }
        ListNode reversedPartConnection = left;
        ListNode reversedPartTail = right;

        ListNode nextRight = right.next;
        while (right != null && r >= 1) {
            nextRight = right.next;
            right.next = left;
            left = right;
            right = nextRight;
            l--;
            r--;
        }
        if (reversedPartConnection != null) {
            reversedPartConnection.next = left; // right = 5
        } else {
            head = left;
        }
        reversedPartTail.next = nextRight; // right = 5

        return head;
    }


    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    n | l | r | expected
                    1 | 1 | 1 | 1
                    2 | 1 | 2 | 2, 1
                    2 | 2 | 2 | 1, 2
                    2 | 1 | 1 | 1, 2
                    3 | 1 | 3 | 3, 2, 1
                    3 | 2 | 3 | 1, 3, 2
                    3 | 3 | 3 | 1, 2, 3
                    5 | 2 | 4 | 1, 4, 3, 2, 5
                    5 | 3 | 3 | 1, 2, 3, 4, 5
                    5 | 4 | 5 | 1, 2, 3, 5, 4
                    5 | 1 | 2 | 2, 1, 3, 4, 5
                    5 | 1 | 5 | 5, 4, 3, 2, 1
                    """

    )
    public void test(int n, int l, int r, String expected) {
        ListNode head = ListNode.createTillN(n);
        assertEquals(expected, reverseBetween(head, l, r).toString());
    }

    public static class ListNode {
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
}
