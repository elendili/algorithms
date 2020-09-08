package leetcode.top_interview_questions.medium.treesAndGraphs;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/108/trees-and-graphs/790/
Given a binary search tree,
write a function kthSmallest to find the kth smallest element in it.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?



Constraints:

The number of elements of the BST is between 1 to 10^4.
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 */
public class KthSmallestElementInaBST {

    public int kthSmallest(TreeNode node, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while(true){
            while(node!=null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if(--k==0){
                return node.val;
            }
            node = node.right;
        }
    }

    @Test
    public void test(){
        TreeNode node = TreeNode.from(3, 2, 4, 1, null, null, 5);
        Assertions.assertEquals(1,kthSmallest(node,1));
        Assertions.assertEquals(2,kthSmallest(node,2));
        Assertions.assertEquals(3,kthSmallest(node,3));
        Assertions.assertEquals(4,kthSmallest(node,4));
        Assertions.assertEquals(5,kthSmallest(node,5));
    }

    @Test
    public void test2(){
        TreeNode node = TreeNode.from(1, 1, 1, 1);
        Assertions.assertEquals(1,kthSmallest(node,1));
        Assertions.assertEquals(1,kthSmallest(node,2));
        Assertions.assertEquals(1,kthSmallest(node,3));
        Assertions.assertEquals(1,kthSmallest(node,4));
    }
}
