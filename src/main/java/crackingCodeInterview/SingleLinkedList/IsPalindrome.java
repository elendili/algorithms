package crackingCodeInterview.SingleLinkedList;

import leetcode.top_interview_questions.LinkedListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class IsPalindrome {
    public boolean isPalindrome(LinkedListNode node) {
        LinkedList<Integer> list = node.toValueDeque();
        boolean out = true;
        while (list.size() > 1) {
            out &= (list.removeFirst().equals(list.removeLast()));
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertTrue(isPalindrome(LinkedListNode.from()));
        Assertions.assertTrue(isPalindrome(LinkedListNode.from(1)));
        Assertions.assertTrue(isPalindrome(LinkedListNode.from(1, 1)));
        Assertions.assertTrue(isPalindrome(LinkedListNode.from(1, 2, 1)));
        Assertions.assertTrue(isPalindrome(LinkedListNode.from(1, 2, 2, 1)));
        Assertions.assertFalse(isPalindrome(LinkedListNode.from(1, 2)));
        Assertions.assertFalse(isPalindrome(LinkedListNode.from(1, 2, 2)));
    }
}
