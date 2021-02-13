package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// https://leetcode.com/problems/longest-univalue-path/
public class LongestUnivaluePath {
    int max = 0;

    public int longestUnivaluePath(TreeNode root) {
        longestUnivaluePath(root, null);
        return max;
    }

    int longestUnivaluePath(TreeNode n, TreeNode parent) {
        if (n == null) {
            return 0;
        }
        int lv = longestUnivaluePath(n.left, n);
        int rv = longestUnivaluePath(n.right, n);
        max = Math.max(max, lv + rv);
        if (parent == null) {
            return lv + rv;
        } else if (parent.val == n.val) {
            return Math.max(lv, rv) + 1;
        } else {
            return 0;
        }
    }

    @Test
    public void test() {
        Assertions.assertEquals(0, longestUnivaluePath(null));
        Assertions.assertEquals(0, longestUnivaluePath(TreeNode.from(5)));
        Assertions.assertEquals(1, longestUnivaluePath(TreeNode.from(5, 5)));
        Assertions.assertEquals(1, longestUnivaluePath(TreeNode.from(5, 4, 5)));
        Assertions.assertEquals(2, longestUnivaluePath(TreeNode.from(5, 5, 5)));
        Assertions.assertEquals(2, longestUnivaluePath(TreeNode.from(5, 4, 5, 1, 1, 5)));
        Assertions.assertEquals(2, longestUnivaluePath(TreeNode.from(1, 4, 5, 4, 4, 5)));
    }
}
