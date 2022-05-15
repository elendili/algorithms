package yandex.Feb52021;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Дано бинарное дерево, нужно найти и вернуть элемент,
 * находящийся на максимальной глубине от корня.
 */

public class DeepestNode {

    TreeNode maxV;
    int maxDepth;

    public TreeNode getDeepestNodeValue(TreeNode root) {
        maxV = root;
        maxDepth = 0;
        getDeepestNodeValue(root, 1);
        return maxV;
    }

    void getDeepestNodeValue(TreeNode n, int depth) {
        if (n != null) {
            if (depth > maxDepth) {
                maxDepth = depth;
                maxV = n;
            }
            getDeepestNodeValue(n.left, depth + 1);
            getDeepestNodeValue(n.right, depth + 1);
        }
    }

    @Test
    public void test() {
        Assertions.assertEquals(null, getDeepestNodeValue(null));
        Assertions.assertEquals("[1]", getDeepestNodeValue(TreeNode.from(1)).toString());
        Assertions.assertEquals("[2]", getDeepestNodeValue(TreeNode.from(1, 2, 3)).toString());
        Assertions.assertEquals("[4]", getDeepestNodeValue(TreeNode.from(1, 2, 3, 4)).toString());
    }
}
