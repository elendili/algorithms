package leetcode.easy;

import leetcode.top_interview_questions.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathSum_112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        int localSum = targetSum - root.val;
        if (root.left == null && root.right == null) {
            return localSum == 0;
        } else {
            boolean out = false;
            if (root.left != null) {
                out = hasPathSum(root.left, localSum);
            }
            if (root.right != null) {
                out = out || hasPathSum(root.right, localSum);
            }
            return out;
        }
    }

    @org.junit.jupiter.api.Test
    public void test() {
        TreeNode actualRoot = new TreeNode(5,
                new TreeNode(4,
                        new TreeNode(11, new TreeNode(7), new TreeNode(2)),
                        null),
                new TreeNode(8,
                        new TreeNode(13),
                        new TreeNode(4, null, new TreeNode(1)))
        );
        assertEquals(true, hasPathSum(actualRoot, 22));
        assertEquals(true, hasPathSum(actualRoot, 26));
        assertEquals(true, hasPathSum(actualRoot, 18));
        assertEquals(false, hasPathSum(actualRoot, 19));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        TreeNode actualRoot = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3));
        assertEquals(false, hasPathSum(actualRoot, 2));
        assertEquals(false, hasPathSum(actualRoot, 5));
        assertEquals(true, hasPathSum(actualRoot, 3));
        assertEquals(true, hasPathSum(actualRoot, 4));
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        assertEquals(false, hasPathSum(null, 2));
    }
}
