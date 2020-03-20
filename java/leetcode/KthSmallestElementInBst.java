package leetcode;

import helpers.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.PriorityQueue;

// https://leetcode.com/problems/kth-smallest-element-in-a-bst/
public class KthSmallestElementInBst {

    public int kthSmallest(TreeNode root, int k) {
        assert root != null;
        assert k > -1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode n = stack.pop();
            pq.add(n.val);
            if (n.right != null) {
                stack.addFirst(n.right);
            }
            if (n.left != null) {
                stack.addFirst(n.left);
            } else if (pq.size()>2*k){
                break; // to avoid full tree traversal
            }
        }
        for (int i = 0; i < k - 1; i++) {
            pq.poll();
        }
        return pq.peek();
    }

    @Test
    public void test1(){
        TreeNode root=new TreeNode(3);
        root.left=new TreeNode(1);
        root.left.right=new TreeNode(2);
        root.right=new TreeNode(4);
        Assertions.assertEquals(1,kthSmallest(root,1));
        Assertions.assertEquals(2,kthSmallest(root,2));
        Assertions.assertEquals(3,kthSmallest(root,3));
        Assertions.assertEquals(4,kthSmallest(root,4));
    }

    @Test
    public void test2(){
        TreeNode root=new TreeNode(5);
        root.left=new TreeNode(3);
        root.left.left =new TreeNode(2);
        root.left.right=new TreeNode(4);
        root.left.left.left =new TreeNode(1);
        root.right=new TreeNode(6);
        Assertions.assertEquals(1,kthSmallest(root,1));
        Assertions.assertEquals(2,kthSmallest(root,2));
        Assertions.assertEquals(3,kthSmallest(root,3));
        Assertions.assertEquals(4,kthSmallest(root,4));
        Assertions.assertEquals(5,kthSmallest(root,5));
        Assertions.assertEquals(6,kthSmallest(root,6));
    }
}
