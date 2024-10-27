package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */
public class FlattenBinaryTreeToLinkedList_114 {

    public TreeNode flatten(TreeNode root) {
        xflatten(root);
        return root;
    }

    public TreeNode xflatten(TreeNode n) {
        if (n == null) {
            return null;
        }
//        System.out.println("in: " + n);
        final TreeNode oldRight = n.right;
        TreeNode rightEnd = n;
        if (n.left != null) {
            TreeNode leftEnd = xflatten(n.left);
            n.right = n.left;
            rightEnd = leftEnd;
            if (leftEnd != null) {
                leftEnd.right = oldRight;
            }
        }
        n.left = null;
        if (oldRight != null) {
            rightEnd = xflatten(oldRight);
        }
//        System.out.println("out: " + rightEnd);
        return rightEnd;
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        TreeNode input = TreeNode.from(1, 2, 3);
        assertEquals("[1,null,2,null,3]", flatten(input).toString());
    }

    @org.junit.jupiter.api.Test
    public void test1_3() {
        TreeNode input = TreeNode.from(1, null, 3);
        assertEquals("[1,null,3]", flatten(input).toString());
    }

    @org.junit.jupiter.api.Test
    public void test1_3_5() {
        TreeNode input = TreeNode.from(1, null, 3, null, 5);
        assertEquals("[1,null,3,null,5]", flatten(input).toString());
    }

    @org.junit.jupiter.api.Test
    public void test1_3_5_7() {
        TreeNode input = TreeNode.from(1, null, 3, null, 5, null, 7);
        assertEquals("[1,null,3,null,5,null,7]", flatten(input).toString());
    }

    @org.junit.jupiter.api.Test
    public void test_zig_zag_to_right() {
        TreeNode input =
                new TreeNode(1,
                        null,
                        new TreeNode(2,
                                new TreeNode(3,
                                        null,
                                        new TreeNode(4, new TreeNode(5), null)
                                ),
                                null));

        assertEquals("[1,null,2,null,3,null,4,null,5]", flatten(input).toString());
    }

    @org.junit.jupiter.api.Test
    public void test_zig_zag_to_left() {
        TreeNode input =
                new TreeNode(1,
                        new TreeNode(2,
                                null,
                                new TreeNode(3, new TreeNode(4, null, new TreeNode(5)), null)), null);

        assertEquals("[1,null,2,null,3,null,4,null,5]", flatten(input).toString());
    }

    @org.junit.jupiter.api.Test
    public void test4() {
        TreeNode input = TreeNode.from(1, 2, 3, 4);
        TreeNode actual = flatten(input);
        assertEquals("[1,null,2,null,4,null,3]", actual.toString());
    }

    @org.junit.jupiter.api.Test
    public void test5() {
        TreeNode input = TreeNode.from(1, 2, 3, 4, 5);
        TreeNode actual = flatten(input);
        assertEquals("[1,null,2,null,4,null,5,null,3]", actual.toString());
    }

    @org.junit.jupiter.api.Test
    public void test() {
        TreeNode input = TreeNode.from(1, 2, 5, 3, 4, null, 6);
        TreeNode actual = flatten(input);
        assertEquals("[1,null,2,null,3,null,4,null,5,null,6]", actual.toString());
    }

    @org.junit.jupiter.api.Test
    public void test1() {
        assertEquals(null, flatten(null));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        TreeNode input = new TreeNode(0);
        assertEquals("[0]", flatten(input).toString());
    }
}
