package leetcode.hard;

import leetcode.top_interview_questions.LinkedListNode;
import org.junit.jupiter.api.Test;

import static leetcode.top_interview_questions.LinkedListNode.genListFromOneToNum;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group/description/
 */
public class ReverseNodesInKGroup {


    public LinkedListNode reverseLinkedList(LinkedListNode head, int k) {

        // Reverse k nodes of the given linked list.
        // This function assumes that the list contains
        // atleast k nodes.
        LinkedListNode new_head = null;
        LinkedListNode ptr = head;

        while (k > 0) {

            // Keep track of the next node to process in the
            // original list
            LinkedListNode next_node = ptr.next;

            // Insert the node pointed to by "ptr"
            // at the beginning of the reversed list
            ptr.next = new_head;
            new_head = ptr;

            // Move on to the next node
            ptr = next_node;

            // Decrement the count of nodes to be reversed by 1
            k--;
        }


        // Return the head of the reversed list
        return new_head;

    }

    public LinkedListNode reverseKGroup(LinkedListNode head, int k) {

        LinkedListNode ptr = head;
        LinkedListNode ktail = null;

        // Head of the final, moified linked list
        LinkedListNode new_head = null;

        // Keep going until there are nodes in the list
        while (ptr != null) {

            int count = 0;

            // Start counting nodes from the head
            ptr = head;

            // Find the head of the next k nodes
            while (count < k && ptr != null) {
                ptr = ptr.next;
                count += 1;
            }

            // If we counted k nodes, reverse them
            if (count == k) {

                // Reverse k nodes and get the new head
                LinkedListNode revHead = this.reverseLinkedList(head, k);

                // new_head is the head of the final linked list
                if (new_head == null)
                    new_head = revHead;

                // ktail is the tail of the previous block of
                // reversed k nodes
                if (ktail != null)
                    ktail.next = revHead;

                ktail = head;
                head = ptr;
            }
        }

        // attach the final, possibly un-reversed portion
        if (ktail != null)
            ktail.next = head;

        return new_head == null ? head : new_head;
    }


    @Test
    public void test_k2_2() {
        assertEquals("2>1>", reverseKGroup(genListFromOneToNum(2), 2).toString());
    }

    @Test
    public void test_k2_3() {
        assertEquals("2>1>3>", reverseKGroup(genListFromOneToNum(3), 2).toString());
    }

    @Test
    public void test_k2_4() {
        assertEquals("2>1>4>3>", reverseKGroup(genListFromOneToNum(4), 2).toString());
    }

    @Test
    public void test_k1() {
        assertEquals("1>2>", reverseKGroup(genListFromOneToNum(2), 1).toString());
    }

    @Test
    public void test3() {
        assertEquals("2>1>4>3>5>", reverseKGroup(genListFromOneToNum(5), 2).toString());
        assertEquals("3>2>1>4>5>", reverseKGroup(genListFromOneToNum(5), 3).toString());
    }
}
