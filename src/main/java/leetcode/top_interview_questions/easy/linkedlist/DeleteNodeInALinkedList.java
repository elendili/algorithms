package leetcode.top_interview_questions.easy.linkedlist;

import leetcode.top_interview_questions.LinkedListNode;
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

    public void deleteNode(LinkedListNode node) {
        //loop and shift values
        LinkedListNode cur = node;
        LinkedListNode prev=cur;
        while(cur.next!=null){
            cur.val = cur.next.val;
            prev=cur;
            cur = cur.next;
        }
        prev.next=null;
    }

    @Test
    public void test() {
        LinkedListNode top= new LinkedListNode(4);
        LinkedListNode toDel = new LinkedListNode(5);
        top.next=toDel;
        LinkedListNode ln1  = new LinkedListNode(1);
        toDel.next=ln1;
        LinkedListNode ln2 = new LinkedListNode(9);
        ln1.next=ln2;
        deleteNode(toDel);
        assertEquals("4>1>9>", top.toString());
    }



}
