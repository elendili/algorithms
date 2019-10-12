package hackerrank.easy;

import hierarchy.Node;
import org.junit.Test;

import java.util.LinkedList;

import static hierarchy.TreeUtils.getTreeRootFromSpacedString;
import static org.junit.Assert.assertEquals;

public class TreeHeightOfBinaryTree {


    public static int recursive(Node root) {
        if (root == null) {
            return -1;
        }
        int lh = recursive(root.left);
        int rh = recursive(root.right);
        int h = 1 + Math.max(lh, rh);
        return h;
    }

    public static int loop(Node node) {
        if (node == null) {
            return 0;
        }
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.addFirst(node);
        int height = 0;
        while (!nodes.isEmpty()) {
            node = nodes.poll();
            if (nodes.isEmpty()) {
                height++;
            }
            if (node.right != null) {
                nodes.push(node.right);
            }
            if (node.left != null) {
                nodes.push(node.left);
            }
        }
        return height - 1;
    }

    @Test
    public void testRecursive() {
        Node in = getTreeRootFromSpacedString("3 5 2 1 4 6 7");
        assertEquals(3, recursive(in));
        in = getTreeRootFromSpacedString("3 1 7 5 4");
        assertEquals(3, recursive(in));
        in = getTreeRootFromSpacedString("15");
        assertEquals(0, recursive(in));
        in = getTreeRootFromSpacedString("1 3 2 5 4 6 7 9 8 11 13 12 10 15 14");
        assertEquals(9, recursive(in));
    }

    @Test
    public void testLoop() {
        Node in;
        in = getTreeRootFromSpacedString("3 5 2 1 4 6 7");
        assertEquals(3, loop(in));
        in = getTreeRootFromSpacedString("3 1 7 5 4");
        assertEquals(3, loop(in));
        in = getTreeRootFromSpacedString("15");
        assertEquals(0, loop(in));
        in = getTreeRootFromSpacedString("1 3 2 5 4 6 7 9 8 11 13 12 10 15 14");
        assertEquals(9, loop(in));
    }

}

