package leetcode.easy;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BalancedBinaryTree {
    boolean balanced;

    public boolean isBalanced(TreeNode n) {
        balanced = true;
        depth(n);
        return balanced;
    }

    int depth(TreeNode n) {
        if (n == null || !balanced) {
            return 0;
        }
        int l = depth(n.left);
        int r = depth(n.right);
        if (Math.abs(l - r) > 1) {
            balanced = false;
        }
        return Math.max(l, r) + 1;
    }

    @Test
    public void test() {
        Assertions.assertTrue(
                isBalanced(TreeNode.from(3, 9, 20, null, null, 15, 7)));
        Assertions.assertFalse(
                isBalanced(TreeNode.from(1, 2, 2, 3, 3, null, null, 4, 4)));
        Assertions.assertTrue(
                isBalanced(null));
    }
}
