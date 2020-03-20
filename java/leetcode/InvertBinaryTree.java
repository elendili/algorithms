package leetcode;

import helpers.TreeNode;

import java.util.LinkedList;

// https://leetcode.com/problems/invert-binary-tree/submissions/
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            LinkedList<TreeNode> list = new LinkedList<>();
            list.add(root);
            while (!list.isEmpty()) {
                TreeNode n = list.pop();
                if (n.left != null) {
                    list.add(n.left);
                }
                if (n.right != null) {
                    list.add(n.right);
                }
                swap(n);
            }
        }
        return root;
    }

    private void swap(TreeNode node) {
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }
}
