package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
 */
public class BinaryTreeMaximumPathSum {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public int maxPathSum(TreeNode root) {
        int[] sumOfBothBranches = new int[]{root.val};
        int leftOrRight = maxPathSum(root, sumOfBothBranches);
        return Math.max(leftOrRight, sumOfBothBranches[0]);
    }

    /**
     * returns max of sums of its left and right branch in order to proceed path
     * sumOfBothBranches - preserves sum of both left and right
     * for the case, when local sum is bigger than sums of paths without left or right branch
     *
     * @return Max of [v, v+left, v+right]
     *         sumOfBothBranches sum has max of [v, left, right, v+left, v+right, v+left+right]
     */
    public int maxPathSum(TreeNode root, int[] sumOfBothBranches) {
        int v = root.val;
        sumOfBothBranches[0] = Math.max(sumOfBothBranches[0], v);

        int leftSum = 0;
        int rightSum = 0;
        boolean leftSet = false;
        if (root.left != null) {
            leftSet = true;
            leftSum = maxPathSum(root.left, sumOfBothBranches);
            sumOfBothBranches[0] = Math.max(sumOfBothBranches[0], leftSum);
            sumOfBothBranches[0] = Math.max(sumOfBothBranches[0], v + leftSum);
        }
        if (root.right != null) {
            rightSum = maxPathSum(root.right, sumOfBothBranches);
            sumOfBothBranches[0] = Math.max(sumOfBothBranches[0], rightSum);
            sumOfBothBranches[0] = Math.max(sumOfBothBranches[0], v + rightSum);
            if (leftSet) {
                sumOfBothBranches[0] = Math.max(sumOfBothBranches[0], leftSum + rightSum + v);
            }
        }
        return Math.max(v, Math.max(leftSum, rightSum) + v);
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        Assertions.assertEquals(6, maxPathSum(root));
    }

    @Test
    public void test2() {
        TreeNode right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        TreeNode root = new TreeNode(-10, new TreeNode(9), right);
        Assertions.assertEquals(42, maxPathSum(root));
    }

    @Test
    public void test3() {
        TreeNode root = new TreeNode(1);
        Assertions.assertEquals(1, maxPathSum(root));
    }

    @Test
    public void test4() {
        // [2,-1,-2]
        TreeNode root = new TreeNode(2, new TreeNode(-1), new TreeNode(-2));
        Assertions.assertEquals(2, maxPathSum(root));
    }

    @Test
    public void test5() {
        TreeNode root = new TreeNode(-3);
        Assertions.assertEquals(-3, maxPathSum(root));
    }

    @Test
    public void test6() {
        // [9,6,-3,null,null,-6,2,null,null,2,null,-6,-6,-6]
//        TreeNode left = new TreeNode(6);
//        TreeNode right = new TreeNode(-3, new TreeNode(6), new TreeNode(2));
//        TreeNode root = new TreeNode(9, left, right);
//        Assertions.assertEquals(16, maxPathSum(root));
    }
}
