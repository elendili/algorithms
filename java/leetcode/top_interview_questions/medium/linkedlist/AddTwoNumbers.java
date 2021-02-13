package leetcode.top_interview_questions.medium.linkedlist;

import leetcode.top_interview_questions.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/107/linked-list/783/

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1==null){
            return l2;
        } else if(l2==null){
            return l1;
        }
        ListNode out = new ListNode();
        ListNode co = out;
        int carried = 0;
        while(l1!=null || l2!=null || carried>0){
            int n1=0;
            if(l1!=null){
                n1=l1.val;
                l1=l1.next;
            }

            int n2=0;
            if(l2!=null){
                n2=l2.val;
                l2=l2.next;
            }

            int nVal = n1+n2+carried;
            co.val = nVal%10;
            carried = nVal/10;

            if(l1!=null || l2!=null || carried>0){
                co.next = new ListNode();
                co = co.next;
            }


        }
        return out;
    }


    @Test
    public void test(){
        Assertions.assertEquals(ListNode.from(2, 4, 3).toString(),
                addTwoNumbers(ListNode.from(2, 4, 3), ListNode.from()).toString());

        Assertions.assertEquals(ListNode.from(7).toString(),
                addTwoNumbers(ListNode.from(2), ListNode.from(5)).toString());

        Assertions.assertEquals(ListNode.from(0, 1).toString(),
                addTwoNumbers(ListNode.from(5), ListNode.from(5)).toString());

        Assertions.assertEquals(ListNode.from(7, 4, 3).toString(),
                addTwoNumbers(ListNode.from(2, 4, 3), ListNode.from(5)).toString());

        Assertions.assertEquals(ListNode.from(2, 2, 3).toString(),
                addTwoNumbers(ListNode.from(1), ListNode.from(1, 2, 3)).toString());

        Assertions.assertEquals(ListNode.from(7, 0, 8).toString(),
                addTwoNumbers(ListNode.from(2, 4, 3), ListNode.from(5, 6, 4)).toString());

        Assertions.assertEquals(ListNode.from(0, 0, 0, 1).toString(),
                addTwoNumbers(ListNode.from(1), ListNode.from(9, 9, 9)).toString());

        Assertions.assertEquals(ListNode.from(8, 9, 1).toString(),
                addTwoNumbers(ListNode.from(9, 9), ListNode.from(9, 9)).toString());


    }
}
