package leetcode.medium;

import leetcode.top_interview_questions.LinkedListNode;
import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/problems/linked-list-in-binary-tree/
public class LinkedListInBinaryTree {

    public boolean isSubPath(LinkedListNode linkedListNodeHead, TreeNode treeNodeHead) {
        // bfs to find value equal to start of linkedlist and then dfs
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(treeNodeHead);
        while (!q.isEmpty()) {
            TreeNode curTn = q.poll();
            if (curTn.val == linkedListNodeHead.val) {
                if (prefixPathContainsExpectedPath(linkedListNodeHead, curTn)) {
                    return true;
                }
            }
            if (curTn.left != null) {
                q.add(curTn.left);
            }
            if (curTn.right != null) {
                q.add(curTn.right);
            }
        }
        return false;
    }

    boolean prefixPathContainsExpectedPath(LinkedListNode ln, TreeNode tn) {
        if (tn != null && ln != null && ln.val == tn.val) {
            if (ln.next == null) {
                return true;
            }
            return prefixPathContainsExpectedPath(ln.next, tn.right)
                    || prefixPathContainsExpectedPath(ln.next, tn.left);
        }
        return false;
    }

    @Test
    public void small() {
        Assertions.assertTrue(
                isSubPath(LinkedListNode.from(1),
                        TreeNode.from(1)));
        Assertions.assertTrue(
                isSubPath(LinkedListNode.from(1, 2),
                        TreeNode.from(1, 2, 3)));

        Assertions.assertFalse(
                isSubPath(LinkedListNode.from(2),
                        TreeNode.from(1)));
        Assertions.assertFalse(
                isSubPath(LinkedListNode.from(),
                        TreeNode.from(1)));
    }

    @Test
    public void fromSite() {
        Assertions.assertTrue(
                isSubPath(LinkedListNode.from(1, 10),
                        TreeNode.from(1, null, 1, 10, 1, 9)));
    }

    @Test
    public void test() {
        Assertions.assertTrue(
                isSubPath(LinkedListNode.from(4, 2, 8),
                        TreeNode.from(1, 4, 4, null, 2, 2, null, 1, null, 6, 8, null, null, null, null, 1, 3)));
        Assertions.assertTrue(
                isSubPath(LinkedListNode.from(1, 4, 2, 6),
                        TreeNode.from(1, 4, 4, null, 2, 2, null, 1, null, 6, 8, null, null, null, null, 1, 3)));
        Assertions.assertFalse(
                isSubPath(LinkedListNode.from(1, 1, 2, 6),
                        TreeNode.from(1, 4, 4, null, 2, 2, null, 1, null, 6, 8, null, null, null, null, 1, 3)));
    }


}
