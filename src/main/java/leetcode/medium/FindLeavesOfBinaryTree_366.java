package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/find-leaves-of-binary-tree/submissions/1855386122/?envType=study-plan-v2&envId=premium-algo-100
 */
public class FindLeavesOfBinaryTree_366 {
    List<List<Integer>> byDepth;

    public List<List<Integer>> findLeaves(TreeNode root) {
        byDepth = new ArrayList<>();
        dfs(root, 0);
        return byDepth;
    }

    int dfs(TreeNode n, int curDepth) {
        if (n == null) {
            return curDepth - 1; // should return total depth of cur leaf
        }
        int leftDepth = dfs(n.left, curDepth + 1);
        int rightDepth = dfs(n.right, curDepth + 1);

        int maxDepth = Math.max(leftDepth, rightDepth);
        int depthFromLeaf = maxDepth - curDepth;
        if (byDepth.size() <= depthFromLeaf) {
            byDepth.add(new ArrayList<>());
        }
        byDepth.get(depthFromLeaf).add(n.val);
        return maxDepth;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        /*
             1
           2   3
         4   5
         */
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        assertEquals("[[4, 5, 3], [2], [1]]", findLeaves(n1).toString());
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        /*
             1
           2   3
          4   5
         */
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n3.left = n5;
        assertEquals("[[4, 5], [2, 3], [1]]", findLeaves(n1).toString());
    }

    @org.junit.jupiter.api.Test
    public void test0() {
        TreeNode n1 = new TreeNode(1);
        assertEquals("[[1]]", findLeaves(n1).toString());
    }

    @org.junit.jupiter.api.Test
    public void test00() {
        assertEquals("[]", findLeaves(null).toString());
    }
}
