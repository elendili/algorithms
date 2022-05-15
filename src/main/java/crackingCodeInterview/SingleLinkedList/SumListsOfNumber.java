package crackingCodeInterview.SingleLinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SumListsOfNumber {
    int getForwardNumber(Node root) {
        Node node = root;
        int power = -1;
        while (node != null) {
            power += 1;
            node = node.next;
        }

        node = root;
        int out = 0;
        while (node != null) {
            out += node.data * Math.pow(10, power);
            power -= 1;
            node = node.next;
        }
        return out;
    }


    int getForwardNumbersSum(Node a, Node b) {
        return getForwardNumber(a) + getForwardNumber(b);
    }

    @Test
    public void getForwardNumberTest() {
        Assertions.assertEquals(716,
                getForwardNumber(SingleLinkedList.from(7, 1, 6).root()));
        Assertions.assertEquals(7,
                getForwardNumber(SingleLinkedList.from(7).root()));
    }

    int getReverseNumber(Node node) {
        int out = 0;
        int power = 1;
        while (node != null) {
            out += node.data * power;
            power *= 10;
            node = node.next;
        }
        return out;
    }

    int getReverseNumbersSum(Node a, Node b) {
        return getReverseNumber(a) + getReverseNumber(b);
    }

    @Test
    public void getReverseNumberTest() {
        Node a = SingleLinkedList.from(7, 1, 6).root();
        Assertions.assertEquals(617, getReverseNumber(a));
    }

    @Test
    public void getReverseNumbersSumTest() {
        Node a = SingleLinkedList.from(7, 1, 6).root();
        Node b = SingleLinkedList.from(5, 9, 2).root();
        Assertions.assertEquals((617 + 295),
                getReverseNumbersSum(a, b));
    }
    @Test
    public void getForwardNumbersSumTest() {
        Node a = SingleLinkedList.from(7, 1, 6).root();
        Node b = SingleLinkedList.from(5, 9, 2).root();
        Assertions.assertEquals((716 + 592),
                getForwardNumbersSum(a, b));
    }
}
