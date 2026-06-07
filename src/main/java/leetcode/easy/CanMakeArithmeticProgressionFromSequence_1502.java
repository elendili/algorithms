package leetcode.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence/
 */
public class CanMakeArithmeticProgressionFromSequence_1502 {
    public boolean canMakeArithmeticProgression(int[] arr) {
        int n = arr.length;
        if (n < 2) {
            return true;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int x : arr) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }

        int totalDiff = max - min;
        if (totalDiff == 0) {
            return true;
        }
        if (totalDiff % (n - 1) != 0) {
            return false;
        }

        int diff = totalDiff / (n - 1);
        boolean[] seen = new boolean[n];
        for (int x : arr) {
            if ((x - min) % diff != 0) {
                return false;
            }
            int pos = (x - min) / diff;
            if (pos < 0 || pos >= n || seen[pos]) {
                return false;
            }
            seen[pos] = true;
        }
        return true;
    }

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(new int[]{3, 5, 1}, true),
                Arguments.of(new int[]{1, 2, 4}, false),
                Arguments.of(new int[]{0, 0, 0}, true),
                Arguments.of(new int[]{-10, -5, 0, 5, 10}, true),
                Arguments.of(new int[]{1, 5, 10, 14}, false),
                Arguments.of(new int[]{1, 4, 4, 7}, false), // Duplicate case
                Arguments.of(new int[]{1}, true),
                Arguments.of(new int[]{}, true)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void test(int[] arr, boolean expected) {
        assertEquals(expected, canMakeArithmeticProgression(arr));
    }
}
