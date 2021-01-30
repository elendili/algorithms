package leetcode;

import leetcode.top_interview_questions.TreeNode;

//    https://leetcode.com/problems/maximum-depth-of-binary-tree/submissions/
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        return maxDepth(root,0);
    }
    public int maxDepth(TreeNode node, int depth) {
        if(node==null){
            return depth;
        } else {
            int l = maxDepth(node.left,depth+1);
            int r = maxDepth(node.right,depth+1);
            return Math.max(l,r);
        }
    }
}
