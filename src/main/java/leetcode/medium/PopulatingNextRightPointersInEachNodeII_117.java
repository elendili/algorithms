package leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class PopulatingNextRightPointersInEachNodeII_117 {

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Deque<Node> q = new ArrayDeque<>();
        q.add(root);
        int levelCount = 1;
        while (!q.isEmpty()) {
            int nextLevelCount = 0;
            while (!q.isEmpty() && levelCount > 0) {
                Node n = q.poll();
                levelCount--;
                if (n.left != null) {
                    q.add(n.left);
                    nextLevelCount++;
                }
                if (n.right != null) {
                    q.add(n.right);
                    nextLevelCount++;
                }
                if (levelCount > 0) {
                    n.next = q.peek();
                }
            }
            levelCount = nextLevelCount;
        }
        return root;
    }


    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
