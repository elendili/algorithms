package leetcode.top_interview_questions.easy.linkedlist;

import leetcode.top_interview_questions.LinkedListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/773/
Given a linked list, determine if it has a cycle in it.
To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed)
in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the second node.

Example 2:

Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the first node.

Example 3:

Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.

Follow up:

Can you solve it using O(1) (i.e. constant) memory?

 */
public class LinkedListCycle {

    public boolean hasCycle(LinkedListNode head) {
        // loop with 2 pointers
        LinkedListNode jump1=head;
        LinkedListNode jump2=head;
        while(jump1!=null && jump2!=null){
            jump1=jump1.next;
            jump2=jump2.next;
            if(jump2!=null){
                jump2=jump2.next;
            }
            if(jump2!=null && jump1==jump2){
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        LinkedListNode head= new LinkedListNode(4);
        LinkedListNode ln1  = new LinkedListNode(1);
        head.next=ln1;
        LinkedListNode ln2 = new LinkedListNode(9);
        ln1.next=ln2;
        assertFalse(hasCycle(head));

        ln2.next=head;
        assertTrue(hasCycle(head));
    }

}
