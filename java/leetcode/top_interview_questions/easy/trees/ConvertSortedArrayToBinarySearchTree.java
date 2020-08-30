package leetcode.top_interview_questions.easy.trees;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/94/trees/631/
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
 */
public class ConvertSortedArrayToBinarySearchTree {

    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length==0){
            return null;
        }
        return recursive(nums,0, nums.length);
    }

    TreeNode recursive(int[] a, int start, int endExclusive) {
        int middle = start + ((endExclusive - start)/2);
        int v = a[middle];
        TreeNode tn = new TreeNode(v);
        if(middle-start>0){
            TreeNode left = recursive(a,start,middle);
            tn.left=left;
        }
        if(endExclusive-(middle+1)>0){
            TreeNode right = recursive(a,middle+1,endExclusive);
            tn.right=right;
        }
        return tn;
    }

    @Test
    public void test(){
        Assertions.assertEquals(TreeNode.from(0).toString(),sortedArrayToBST(new int[]{0}).toString());
        Assertions.assertEquals(TreeNode.from(1,0).toString(),sortedArrayToBST(new int[]{0,1}).toString());
        Assertions.assertEquals(TreeNode.from(1,0,2).toString(),sortedArrayToBST(new int[]{0,1,2}).toString());
        Assertions.assertEquals(TreeNode.from(0,-3,9,-10,null,5).toString(),sortedArrayToBST(new int[]{-10,-3,0,5,9}).toString());
    }

}
