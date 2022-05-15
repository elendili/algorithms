package leetcode.easy;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null && p.val == q.val) {
            boolean out = isSameTree(p.left, q.left)
                    && isSameTree(p.right, q.right);
            return out;
        }
        return false;
    }

    @Test
    public void test() {
        Assertions.assertTrue(
                isSameTree(TreeNode.from(1, 2, 3),
                        TreeNode.from(1, 2, 3)));
        Assertions.assertFalse(
                isSameTree(TreeNode.from(1, 2),
                        TreeNode.from(1, null, 2)));
        Assertions.assertFalse(
                isSameTree(TreeNode.from(1, 2, 1),
                        TreeNode.from(1, 1, 2)));
        Assertions.assertFalse(
                isSameTree(TreeNode.from(1, 1),
                        TreeNode.from(1)));
    }
}
