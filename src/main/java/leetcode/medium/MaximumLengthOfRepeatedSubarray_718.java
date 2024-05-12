package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/maximum-length-of-repeated-subarray/description/
 */
public class MaximumLengthOfRepeatedSubarray_718 {
    interface MaximumLengthOfRepeatedSubarray {
        int findLength(int[] nums1, int[] nums2);
    }

    static class MaximumLengthOfRepeatedSubarrayImpl implements MaximumLengthOfRepeatedSubarray {
        @Override
        public int findLength(int[] nums1, int[] nums2) {
            int n1 = nums1.length;
            int n2 = nums2.length;
            int[][] dp = new int[n1][n2];
            int maxOut = 0;
            for (int i = 0; i < n1; i++) {
                for (int j = 0; j < n2; j++) {
                    if (nums1[i] == nums2[j]) {
                        int prefixLength = 0;
                        if (i > 0 && j > 0) {
                            prefixLength = dp[i - 1][j - 1];
                        }
                        int v = 1 + prefixLength;
                        maxOut = Math.max(maxOut, v);
                        dp[i][j] = v;
                    }
                }
            }
            return maxOut;
        }

    }

    public static Stream<MaximumLengthOfRepeatedSubarray> implementationsSource() {
        return Stream.of(new MaximumLengthOfRepeatedSubarrayImpl());
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test(MaximumLengthOfRepeatedSubarray impl) {
        assertEquals(3, impl.findLength(
                new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7}
        ));
        assertEquals(3, impl.findLength(
                new int[]{1, 2, 3, 2, 1}, new int[]{1, 1, 2, 3, 7}
        ));
        assertEquals(5, impl.findLength(
                new int[]{0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0}
        ));
    }
}
