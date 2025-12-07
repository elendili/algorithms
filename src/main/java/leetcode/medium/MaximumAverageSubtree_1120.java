package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/maximum-average-subtree/?envType=study-plan-v2&envId=premium-algo-100
 */
public class MaximumAverageSubtree_1120 {
    double maxAverage = Integer.MIN_VALUE;

    public double maximumAverageSubtree(TreeNode root) {
        dfs(root);
        return maxAverage;
    }

    int[] dfs(TreeNode n) {
        int[] out = new int[]{n.val, 1};
        if (n.right != null) {
            int[] v = dfs(n.right);
            out[0] += v[0];
            out[1] += v[1];
        }
        if (n.left != null) {
            int[] v = dfs(n.left);
            out[0] += v[0];
            out[1] += v[1];
        }
        double average = ((double) out[0]) / out[1];
        maxAverage = Math.max(maxAverage, average);
        return out;
    }

    @Test
    public void test() {
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(6);
        TreeNode n3 = new TreeNode(1);
        n1.left = n2;
        n1.right = n3;
        assertEquals(6, maximumAverageSubtree(n1));
    }

    @Test
    public void test2() {
        TreeNode n1 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(1);
        n1.left = n2;
        n1.right = n3;
        assertEquals(3, maximumAverageSubtree(n1));
    }

    @Test
    public void test3() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(1);
        n1.left = n2;
        n1.right = n3;
        assertEquals(2, maximumAverageSubtree(n1));
    }

    @Test
    public void test5() {
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(1);
        n1.left = n2;
        n1.right = n3;
        assertEquals(2.66666, maximumAverageSubtree(n1), 0.0001);
    }

    @Test
    public void test4() {
        /*
              17
          1       1
        4   3   1   2
         */
        TreeNode n1 = new TreeNode(17);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(3);
        TreeNode n6 = new TreeNode(1);
        TreeNode n7 = new TreeNode(2);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        assertEquals(4.14285d, maximumAverageSubtree(n1), 0.0001);
    }
}
