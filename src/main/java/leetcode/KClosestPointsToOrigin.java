package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

// https://leetcode.com/problems/k-closest-points-to-origin/
public class KClosestPointsToOrigin {
    class Solution {
        public int[][] kClosest(int[][] points, int K) {
            int n = points.length;
            if (n == 1 || n == K) {
                return points;
            }
            int[] dists = new int[n];
            for (int i = 0; i < n; i++) {
                int[] d = points[i];
                dists[i] = d[0] * d[0] + d[1] * d[1];
            }
            Arrays.sort(dists);

            int distK = dists[K - 1];
            int[][] out = new int[K][];
            for (int oi=0,i = 0; i < n; i++) {
                int[] d = points[i];
                int di = d[0] * d[0] + d[1] * d[1];
                if (di <= distK) {
                    out[oi++] = points[i];
                }
            }
            return out;
        }
    }

    @Test
    public void test() {
        Solution s = new Solution();
        Assertions.assertArrayEquals(new int[][]{{-2, 2}},
                s.kClosest(new int[][]{{1, 3}, {-2, 2}}, 1));
        Assertions.assertArrayEquals(new int[][]{{3, 3}, {-2, 4}},
                s.kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2));
    }
}
