package leetcode.top_interview_questions.easy.trees;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/94/trees/628/
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
 */
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if(root==null){
            return Collections.emptyList();
        }
        onLevelRecursive(root,0,lists);
        return lists;
    }

    void onLevelRecursive(TreeNode tn, int level, final List<List<Integer>> lists) {
        if(tn!=null){
            if(level>lists.size()-1){
                lists.add(new ArrayList<>());
            }
            List<Integer> l = lists.get(level);

            l.add(tn.val);
            onLevelRecursive(tn.left, level+1,lists);
            onLevelRecursive(tn.right,level+1,lists);
        }
    }

    @Test
    public void test(){
        Assertions.assertEquals("[[1]]",  levelOrder(TreeNode.from(1)).toString() );
        Assertions.assertEquals("[[1], [2]]",  levelOrder(TreeNode.from(1,2)).toString() );
        Assertions.assertEquals("[[1], [3], [6]]",  levelOrder(TreeNode.from(1,null,3,null,null,6)).toString() );
        TreeNode tn = TreeNode.from(3, 9, 20, null, null, 15, 7);
        Assertions.assertEquals("[[3], [9, 20], [15, 7]]",  levelOrder(tn).toString() );
    }

}
