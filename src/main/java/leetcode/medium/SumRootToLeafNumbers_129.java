package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/sum-root-to-leaf-numbers
 */
public class SumRootToLeafNumbers_129 {
    int sum = 0;

    public int sumNumbers(TreeNode root) {
        this.sum = 0;
        gatherNumbersToSum(root, 0);
        return sum;
    }

    public void gatherNumbersToSum(TreeNode root, final int gatheredNumber) {
        if (root != null) {
            int newGatheredNumber = gatheredNumber * 10 + root.val;
            if (root.left == null && root.right == null) {
                sum += newGatheredNumber;
            } else {
                gatherNumbersToSum(root.left, newGatheredNumber);
                gatherNumbersToSum(root.right, newGatheredNumber);
            }
        }
    }

    @org.junit.jupiter.api.Test
    public void test1() {
        assertEquals(25, sumNumbers(TreeNode.from(1, 2, 3)));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals(1026, sumNumbers(TreeNode.from(4, 9, 0, 5, 1)));
    }

    @org.junit.jupiter.api.Test
    public void test_depth_10() {
        TreeNode input = new TreeNode(1, null,
                new TreeNode(9, null,
                        new TreeNode(9, null,
                                new TreeNode(9, null,
                                        new TreeNode(9, null,
                                                new TreeNode(9, null,
                                                        new TreeNode(9, null,
                                                                new TreeNode(9, null,
                                                                        new TreeNode(9, null,
                                                                                new TreeNode(9))))))))));
        assertEquals(1999999999, sumNumbers(input));
    }
}
