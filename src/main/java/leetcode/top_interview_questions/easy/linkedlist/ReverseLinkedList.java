package leetcode.top_interview_questions.easy.linkedlist;

import leetcode.top_interview_questions.LinkedListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/93/linked-list/560/

Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?

 */
public class ReverseLinkedList {

    public LinkedListNode reverseListRecutsion(final LinkedListNode head) {
        if (head==null){
            return null;
        }
        AtomicReference<LinkedListNode> newHeadR = new AtomicReference<>();
        reverseListRecursionHelper(head,newHeadR);
        return newHeadR.get();
    }

    private LinkedListNode reverseListRecursionHelper(final LinkedListNode n, AtomicReference<LinkedListNode> newHeadR) {
        if(n.next==null){
            newHeadR.set(n);
        } else{
            LinkedListNode x = reverseListRecursionHelper(n.next,newHeadR);
            x.next=n;
            n.next=null; //  to prevent eternal recursion
        }
        return n;
    }


    public LinkedListNode reverseList(final LinkedListNode head) {
        if (head==null){
            return null;
        }
        LinkedListNode prev = head;
        LinkedListNode cur = head.next;
        head.next=null;
        LinkedListNode tmp=head;
        while(cur!=null){
            tmp = cur;
            cur = cur.next;
            tmp.next = prev;
            prev = tmp;
        }
        return tmp;
    }

    @Test
    public void testRecursive(){
        Assertions.assertEquals("1>", reverseListRecutsion(LinkedListNode.genListFromOneToNum(1)).toString());
        Assertions.assertEquals("2>1>", reverseListRecutsion(LinkedListNode.genListFromOneToNum(2)).toString());
        Assertions.assertEquals("3>2>1>", reverseListRecutsion(LinkedListNode.genListFromOneToNum(3)).toString());
        Assertions.assertEquals("4>3>2>1>", reverseListRecutsion(LinkedListNode.genListFromOneToNum(4)).toString());
        Assertions.assertEquals("5>4>3>2>1>", reverseListRecutsion(LinkedListNode.genListFromOneToNum(5)).toString());
    }

    @Test
    public void testIteration(){
        Assertions.assertEquals(null, reverseList(null));
        Assertions.assertEquals("1>", reverseList(LinkedListNode.genListFromOneToNum(1)).toString());
        Assertions.assertEquals("2>1>", reverseList(LinkedListNode.genListFromOneToNum(2)).toString());
        Assertions.assertEquals("3>2>1>", reverseList(LinkedListNode.genListFromOneToNum(3)).toString());
        Assertions.assertEquals("4>3>2>1>", reverseList(LinkedListNode.genListFromOneToNum(4)).toString());
        Assertions.assertEquals("5>4>3>2>1>", reverseList(LinkedListNode.genListFromOneToNum(5)).toString());
    }
}
