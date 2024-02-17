package leetcode.medium;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathSumII {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> out = new ArrayList<>();
        Deque<Integer> currentDeque = new ArrayDeque<>();
        recurse(root, targetSum, currentDeque, 0, out);
        return out;
    }

    private void recurse(TreeNode node,
                         int targetSum,
                         Deque<Integer> currentDeque,
                         int curSum,
                         List<List<Integer>> out) {
        if(node!=null) {
                currentDeque.addLast(node.val);
                int newSum = curSum + node.val;
                if(newSum==targetSum && node.left == null && node.right == null){
                    out.add(new ArrayList<>(currentDeque));
                } else {
                    recurse(node.left, targetSum, currentDeque, newSum, out);
                    recurse(node.right, targetSum, currentDeque, newSum, out);
                }
                currentDeque.removeLast();
        }
    }

    @Test
    public void test22() {
        TreeNode parent = new TreeNode(11, new TreeNode(7), new TreeNode(2));
        TreeNode leftRoot = new TreeNode(4, parent, null);

        parent = new TreeNode(4, new TreeNode(5), new TreeNode(1));
        TreeNode rightRoot = new TreeNode(8, new TreeNode(13), parent);
        TreeNode root = new TreeNode(5, leftRoot, rightRoot);

        String expected = "[[5, 4, 11, 2], [5, 8, 4, 5]]";
        assertEquals(expected, pathSum(root, 22).toString());
    }
    
    @Test
    public void test123() {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        assertEquals("[]", pathSum(root, 5).toString());
        assertEquals("[[1, 2]]", pathSum(root, 3).toString());
        assertEquals("[[1, 3]]", pathSum(root, 4).toString());
    }

}
