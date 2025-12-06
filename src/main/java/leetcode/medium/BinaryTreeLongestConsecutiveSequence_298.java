package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/?envType=study-plan-v2&envId=premium-algo-100
 */
public class BinaryTreeLongestConsecutiveSequence_298 {
    public int longestConsecutive(TreeNode root) {
        return recursive(root, 0, 0);
    }

    int recursive(TreeNode root, int prevValue, int length) {
        if (root==null){
            return length;
        }
        int newLength = (root.val == prevValue + 1) ? length + 1 : 1;
        int l = recursive(root.left, root.val, newLength);
        int r = recursive(root.right, root.val, newLength);
        return Math.max(length, Math.max(l, r));
    }

    @org.junit.jupiter.api.Test
    public void test() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        n1.right = n3;
        n3.left = n2;
        n3.right = n4;
        n4.right = n5;

        assertEquals(3, longestConsecutive(n1));
    }
    @org.junit.jupiter.api.Test
    public void test2() {
        TreeNode head = new TreeNode(2,
                null, new TreeNode(3,new TreeNode(2, new TreeNode(1), null), null)
        );

        assertEquals(2, longestConsecutive(head));
    }
}
