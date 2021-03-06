package leetcode.top_interview_questions.easy.trees;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
https://leetcode.com/explore/interview/card/top-interview-questions-easy/94/trees/555/
Given a binary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
Note: A leaf is a node with no children.

Example:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its depth = 3.
 */
public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
     if(root==null){
         return 0;
     }
     return 1+Math.max(
             maxDepth(root.left),
             maxDepth(root.right)
     );
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode();
        assertEquals(1,maxDepth(root));
        root.left=new TreeNode();
        assertEquals(2,maxDepth(root));
        root.right=new TreeNode();
        assertEquals(2,maxDepth(root));
        root.right.left=new TreeNode();
        assertEquals(3,maxDepth(root));
    }
}
