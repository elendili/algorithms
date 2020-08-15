package leetcode.top_interview_questions.easy.linkedlist;

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
 class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
 }
    public boolean hasCycle(ListNode head) {
        // loop with 2 pointers
        ListNode jump1=head;
        ListNode jump2=head;
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
        ListNode head= new ListNode(4);
        ListNode ln1  = new ListNode(1);
        head.next=ln1;
        ListNode ln2 = new ListNode(9);
        ln1.next=ln2;
        assertFalse(hasCycle(head));

        ln2.next=head;
        assertTrue(hasCycle(head));
    }

}
