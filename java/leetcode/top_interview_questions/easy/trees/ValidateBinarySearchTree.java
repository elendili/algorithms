package leetcode.top_interview_questions.easy.trees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
https://leetcode.com/explore/interview/card/top-interview-questions-easy/94/trees/625/
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:

    2
   / \
  1   3

Input: [2,1,3]
Output: true

Example 2:

    5
   / \
  1   4
     / \
    3   6

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class ValidateBinarySearchTree {

    public class TreeNode {
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

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,null,null);
    }
    public boolean isValidBST(TreeNode n, Integer noMore, Integer noLess) {
        if(n==null){
            return true;
        }
        if((noMore!=null&& n.val>=noMore) || (noLess!=null && n.val<=noLess)){
            return false;
        }
        boolean out = isValidBST(n.right,noMore,n.val)
                &&
               isValidBST(n.left,n.val,noLess);
        return out;
    }

    @Test
    public void test(){
        TreeNode h = new TreeNode(5);
        h.left= new TreeNode(1);
        h.right=new TreeNode(6);
        assertTrue(isValidBST(h));
        h.right.left=new TreeNode(4);
        assertFalse(isValidBST(h));
        h.right.right= new TreeNode(7);
        assertFalse(isValidBST(h));
    }

}
