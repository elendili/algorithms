package leetcode.hard;

import leetcode.top_interview_questions.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import static leetcode.top_interview_questions.ListNode.genListFromNums;

/*
https://leetcode.com/problems/merge-k-sorted-lists
 */
public class MergeKSortedLinkedLists {

    interface MergeKLists {

        ListNode mergeKLists(ListNode[] lists);
    }

    static class WithPriorityQueue implements MergeKLists {

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
    }

    static class DivideAndConquer implements MergeKLists {

        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            return divideAndConquer(lists, 0, lists.length - 1);
        }

        // divide by 2 and merge fragments from the pairs
        private ListNode divideAndConquer(ListNode[] lists, int low, int high) {
            if (low > high) {
                return null;
            } else if (low == high) {
                return lists[low];
            }
            int mid = low + (high - low) / 2;
            ListNode leftNode = divideAndConquer(lists, low, mid);
            ListNode rightNode = divideAndConquer(lists, mid + 1, high);
            return merge(leftNode, rightNode);
        }

        private ListNode merge(ListNode leftNode, ListNode rightNode) {
            ListNode head = new ListNode(Integer.MIN_VALUE); // dummy head
            ListNode cur = head;
            while (leftNode != null && rightNode != null) { // merge one by one till one is fully traversed
                if (leftNode.val < rightNode.val) {
                    cur.next = leftNode;
                    leftNode = leftNode.next;
                } else {
                    cur.next = rightNode;
                    rightNode = rightNode.next;
                }
                cur = cur.next;
            }
            cur.next = (leftNode != null) ? leftNode : rightNode; // assign what is left
            return head.next;
        }
    }

    public static Stream<Arguments> predicateStream() {
        return Stream.of(
                Arguments.arguments("WithPriorityQueue", new WithPriorityQueue()),
                Arguments.arguments("DivideAndConquer", new DivideAndConquer())
        );
    }

    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("predicateStream")
    public void assymetric(String name, MergeKLists methodUnderTest) {
        Assertions.assertEquals("1>1>2>2>3>3>",
                methodUnderTest.mergeKLists(
                        new ListNode[]{genListFromNums(1, 2, 3), genListFromNums(2), genListFromNums(1, 3)})
                        .toString());
    }
}
