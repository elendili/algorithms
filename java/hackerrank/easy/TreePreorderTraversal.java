package hackerrank.easy;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;

import static hackerrank.TestHelper.assertStdOutAfterStdInput;

public class TreePreorderTraversal {

    public static void preOrderRecursive(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrderRecursive(root.left);
            preOrderRecursive(root.right);
        }
    }

    public static void preOrderLoop(Node root) {
        LinkedList<Node> list = new LinkedList<>();
        Node node = root;
        do {
            System.out.printf(node.data + " ");
            if (node.right != null) {
                list.addFirst(node.right);
            }
            if (node.left != null) {
                list.addFirst(node.left);
            }
            node = list.pollFirst();
        } while (node != null);
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static Node getTreeRootFromSystemInput() {
        try (Scanner scan = new Scanner(System.in)) {
            int t = scan.nextInt();
            Node root = null;
            while (t-- > 0) {
                int data = scan.nextInt();
                root = insert(root, data);
            }
            return root;
        }
    }

    @Test
    public void testRecursive() {
        assertStdOutAfterStdInput("6\n1\n2\n5\n3\n6\n4", "1 2 5 3 4 6 ",
                () -> preOrderRecursive(getTreeRootFromSystemInput()));
    }

    @Test
    public void testLoop() {
        assertStdOutAfterStdInput("6\n1\n2\n5\n3\n6\n4", "1 2 5 3 4 6 ",
                () -> preOrderLoop(getTreeRootFromSystemInput()));
    }

}

class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    @Override
    public String toString() {
        return " " + this.data + ": [" +
                Optional.ofNullable(left).map(n -> "" + n.data).orElse("")
                + "," +
                Optional.ofNullable(right).map(n -> "" + n.data).orElse("")
                + "]";
    }
}
