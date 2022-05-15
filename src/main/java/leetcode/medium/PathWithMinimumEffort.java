package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

// https://leetcode.com/problems/path-with-minimum-effort/
public class PathWithMinimumEffort {
    static class Step implements Comparable<Step> {
        public Step(int x, int y, int diff) {
            this.diff = diff;
            this.x = x;
            this.y = y;
        }

        int diff;// diff with previous cell
        int x, y; // coordinates

        @Override
        public int compareTo(Step o) {
            return diff - o.diff;
        }
    }

    public int minimumEffortPath(int[][] heights) {
        int width = heights.length;
        int height = heights[0].length;
        PriorityQueue<Step> q = new PriorityQueue<>();
        q.add(new Step(0, 0, 0));

        while (!q.isEmpty()) {
            Step s = q.poll();
            if (s.x == width - 1 && s.y == height - 1) {
                return s.diff;
            }
            int h = heights[s.x][s.y];
            if (h > 0) {
                // step left
                int curV = heights[s.x][s.y];
                if (s.x > 0 && heights[s.x - 1][s.y] > 0) {
                    int d = Math.max(s.diff, Math.abs(heights[s.x - 1][s.y] - curV));
                    q.add(new Step(s.x - 1, s.y, d));
                }
                // step right
                if (s.x < width - 1 && heights[s.x + 1][s.y] > 0) {
                    int d = Math.max(s.diff, Math.abs(heights[s.x + 1][s.y] - curV));
                    q.add(new Step(s.x + 1, s.y, d));

                }
                // step up
                if (s.y > 0 && heights[s.x][s.y - 1] > 0) {
                    int d = Math.max(s.diff, Math.abs(heights[s.x][s.y - 1] - curV));
                    q.add(new Step(s.x, s.y - 1, d));
                }
                // step down
                if (s.y < height - 1 && heights[s.x][s.y + 1] > 0) {
                    int d = Math.max(s.diff, Math.abs(heights[s.x][s.y + 1] - curV));
                    q.add(new Step(s.x, s.y + 1, d));
                }
                heights[s.x][s.y] = Integer.MIN_VALUE; // make visited
            }
        }
        return Integer.MIN_VALUE; // unreachable
    }

    @Test
    public void custom() {
        Assertions.assertEquals(0, minimumEffortPath(new int[][]{{1}}));
        Assertions.assertEquals(0, minimumEffortPath(new int[][]{{1}, {1}}));
        Assertions.assertEquals(1, minimumEffortPath(new int[][]{{1}, {2}}));
        Assertions.assertEquals(1, minimumEffortPath(new int[][]{{1, 2}}));
        Assertions.assertEquals(1, minimumEffortPath(new int[][]{{1, 3}, {2, 3}}));
        Assertions.assertEquals(1, minimumEffortPath(new int[][]{{1, 2}, {3, 3}}));
        Assertions.assertEquals(9_999_997, minimumEffortPath(new int[][]{{1, 3}, {1, 10_000_000}}));
    }

    @Test
    public void test0() {
        int[][] a = new int[][]{{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}};
        Assertions.assertEquals(0, minimumEffortPath(a));
    }

    @Test
    public void test1() {
        int[][] a = new int[][]
                {{1, 2, 3}, {3, 8, 4}, {5, 3, 5}};
        Assertions.assertEquals(1, minimumEffortPath(a));
    }

    @Test
    public void test2() {
        int[][] a = new int[][]
                {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        Assertions.assertEquals(2, minimumEffortPath(a));
    }
}
