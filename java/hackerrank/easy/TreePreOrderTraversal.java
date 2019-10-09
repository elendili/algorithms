package hackerrank.easy;

import hierarchy.Node;
import org.junit.Test;

import java.util.LinkedList;

import static hackerrank.TestHelper.assertStdOutAfterStdInput;
import static hierarchy.TreeUtils.getTreeRootFromSystemInput;

public class TreePreOrderTraversal {

    public static void recursive(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            recursive(root.left);
            recursive(root.right);
        }
    }

    public static void loop(Node root) {
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

    @Test
    public void testRecursive() {
        assertStdOutAfterStdInput("6\n1\n2\n5\n3\n6\n4", "1 2 5 3 4 6 ",
                () -> recursive(getTreeRootFromSystemInput()));
    }

    @Test
    public void testLoop() {
        assertStdOutAfterStdInput("6\n1\n2\n5\n3\n6\n4", "1 2 5 3 4 6 ",
                () -> loop(getTreeRootFromSystemInput()));
    }

}

