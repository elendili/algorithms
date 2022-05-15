package leetcode.easy;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnivaluedBinaryTree {
    boolean isunival = true;

    public boolean isUnivalTree(TreeNode n) {
        isunival = true;
        recursive(n);
        return isunival;
    }

    public void recursive(TreeNode n) {
        if (isunival && n != null) {
            if (n.left != null && n.val != n.left.val
                    || n.right != null && n.val != n.right.val
            ) {
                this.isunival = false;
            } else {
                recursive(n.left);
                recursive(n.right);
            }
        }
    }

    @Test
    public void test() {
        Assertions.assertTrue(
                isUnivalTree(TreeNode.from(1, 1, 1, 1, 1, null, 1)));
        Assertions.assertFalse(
                isUnivalTree(TreeNode.from(2, 2, 2, 5, 2)));
        Assertions.assertFalse(
                isUnivalTree(TreeNode.from(3, 3, 3, null, null, 2, 3)));
    }
}
