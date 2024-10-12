package leetcode.easy;

import leetcode.top_interview_questions.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class MinimumAbsoluteDifferenceInBST_530 {
    public int getMinimumDifference(TreeNode root) {
        int minDiff = Integer.MAX_VALUE;
        List<Integer> inorder = new ArrayList<>();
        getInorderTraversal(root, inorder);
        for (int i = 1; i < inorder.size(); i++) {
            minDiff = Math.min(minDiff, Math.abs(inorder.get(i) - inorder.get(i - 1)));
        }
        return minDiff;
    }

    public void getInorderTraversal(TreeNode root, List<Integer> inorder) {
        if (root != null) {
            getInorderTraversal(root.left, inorder);
            inorder.add(root.val);
            getInorderTraversal(root.right, inorder);
        }
    }

    @org.junit.jupiter.api.Test
    public void test() {
        TreeNode input = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(6));
        assertEquals(1, getMinimumDifference(input));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        TreeNode input = new TreeNode(1,
                new TreeNode(0),
                new TreeNode(48, new TreeNode(12), new TreeNode(49)));
        assertEquals(1, getMinimumDifference(input));
    }
}
