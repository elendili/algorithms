package leetcode.easy;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RangeSumOfBst {
    public int rangeSumBST(TreeNode root, int low, int high) {
        return recursive(root, low, high);
    }

    int recursive(TreeNode node, int low, int high) {
        if (node == null) {
            return 0;
        }
        int out = (node.val < low || node.val > high) ? 0 : node.val;
        out += (node.val < low) ? 0 : recursive(node.left, low, high);
        out += (node.val > high) ? 0 : recursive(node.right, low, high);
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(32,
                rangeSumBST(TreeNode.from(10, 5, 15, 3, 7, null, 18),
                        7, 15));
    }

    @Test
    public void test1() {
        Assertions.assertEquals(23,
                rangeSumBST(TreeNode.from(10, 5, 15, 3, 7, 13, 18, 1, null, 6),
                        6, 10));
    }

    @Test
    public void test3() {
        Assertions.assertEquals(17,
                rangeSumBST(TreeNode.from(10, 5, 15, 3, 7),
                        6, 10));
    }

    @Test
    public void test2() {
        Assertions.assertEquals(15, rangeSumBST(TreeNode.from(5, 1, 9), 1, 9));
        Assertions.assertEquals(5, rangeSumBST(TreeNode.from(5, 1, 9), 2, 8));
        Assertions.assertEquals(14, rangeSumBST(TreeNode.from(5, 1, 9), 2, 9));
        Assertions.assertEquals(6, rangeSumBST(TreeNode.from(5, 1, 9), 1, 8));
    }
}
