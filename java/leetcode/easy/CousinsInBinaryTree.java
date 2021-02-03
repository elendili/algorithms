package leetcode.easy;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CousinsInBinaryTree {
    int xd, yd;
    TreeNode xP, yP;

    public boolean isCousins(TreeNode root, int x, int y) {
        xd = -1;
        yd = -1;
        xP = null;
        yP = null;
        depth(root, null, x, y, 0);
        return xd == yd && xP != yP;
    }

    void depth(TreeNode n, TreeNode parent, int x, int y, int d) {
        if (n != null && (xd < 0 || yd < 0)) {
            if (n.val == x) {
                xd = d;
                xP = parent;
            } else if (n.val == y) {
                yd = d;
                yP = parent;
            }
            depth(n.left, n, x, y, d + 1);
            depth(n.right, n, x, y, d + 1);
        }
    }

    @Test
    public void test() {
        Assertions.assertFalse(isCousins(TreeNode.from(1, 2, 3, 4), 4, 3));
        Assertions.assertFalse(isCousins(TreeNode.from(1, 2, 3, 4), 4, 2));
        Assertions.assertFalse(isCousins(TreeNode.from(1, 2, 3, 4), 2, 3));
        Assertions.assertTrue(isCousins(TreeNode.from(1, 2, 3, null, 4, null, 5), 5, 4));
    }

    @Test
    public void test2() {
        Assertions.assertFalse(isCousins(TreeNode.from(1, 2, 3, null, 4), 2, 3));
    }
}
