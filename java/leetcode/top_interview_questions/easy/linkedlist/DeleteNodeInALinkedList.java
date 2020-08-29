package leetcode.top_interview_questions.easy.linkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/553/
Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Given linked list -- head = [4,5,1,9], which looks like following:

Example 1:

Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
Example 2:

Input: head = [4,5,1,9], node = 1
Output: [4,5,9]
Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.


Note:

The linked list will have at least two elements.
All of the nodes' values will be unique.
The given node will not be the tail and it will always be a valid node of the linked list.
Do not return anything from your function.
 */
public class DeleteNodeInALinkedList {

    public void deleteNode(ListNode node) {
        //loop and shift values
        ListNode cur = node;
        ListNode prev=cur;
        while(cur.next!=null){
            cur.val = cur.next.val;
            prev=cur;
            cur = cur.next;
        }
        prev.next=null;
    }

    @Test
    public void test() {
        ListNode top= new ListNode(4);
        ListNode toDel = new ListNode(5);
        top.next=toDel;
        ListNode ln1  = new ListNode(1);
        toDel.next=ln1;
        ListNode ln2 = new ListNode(9);
        ln1.next=ln2;
        deleteNode(toDel);
        assertEquals("4>1>9>", top.toString());
    }



}
