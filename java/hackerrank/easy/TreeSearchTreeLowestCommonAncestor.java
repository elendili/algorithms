package hackerrank.easy;

import hierarchy.Node;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static hierarchy.TreeUtils.getTreeRootFromSpacedString;
import static org.junit.Assert.assertEquals;

// https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor/problem
public class TreeSearchTreeLowestCommonAncestor {
    private static LinkedList<Node> backTrace(Node n, Map<Node, Node> backRefs) {
        LinkedList<Node> out = new LinkedList<>();
        out.addFirst(n);
        do {
            n = backRefs.get(n);
            if (n != null) {
                out.addFirst(n);
            }
        } while (backRefs.containsKey(n));
        return out;
    }

    public static Node lca(Node root, int v1, int v2) {
        if (root == null) {
            return null;
        }
        Node node = root;
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.addFirst(node);
        Node n;
        Map<Node, Node> backReferences = new HashMap<>();
        Node v1Node = null, v2Node = null;
        while (!nodes.isEmpty()) {
            node = nodes.pollLast();
            if (node != null) {
                v1Node = (node.data == v1) ? node : v1Node;
                v2Node = (node.data == v2) ? node : v2Node;
                if (v1Node != null && v2Node != null) {
                    break;
                }
            }
            if (!(node.data > v1 && node.data > v2)) {
                n = node.right;
                if (n != null) {
                    nodes.addFirst(n);
                    backReferences.put(n, node);
                }
            }
            if (!(node.data > v1 && node.data > v2)) {
                n = node.left;
                if (n != null) {
                    nodes.addFirst(n);
                    backReferences.put(n, node);
                }
            }
        }
        LinkedList<Node> path1 = backTrace(v1Node, backReferences);
        LinkedList<Node> path2 = backTrace(v2Node, backReferences);
        Node out = null;
        while ((!path1.isEmpty() && !path2.isEmpty()) && path1.peekFirst().equals(path2.peekFirst())) {
            out = path1.pollFirst();
            path2.pollFirst();
        }

        return out;
    }

    public static Node recursiveLca(Node root, int v1, int v2) {
        if (root == null) {
            return null;
        }
        if (root.data > v1 && root.data > v2) {
            return recursiveLca(root.left, v1, v2);
        }
        if (root.data < v1 && root.data < v2) {
            return recursiveLca(root.right, v1, v2);
        }
        return root;
    }

    @Test
    public void testIterable() {
        cases(TreeSearchTreeLowestCommonAncestor::lca);
    }

    @Test
    public void tesRecursive() {
        cases(TreeSearchTreeLowestCommonAncestor::recursiveLca);
    }

    @FunctionalInterface
    public interface FunctionUnderTest<T, U, R> {
        R apply(T t, U u1, U u2);
    }

    public void cases(FunctionUnderTest<Node, Integer, Node> functionUnderTest) {
        Node a = functionUnderTest.apply(getTreeRootFromSpacedString("1 2"), 1, 2);
        assertEquals(1, a.data);
        a = functionUnderTest.apply(getTreeRootFromSpacedString("4 2 3 1 7 6"), 1, 7);
        assertEquals(4, a.data);
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
