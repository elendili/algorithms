package leetcode.educational.disjoint_set;

import org.junit.jupiter.api.Test;

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
    // return 1 if joined, 0 otherwise
    private int union(int[] a, int i, int j) {
        int iRoot = find(a, i);
        int jRoot = find(a, j);
        if (iRoot != jRoot) {
            a[j] = i;
            return 1;
        }
        return 0;
    }

    private int find(int[] a, int i) {
        if (i == a[i]) {
            return i;
        }
        return a[i] = find(a, a[i]);
    }

    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }
        int n = isConnected.length;
        int out = n;

        int[] disSets = new int[n];
        for (int i = 0; i < n; i++) {
            disSets[i] = i; // set current as root
        }

        for (int i = 0; i < n; i++) {
            int[] row = isConnected[i];
            for (int j = i + 1; j < n; j++) {
                int v = row[j];
                if (v == 1) {
                    out -= union(disSets, i, j);
                }
            }
        }
        return out;
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
    }
}
