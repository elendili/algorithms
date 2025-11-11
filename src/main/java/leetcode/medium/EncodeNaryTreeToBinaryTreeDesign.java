package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Definition for a Node.
 * https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/?envType=study-plan-v2&envId=premium-algo-100
 */
public class EncodeNaryTreeToBinaryTreeDesign {
    class Codec {
        // Encodes an n-ary tree to a binary tree.
        // rule: own siblings are on right
        // rule: own children are on left
        public TreeNode encode(Node root) {
            return listOfNodesIntoTreeNode(List.of(root));
        }

        TreeNode listOfNodesIntoTreeNode(List<Node> children) {
            if (children == null || children.isEmpty()) {
                return null;
            }
            Node first = children.getFirst();
            TreeNode out = new TreeNode(first.val);
            out.right = listOfNodesIntoTreeNode(children.subList(1, children.size()));
            out.left = listOfNodesIntoTreeNode(first.children);
//            if (first.children!=null && first.children.isEmpty()) {
//                out.val = -out.val;
//            }
            return out;
        }

        // Decodes your binary tree to an n-ary tree.
        public Node decode(TreeNode root) {
            if (root != null) {
                Node out = new Node(root.val);
                List<Node> list = new ArrayList<>();
                gatherListOfChildren(root.left, list);
                if (!list.isEmpty()) {
                    out.children = list;
                }
                return out;
            }
            return null;
        }

        void gatherListOfChildren(TreeNode node, List<Node> list) {
            if (node != null) {
                Node cur = decode(node);
                list.add(cur);
                gatherListOfChildren(node.right, list);
            }
        }
    }


    Codec codec = new Codec();

    @ParameterizedTest
    @MethodSource("nodes")
    void parameterizedTest(Node root) {
        assertCodec(root);
    }

    static Stream<Node> nodes() {
        Stream<Node> out = Stream.of(
//                new Node(1, Collections.singletonList(null)),
                new Node(1),
                new Node(1, of(new Node(2))),
                new Node(1, of(new Node(2), new Node(3))),
                new Node(1, of(
                        new Node(2),
                        new Node(3, of(
                                new Node(4),
                                new Node(5)
                        ))
                )),
                new Node(1, of(
                        new Node(2, of(
                                new Node(3),
                                new Node(4)
                        )),
                        new Node(5)
                )),
                new Node(1, of(
                        new Node(2, of(
                                new Node(3, of(
                                        new Node(4),
                                        new Node(5)
                                )),
                                new Node(6)
                        ))
                )),
                new Node(1, of(
                        new Node(2),
                        new Node(3, of(
                                new Node(6),
                                new Node(7, of(
                                        new Node(11, of(
                                                new Node(14)
                                        ))
                                ))
                        )),
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
                )),
                new Node(1, of(
                        new Node(3, of(
                                new Node(5),
                                new Node(6)
                        )),
                        new Node(2, of()),
                        new Node(4, of())))
        );
        return out;
    }

    void assertCodec(Node root) {
        TreeNode encoded = codec.encode(root);
        System.out.println(encoded);
        Node decoded = codec.decode(encoded);
        assertEquals(root, decoded);
    }

}
