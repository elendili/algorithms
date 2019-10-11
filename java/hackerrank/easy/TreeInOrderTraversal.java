package hackerrank.easy;

import hierarchy.Node;
import org.junit.Test;

import java.util.LinkedList;

import static hackerrank.TestHelper.assertStdOutAfterStdInput;
import static hierarchy.TreeUtils.getTreeRootFromSystemInput;

public class TreeInOrderTraversal {

    public static void recursive(Node root) {
        if (root != null) {
            recursive(root.left);
            System.out.print(root.data + " ");
            recursive(root.right);
        }
    }

    public static void loop(Node root) {
        if (root == null)
            return;
        LinkedList<Node> stack = new LinkedList<>();
        Node c = root;
        while (c != null || stack.size() > 0) {
            while (c != null) {
                stack.push(c);
                c = c.left;
            }
            c = stack.pop();
            System.out.print(c.data + " ");
            c = c.right;
        }
    }

    @Test
    public void testRecursive() {
        assertStdOutAfterStdInput("6\n1\n2\n5\n3\n6\n4", "1 2 3 4 5 6 ",
                () -> recursive(getTreeRootFromSystemInput()));
    }

    @Test
    public void testLoop() {
        assertStdOutAfterStdInput("6\n1\n2\n5\n3\n6\n4", "1 2 3 4 5 6 ",
                () -> loop(getTreeRootFromSystemInput()));
    }

}

