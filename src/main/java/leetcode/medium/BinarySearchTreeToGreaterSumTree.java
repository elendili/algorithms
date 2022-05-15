package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BinarySearchTreeToGreaterSumTree {
    int biggerSum = 0;

    public TreeNode bstToGst(TreeNode root) {
        biggerSum = 0;
        recursiveInOrder(root);
        return root;
    }

    private void recursiveInOrder(TreeNode root) {
        // find the rightest nodes from current
        if (root.right != null) recursiveInOrder(root.right);
        root.val += biggerSum;
        biggerSum = root.val;
        // apply sum on smallest nodes
        if (root.left != null) recursiveInOrder(root.left);
    }

    @Test
    public void test0() {
        Assertions.assertEquals(TreeNode.from(30, 36, 21, 36, 35, 26, 15, null, null, null, 33, null, null, null, 8),
                bstToGst(TreeNode.from(4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8)));
    }

    @Test
    public void test1() {
        Assertions.assertEquals(TreeNode.from(1, null, 1), bstToGst(TreeNode.from(0, null, 1)));
    }

    @Test
    public void test2() {
        Assertions.assertEquals(TreeNode.from(3, 3, 2), bstToGst(TreeNode.from(1, 0, 2)));
    }

    @Test
    public void test3() {
        Assertions.assertEquals(TreeNode.from(7, 9, 4, 10), bstToGst(TreeNode.from(3, 2, 4, 1)));
    }
}
