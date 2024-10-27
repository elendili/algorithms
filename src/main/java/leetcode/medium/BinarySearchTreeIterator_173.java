package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator
 */
public class BinarySearchTreeIterator_173 {

    class BSTIterator {
        private final Deque<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new ArrayDeque<>();
            pushInStackLeftAndNode(root);
        }

        /*
   1
2     3
         */
        public int next() {
            assert !stack.isEmpty() : "Stack is empty on next()";
            final TreeNode node = stack.poll();
            int out = node.val;
            if (node.right != null) {
                pushInStackLeftAndNode(node.right);
            }
            return out;
        }

        void pushInStackLeftAndNode(final TreeNode node) {
            stack.push(node);
            TreeNode c = node;
            while (c.left != null) {
                stack.addFirst(c.left);
                c = stack.peekFirst();
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    @org.junit.jupiter.api.Test
    public void test() {
        TreeNode input = TreeNode.from(7, 3, 15, null, null, 9, 20);
        BSTIterator bSTIterator = new BSTIterator(input);
        assertEquals(3, bSTIterator.next());    // return 3
        assertEquals(7, bSTIterator.next());    // return 7
        assertEquals(true, bSTIterator.hasNext()); // return True
        assertEquals(9, bSTIterator.next());    // return 9
        assertEquals(true, bSTIterator.hasNext()); // return True
        assertEquals(15, bSTIterator.next());    // return 15
        assertEquals(true, bSTIterator.hasNext()); // return True
        assertEquals(20, bSTIterator.next());    // return 20
        assertEquals(false, bSTIterator.hasNext()); // return False
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        TreeNode input = TreeNode.from(1, 2, 3, 4, 5, 6, 7);
        BSTIterator bSTIterator = new BSTIterator(input);
        assertEquals(4, bSTIterator.next());
        assertEquals(2, bSTIterator.next());
        assertEquals(5, bSTIterator.next());
        assertEquals(1, bSTIterator.next());
        assertEquals(6, bSTIterator.next());
        assertEquals(3, bSTIterator.next());
        assertEquals(7, bSTIterator.next());
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        TreeNode input = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4))));
        BSTIterator bSTIterator = new BSTIterator(input);
        assertEquals(true, bSTIterator.hasNext());
        assertEquals(1, bSTIterator.next());
        assertEquals(true, bSTIterator.hasNext());
        assertEquals(2, bSTIterator.next());
        assertEquals(true, bSTIterator.hasNext());
        assertEquals(3, bSTIterator.next());
        assertEquals(true, bSTIterator.hasNext());
        assertEquals(4, bSTIterator.next());
        assertEquals(false, bSTIterator.hasNext());
    }
}
