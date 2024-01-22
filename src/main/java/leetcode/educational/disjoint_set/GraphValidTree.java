package leetcode.educational.disjoint_set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3910/
/*
You have a graph of n nodes labeled from 0 to n - 1.
You are given an integer n and a list of edges where edges[i] = [ai, bi]
indicates that there is an undirected edge between nodes ai and bi in the graph.

Return true if the edges of the given graph make up a valid tree, and false otherwise.
 */

public class GraphValidTree {
    /*

    For the graph to be a valid tree, it must have exactly n - 1 edges. Any less, and it can't possibly be fully connected. Any more, and it has to contain cycles. Additionally, if the graph is fully connected and contains exactly n - 1 edges, it can't possibly contain a cycle, and therefore must be a tree!
     */
    public boolean validTree(int n, int[][] edges) {
        // The tree must contain n - 1 edges.
        if (edges.length != n - 1) {
            return false;
        }
        // count nodes with edges
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            if (!unionFind.union(edge[0], edge[1])) {
                return false;
            }
        }
        return true;
    }


    static class UnionFind {
        // index is node id, value is node's parent id
        final int[] parents;

        UnionFind(int n) {
            this.parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        boolean union(int a, int b) {
            int ra = findRoot(a);
            int rb = findRoot(b);
            if (ra == rb) {
                return false;
            }
            parents[ra] = rb;
            return true;
        }

        private int findRoot(int node) {
            while (parents[node] != node) { // root is when index == index of parent
                node = parents[node];
            }
            return node;
        }
    }


    @Test
    public void test() {
        assertTrue(validTree(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}}));
        assertFalse(validTree(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}));
        assertFalse(validTree(5, new int[][]{{0, 1}, {1, 2}, {3, 4}}));
        assertFalse(validTree(5, new int[][]{{0, 1}, {0, 4}, {1, 4}, {2, 3}}));
    }
}
