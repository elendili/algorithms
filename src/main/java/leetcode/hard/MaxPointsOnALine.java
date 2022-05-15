package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine {
    /**
     * iterate  points in pairs in 2 loops,
     * calc line equation for given pair of points
     * line equation y=m*x+b=(y2-y1)/(x2-x1)+b
     * get slope m=(y2-y1)/(x2-x1) and shift
     * shift y=mx+b => b=y-mx =>  b=y1-slope*x1
     * put in map of equation to count of points
     * before next iteration clean the map
     */
    public int maxPoints(int[][] points) {
        int n = points.length;
        int longestLine = 1;
        Map<String, Integer> lineToPoints = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            lineToPoints.clear();
            for (int j = i + 1; j < n; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                String line = getEquationFromPoints(p1, p2);
                int pointsCountOnALine = lineToPoints.compute(line, (k, v) -> v == null ? 2 : v + 1);
                longestLine = Math.max(longestLine, pointsCountOnALine);
            }
        }
        return longestLine;
    }

    /**
     * for preciseness we can use BigDecimal or avoid division
     * let's avoid division
     */
    private String getEquationFromPoints(int[] p1, int[] p2) {
        // slope calculation
        //   y2-y1
        int slope_numerator = p2[1] - p1[1];
        //  (x2-x1)
        int slope_denominator = p2[0] - p1[0];
        int s_gcd = gcd(slope_numerator, slope_denominator);
        if (s_gcd != 0) {
            slope_numerator /= s_gcd;
            slope_denominator /= s_gcd;
        }
        // shift calculation
        // y = slope*x + b
        // b = y - slope*x
        // b = y1 - slope*x1
        // b = y1 - slope_numerator*x1/slope_denominator
        // b = ( y1*slope_denominator - slope_numerator*x1)/slope_denominator
        // b_numerator = ( y1*slope_denominator - slope_numerator*x1)
        // b_denominator = slope_denominator
        // get gcd for both
        int b_numerator = p1[1] * slope_denominator - slope_numerator * p1[0];
        int b_denominator = slope_denominator;
        int b_gcd = gcd(b_numerator, b_denominator);
        if (b_gcd != 0) {
            b_numerator /= b_gcd;
            b_denominator /= b_gcd;
        }
        String out = slope_numerator + "/" + slope_denominator + " + " + b_numerator + "/" + b_denominator;
        return out;
    }

    /**
     * Euclidean gcd
     */
    private int gcd(int remainder, int number) {
        return (remainder == 0) ? number : gcd(number % remainder, remainder);
    }

    @Test
    public void test1() {
        Assertions.assertEquals(1, maxPoints(new int[][]{{1, 1}}));
    }

    @Test
    public void test2() {
        Assertions.assertEquals(2, maxPoints(new int[][]{{1, 1}, {2, 2}}));
    }

    @Test
    public void test3() {
        Assertions.assertEquals(3, maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
    }

    @Test
    public void testNegativePoints() {
        Assertions.assertEquals(4, maxPoints(new int[][]{{-1, -1}, {-2, -2}, {3, 1}, {1, 1}, {-3, -3}}));
    }

    @Test
    public void testHorizontalLine() {
        Assertions.assertEquals(3, maxPoints(new int[][]{{1, 1}, {-2, 1}, {3, 1}, {1, 2}, {3, 4}}));
    }

    @Test
    public void testVerticalLine() {
        Assertions.assertEquals(3, maxPoints(new int[][]{{1, 1}, {1, -2}, {1, 3}, {2, 2}, {3, 4}}));
    }

    @Test
    public void testDescendingLine() {
        Assertions.assertEquals(3, maxPoints(new int[][]{{1, 3}, {2, 2}, {3, 1}, {0, 5}, {-1, 6}}));
    }

    @Test
    public void testDupes() {
        Assertions.assertEquals(3, maxPoints(new int[][]{{2, 2}, {2, 2}, {2, 2}}));
    }

    @Test
    public void test4() {
        Assertions.assertEquals(4, maxPoints(new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}}));
    }

    @Test
    public void testBig() {
        Assertions.assertEquals(2, maxPoints(new int[][]{{0, 0}, {94911151, 94911150}, {94911152, 94911151}}));
    }

}
