package leetcode.medium;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/min-cost-to-connect-all-points/description/
 */
public class MinCostToConnectAllPoints_1584 {

    static int getManhattanDistance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int pointsInMSTCount = 0;
        boolean[] inMST = new boolean[n];
        // PQ contains int[]{cost, index of next point}
        Queue<int[]> sortedEdges = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        sortedEdges.add(new int[]{0, 0}); // zero weight "edge" from first point
        int out = 0;
        while (pointsInMSTCount < n) {
            int[] curEdge = sortedEdges.poll();
            int point = curEdge[1];
            if (inMST[point]) {
                continue;
            }
            inMST[point] = true;
            out += curEdge[0];
            pointsInMSTCount++;
            // iterate over other points, get cost of edge and sort and get edge with min cost
            for (int i = 0; i < n; i++) {
                if (!inMST[i]) {
                    int cost = getManhattanDistance(points[point], points[i]);
                    sortedEdges.add(new int[]{cost, i});
                }
            }
        }

        return out;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(20, minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals(18, minCostConnectPoints(new int[][]{{3, 12}, {-2, 5}, {-4, 1}}));
    }
}
