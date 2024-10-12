package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class ConstructBinaryTreefromInorderAndPostorderTraversal_106 {

    private int[] postorder;
    private int pi;
    private Map<Integer, Integer> inorderIndexes;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // recursively go on postorder,
        // on every recurse call decrease index in postorder
        // take postorder latest as new root
        // find value in inorder, and split inorder to left and right parts
        // and go recursion with right, then left
        this.postorder = postorder;
        this.pi = postorder.length - 1;
        this.inorderIndexes = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexes.put(inorder[i], i);
        }

        return buildTree(0, inorder.length - 1);
    }

    public TreeNode buildTree(int li, int ri) {
        if (li > ri) {
            return null;
        }
        int rootValue = postorder[pi];
        pi--;
        int inorderIndex = inorderIndexes.get(rootValue);
        TreeNode rootNode = new TreeNode(rootValue);
        rootNode.right = buildTree(inorderIndex + 1, ri);
        rootNode.left = buildTree(li, inorderIndex - 1);
        return rootNode;
    }


    @org.junit.jupiter.api.Test
    public void test1() {
        assertEquals(new TreeNode(-1), buildTree(new int[]{-1}, new int[]{-1}));
    }
    @org.junit.jupiter.api.Test
    public void test2() {
        TreeNode expected = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        assertEquals(expected, buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3}));
    }
}
