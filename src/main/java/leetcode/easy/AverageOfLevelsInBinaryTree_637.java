package leetcode.easy;

import leetcode.top_interview_questions.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AverageOfLevelsInBinaryTree_637 {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> out = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int nextLevelCount = 1;
        while (!q.isEmpty()) {
            final int levelCount = nextLevelCount;
            int levelCounter = nextLevelCount;
            nextLevelCount = 0;
            long levelSum = 0;
            while (!q.isEmpty() && levelCounter > 0) {
                // iterate on level
                TreeNode n = q.poll();
                levelCounter -= 1;
                levelSum += n.val;
                if (n.left != null) {
                    q.add(n.left);
                    nextLevelCount++;
                }
                if (n.right != null) {
                    q.add(n.right);
                    nextLevelCount++;
                }
            }
            double levelAverage = ((double) levelSum) / levelCount;
            out.add(levelAverage);
        }
        return out;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        TreeNode input = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7))
        );

        assertEquals(asList(3.00000, 14.50000, 11.00000), averageOfLevels(input));
    }
    @org.junit.jupiter.api.Test
    public void test2() {
        TreeNode input = new TreeNode(3,
                new TreeNode(9, new TreeNode(15), new TreeNode(7)),
                new TreeNode(20)
        );

        assertEquals(asList(3.00000, 14.50000, 11.00000), averageOfLevels(input));
    }
}
