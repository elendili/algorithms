package leetcode.easy;

import leetcode.top_interview_questions.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountCompleteTreeNodes_222 {
    public int countNodes_WithLinearPerformance(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * 1
     * 2        3
     * 4   5     6   7
     * x x x x   x x x x
     * <p>
     * 8  <- l l l  1000 - 8
     * 9  <- l l r  1001 - 9
     * 10 <- l r l  1010 - 10
     * 11 <- l r r  1011 - 11
     * <p>
     * 12 <- r l l  1100 - 12
     * 13 <- r l r
     * 14 <- r r l
     * 15 <- r r r  1111 - 15
     * <p>
     * go all left - measure height
     * height is amount of bits ro describe path and total number of nodes
     * (2^(height-1)-1) - node count on levels before last
     * (2^(height)-1)   - max node count
     * <p>
     * search count between 2^(height-1) .. 2^(height)-1
     */

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int h = getHeight(root);
        int min = 1 << (h - 1);
        int posMax = (1 << h) - 1;
        int out = binarySearchOfLastExistingNode(root, min, posMax);
        return out;
    }

    int binarySearchOfLastExistingNode(TreeNode root, int left, int right) {
        int out = left;
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            boolean exists = nodeExistsForN(root, mid);
            if (exists) {
                left = mid + 1;
                out = mid;
            } else {
                right = mid - 1;
            }
        }
        return out;
    }

    boolean nodeExistsForN(TreeNode root, int n) {
        // n=8 -> 1000
        // n=9 -> 1001
        TreeNode c = root;
        int msb = getMostSignificantBit(n);
        int curBitIndex = msb - 1;
        while (curBitIndex >= 0) {
            boolean bitSet = (n & (1 << curBitIndex)) > 0;
            if (bitSet) {
                c = c.right;
            } else {
                c = c.left;
            }
            curBitIndex--;
        }
        return c != null;
    }

    int getMostSignificantBit(int n) {
        int o = 0;
        int c = n >> 1;
        while (c > 0) {
            c = c >> 1;
            o++;
            // c=8(o=1), c=4(o=2), c=2(o=3), c=1(o=4)
        }
        return o;
    }

    int getHeight(TreeNode node) {
        int i = 0;
        while (node != null) {
            i++;
            node = node.left;
        }
        return i;
    }

    @org.junit.jupiter.api.Test
    public void test6() {
        TreeNode input = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, new TreeNode(6), null));
        assertEquals(6, countNodes(input));
    }

    @org.junit.jupiter.api.Test
    public void test7() {
        TreeNode input = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, new TreeNode(6), new TreeNode(7)));
        assertEquals(7, countNodes(input));
    }

    @org.junit.jupiter.api.Test
    public void test0() {
        assertEquals(0, countNodes(null));
    }

    @org.junit.jupiter.api.Test
    public void test1() {
        assertEquals(1, countNodes(new TreeNode(1)));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals(2, countNodes(new TreeNode(1, new TreeNode(2), null)));
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        assertEquals(3, countNodes(new TreeNode(1, new TreeNode(2), new TreeNode(3))));
    }

    @org.junit.jupiter.api.Test
    public void test4() {
        assertEquals(4, countNodes(new TreeNode(1, new TreeNode(2, new TreeNode(4), null), new TreeNode(3))));
    }
    @org.junit.jupiter.api.Test
    public void test5() {
        assertEquals(5, countNodes(new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3))));
    }
}
