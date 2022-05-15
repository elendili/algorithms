package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// https://leetcode.com/problems/number-of-operations-to-make-network-connected/
public class NumberOfOperationsToMakeNetworkConnected {
    static class Solution {
        int find(int[] parent, int i) {
            if (parent[i] != i) {
                parent[i] = find(parent, parent[i]);
            }
            return parent[i];
        }

        public int makeConnected(final int n,
                                 final int[][] c) {
            if (n == 0 || c.length == 0 || n - 1 > c.length) {
                return -1;
            }
            final int[] parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            int disjointSets = n;
            int extra = 0;
            for (int z = 0; z < 2; z++) {
                for (int i = 0; i < c.length; i++) {
                    int _0 = find(parent, c[i][0]);
                    int _1 = find(parent, c[i][1]);
                    if (_0 != _1) {
                        parent[parent[_0]] = parent[_1]; // union
                        disjointSets--;
                    } else {
                        extra++;
                    }
                }
            }
            int reqLinks = disjointSets - 1;
            return reqLinks;
        }

        @Test
        public void test() {
            Assertions.assertEquals(0, makeConnected(3, new int[][]{{0, 1}, {0, 2}}));
            Assertions.assertEquals(0, makeConnected(3, new int[][]{{1, 0}, {2, 0}}));
            Assertions.assertEquals(1, makeConnected(4, new int[][]{{0, 1}, {0, 2}, {1, 2}}));
            Assertions.assertEquals(2, makeConnected(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}}));
            Assertions.assertEquals(-1, makeConnected(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}}));
            Assertions.assertEquals(0, makeConnected(5, new int[][]{{0, 1}, {0, 2}, {3, 4}, {2, 3}}));
            Assertions.assertEquals(2, makeConnected(7, new int[][]{{1, 3}, {2, 5}, {2, 6}, {3, 4}, {4, 1}, {5, 6}}));
            Assertions.assertEquals(3, makeConnected(8, new int[][]{{0, 1}, {0, 2}, {0, 4}, {1, 2}, {2, 4}, {2, 6}, {4, 6}}));
            Assertions.assertEquals(1, makeConnected(7,
                    new int[][]{{1, 3}, {2, 5}, {1, 2}, {2, 6}, {3, 4}, {4, 1}, {4, 5}, {5, 6}}));
        }
    }
}
