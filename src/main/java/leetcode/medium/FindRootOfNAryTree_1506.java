package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/find-root-of-n-ary-tree/?envType=study-plan-v2&envId=premium-algo-100
 */
public class FindRootOfNAryTree_1506 {
    interface FindRootInterface {
        Node findRoot(List<Node> list);
    }

    static class WithMarkerNode implements FindRootInterface {

        static final Node MARKER = new Node(-1);

        public Node findRoot(List<Node> list) {
        /* iterate over list and save all nodes that are children of every node[i]
         and all nodes in set.
         At the end find the node which is not a child of any node
         It gets O(n) space and O(n) time

        How to achieve with O(1) space and O(n) time?
         somehow mark nodes that are children of node[i]
         then the only left node without mark is the root.
        */
            if (list == null || list.isEmpty()) {
                return null;
            }
            if (list.size() == 1) {
                return list.getLast();
            }
            // Mark children nodes
            for (Node n : list) {
                if (n == null) continue;
                for (Node c : n.children) {
                    if (c != MARKER) {
                        c.children.add(MARKER);
                    }
                }
            }
            // find root and remove marks
            Node root = null;
            for (Node n : list) {
                if (n == null) continue;
                if (n.children.getLast() == MARKER) {
                    n.children.removeLast();
                } else {
                    root = n;
                }
            }
            return root;
        }
    }

    static class WithXoredValue implements FindRootInterface {

        public Node findRoot(List<Node> list) {
        /*  user xor to find node that is not a children of other node
        */
            if (list == null || list.isEmpty()) {
                return null;
            }
            if (list.size() == 1) {
                return list.getLast();
            }
            int xoredValue = 0; // to accumulate values via xor and expose the value which is met only once!
            // Mark children nodes
            for (Node n : list) {
                if (n == null) continue;
                xoredValue ^= n.val;
                for (Node c : n.children) {
                    xoredValue ^= c.val;
                }
            }
            // find root and remove marks
            Node root = null;
            for (Node n : list) {
                if (n == null) continue;
                if (n.val == xoredValue) {
                    return n;
                }
            }
            return root;
        }
    }

    public static Stream<FindRootInterface> source() {
        return Stream.of(new WithMarkerNode(), new WithXoredValue());
    }

    @ParameterizedTest
    @MethodSource("source")
    public void test(FindRootInterface sut) {
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n3 = new Node(3, new ArrayList<>(Arrays.asList(n5, n6)));
        Node n2 = new Node(2);
        Node n4 = new Node(4);
        Node root = new Node(1, new ArrayList<>(Arrays.asList(n3, n2, n4)));
        assertEquals(root, sut.findRoot(Arrays.asList(n4, n2, n3, n6, n5, null, root)));
        assertEquals(root, sut.findRoot(Arrays.asList(root, n2, n3, n6, n5, n4)));
    }

    @ParameterizedTest
    @MethodSource("source")
    public void test2(FindRootInterface sut) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        Node n10 = new Node(10);
        Node n11 = new Node(11);
        Node n12 = new Node(12);
        Node n13 = new Node(13);
        Node n14 = new Node(14);

        n1.children.addAll(Arrays.asList(n2, n3, n4, n5));
        n3.children.addAll(Arrays.asList(n6, n7));
        n7.children.addAll(Arrays.asList(n11));
        n11.children.addAll(Arrays.asList(n14));
        n4.children.addAll(Arrays.asList(n8));
        n8.children.addAll(Arrays.asList(n12));
        n5.children.addAll(Arrays.asList(n9, n10));
        n9.children.addAll(Arrays.asList(n13));

        List<Node> input = Arrays.asList(n3, n2, null, n1, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14);
        assertEquals(n1, sut.findRoot(input));
    }

    @ParameterizedTest
    @MethodSource("source")
    public void test3(FindRootInterface sut) {
        Node n1 = new Node(1);
        List<Node> input = Arrays.asList(n1);
        assertEquals(n1, sut.findRoot(input));
    }


}
