package leetcode.hard;

import leetcode.top_interview_questions.LinkedListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import static leetcode.top_interview_questions.LinkedListNode.from;

/*
https://leetcode.com/problems/merge-k-sorted-lists
 */
public class MergeKSortedLinkedLists {

    interface MergeKLists {

        LinkedListNode mergeKLists(LinkedListNode[] lists);
    }

    static class WithPriorityQueue implements MergeKLists {

        public LinkedListNode mergeKLists(LinkedListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            PriorityQueue<LinkedListNode> pq = new PriorityQueue<>(lists.length,
                    Comparator.comparingInt(ln -> ln.val));
            for (LinkedListNode ln : lists) {
                if (ln != null) {
                    pq.add(ln);
                }
            }

            LinkedListNode dummy = new LinkedListNode(-1);
            LinkedListNode tail = dummy;
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

        public LinkedListNode mergeKLists(LinkedListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            return divideAndConquer(lists, 0, lists.length - 1);
        }

        // divide by 2 and merge fragments from the pairs
        private LinkedListNode divideAndConquer(LinkedListNode[] lists, int low, int high) {
            if (low > high) {
                return null;
            } else if (low == high) {
                return lists[low];
            }
            int mid = low + (high - low) / 2;
            LinkedListNode leftNode = divideAndConquer(lists, low, mid);
            LinkedListNode rightNode = divideAndConquer(lists, mid + 1, high);
            return merge(leftNode, rightNode);
        }

        private LinkedListNode merge(LinkedListNode leftNode, LinkedListNode rightNode) {
            LinkedListNode head = new LinkedListNode(Integer.MIN_VALUE); // dummy head
            LinkedListNode cur = head;
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
                        new LinkedListNode[]{from(1, 2, 3), from(2), from(1, 3)})
                        .toString());
    }
}
