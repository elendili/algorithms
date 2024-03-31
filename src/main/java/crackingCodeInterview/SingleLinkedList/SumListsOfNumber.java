package crackingCodeInterview.LinkedListNode;

import leetcode.top_interview_questions.LinkedListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SumListsOfNumber {
    int getForwardNumber(LinkedListNode root) {
        LinkedListNode LinkedListNode = root;
        int power = -1;
        while (LinkedListNode != null) {
            power += 1;
            LinkedListNode = LinkedListNode.next;
        }

        LinkedListNode = root;
        int out = 0;
        while (LinkedListNode != null) {
            out += LinkedListNode.val * Math.pow(10, power);
            power -= 1;
            LinkedListNode = LinkedListNode.next;
        }
        return out;
    }


    int getForwardNumbersSum(LinkedListNode a, LinkedListNode b) {
        return getForwardNumber(a) + getForwardNumber(b);
    }

    @Test
    public void getForwardNumberTest() {
        Assertions.assertEquals(716,
                getForwardNumber(LinkedListNode.from(7, 1, 6)));
        Assertions.assertEquals(7,
                getForwardNumber(LinkedListNode.from(7)));
    }

    int getReverseNumber(LinkedListNode LinkedListNode) {
        int out = 0;
        int power = 1;
        while (LinkedListNode != null) {
            out += LinkedListNode.val * power;
            power *= 10;
            LinkedListNode = LinkedListNode.next;
        }
        return out;
    }

    int getReverseNumbersSum(LinkedListNode a, LinkedListNode b) {
        return getReverseNumber(a) + getReverseNumber(b);
    }

    @Test
    public void getReverseNumberTest() {
        LinkedListNode a = LinkedListNode.from(7, 1, 6);
        Assertions.assertEquals(617, getReverseNumber(a));
    }

    @Test
    public void getReverseNumbersSumTest() {
        LinkedListNode a = LinkedListNode.from(7, 1, 6);
        LinkedListNode b = LinkedListNode.from(5, 9, 2);
        Assertions.assertEquals((617 + 295),
                getReverseNumbersSum(a, b));
    }
    @Test
    public void getForwardNumbersSumTest() {
        LinkedListNode a = LinkedListNode.from(7, 1, 6);
        LinkedListNode b = LinkedListNode.from(5, 9, 2);
        Assertions.assertEquals((716 + 592),
                getForwardNumbersSum(a, b));
    }
}
