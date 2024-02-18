package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaximizeDistanceToClosestPerson {

    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int leftManIndex = -1;
        int rightManIndex = -1;
        int start = 0;
        int maxDistance = 0;
        for (int end = 0; end < n; end++) {
            if (seats[end] == 1) {
                if (leftManIndex < 0) {
                    leftManIndex = end;
                }
                rightManIndex = end;
                maxDistance = Math.max(maxDistance, (end - start + 1));
                start = end + 1;
            }
        }
        int from0ToLeftManDistance = leftManIndex;
        int fromEndToRightManDistance = n - rightManIndex - 1;
        int maxDistanceBetweenMen = maxDistance / 2;

        int out = Math.max(Math.max(from0ToLeftManDistance, fromEndToRightManDistance), maxDistanceBetweenMen);
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(1, maxDistToClosest(new int[]{0, 1}));
        Assertions.assertEquals(1, maxDistToClosest(new int[]{1, 0}));
        Assertions.assertEquals(1, maxDistToClosest(new int[]{1, 0, 1}));
        Assertions.assertEquals(2, maxDistToClosest(new int[]{1, 0, 0}));
    }

    @Test
    public void test2() {
        Assertions.assertEquals(3, maxDistToClosest(new int[]{1, 0, 0, 0}));
        Assertions.assertEquals(2, maxDistToClosest(new int[]{1, 0, 0, 0, 1, 0, 1}));
        Assertions.assertEquals(3, maxDistToClosest(new int[]{1, 0, 0, 0, 0, 0, 1}));
        Assertions.assertEquals(5, maxDistToClosest(new int[]{1, 0, 0, 0, 0, 0}));
        Assertions.assertEquals(5, maxDistToClosest(new int[]{0, 0, 0, 0, 0, 1}));
        Assertions.assertEquals(3, maxDistToClosest(new int[]{0, 0, 0, 1, 0, 0}));
        Assertions.assertEquals(2, maxDistToClosest(new int[]{0, 0, 1, 1, 0, 0}));
        Assertions.assertEquals(1, maxDistToClosest(new int[]{1, 0, 1, 1, 0, 1}));
    }
}
