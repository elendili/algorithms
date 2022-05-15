package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/problems/deepest-leaves-sum/
 */
public class DeepestLeavesSum {
    int maxLevel;
    int sum;

    public int deepestLeavesSum(TreeNode root) {
        sum = 0;
        maxLevel = 0;
        overALevel(0, root);
        return sum;
    }

    void overALevel(int level, TreeNode node) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                if (level > maxLevel) {
                    sum = 0;
                    maxLevel = level;
                }
                if (level == maxLevel) {
                    sum += node.val;
                }
            } else {
                overALevel(level + 1, node.left);
                overALevel(level + 1, node.right);
            }
        }
    }

    @Test
    public void test() {
        Assertions.assertEquals(5, deepestLeavesSum(TreeNode.from(1, 2, 3)));
        Assertions.assertEquals(9, deepestLeavesSum(TreeNode.from(1, 2, null, 4, 5)));
        Assertions.assertEquals(13, deepestLeavesSum(TreeNode.from(1, 2, 3, null, null, 6, 7)));
    }

    @Test
    public void test15() {
        Assertions.assertEquals(15, deepestLeavesSum(TreeNode.from(1, 2, 3, 4, 5, null, 6, 7, null, null, null, null, 8)));
    }
}
