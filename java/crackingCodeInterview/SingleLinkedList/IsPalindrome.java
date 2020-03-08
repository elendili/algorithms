package crackingCodeInterview.SingleLinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class IsPalindrome {
    public boolean isPalindrome(Node node) {
        LinkedList<Integer> list = new SingleLinkedList(node).toValueDeque();
        boolean out = true;
        while (list.size() > 1) {
            out &= (list.removeFirst().equals(list.removeLast()));
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertTrue(isPalindrome(SingleLinkedList.from().root()));
        Assertions.assertTrue(isPalindrome(SingleLinkedList.from(1).root()));
        Assertions.assertTrue(isPalindrome(SingleLinkedList.from(1, 1).root()));
        Assertions.assertTrue(isPalindrome(SingleLinkedList.from(1, 2, 1).root()));
        Assertions.assertTrue(isPalindrome(SingleLinkedList.from(1, 2, 2, 1).root()));
        Assertions.assertFalse(isPalindrome(SingleLinkedList.from(1, 2).root()));
        Assertions.assertFalse(isPalindrome(SingleLinkedList.from(1, 2, 2).root()));
    }
}
