package leetcode.hard;

import helpers.LeetcodeApplicationTestWrapperHelper;

public class DesignGraphWithShortestPathCalculator {

    static class Graph {

        public Graph(int n, int[][] edges) {

        }

        public void addEdge(int[] edge) {

        }

        public int shortestPath(int node1, int node2) {
            return 1;
        }
    }

    @org.junit.jupiter.api.Test
    public void test(){


        String input = "[\"Graph\", \"shortestPath\", \"shortestPath\", \"addEdge\", \"shortestPath\"]\n" +
        "[[4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]], [3, 2], [0, 3], [[1, 3, 4]], [0, 3]]\n" +
        "Output\n" +
        "[null, 6, -1, null, 6]";
        LeetcodeApplicationTestWrapperHelper.from(input).execute();

    }
}
