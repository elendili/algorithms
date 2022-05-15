package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LowestCommonAancestorOfDeepestLeaves {
    private TreeNode lca;
    private int deepest;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        lca = root;
        deepest = 0;
        helper(root, 0);
        return lca;
    }

    private int helper(TreeNode node, int depth) {
        if (node == null) {
            deepest = Math.max(depth, deepest);
            return depth;
        }
        int l = helper(node.left, depth + 1);
        int r = helper(node.right, depth + 1);
        if (l == r && l == deepest) {
            lca = node;
        }
        return Math.max(l, r);
    }

    @Test
    public void test() {
        Assertions.assertEquals(1, lcaDeepestLeaves(TreeNode.from(1)).val);
        Assertions.assertEquals(2, lcaDeepestLeaves(TreeNode.from(0, 1, 3, null, 2)).val);
    }

    @Test
    public void test2() {
        Assertions.assertEquals(2, lcaDeepestLeaves(TreeNode.from(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4)).val);
    }
}
