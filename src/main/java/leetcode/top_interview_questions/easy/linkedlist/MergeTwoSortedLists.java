package leetcode.top_interview_questions.easy.linkedlist;

import leetcode.top_interview_questions.LinkedListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/93/linked-list/771/

Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4

 */
public class MergeTwoSortedLists {


    public LinkedListNode mergeTwoLists(LinkedListNode l1, LinkedListNode l2) {
        LinkedListNode newHead = new LinkedListNode(-1);
        LinkedListNode c0 = newHead;
        LinkedListNode c1 = l1;
        LinkedListNode c2 = l2;
        while(c1!=null && c2!=null){
            if (c1.val<=c2.val){
                c0.next=c1;
                c0 = c0.next;
                c1=c1.next;
            } else {
                c0.next=c2;
                c0 = c0.next;
                c2=c2.next;
            }
        }
        while(c1!=null){
                c0.next=c1;
                c0 = c0.next;
                c1=c1.next;
        }
        while(c2!=null){
                c0.next=c2;
                c0 = c0.next;
                c2=c2.next;
        }
        return newHead.next;
    }

    @Test
    public void test(){
        Assertions.assertEquals("1>1>",mergeTwoLists(LinkedListNode.genListFromOneToNum(1), LinkedListNode.genListFromOneToNum(1)).toString());
        Assertions.assertEquals("3>3>4>4>5>",mergeTwoLists(LinkedListNode.genListFromRange(3,4), LinkedListNode.genListFromRange(3,5)).toString());
        Assertions.assertEquals("1>1>2>2>3>",mergeTwoLists(LinkedListNode.genListFromOneToNum(3), LinkedListNode.genListFromOneToNum(2)).toString());
    }

}
