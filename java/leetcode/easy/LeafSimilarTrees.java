package leetcode.easy;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/*
https://leetcode.com/problems/leaf-similar-trees/
 */
public class LeafSimilarTrees {
    public boolean leafSimilar(TreeNode n1, TreeNode n2) {
        Deque<TreeNode> s1 = new ArrayDeque<>(), s2 = new ArrayDeque<>();
        s1.push(n1);
        s2.push(n2);
        while (!s1.isEmpty() && !s2.isEmpty()) {
            if (depthSearch(s1) != depthSearch(s2)) {
                return false;
            }
        }
        return s1.isEmpty() && s2.isEmpty();
    }

    // searches till leaf is found, returns leaf value
    // leaving unvisited nodes in stack for future discovery
    public int depthSearch(Deque<TreeNode> s) {
        while (true) {
            TreeNode n = s.pop();
            if (n.right != null) {
                s.push(n.right);
            }
            if (n.left != null) {
                s.push(n.left);
            }
            if (n.left == null && n.right == null) {
                return n.val;
            }
        }
    }

    @Test
    public void test() {
        Assertions.assertTrue(leafSimilar(TreeNode.from(1), TreeNode.from(1)));
        Assertions.assertFalse(leafSimilar(TreeNode.from(1), TreeNode.from(2)));
        Assertions.assertTrue(leafSimilar(TreeNode.from(1, 2), TreeNode.from(2, 2)));
        Assertions.assertFalse(leafSimilar(TreeNode.from(1, 1), TreeNode.from(1, 2)));
        Assertions.assertFalse(leafSimilar(TreeNode.from(1, 2, 3), TreeNode.from(1, 3, 2)));
    }

    @Test
    public void test2() {
        Assertions.assertTrue(leafSimilar(
                TreeNode.from(3, 5, 1, 6, 2, 9, 8, null, null, 7, 4),
                TreeNode.from(3, 5, 1, 6, 7, 4, 2, null, null, null, null, null, null, 9, 8)));
    }
}
