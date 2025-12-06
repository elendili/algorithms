package leetcode.medium;

import java.util.ArrayDeque;

public class PrintImmutableLinkedListInReverse_1265 {
    interface ImmutableListNode {
        public void printValue(); // print the value of this node.

        public ImmutableListNode getNext(); // return the next node.
    }

    public void printLinkedListInReverse(ImmutableListNode head) {
        ArrayDeque<ImmutableListNode> stack = new ArrayDeque<>();
        ImmutableListNode n = head;
        while (n != null) {
            stack.addFirst(n);
            n = n.getNext();
        }
        while (!stack.isEmpty()) {
            n = stack.removeFirst();
            n.printValue();
        }
    }
}
