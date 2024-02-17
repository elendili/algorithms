package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 */
public class BinaryTreeRightSideView {

    public static class TreeNode {
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

    public List<Integer> rightSideView(TreeNode root) {
        // bfs with taking right-most element on every level

        List<Integer> out = new ArrayList<>();
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        q.addLast(null);

        while (!q.isEmpty() && q.peek()!=null) {
            TreeNode curNode;
            while ((curNode = q.pollFirst()) != null) {
                if (curNode.left != null) {
                    q.addLast(curNode.left);
                }
                if (curNode.right != null) {
                    q.addLast(curNode.right);
                }
                if(q.peek()==null){
                    out.add(curNode.val);
                    q.addLast(null);
                }
            }

        }
        return out;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(5)),
                new TreeNode(3, null, new TreeNode(4)));

        Assertions.assertEquals("[1, 3, 4]", rightSideView(root).toString());
    }

    @Test
    public void test2() {
        TreeNode root = new TreeNode(1, null, new TreeNode(3));
        Assertions.assertEquals("[1, 3]", rightSideView(root).toString());
        root = new TreeNode(1, new TreeNode(3), null);
        Assertions.assertEquals("[1, 3]", rightSideView(root).toString());
        root = new TreeNode(1, new TreeNode(3,new TreeNode(5), null), null);
        Assertions.assertEquals("[1, 3, 5]", rightSideView(root).toString());
        root = new TreeNode(1, new TreeNode(3,new TreeNode(5), new TreeNode(6)), null);
        Assertions.assertEquals("[1, 3, 6]", rightSideView(root).toString());
    }

    @Test
    public void test3() {
        Assertions.assertEquals("[]", rightSideView(null).toString());
    }
}
