package leetcode.top_interview_questions.medium.linkedlist;

import leetcode.top_interview_questions.LinkedListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/107/linked-list/784/
Given a singly linked list, group all odd nodes together followed by the even nodes.
Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example 1:

Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL
Example 2:

Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL


Constraints:

The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...
The length of the linked list is between [0, 10^4].
 */
public class OddEvenLinkedList {
    public LinkedListNode oddEvenList(LinkedListNode head) {
        if(head==null || head.next==null || head.next.next == null) {
            return head;
        }
        LinkedListNode oddPrev = head;
        LinkedListNode headEven = head.next;
        LinkedListNode evenPrev = headEven;

        int counter = 0;
        LinkedListNode c = head;
        while (c!=null){
            LinkedListNode next = c.next;
            if(++counter % 2 == 1){
                // for odd
                oddPrev.next = c;
                oddPrev = c;
            } else {
                // for even
                evenPrev.next = c;
                evenPrev = c;
            }
            c.next=null;
            c=next;
        }
        oddPrev.next=headEven;
        return head;
    }

    @Test
    public void test(){
        Assertions.assertEquals(LinkedListNode.from(1, 3, 5, 2, 4).toString(), oddEvenList(LinkedListNode.genListFromRange(1, 5)).toString());
        Assertions.assertEquals(LinkedListNode.from(2, 3, 6, 7, 1, 5, 4).toString(), oddEvenList(LinkedListNode.from(2, 1, 3, 5, 6, 4, 7)).toString());

        Assertions.assertNull(oddEvenList(null));
        Assertions.assertEquals(LinkedListNode.from(1).toString(), oddEvenList(LinkedListNode.from(1)).toString());
        Assertions.assertEquals(LinkedListNode.from(1, 2).toString(), oddEvenList(LinkedListNode.from(1, 2)).toString());
        Assertions.assertEquals(LinkedListNode.from(1, 3, 2).toString(), oddEvenList(LinkedListNode.from(1, 2, 3)).toString());
    }

}
