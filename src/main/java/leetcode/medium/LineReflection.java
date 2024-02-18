package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * https://leetcode.com/problems/line-reflection/
 */
public class LineReflection {
    public boolean isReflected(int[][] points) {
        if (points.length == 1) {
            return true;
        }
        // point[0] = x, point[1] = y
        // assume vertical line on x=c,
        // then set of original points should be original[0]=c-d, where d is delta
        // then set of reflected points should be reflected[0]=c+d, where d is delta to c
        // 1,2,3,4,5
        // HOW ????????????????
        // map of y-points to list of x points, thus we can find reflection, but how to choose specific x=c
        // iterate x^2 over all x-s in specific y-level and calc differences between them, thus getting sets of reflection lines
        // then need to repeat with other y-levels and check
        // on every y-line there should be 1 or even amount of x-points

        // create y to xs points map
        Map<Integer, List<Integer>> yToXPointsMap = new HashMap<>();
        for (int[] p : points) {
            yToXPointsMap.compute(p[1], (k, v) -> {
                v = v == null ? new ArrayList<>() : v;
                v.add(p[0]);
                return v;
            });
        }

        // iterate points by y-levels
        double assumedReflectionLineX = Double.MAX_VALUE;
        for (Map.Entry<Integer, List<Integer>> e : yToXPointsMap.entrySet()) {
            // check level where y = e.getKey()
            List<Integer> xPoints = e.getValue();
            // sort for search reflection via two pointers from left and right
            Collections.sort(xPoints);
            // iterate x-points from left and right and calc current reflection line x

            for (int leftI = 0, rightI = xPoints.size() - 1; leftI <= rightI; ) {
                // skip left duplicates
                if (leftI != 0 && xPoints.get(leftI).equals(xPoints.get(leftI - 1))) {
                    leftI++;
                    continue;
                }
                int left = xPoints.get(leftI++);

                // skip right duplicates
                if (rightI != xPoints.size() - 1 && xPoints.get(rightI).equals(xPoints.get(rightI + 1))) {
                    rightI--;
                    continue;
                }
                int right = xPoints.get(rightI--);

                double currentReflectionLineX = left + ((double) right - left) / 2;
                if (assumedReflectionLineX == Double.MAX_VALUE) {
                    assumedReflectionLineX = currentReflectionLineX;
                } else if (assumedReflectionLineX != currentReflectionLineX) {
                    return false;
                }
            }
        }
        // here assumedReflectionLineX should have x-coordinate of vertical reflection line, checked on all points
        return true;
    }

    @Test
    public void test() {
        Assertions.assertTrue(isReflected(new int[][]{{0, 0}}));
        Assertions.assertTrue(isReflected(new int[][]{{1, 1}, {-1, 1}}));
        Assertions.assertTrue(isReflected(new int[][]{{1, 1}, {0, 1}, {1, 2}, {0, 2},}));
        Assertions.assertTrue(isReflected(new int[][]{{1, 1}, {0, 1}, {1, 2}, {0, 2}, {2, 3}, {-1, 3}}));
        // xs= 1, 0 . linex=0
        // xs= 2, -1 . linex=(-1+(2--1)/2)=(-1+(3)/2)=(-1+1)=0

        // [[-16,1],[16,1],[16,1]]
        Assertions.assertTrue(isReflected(new int[][]{{1, 1}, {0, 1}, {1, 2}, {0, 2}, {2, 3}, {-1, 3}}));

        Assertions.assertFalse(isReflected(new int[][]{{1, 1}, {-1, -1}}));
        Assertions.assertFalse(isReflected(new int[][]{{1, 1}, {-1, 1}, {1, 2}, {2, 2},}));
    }

    @Test
    public void test2() {
        // [[-16,1],[16,1],[16,1]]
        Assertions.assertTrue(isReflected(new int[][]{{-16, 1}, {16, 1}, {16, 1}}));
    }

    @Test
    public void test3() {
        // [[0,0],[1,0],[3,0]]
        Assertions.assertFalse(isReflected(new int[][]{{0, 0}, {1, 0}, {3, 0}}));
    }
}
