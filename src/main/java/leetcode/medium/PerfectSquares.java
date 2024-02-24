package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

/**
 * https://leetcode.com/problems/perfect-squares/
 */
public class PerfectSquares {

    private static final int[] perfectSquares = new int[101];

    static {
        for (int i = 0; i < perfectSquares.length; i++) {
            perfectSquares[i] = (i + 1) * (i + 1);
        }
    }

    public int numSquares(int n) {
        HashSet<Integer> set = new HashSet<>();
        int level = 0;
        set.add(n);
        while (!set.isEmpty()) {
            level++;
            HashSet<Integer> next_level_set = new HashSet<>();
            for (int remainder : set) {
                int biggestPerfectSquareRoot = (int) Math.sqrt(remainder);
                for (int i = biggestPerfectSquareRoot; i >= 0; i--) {
                    int square = perfectSquares[i];
                    if (remainder == square) {
                        return level;
                    } else {
                        int next = remainder - square;
                        if (next > 0) {
                            next_level_set.add(next);
                        }
                    }
                }
            }
            set = next_level_set;
        }
        return -1; // unreachable
    }


    @Test
    public void test() {
        Assertions.assertEquals(1, numSquares(1));
        Assertions.assertEquals(3, numSquares(12));
        Assertions.assertEquals(2, numSquares(13));
        Assertions.assertEquals(4, numSquares(15));
        Assertions.assertEquals(1, numSquares(16));
        Assertions.assertEquals(2, numSquares(17));
    }

    @Test
    public void test43() {
        Assertions.assertEquals(3, numSquares(43)); // 25+9+9=43
    }

    @Test
    public void test378() {
        Assertions.assertEquals(3, numSquares(378)); //
    }

    @Test
    public void test_7217() {
        Assertions.assertEquals(3, numSquares(7217)); //
    }

    @Test
    public void test_60() {
        // (4^k)(8*m+7)
        // 4(8+7)=60
        Assertions.assertEquals(4, numSquares(60)); //
    }

    @Test
    public void test_9980() {
        // (4^k)(8*m+7)
        // 4(8*311+7)=9980
        Assertions.assertEquals(4, numSquares(9980)); //
    }

    @Test
    public void test_10000() {
        Assertions.assertEquals(1, numSquares(10000)); //
    }
}
