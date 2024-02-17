package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class LowestCommonAncestorOfaBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int x, TreeNode left, TreeNode right) {
            val = x;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // find path from root to p and to q

        Map<Integer, TreeNode> childToParent = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty() && !(childToParent.containsKey(p.val) && childToParent.containsKey(q.val))) {
            TreeNode cur = stack.pop();
            if (cur.left != null) {
                childToParent.put(cur.left.val, cur);
                stack.push(cur.left);
            }
            if (cur.right != null) {
                childToParent.put(cur.right.val, cur);
                stack.push(cur.right);
            }
        }
        // create set of p parents
        Set<Integer> pSet = new HashSet<>();
        TreeNode c = p;
        do {
            pSet.add(c.val);
            c = childToParent.get(c.val);
        } while (c != null);

        // search lowest Common Ancestor of p and q
        c = q;
        while (!pSet.contains(c.val)) {
            c = childToParent.get(c.val);
        }
        return c;
    }

    @Test
    public void test() {
        TreeNode p = new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4)));
        TreeNode q = new TreeNode(1, new TreeNode(0), new TreeNode(8));
        TreeNode root = new TreeNode(3, p, q);
        Assertions.assertEquals(3, lowestCommonAncestor(root, p, q).val);
    }

    @Test
    public void test2() {
        TreeNode q = new TreeNode(4);
        TreeNode p = new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), q));
        TreeNode root = new TreeNode(3, p, new TreeNode(1, new TreeNode(0), new TreeNode(8)));
        Assertions.assertEquals(5, lowestCommonAncestor(root, p, q).val);
    }

    @Test
    public void test3() {
        TreeNode q = new TreeNode(2);
        TreeNode p = new TreeNode(1, q, null);
        Assertions.assertEquals(1, lowestCommonAncestor(p, p, q).val);
    }

}
