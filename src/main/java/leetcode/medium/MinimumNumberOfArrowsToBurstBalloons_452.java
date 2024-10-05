package leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumNumberOfArrowsToBurstBalloons_452 {
    final Comparator<int[]> comparatorByXEnd = Comparator.comparingInt(a -> a[1]);

    public int findMinArrowShots(int[][] points) {
        // sort by end point
        Arrays.sort(points, comparatorByXEnd);
        int arrows = 1;
        int coveringBalloonEnd = points[0][1];
        // iterate over points, increase arrows count
        // only when coveringBalloonEnd < start of next balloon
        for (int[] ps : points) {
            int xStart = ps[0];
            int xEnd = ps[1];
            if (coveringBalloonEnd < xStart) {
                arrows += 1;
                coveringBalloonEnd = xEnd;
            }
        }
        return arrows;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(2,
                findMinArrowShots(new int[][]{
                        {10, 16},
                        {2, 8},
                        {1, 6},
                        {7, 12}
                }));
        assertEquals(4,
                findMinArrowShots(new int[][]{
                        {1, 2},
                        {3, 4},
                        {5, 6},
                        {7, 8}
                }));
        assertEquals(2,
                findMinArrowShots(new int[][]{
                        {1, 2},
                        {2, 3},
                        {3, 4},
                        {4, 5}
                }));
    }
}
