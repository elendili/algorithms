package leetcode.hard;

import leetcode.top_interview_questions.TreeNode;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestBinarySearchTreeValueII_272 {

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        if (root == null || k <= 0) {
            return Collections.emptyList();
        }
        LinkedList<Integer> dq = new LinkedList<>();
        dfs(root, target, k, dq);
        return dq;
    }

    void dfs(TreeNode root, double target, int k, Deque<Integer> dq) {
        if (root == null) return;
        dfs(root.left, target, k, dq);
        if (dq.size() < k) {
            dq.addLast(root.val);
        } else {
            if (Math.abs(root.val - target) <= Math.abs(dq.getFirst() - target)) {
                dq.removeFirst();
                dq.add(root.val);
            } else return;
        }
        dfs(root.right, target, k, dq);
    }

    @org.junit.jupiter.api.Test
    public void test() {
        TreeNode root = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(5));
        assertEquals("[3, 4]", closestKValues(root, 3.714286, 2).toString());
        assertEquals("[1, 2, 3, 4, 5]", closestKValues(root, 3.714286, 5).toString());
        assertEquals("[]", closestKValues(root, 3.714286, 0).toString());
        assertEquals("[]", closestKValues(null, 3.714286, 0).toString());
        assertEquals("[1, 2]", closestKValues(root, -1, 2).toString());
        assertEquals("[4, 5]", closestKValues(root, 6, 2).toString());
    }
}
