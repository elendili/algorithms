package leetcode.medium;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertBinarySearchTreeToSortedDoublyLinkedList {
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }

        @Override
        public String toString() {
            return "" + val;
        }
    }

    public Node treeToDoublyList(Node root) {
        if(root==null) {
            return null;
        }
        // use inorder traversal
        Node[] res = traverse(root);
        Node first = res[0];
        Node last = res[1];
        last.right = first;
        first.left = last;
        return first;
    }

    Node[] traverse(final Node node) {
        Node first = node;
        Node last = node;
        if (node.left != null) {
            Node[] traversed = traverse(node.left);
            traversed[1].right = node;
            node.left = traversed[1];
            first = traversed[0];
        }
        if (node.right != null) {
            Node[] traversed = traverse(node.right);
            traversed[0].left = node;
            node.right = traversed[0];
            last = traversed[1];
        }

        Node[] out = new Node[]{first, last};
        return out;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        Node root = new Node(4, new Node(2, new Node(1), new Node(3)), new Node(5));
        assertEquals("[1, 2, 3, 4, 5]", circularLinedListToString(treeToDoublyList(root)));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        Node root = new Node(2, new Node(1), new Node(3));
        assertEquals("[1, 2, 3]", circularLinedListToString(treeToDoublyList(root)));
    }

    @org.junit.jupiter.api.Test
    public void test1() {
        Node root = new Node(1);
        assertEquals("[1]", circularLinedListToString(treeToDoublyList(root)));
    }

    @org.junit.jupiter.api.Test
    public void test12() {
        Node root = new Node(1, null, new Node(2));
        assertEquals("[1, 2]", circularLinedListToString(treeToDoublyList(root)));
    }

    @org.junit.jupiter.api.Test
    public void test01() {
        Node root = new Node(1, new Node(0), null);
        assertEquals("[0, 1]", circularLinedListToString(treeToDoublyList(root)));
    }

    String circularLinedListToString(Node first) {
        List<Integer> list = new ArrayList<>();
        Set<Node> m = Collections.newSetFromMap(new IdentityHashMap<>());
        Node cur = first;
        while (m.add(cur)) {
            list.add(cur.val);
            cur = cur.right;
        }
        return list.toString();
    }

}
