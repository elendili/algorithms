package leetcode.educational.disjoint_set;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3845
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 * <p>
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * <p>
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 * <p>
 * Return the total number of provinces.
 */

public class NumberOfProvinces {
    private static class UnionFind {
        private final int[] disjoinedSets;
        private int count;

        UnionFind(int size) {
            this.disjoinedSets = new int[size];
            this.count = size;
            Arrays.fill(disjoinedSets, -1);
        }

        void union(int i, int j) {
            int iRoot = find(i);
            int jRoot = find(j);
            if (iRoot != jRoot) {
                disjoinedSets[jRoot] = iRoot;
                count--;
            }
        }

        private int find(int i) {
            if (disjoinedSets[i] == -1) {
                return i;
            }
            int out = find(disjoinedSets[i]);
            disjoinedSets[i] = out;
            return out;
        }

        private int count() {
            return count;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < disjoinedSets.length; i++) {
                sb.append(i).append(" ").append(disjoinedSets[i]).append("\n");
            }
            return sb.toString();
        }
    }


    public int findCircleNum(int[][] isConnectedArray) {
        if (isConnectedArray == null || isConnectedArray.length == 0) {
            return 0;
        }
        int n = isConnectedArray.length;
        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnectedArray[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.count();
    }

    @Test
    public void testFromLeetCode() {
        int[][] input = new int[][]{
                {1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, // 0,1,7
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 0,1
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 2 <---
                {0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0}, // 3,5,6
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}, // 3,4,9,10
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0}, // 3,4,5,10 --
                {0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0}, // 6,8,13
                {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0}, // 6,7,8,13
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0}, // 6,7,8,13
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1}, // 3,9,11,14
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0}, // 3,4,5,9,10,11
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, // 3,4,5,9,10,11
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, // 12 <---
                {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0}, // 6,7,8,13
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1}  // 9,14
        };
        assertEquals(3, findCircleNum(input));
    }


    @Test
    public void test2() {
        assertEquals(3, findCircleNum(new int[][]{
                {1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 1, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 1, 1, 1},
        }));
    }

    @Test
    public void test1() {
        assertEquals(0, findCircleNum(new int[][]{}));
        assertEquals(2, findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        assertEquals(3, findCircleNum(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}));
        assertEquals(1, findCircleNum(new int[][]{{1, 1, 1}, {0, 1, 0}, {0, 0, 1}}));

        assertEquals(3, findCircleNum(new int[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 1},
                {0, 0, 1, 0},
                {0, 1, 0, 1},
        }));
        assertEquals(4, findCircleNum(new int[][]{
                {1, 0, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1},
        }));
        assertEquals(3, findCircleNum(new int[][]{
                {1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {1, 0, 0, 0, 1},
        }));
        assertEquals(3, findCircleNum(new int[][]{
                {1, 0, 0, 0, 1},
                {0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 0, 0, 0, 1},
        }));
        assertEquals(2, findCircleNum(new int[][]{
                {1, 1, 0, 0, 1},
                {1, 1, 1, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 0, 0, 0, 1},
        }));
        assertEquals(4, findCircleNum(new int[][]{
                {1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 1, 0},
                {0, 0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 1},
        }));
        assertEquals(2, findCircleNum(new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1},
        }));
    }
}
