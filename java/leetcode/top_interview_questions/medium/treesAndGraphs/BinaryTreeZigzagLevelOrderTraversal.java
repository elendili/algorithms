package leetcode.top_interview_questions.medium.treesAndGraphs;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/108/trees-and-graphs/787/

Given a binary tree, return the zigzag level order traversal of its nodes' values.
(ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]

 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list= new ArrayList<>();
        recursive(root,0, list);
        return list;
    }

    private void recursive(TreeNode node, int level, List<List<Integer>> list){
        if(node!=null){
            if(list.size()<=level){
                list.add(new ArrayList<>());
            }

            List<Integer> layer = list.get(level);
            if(level%2==0){
                // left to right
                layer.add(node.val);
            } else {
                // right to left
                layer.add(0,node.val);
            }
            recursive(node.left,level+1,list);
            recursive(node.right,level+1,list);
        }
    }

    @Test
    public void test(){
        Assertions.assertEquals("[]",zigzagLevelOrder(TreeNode.from()).toString());
        Assertions.assertEquals("[[1], [3, 2]]",zigzagLevelOrder(TreeNode.from(1,2,3)).toString());
        Assertions.assertEquals("[[3], [20, 9], [15, 7]]",zigzagLevelOrder(TreeNode.from(3,9,20,null,null,15,7)).toString());
    }
}
