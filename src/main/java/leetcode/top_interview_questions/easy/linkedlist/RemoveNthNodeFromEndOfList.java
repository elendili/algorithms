package leetcode.top_interview_questions.easy.linkedlist;

import leetcode.top_interview_questions.LinkedListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/93/linked-list/603/
Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?
 */
public class RemoveNthNodeFromEndOfList {

    public LinkedListNode removeNthFromEnd(LinkedListNode head, int n) {
        if(n<1){
            return head;
        }
        LinkedListNode cur = head, preN=head;
        int counter=0;
        while(cur!=null && counter<=n) {
            cur = cur.next;
            counter++;
        }
        while(cur!=null) {
            preN = preN.next; // jump of n-th node
            cur=cur.next; // jump to tail
            counter++;
        }
        // delete node
        if(counter>n){
            preN.next = preN.next.next;
        } else if(counter==n){
            return preN.next;
        }
        return head;
    }

    @Test
    public void test(){
        LinkedListNode _1 = new LinkedListNode(1);
        Assertions.assertEquals("1>", removeNthFromEnd(_1,0).toString());
        _1.next=new LinkedListNode(2);
        Assertions.assertEquals("1>", removeNthFromEnd(_1,1).toString());
        _1.next=new LinkedListNode(2);
        _1.next.next=new LinkedListNode(3);
        Assertions.assertEquals("1>3>", removeNthFromEnd(_1,2).toString());
    }

    @Test
    public void testLongList(){
        Assertions.assertEquals("1>2>3>4>", removeNthFromEnd(LinkedListNode.genListFromOneToNum(5),1).toString());
        Assertions.assertEquals("1>2>3>5>", removeNthFromEnd(LinkedListNode.genListFromOneToNum(5),2).toString());
        Assertions.assertEquals("1>2>4>5>", removeNthFromEnd(LinkedListNode.genListFromOneToNum(5),3).toString());
        Assertions.assertEquals("1>3>4>5>", removeNthFromEnd(LinkedListNode.genListFromOneToNum(5),4).toString());
        Assertions.assertEquals("2>3>4>5>", removeNthFromEnd(LinkedListNode.genListFromOneToNum(5),5).toString());
        Assertions.assertEquals("1>2>3>4>5>", removeNthFromEnd(LinkedListNode.genListFromOneToNum(5),6).toString());
    }


}
