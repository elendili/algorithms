package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/?envType=study-plan-v2&envId=premium-algo-100
 */

public class BinaryTreeLongestConsecutiveSequenceII_549 {
    int maxval = 0;

    public int longestConsecutive(TreeNode root) {
        getPaths(root);
        return maxval;
    }

    int[] getPaths(TreeNode cur) {
        if (cur == null) {
            return new int[]{0, 0};   // [incr, decr]
        }
        int incr = 1, decr = 1;
        if (cur.left != null) {
            int[] lengths = getPaths(cur.left);
            int delta = cur.val - cur.left.val;
            if (delta == 1) {
                decr = lengths[1] + 1;
            } else if (delta == -1) {
                incr = lengths[0] + 1;
            }
        }
        if (cur.right != null) {
            int[] lengths = getPaths(cur.right);
            int delta = cur.val - cur.right.val;
            if (delta == 1) {
                decr = Math.max(decr, lengths[1] + 1);
            } else if (delta == -1) {
                incr = Math.max(incr, lengths[0] + 1);
            }
        }

        maxval = Math.max(maxval, decr + incr - 1);
        return new int[]{incr, decr};
    }

    @Test
    public void test1() {
        //   1
        // 3   3
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;
        assertEquals(1, longestConsecutive(n1));
    }

    @Test
    public void test2() {
        //   1
        // 2   3
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;
        assertEquals(2, longestConsecutive(n1));
    }

    @Test
    public void test3() {
        //   2
        // 1   3
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n2.left = n1;
        n2.right = n3;
        assertEquals(3, longestConsecutive(n2));
    }

    @Test
    public void test4() {
        //   1
        //    2
        //     3
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.right = n2;
        n2.right = n3;
        assertEquals(3, longestConsecutive(n1));
    }

    @Test
    public void test5() {
        //   1
        //  2
        // 3
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n2.left = n3;
        assertEquals(3, longestConsecutive(n1));
    }

    @Test
    public void test6() {
        //    1
        //  2  2
        // 1    1
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(2);
        TreeNode n5 = new TreeNode(1);
        n1.left = n2;
        n2.left = n3;
        n1.right = n4;
        n4.right = n5;
        assertEquals(2, longestConsecutive(n1));
    }

    @Test
    public void test7() {
        //    3
        //  2  4
        // 1    5
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n3.left = n2;
        n3.right = n4;
        n2.left = n1;
        n4.right = n5;
        assertEquals(5, longestConsecutive(n3));
    }

    @Test
    public void test8() {
        //    3
        //  2  4
        // 1    4
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(4);
        n3.left = n2;
        n3.right = n4;
        n2.left = n1;
        n4.right = n5;
        assertEquals(4, longestConsecutive(n3));
    }

    @Test
    public void test9() {
        //    3
        //  4  2
        // 5    1
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n3.left = n4;
        n3.right = n2;
        n2.right = n1;
        n4.left = n5;
        assertEquals(5, longestConsecutive(n3));
    }

}
