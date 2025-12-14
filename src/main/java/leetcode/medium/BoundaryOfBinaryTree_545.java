package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/boundary-of-binary-tree/?envType=study-plan-v2&envId=premium-algo-100
 */
public class BoundaryOfBinaryTree_545 {
    List<Integer> boundaries;
    boolean debug = true;

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        boundaries = new ArrayList<>();
        if (root == null) {
            return boundaries;
        }
        boundaries.add(root.val);
        if (debug) System.err.println("with root: " + boundaries);
        if (root.left != null) {
            TreeNode n = root.left;
            while (n != null && !isLeaf(n)) {
                boundaries.add(n.val);
                n = n.left != null ? n.left : n.right;
            }
        }

        if (debug) System.err.println("with left boundry: " + boundaries);
        if (!isLeaf(root)) {
            gatherLeafs(root);
        }
        if (debug) System.err.println("with leafs: " + boundaries);

        Deque<Integer> stack = new ArrayDeque<>();
        if (root.right != null) {
            TreeNode n = root.right;
            while (n != null && !isLeaf(n)) {
                stack.push(n.val);
                n = n.right != null ? n.right : n.left;
            }
        }
        while (!stack.isEmpty()) {
            boundaries.add(stack.pop());
        }

        if (debug) System.err.println("with right boundry: " + boundaries);
        return boundaries;
    }

    boolean isLeaf(TreeNode n) {
        return n.left == null && n.right == null;
    }

    private void gatherLeafs(TreeNode n) {
        if (n == null) {
            return;
        }
        if (isLeaf(n)) {
            boundaries.add(n.val);
        } else {
            gatherLeafs(n.left);
            gatherLeafs(n.right);
        }
    }

    @Test
    public void test0() {
        TreeNode n1 = new TreeNode(1);

        assertEquals("[1]",
                boundaryOfBinaryTree(n1).toString());
    }

    @Test
    public void test1() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);

        n1.right = n2;
        n2.left = n3;
        n2.right = n4;

        assertEquals("[1, 3, 4, 2]",
                boundaryOfBinaryTree(n1).toString());
    }

    @Test
    public void test10() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);

        n1.left = n2;
        n1.right = n3;

        assertEquals("[1, 2, 3]",
                boundaryOfBinaryTree(n1).toString());
    }

    @Test
    public void test11() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        /*
              1
           2     3
            4   5
         */
        n1.left = n2;
        n1.right = n3;
        n2.right = n4;
        n3.left = n5;

        assertEquals("[1, 2, 4, 5, 3]",
                boundaryOfBinaryTree(n1).toString());
    }

    @Test
    public void test2() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        TreeNode n10 = new TreeNode(10);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n5.left = n7;
        n5.right = n8;
        n3.left = n6;
        n6.left = n9;
        n6.right = n10;

        assertEquals("[1, 2, 4, 7, 8, 9, 10, 6, 3]",
                boundaryOfBinaryTree(n1).toString());
    }
}
