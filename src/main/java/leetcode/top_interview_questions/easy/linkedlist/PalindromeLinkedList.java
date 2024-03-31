package leetcode.top_interview_questions.easy.linkedlist;

import leetcode.top_interview_questions.LinkedListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/93/linked-list/772/

Given a singly linked list, determine if it is a palindrome.

Example 1:
Input: 1->2
Output: false

Example 2:
Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?

 */
public class PalindromeLinkedList {
    public boolean isPalindrome(LinkedListNode head) {
        if(head==null || head.next==null){
            return true;
        }
        List<Integer> list = new ArrayList<>();
        LinkedListNode cur = head;
        while(cur!=null){
            list.add(cur.val);
            cur = cur.next;
        }
        int n = list.size();
        for(int i=0;i<n/2;i++){
            if(!list.get(i).equals(list.get(n-i-1))){
                return false;
            }
        }
        return true;
    }

    @Test
    public void test(){
        Assertions.assertEquals("1>2>2>1>", LinkedListNode.from(1, 2, 2, 1).toString());
        Assertions.assertEquals(false, isPalindrome(LinkedListNode.from(1, 2, 1, 2)));
        Assertions.assertEquals(false, isPalindrome(LinkedListNode.genListFromRange(1, 2)));

        Assertions.assertEquals(true, isPalindrome(LinkedListNode.genListFromRange(1, 1)));
        Assertions.assertEquals(true, isPalindrome(LinkedListNode.from(1, 2, 1)));
        Assertions.assertEquals(true, isPalindrome(LinkedListNode.from(2, 2, 2, 2)));
        Assertions.assertEquals(true, isPalindrome(LinkedListNode.from(1, 2, 2, 1)));
    }
}
