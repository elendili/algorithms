package leetcode.top_interview_questions.medium.treesAndGraphs;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/108/trees-and-graphs/788/
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
 */
public class ConstructBinaryTreefromPreorderandInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder==null || inorder==null || preorder.length==0 || inorder.length==0){
            return null;
        }
        return recursive(preorder,inorder,new AtomicInteger(0),0,inorder.length);
    }

    private TreeNode recursive(int[] preorder, int[] inorder, AtomicInteger indexPreOrder, int startInOrder, int endInOrder){
        if(startInOrder>=endInOrder){
            return null;
        }
        TreeNode node = new TreeNode(preorder[indexPreOrder.getAndIncrement()]);
        if (startInOrder==endInOrder-1){
            return node;
        }

        int indexInOrder;
        for(indexInOrder=startInOrder; indexInOrder<endInOrder; indexInOrder++) {
            if (inorder[indexInOrder]==node.val){break;}
        }

        node.left = recursive(preorder,inorder,indexPreOrder,startInOrder,indexInOrder);
        node.right = recursive(preorder,inorder,indexPreOrder,indexInOrder+1,endInOrder);
        return node;
    }

    @Test
    public void test(){
        Assertions.assertEquals(TreeNode.from(3,9,20,null,null,15,7).toString(),
                buildTree(new int[]{3,9,20,15,7},new int[]{9,3,15,20,7}).toString());
    }


    @Test
    public void test2(){
        TreeNode t;
        t = TreeNode.from(1,2,null,null,3);
        Assertions.assertEquals(t.toString(),
                buildTree(t.preOrderArray(),t.inOrderArray()).toString());
    }

    @Test
    public void test4(){
        TreeNode t;
        t = TreeNode.from(1,2,null,null,3);
        Assertions.assertEquals(t.toString(),
                buildTree(new int[]{1,2,3},new int[]{2,3,1}).toString());
    }


    @Test
    @Disabled
    public void test3(){
        TreeNode t = TreeNode.from(1, 2, 3, 4);
        int[] preorder = t.preOrderArray();
        int[] inorder = t.inOrderArray();
        System.out.println(Arrays.stream(preorder).boxed().collect(Collectors.toList()));
        System.out.println(Arrays.stream(inorder).boxed().collect(Collectors.toList()));
        TreeNode actual = buildTree(preorder, inorder);
//        Assertions.assertTrue(t.equals(actual));
        Assertions.assertEquals(t,actual);
    }

}
