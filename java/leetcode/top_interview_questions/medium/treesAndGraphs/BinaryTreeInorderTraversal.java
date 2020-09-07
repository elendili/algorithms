package leetcode.top_interview_questions.medium.treesAndGraphs;
/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/108/trees-and-graphs/786/

Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?

 */

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

public class BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        iterative(root,list);
        return list;
    }

    private void iterative(TreeNode node, List<Integer> list){
        LinkedList<TreeNode> stack = new LinkedList<>();
        while(node!=null || !stack.isEmpty()){
            while(node!=null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }
    }

    void recursive(TreeNode tn, List<Integer> list) {
        if(tn!=null){
            recursive(tn.left,list);
            list.add(tn.val);
            recursive(tn.right,list);
        }
    }

    @Test
    public void test(){
        Assertions.assertEquals(asList(2,1,3).toString(),inorderTraversal(TreeNode.from(1,2,3)).toString());
        Assertions.assertEquals(asList(1,3,2).toString(),inorderTraversal(TreeNode.from(1,null,2,null,null,3)).toString());
    }
}
