package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree
 */
public class LowestCommonAncestorOfaBinarySearchTree {

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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val >= q.val) {
            return lowestCommonAncestor(root, q, p);
        }
        // p, root, q
        if (root.val >= p.val && root.val <= q.val) {
            return root;
        }
        // p, q, root
        if (root.val >= p.val && root.val >= q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // root, p, q
        if (root.val <= p.val && root.val <= q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        throw new AssertionError("Should not be here");
    }

    @Test
    public void test() {
//        Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
//        Output: 6
        TreeNode pRightLeft = new TreeNode(3);
        TreeNode pRight = new TreeNode(4, pRightLeft, new TreeNode(5));
        TreeNode p = new TreeNode(2, new TreeNode(0), pRight);
        TreeNode q = new TreeNode(8, new TreeNode(7), new TreeNode(9));
        TreeNode root = new TreeNode(6, p, q);
        Assertions.assertEquals(6, lowestCommonAncestor(root, p, q).val);
        Assertions.assertEquals(2, lowestCommonAncestor(root, p, pRight).val);
        Assertions.assertEquals(4, lowestCommonAncestor(root, pRightLeft, pRight).val);
    }
}
