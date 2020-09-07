package leetcode.top_interview_questions.medium.linkedlist;

import leetcode.top_interview_questions.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/107/linked-list/785/

Write a program to find the node at which the intersection of two singly linked lists begins.

For example, the following two linked lists:


begin to intersect at node c1.



Example 1:


Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Reference of the node with value = 8
Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5].
There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.


Example 2:


Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Reference of the node with value = 2
Input Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4].
There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.


Example 3:


Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: null
Input Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5].
Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Each value on each linked list is in the range [1, 10^9].
Your code should preferably run in O(n) time and use only O(1) memory.

 */
public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null || headB==null){
            return null;
        }
        // get length and tail of
        int la = 0;
        ListNode ac = headA;
        while(ac.next!=null){
            ac=ac.next;
            la++;
        }
        int lb = 0;
        ListNode bc = headB;
        while(bc.next!=null){
            bc=bc.next;
            lb++;
        }

        if(ac!=bc){
            // not intersected
            return null;
        }

        // iterate till difference disappear
        int diff = la-lb;
        if(diff>0) {
            for (int i = 0; i < diff; i++) {
                headA = headA.next;
            }
        } else if(diff<0){
            for (int i = diff; i < 0; i++) {
                headB = headB.next;
            }
        }
        // search intersection
        if(headA==headB){
            return headA;
        } else {
            while(headA.next!=headB.next){
                headA = headA.next;
                headB = headB.next;
            }
            return headA.next;
        }
    }

    @Test
    public void test1(){
        ListNode ln1 = ListNode.genListFromNums(4, 1, 8, 4, 5);
        ListNode intersection = ln1.next.next;
        ListNode ln2 = ListNode.genListFromNums(5, 6, 1);
        ln2.next.next.next=intersection;
        Assertions.assertEquals(intersection,getIntersectionNode(ln1,ln2));
        Assertions.assertEquals(intersection,getIntersectionNode(ln2,ln1));
    }

    @Test
    public void test2(){
        ListNode ln1 = ListNode.genListFromNums(3,2,4);
        ListNode intersection = ln1.next;
        ListNode ln2 = ListNode.genListFromNums(1, 9, 1);
        ln2.next.next.next=intersection;
        Assertions.assertEquals(intersection,getIntersectionNode(ln1,ln2));
        Assertions.assertEquals(intersection,getIntersectionNode(ln2,ln1));
    }

    @Test
    public void test3(){
        ListNode ln1 = ListNode.genListFromNums(2,6,4);
        ListNode ln2 = ListNode.genListFromNums(1, 5);
        Assertions.assertEquals(null,getIntersectionNode(ln1,ln2));
        Assertions.assertEquals(null,getIntersectionNode(ln2,ln1));
    }

    @Test
    public void test4(){
        ListNode ln1 = ListNode.genListFromNums(1);
        ListNode ln2 = ListNode.genListFromNums(2);
        ln2.next = ln1;
        Assertions.assertEquals(ln1,getIntersectionNode(ln2,ln1));
        Assertions.assertEquals(ln1,getIntersectionNode(ln1,ln2));
    }

}
