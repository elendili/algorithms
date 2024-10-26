package leetcode.medium;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/triangle/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class Triangle_120 {
    /*
    |   2
    |  3 4
    | 6 5 7
    |4 1 8 3
    to every element on the level only [1,2] paths come
    for every level need to preserve paths min sum
    l = i==0?0:l;
    r = (i==level.size()-1)? i-1:i;
    minPrevSum = Math.min(prevSum[l],prevSum[r]) // i-1, i
    curSum[i] = level[i] + minPrevSum
    */

    public int minimumTotal(List<List<Integer>> triangle) {
        final int n = triangle.size();
        final int[] curSums = new int[n];
        final int[] prevSum = new int[n];
        prevSum[0] = triangle.get(0).get(0);
        int levelMin = prevSum[0];
        for (int j = 1; j < n; j++) {
            final List<Integer> level = triangle.get(j);
            levelMin = Integer.MAX_VALUE;
            for (int i = 0; i < level.size(); i++) {
                final int l = i == 0 ? 0 : i - 1;
                final int r = (i == level.size() - 1) ? i - 1 : i;
                final int minPrevSum = Math.min(prevSum[l], prevSum[r]);
                final int curSum = level.get(i) + minPrevSum;
                curSums[i] = curSum;
                levelMin = Math.min(levelMin, curSum);
            }
            System.arraycopy(curSums, 0, prevSum, 0, n);
        }
        return levelMin;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(11, minimumTotal(asList(
                asList(2),
                asList(3, 4),
                asList(6, 5, 7),
                asList(4, 1, 8, 3)
        )));
        assertEquals(0, minimumTotal(asList(
                asList(0),
                asList(3, 0),
                asList(6, 5, 0),
                asList(4, 1, 8, 0)
        )));
        assertEquals(0, minimumTotal(asList(
                asList(0),
                asList(0, 4),
                asList(0, 5, 7),
                asList(0, 1, 8, 3)
        )));
        assertEquals(-89, minimumTotal(asList(
                asList(0),
                asList(0, 4),
                asList(0, 5, 7),
                asList(0, 1, 8, -100)
        )));
        assertEquals(-96, minimumTotal(asList(
                asList(0),
                asList(0, 4),
                asList(0, 5, -100)
        )));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals(-10, minimumTotal(asList(
                asList(-10)
        )));
    }
}
