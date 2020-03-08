package crackingCodeInterview.SingleLinkedList;

import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertTrue(isPalindrome(SingleLinkedList.from().root()));
        Assert.assertTrue(isPalindrome(SingleLinkedList.from(1).root()));
        Assert.assertTrue(isPalindrome(SingleLinkedList.from(1, 1).root()));
        Assert.assertTrue(isPalindrome(SingleLinkedList.from(1, 2, 1).root()));
        Assert.assertTrue(isPalindrome(SingleLinkedList.from(1, 2, 2, 1).root()));
        Assert.assertFalse(isPalindrome(SingleLinkedList.from(1, 2).root()));
        Assert.assertFalse(isPalindrome(SingleLinkedList.from(1, 2, 2).root()));
    }
}
