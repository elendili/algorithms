package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/diameter-of-n-ary-tree/description/?envType=study-plan-v2&envId=premium-algo-100
 */
public class DiameterOfNAryTree_1522 {

    interface FindDiameter {
        int diameter(Node root);
    }

    static class A implements FindDiameter {
        int globalMax;

        public int diameter(Node root) {
            globalMax = 0;
            dfs(root);
            return globalMax - 1;
        }

        int dfs(Node root) {
            if (root.children == null || root.children.isEmpty()) {
                return 1;
            }
            int maxOfChildren = 0;
            int maxOfChildren2 = 0;
            for (Node c : root.children) {
                int cd = dfs(c);
                if (cd >= maxOfChildren) {
                    maxOfChildren2 = maxOfChildren;
                    maxOfChildren = cd;
                } else if (cd>=maxOfChildren2) {
                    maxOfChildren2 = cd;
                }
            }
            // path length which goes up through current
            int connectedChildrenThroughCurrent = maxOfChildren + maxOfChildren2 + 1;
            int childrenAndCurrent = maxOfChildren + 1;
            globalMax = Math.max(globalMax, connectedChildrenThroughCurrent);
            globalMax = Math.max(globalMax, childrenAndCurrent);
//            System.err.println(root.val + ", connectedChildrenThroughCurrent="+connectedChildrenThroughCurrent+", childrenAndCurrent="+childrenAndCurrent);
            return childrenAndCurrent;
        }
    }

    public static Stream<FindDiameter> source() {
        return Stream.of(new A());
    }

    @ParameterizedTest
    @MethodSource("source")
    public void test0(FindDiameter sut) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        n1.children.addAll(List.of(n2, n3));

        assertEquals(2, sut.diameter(n1));
    }

    @ParameterizedTest
    @MethodSource("source")
    public void test01(FindDiameter sut) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        n1.children.addAll(List.of(n2));

        assertEquals(1, sut.diameter(n1));
    }

    @ParameterizedTest
    @MethodSource("source")
    public void test1(FindDiameter sut) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        n1.children.addAll(List.of(n2, n3, n4));
        n3.children.addAll(List.of(n5, n6));

        assertEquals(3, sut.diameter(n1));
    }

    @ParameterizedTest
    @MethodSource("source")
    public void test2(FindDiameter sut) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        n1.children.add(n2);
        n2.children.addAll(List.of(n3, n4));
        n3.children.addAll(List.of(n5));
        n4.children.addAll(List.of(n6));

        assertEquals(4, sut.diameter(n1));
    }

    @ParameterizedTest
    @MethodSource("source")
    public void test3(FindDiameter sut) {
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

        n1.children.addAll(List.of(n2, n3, n4, n5));
        n3.children.addAll(List.of(n6, n7));
        n4.children.addAll(List.of(n8));
        n5.children.addAll(List.of(n9, n10));
        n7.children.addAll(List.of(n11));
        n8.children.addAll(List.of(n12));
        n9.children.addAll(List.of(n13));
        n11.children.addAll(List.of(n14));

        assertEquals(7, sut.diameter(n1));
    }


}
