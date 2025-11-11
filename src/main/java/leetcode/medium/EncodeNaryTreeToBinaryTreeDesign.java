package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;

import java.util.List;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Definition for a Node.
 * https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/?envType=study-plan-v2&envId=premium-algo-100
 */
public class EncodeNaryTreeToBinaryTreeDesign {
    class Codec {
        // Encodes an n-ary tree to a binary tree.
        public TreeNode encode(Node root) {
            // put all children as left child chain recursively?
            // put all siblings as right child chain recursively?

            // put root first children as left child,
            // put root second children as right child of first root child
            // rule: siblings are on right
            // rule: children are on left

            // collection of children converted to TreeNode of
            TreeNode out = new TreeNode(root.val);
            out.left = listOfNodesIntoTreeNode(root.children);
            return out;
        }

        TreeNode listOfNodesIntoTreeNode(List<Node> children) {
            if (children == null || children.isEmpty()) {
                return null;
            }
            Node first = children.getFirst();
            TreeNode out = new TreeNode(first.val);
            out.right = listOfNodesIntoTreeNode(children.subList(1, children.size()));
            out.left = listOfNodesIntoTreeNode(first.children);
            return out;
        }

        // Decodes your binary tree to an n-ary tree.
        public Node decode(TreeNode root) {
            // get left, then right chain as siblings
            return null;
        }
    }


    Node root;
    Codec codec = new Codec();

    @org.junit.jupiter.api.Test
    public void test1Empty() {
        root = new Node(1, of());
        assertCodec(root);
    }
    @org.junit.jupiter.api.Test
    public void test1Null() {
        root = new Node(1);
        assertCodec(root);
    }

    @org.junit.jupiter.api.Test
    public void test00() {
        root = new Node(1, of(new Node(2)));
        assertCodec(root);
        root = new Node(1, of(new Node(2), new Node(3)));
        assertCodec(root);
    }

    @org.junit.jupiter.api.Test
    public void test1() {
        root = new Node(1, of(
                new Node(2),
                new Node(3, of(
                        new Node(4),
                        new Node(5)))));
        assertCodec(root);
        root = new Node(1, of(
                new Node(2, of(
                        new Node(3),
                        new Node(4))),
                new Node(5)));
        assertCodec(root);
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        root = new Node(1, of(
                new Node(2, of(
                        new Node(3, of(
                                new Node(4), new Node(5))
                        ), new Node(6)))));
        assertCodec(root);
    }

    @org.junit.jupiter.api.Test
    public void testFromLeetCode() {
        root = new Node(1, of(
                new Node(2),
                new Node(3, of(
                        new Node(6),
                        new Node(7, of(
                                new Node(11, of(
                                        new Node(14)
                                ))
                        )))),
                new Node(4, of(
                        new Node(8, of(
                                new Node(12)
                        ))
                )),
                new Node(5, of(
                        new Node(9, of(
                                new Node(13)
                        )),
                        new Node(10)
                ))
        ));
        assertCodec(root);
    }

    void assertCodec(Node root) {
        TreeNode encoded = codec.encode(root);
        System.out.println(encoded);
        Node decoded = codec.decode(encoded);
        assertEquals(root, decoded);
    }

}
