package leetcode.medium;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumHeightTrees {
    // through topological sorting, remove least connected until central nodes are found
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            if (n == 1) {
                return Arrays.asList(0);
            }
            List<Set<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adjList.add(new HashSet<>());
            }
            for (int[] edge : edges) {
                int a = edge[0];
                int b = edge[1];
                adjList.get(a).add(b);
                adjList.get(b).add(a);
            }

            // init topological sort
            List<Integer> leaves = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (adjList.get(i).size() == 1) {
                    leaves.add(i);
                }
            }
            int remainingNodes = n;
            // when only 2 or 1 nodes are left untrimmed, those are central nodes
            while (remainingNodes > 2) {
                remainingNodes -= leaves.size();
                List<Integer> newLeaves = new ArrayList<>();
                for (Integer leaf : leaves) {
                    // get neighbours of leaves, which closer to center and remove leaf from its references
                    for (int neigh : adjList.get(leaf)) {
                        adjList.get(neigh).remove(leaf);
                        if (adjList.get(neigh).size() == 1) {
                            newLeaves.add(neigh);
                        }
                    }
                }
                leaves = newLeaves;
            }
            return leaves;
        }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals("[1]", findMinHeightTrees(4, new int[][]{
                {1, 0}, {1, 2}, {1, 3}
        }).toString());
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals("[3, 4]", findMinHeightTrees(6, new int[][]{
                {3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}
        }).toString());
    }

    @org.junit.jupiter.api.Test
    public void test0() {
        assertEquals("[0]", findMinHeightTrees(1, new int[][]{}).toString());
    }
}
