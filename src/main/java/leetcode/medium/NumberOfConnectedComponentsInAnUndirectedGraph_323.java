package leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph_323 {
    public int countComponents(int n, int[][] edges) {
        // use Disjoint Set Union (DSU)
        int out = n;
        int[] vertices = new int[n];
        int[] sizes = new int[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = i;
            sizes[i] = 1;
        }

        for (int i = 0; i < edges.length; i++) {
            boolean joined = merge(vertices, sizes, edges[i][0], edges[i][1]);
            if (joined) {
                out -= 1;
            }
        }
        return out;
    }

    // return true  if a and b were merged -- connected in one graph
    boolean merge(int[] vertices, int[] sizes, int a, int b) {
        int rootA = find(vertices, a);
        int rootB = find(vertices, b);
        if (rootA != rootB) {
            // choose to which root merge
            if (sizes[rootA] > sizes[rootB]) {
                sizes[rootA] += sizes[rootB];
                vertices[rootB] = rootA;
            } else {
                sizes[rootB] += sizes[rootA];
                vertices[rootA] = rootB;
            }
            return true;
        }
        return false;
    }

    // find  root/initial vertex fron which graph started
    int find(int[] vertices, int vertex) {
        if (vertex == vertices[vertex]) {
            return vertex;
        }
        int rootVertex = find(vertices, vertices[vertex]);
        vertices[vertex] = rootVertex; // to make consequent searches having less steps
        return rootVertex;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(2, countComponents(5, new int[][]{{0, 1}, {1, 2}, {3, 4}}));
        assertEquals(1, countComponents(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}}));
        assertEquals(5, countComponents(5, new int[][]{}));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals(1, countComponents(5, new int[][]{{0, 1}, {0, 2}, {1, 2}, {2, 3}, {2, 4}}));
    }
}
