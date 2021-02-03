package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BinaryTreePruning {
    public TreeNode pruneTree(TreeNode n) {
        return recursive(n, null);
    }

    TreeNode recursive(TreeNode curNode, TreeNode parentNode) {
        if (curNode != null) {
            recursive(curNode.left, curNode);
            recursive(curNode.right, curNode);
            if (curNode.left == null
                    && curNode.right == null
                    && curNode.val == 0
            ) {
                if (parentNode == null) {
                    return null;
                }
                // remove leaf from tree
                if (curNode == parentNode.left) {
                    parentNode.left = null;
                } else {
                    parentNode.right = null;
                }
            }
        }
        return curNode;
    }

    @Test
    public void test() {
        TreeNode input = new TreeNode(1);
        input.right = new TreeNode(0);
        input.right.left = new TreeNode(0);
        input.right.right = new TreeNode(1);
        TreeNode output = new TreeNode(1);
        output.right = new TreeNode(0);
        output.right.right = new TreeNode(1);
        Assertions.assertEquals(output, pruneTree(input));
    }

    @Test
    public void test2() {
        TreeNode input = new TreeNode(1);
        input.left = new TreeNode(0);
        input.left.left = new TreeNode(0);
        input.left.right = new TreeNode(0);
        input.right = new TreeNode(1);
        input.right.left = new TreeNode(0);
        input.right.right = new TreeNode(1);

        TreeNode output = new TreeNode(1);
        output.right = new TreeNode(1);
        output.right.right = new TreeNode(1);

        Assertions.assertEquals(output, pruneTree(input));
    }

    @Test
    public void test3() {
        Assertions.assertEquals(TreeNode.from(1, 1, 0, 1, 1, null, 1),
                pruneTree(TreeNode.from(1, 1, 0, 1, 1, 0, 1, 0)));
    }

    @Test
    public void test4() {
        Assertions.assertEquals(null, pruneTree(TreeNode.from(0)));
    }
}
