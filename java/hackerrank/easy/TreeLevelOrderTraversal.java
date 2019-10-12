package hackerrank.easy;

import hierarchy.Node;
import org.junit.Test;

import java.util.*;

import static hackerrank.TestHelper.assertStdOutAfterStdInput;
import static hierarchy.TreeUtils.getTreeRootFromSystemInput;

// https://www.hackerrank.com/challenges/tree-level-order-traversal/problem
public class TreeLevelOrderTraversal {

    public static void levelOrder(Node root) {
        if (root != null) {
            Node node = root;
            LinkedList<Node> nodes = new LinkedList<>();
            nodes.addFirst(node);

            Node n;
            while (!nodes.isEmpty()) {
                node = nodes.pollLast();
                System.out.print(node.data+" ");

                n = node.left;
                if (n != null) {
                    nodes.addFirst(n);
                }
                n = node.right;
                if (n != null) {
                    nodes.addFirst(n);
                }
            }
        }
    }

    @Test
    public void test() {
        assertStdOutAfterStdInput("6\n1\n2\n5\n3\n6\n4", "1 2 5 3 6 4 ",
                () -> levelOrder(getTreeRootFromSystemInput()));
    }

    @Test
    public void test2() {
        assertStdOutAfterStdInput("7\n10\n12\n12\n3\n4\n5\n6\n", "3 10 12 6 ",
                () -> levelOrder(getTreeRootFromSystemInput()));
    }

    @Test
    public void test4() {
        assertStdOutAfterStdInput("7\n1\n0\n10\n9\n8\n7\n6\n", "6 7 0 1 10 ",
                () -> levelOrder(getTreeRootFromSystemInput()));
    }

    @Test
    public void test3() {
        assertStdOutAfterStdInput("4\n2\n2\n3\n3\n", "2 2 3 ",
                () -> levelOrder(getTreeRootFromSystemInput()));
    }

    @Test
    public void test5() {
        assertStdOutAfterStdInput("116\n" +
                        "37 23 108 59 86 64 94 14 105 17 111 65 55 31 79 97 78 25 50 22 66 46 104 98 81 90 68 40 103 77 74 18 69 82 41 4 48 83 67 6 2 95 54 100 99 84 34 88 27 72 32 62 9 56 109 115 33 15 91 29 85 114 112 20 26 30 93 96 87 42 38 60 7 73 35 12 10 57 80 13 52 44 16 70 8 39 107 106 63 24 92 45 75 116 5 61 49 101 71 11 53 43 102 110 1 58 36 28 76 47 113 21 89 51 19 3",
                "1 2 4 14 23 37 108 111 115 116 83 84 85 ",
                () -> levelOrder(getTreeRootFromSystemInput()));

    }

}
/*

              1
              /\
             0  12
              \
                4
                 \
                  5
                   \
                    6
                    */
