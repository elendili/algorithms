package leetcode.easy;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiameterOfBinaryTree {
    int diameter;

    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        depth(root);
        return diameter;
    }

    public int depth(TreeNode n) {
        if (n == null) return 0;
        int leftDepth = depth(n.left);
        int rightDepth = depth(n.right);
        diameter = Math.max(diameter, leftDepth + rightDepth);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    @Test
    public void test() {
        Assertions.assertEquals(0, diameterOfBinaryTree(TreeNode.from(1)));
        Assertions.assertEquals(1, diameterOfBinaryTree(TreeNode.from(1, 2)));
        Assertions.assertEquals(2, diameterOfBinaryTree(TreeNode.from(1, 2, 3)));
        Assertions.assertEquals(3, diameterOfBinaryTree(TreeNode.from(1, 2, 3, 4)));
        Assertions.assertEquals(3, diameterOfBinaryTree(TreeNode.from(1, 2, 3, 4, 5)));
    }
}
