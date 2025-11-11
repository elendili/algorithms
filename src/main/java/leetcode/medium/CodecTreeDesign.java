package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/?envType=study-plan-v2&envId=premium-algo-100
 */
public class CodecTreeDesign {

    class Codec {
        // Encodes a tree to a single string.
        public String serialize(Node root) {
            if (root == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(root.val);
            List<Node> children = root.children;
            if (children != null) {
                sb.append('[');
                for (int i = 0; i < children.size(); i++) {
                    Node child = children.get(i);
                    sb.append(serialize(child));
                    if (i < children.size() - 1) {
                        sb.append(',');
                    }
                }
                sb.append(']');
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {
            di = 0;
            System.out.println(data);
            return deserializeRecursive(data);
        }

        int di;

        // Decodes your encoded data to tree.
        public Node deserializeRecursive(String data) {
            int si = di;
            while (di < data.length() && Character.isDigit(data.charAt(di))) {
                di++;
            }
            if (si == di) {
                return null;
            }
            final int v = Integer.parseInt(data.substring(si, di));
            final Node node = new Node(v);
            if (di < data.length() && data.charAt(di) == '[') {
                // children
                node.children = new ArrayList<>();
                do {
                    di++;
                    Node child = deserializeRecursive(data);
                    if (child != null) {
                        node.children.add(child);
                    }
                } while (data.charAt(di) == ',');
                di++; // skip ']'
            }
            return node;
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
        assertEquals(root, codec.deserialize(codec.serialize(root)));
    }
}
