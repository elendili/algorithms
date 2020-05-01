package leetcode;

import helpers.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

//https://leetcode.com/problems/longest-univalue-path/
public class LongestUnivaluePath {
    public int longestUnivaluePath(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        TreeNode node;
        int out = 0;
        int curPath = 0;
        int pathBranchesCount = 0;
        while ((node = queue.poll()) != null) {
            int cVal = node.val;
            TreeNode nL = node.left;
            TreeNode nR = node.right;
            int onLevel = 0;
            if (nL != null) {
                if (cVal == nL.val) {
                    queue.addFirst(nL);
                    onLevel += 1;
                } else {
                    queue.addLast(nL);
                }
            }

            if (nR != null) {
                if (cVal == nR.val) {
                    queue.addFirst(nR);
                    onLevel += 1;
                } else {
                    //  separate same children
                    if (nL != null && nR.val == nL.val) {
                        node.left = null;
                        node.right = null;
                        queue.addLast(node);
                    }
                    queue.addLast(nR);
                }
            }
            if (onLevel > 0) {
                pathBranchesCount += (onLevel > 1 ? 1 : 0);
                curPath += onLevel;
            } else {
                TreeNode head = queue.peek();
                if (head == null || head.val != cVal) {
                    out = Math.max(curPath, out);
                    curPath = 0;
                    pathBranchesCount = 0;
                }
            }
        }
        return out;
    }
    public int longestUnivaluePath3(TreeNode n){
        AtomicInteger z = new AtomicInteger();
        dfs(n,z);
        return z.get();
    }
    public int dfs(TreeNode n, AtomicInteger dist){
        if (n==null){
            return 0;
        }
        /*


  # Recursive calls to check for subtrees
  left = length(node.left, ans)
  right = length(node.right, ans)

  # Variables to store maximum lengths
  # in two directions
  Leftmax = 0
  Rightmax = 0

  # If curr node and it's left child has same value
  if (node.left and node.left.val == node.val):
    Leftmax += left + 1

  # If curr node and it's right child has same value
  if (node.right and node.right.val == node.val):
    Rightmax += right + 1

  ans[0] = max(ans[0], Leftmax + Rightmax)
  return max(Leftmax, Rightmax)
        * */
        int left = dfs(n.left,dist);
        int right = dfs(n.right,dist);
        if(n.left!=null && n.val==n.left.val){
            left+=1;
        }
        if(n.right!=null && n.val==n.right.val){
            right+=1;
        }
        dist.set(IntStream.of(dist.get(),left,right).max().orElse(0));
        return Math.max(left,right);
    }

    @Test
    public void test1() {
        TreeNode root = new TreeNode(5); // x
        root.right = new TreeNode(5);// x
        root.right.right = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        assertEquals(2, longestUnivaluePath(root));

        root.right.right.right = new TreeNode(5);
        assertEquals(3, longestUnivaluePath(root));

        root.left.val = 5;
        assertEquals(4, longestUnivaluePath(root));

        root.left.left = new TreeNode(5);
        assertEquals(5, longestUnivaluePath(root));

        root.left.right = new TreeNode(5);
        assertEquals(5, longestUnivaluePath(root));
    }

    @Test
    public void test2() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(4);
        root.right.right = new TreeNode(4);
        assertEquals(4, longestUnivaluePath(root));
    }

    @Test
    public void test3() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(4);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        assertEquals(4, longestUnivaluePath(root));
    }

    @Test
    public void test4() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(2);
        assertEquals(2, longestUnivaluePath(root));
    }

    @Test
    public void test5() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(1);
        root.right.left.left = new TreeNode(1);
        root.right.left.right = new TreeNode(1);
        root.right.right = new TreeNode(1);
        root.right.right.right = new TreeNode(1);
        assertEquals(4, longestUnivaluePath(root));
    }
}
