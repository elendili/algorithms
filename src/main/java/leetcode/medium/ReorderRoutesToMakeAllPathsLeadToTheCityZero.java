package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero
 */
public class ReorderRoutesToMakeAllPathsLeadToTheCityZero {
    public int minReorder(int n, int[][] connections) {
        // create adjacency matrix, matrix of neighbours.
        // if road from A to neighbour B exists among connections mark it as "real"
        // then using DFS or BFS from city 0 need to find all children
        // if road to children goes through oficial road then ok, otherwise increase count of flips

        Map<Integer, List<int[]>> neighbours = new HashMap<>();
        for (int[] connection : connections) {
            int from = connection[0];
            int to = connection[1];
            // road from key city to neighbour city
            neighbours.computeIfAbsent(from, k -> new ArrayList<>()).add(
                    new int[]{to, 1});
            // road from neighbour city to key city
            neighbours.computeIfAbsent(to, k -> new ArrayList<>()).add(
                    new int[]{from, 0});
        }

        // BFS
        int switchCount = 0;
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);
        visited[0] = true;
        while (!q.isEmpty()) {
            Integer c = q.poll();
            List<int[]> cNeigs = neighbours.get(c);
            if (cNeigs != null) {
                for (int[] neiPair : cNeigs) {
                    int city = neiPair[0];
                    int real = neiPair[1];
                    if (!visited[city]) {
                        switchCount += real;    // no need to switch unreal
                        visited[city] = true;
                        q.add(city);
                    }
                }
            }
        }
        return switchCount;
    }


    @Test
    public void test1() {
        Assertions.assertEquals(0, minReorder(3, new int[][]{{1, 0}, {2, 0}}));
    }

    @Test
    public void test2() {
        Assertions.assertEquals(2, minReorder(5, new int[][]{{1, 0}, {1, 2}, {3, 2}, {3, 4}}));
    }

    @Test
    public void test3() {
        Assertions.assertEquals(3, minReorder(6, new int[][]{{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}}));
    }
}
