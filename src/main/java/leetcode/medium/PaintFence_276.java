package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/paint-fence
 * <p>
 * You are painting a fence of n posts with k different colors. You must paint the posts following these rules:
 * <p>
 * Every post must be painted exactly one color.
 * There cannot be three or more consecutive posts with the same color.
 * Given the two integers n and k, return the number of ways you can paint the fence.
 */
public class PaintFence_276 {

    interface PaintFence {
        int numWays(int n, int k);
    }

    static class TopDown implements PaintFence {
        int k;

        public int numWays(int n, int k) {
            this.k = k;
            return totalWays(n);
        }

        int totalWays(int i) {
            if (i == 1) {
                return k;
            }
            if (i == 2) {
                return k * k;
            }
            // more than 2, which means i-th color can't be the same
            int prevCount = totalWays(i - 1);
            int prevPrevCount = totalWays(i - 2);
            return (k - 1) * (prevCount + prevPrevCount);
        }
    }

    static class BottomUp implements PaintFence {
        public int numWays(int n, int k) {
            if (n == 1) {
                return k;
            }
            int prevPrev = k;
            int prev = k * k;

            for (int i = 3; i <= n; i++) {
                int cur = (k - 1) * (prev + prevPrev);
                prevPrev = prev;
                prev = cur;
            }
            return prev;
        }

    }

    static Stream<PaintFence> implementationSource() {
        return Stream.of(new TopDown(), new BottomUp());
    }

    @ParameterizedTest
    @MethodSource("implementationSource")
    public void test_3_2(PaintFence impl) {
        assertEquals(1, impl.numWays(1, 1));
        assertEquals(1, impl.numWays(2, 1));
        assertEquals(4, impl.numWays(2, 2));
        assertEquals(6, impl.numWays(3, 2));
        assertEquals(24, impl.numWays(3, 3));
        assertEquals(42, impl.numWays(7, 2));
        assertEquals(2, impl.numWays(1, 2));
    }
}
