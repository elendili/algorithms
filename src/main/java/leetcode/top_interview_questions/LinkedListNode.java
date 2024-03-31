package leetcode.top_interview_questions;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Objects;

import static leetcode.top_interview_questions.LinkedListNode.genListFromRange;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedListNode {
    public int val;
    public LinkedListNode next;

    public LinkedListNode() {
    }

    public LinkedListNode(int val) {
        this.val = val;
    }

    public LinkedListNode(int val, LinkedListNode next) {
        this.val = val;
        this.next = next;
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        LinkedListNode ln = this;
        LinkedListNode visited = null;
        while (ln != null) {
            if (visited == null) {
                visited = ln;
            } else if (visited == ln) {
                return "self cycle: " + out;
            }
            out.append(ln.val).append(">");
            ln = ln.next;

        }
        return out.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedListNode linkedListNode = (LinkedListNode) o;
        return val == linkedListNode.val &&
                Objects.equals(next, linkedListNode.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }

    public static LinkedListNode genListFromOneToNum(int n) {
        return genListFromRange(1, n);
    }

    public static LinkedListNode genListFromRange(int start, int end) {
        assert start <= end;
        LinkedListNode head = new LinkedListNode(start);
        ;
        if (start == end) {
            return head;
        }
        LinkedListNode cur = head;
        for (int i = start + 1; i <= end; i++) {
            cur.next = new LinkedListNode(i);
            cur = cur.next;
        }
        return head;
    }

    public static LinkedListNode from(int... ints) {
        if (ints.length == 0) {
            return new LinkedListNode();
        }
        LinkedListNode head = new LinkedListNode(ints[0]);
        ;
        LinkedListNode cur = head;
        for (int i = 1; i < ints.length; i++) {
            cur.next = new LinkedListNode(ints[i]);
            cur = cur.next;
        }
        return head;
    }

    public LinkedList<Integer> toValueDeque(){
        LinkedList<Integer> list = new LinkedList<>();
        LinkedListNode node = this;
        while(node!=null){
            list.add(node.val);
            node = node.next;
        }
        return list;
    }


}

class Test4LinkedListNode {
    @Test
    public void test() {
        assertEquals("1>", genListFromRange(1, 1).toString());
        assertEquals("1>2>", genListFromRange(1, 2).toString());
        assertEquals("1>2>3>", genListFromRange(1, 3).toString());
        assertEquals("3>4>5>", genListFromRange(3, 5).toString());
    }
}
