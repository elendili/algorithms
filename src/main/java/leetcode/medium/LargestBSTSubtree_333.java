package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/largest-bst-subtree/?envType=study-plan-v2&envId=premium-algo-100
 */
public class LargestBSTSubtree_333 {
    record SubtreeInfo(int count, int min, int max) {
    }

    public int largestBSTSubtree(TreeNode root) {
        return postOrder(root).count;
    }

    SubtreeInfo postOrder(TreeNode root) {
        if (root == null) {
            // leaf is a subtree that is BST and min=Integer.MAX_VALUE, max=Integer.MIN_VALUE
            return new SubtreeInfo(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        SubtreeInfo left = postOrder(root.left);
        SubtreeInfo right = postOrder(root.right);
        if (left.max < root.val && root.val < right.min) {
            // it's a BST
            return new SubtreeInfo(left.count + right.count + 1,
                    Math.min(left.min, root.val),
                    Math.max(right.max, root.val)
            );
        }

        // Otherwise, return [-inf, inf] so that parent can't be valid BST
        int newCount = Math.max(left.count, right.count); // propagate max BST count to parent
        return new SubtreeInfo(newCount, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    @org.junit.jupiter.api.Test
    public void test000() {
        assertEquals(0, largestBSTSubtree(null));
    }

    @org.junit.jupiter.api.Test
    public void test00() {
        TreeNode n10 = new TreeNode(10);
        assertEquals(1, largestBSTSubtree(n10));
    }

    @org.junit.jupiter.api.Test
    public void test0() {
        TreeNode n10 = new TreeNode(10);
        TreeNode n5 = new TreeNode(5);
        TreeNode n15 = new TreeNode(15);

        n10.left = n5;
        n10.right = n15;
        assertEquals(3, largestBSTSubtree(n10));
    }

    @org.junit.jupiter.api.Test
    public void test01() {
        TreeNode n10 = new TreeNode(10);
        TreeNode n15 = new TreeNode(15);
        TreeNode n7 = new TreeNode(7);

        n10.right = n15;
        n15.right = n7;
        assertEquals(1, largestBSTSubtree(n10));
    }

    @org.junit.jupiter.api.Test
    public void test1() {
        TreeNode n10 = new TreeNode(10);
        TreeNode n5 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        TreeNode n8 = new TreeNode(8);
        TreeNode n15 = new TreeNode(15);
        TreeNode n7 = new TreeNode(7);

        n10.left = n5;
        n10.right = n15;
        n5.left = n1;
        n5.right = n8;
        n15.right = n7;
        assertEquals(3, largestBSTSubtree(n10));
    }
}
