package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/binary-tree-vertical-order-traversal/description/?envType=study-plan-v2&envId=premium-algo-100
 */
public class BinaryTreeVerticalOrderTraversal_314 {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> out = new ArrayList<>();
        if (root == null) {
            return out;
        }
        int shift = 0;

        record NodeAndIndex(TreeNode n, int index) {
        }
        Queue<NodeAndIndex> q = new ArrayDeque<>();
        q.add(new NodeAndIndex(root, 0));
        while (!q.isEmpty()) {
            NodeAndIndex nai = q.poll();
            TreeNode n = nai.n;
            int index = nai.index;
            if (index < -shift) {
                out.addFirst(new ArrayList<>());
                shift += 1;
            }
            int shiftedColumn = index + shift;
            if (shiftedColumn >= out.size()) {
                out.addLast(new ArrayList<>());
            }
            out.get(shiftedColumn).add(n.val);
            if (n.left != null) {
                q.add(new NodeAndIndex(n.left, index - 1));
            }
            if (n.right != null) {
                q.add(new NodeAndIndex(n.right, index + 1));
            }
        }
        return out;
    }

    @org.junit.jupiter.api.Test
    public void testNull() {
        assertEquals("[]",
                verticalOrder(null).toString());
    }

    @org.junit.jupiter.api.Test
    public void test1() {
        assertEquals("[[1]]",
                verticalOrder(new TreeNode(1)).toString());
    }

    @org.junit.jupiter.api.Test
    public void test() {
        TreeNode n3 = new TreeNode(3);
        TreeNode n9 = new TreeNode(9);
        TreeNode n20 = new TreeNode(20);
        TreeNode n15 = new TreeNode(15);
        TreeNode n7 = new TreeNode(7);
        n3.left = n9;
        n3.right = n20;
        n20.left = n15;
        n20.right = n7;

        assertEquals("[[9],[3,15],[20],[7]]",
                verticalOrder(n3).toString().replaceAll(" ", ""));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        TreeNode n3 = new TreeNode(3);
        TreeNode n9 = new TreeNode(9);
        TreeNode n8 = new TreeNode(8);
        TreeNode n4 = new TreeNode(4);
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n7 = new TreeNode(7);
        n3.left = n9;
        n3.right = n8;
        n9.left = n4;
        n9.right = n0;
        n8.left = n1;
        n8.right = n7;

        assertEquals("[[4],[9],[3,0,1],[8],[7]]",
                verticalOrder(n3).toString().replaceAll(" ", ""));
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n10 = new TreeNode(10);
        TreeNode n9 = new TreeNode(9);
        TreeNode n11 = new TreeNode(11);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n10;
        n3.left = n9;
        n3.right = n11;
        n4.right = n5;
        n5.right = n6;

        assertEquals("[[4],[2,5],[1,10,9,6],[3],[11]]",
                verticalOrder(n1).toString().replaceAll(" ", ""));
    }

    @org.junit.jupiter.api.Test
    public void test30() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.right = n4;
        n3.left = n5;

        assertEquals("[[2],[1,4,5],[3]]",
                verticalOrder(n1).toString().replaceAll(" ", ""));
    }
}
