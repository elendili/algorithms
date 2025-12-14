package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/closest-binary-search-tree-value/?envType=study-plan-v2&envId=premium-algo-100
 */
public class ClosestBinarySearchTreeValue_270 {
    public int closestValue(TreeNode root, double target) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        int closest = root.val;
        double diff = Math.abs(root.val - target);
        while (!stack.isEmpty()) {
            TreeNode n = stack.pop();
            int val = n.val;
            // search closest diff
            double ndiff = Math.abs(val - target);
            if (ndiff < diff) {
                diff = ndiff;
                closest = val;
            } else if (ndiff == diff) {
                closest = Math.min(closest, val);
            }
            // get next node to search
            if (val > target) {
                // go left
                if (n.left != null) {
                    stack.push(n.left);
                }
            } else if (val < target) {
//                go right
                if (n.right != null) {
                    stack.push(n.right);
                }
            } else {
                break;
            }
        }
        return closest;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        TreeNode root = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(5));
        assertEquals(4, closestValue(root, 3.714286));
    }
    @org.junit.jupiter.api.Test
    public void test0() {
        TreeNode root = new TreeNode(0, new TreeNode(-1),  new TreeNode(2));
        assertEquals(0, closestValue(root, 0.5));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        TreeNode root = new TreeNode(1);
        assertEquals(1, closestValue(root, 4));
    }
}
