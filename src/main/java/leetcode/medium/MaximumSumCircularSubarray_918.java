package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/maximum-sum-circular-subarray/description/
 */
public class MaximumSumCircularSubarray_918 {
    interface MaximumSumCircularSubarray {
        int maxSubarraySumCircular(int[] nums);
    }

    static class MaximumSumCircularSubarrayImpl implements MaximumSumCircularSubarray {
        @Override
        public int maxSubarraySumCircular(int[] nums) {
            // Kadane's algorithm
            int maxSum = nums[0];
            int curMax = maxSum;
            int minSum = nums[0];
            int curMin = minSum;

            int totalSum = nums[0];
            for (int i = 1; i < nums.length; i++) {
                int v = nums[i];
                totalSum += v;

                // Kadane's max
                curMax = Math.max(curMax, 0) + v;
                maxSum = Math.max(maxSum, curMax);

                // Kadane's min
                curMin = Math.min(curMin, 0) + v;
                minSum = Math.min(minSum, curMin);
            }

            if (minSum == totalSum) { // the array contains only negative numbers
                return maxSum; // return at least something maximum
            }
            int circularSum = totalSum - minSum;
            int out = Math.max(circularSum, maxSum);
            return out;
        }

    }

    public static Stream<MaximumSumCircularSubarray> implementationsSource() {
        return Stream.of(new MaximumSumCircularSubarrayImpl());
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test(MaximumSumCircularSubarray impl) {
        assertEquals(3, impl.maxSubarraySumCircular(new int[]{1, -2, 3, -2}));
        assertEquals(10, impl.maxSubarraySumCircular(new int[]{5, -3, 5}));
        assertEquals(-2, impl.maxSubarraySumCircular(new int[]{-3, -2, -3}));
        assertEquals(6, impl.maxSubarraySumCircular(new int[]{1, -2, -3, 5}));
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test6(MaximumSumCircularSubarray impl) {
        assertEquals(6, impl.maxSubarraySumCircular(new int[]{1, -2, 1, -3, 5}));
        assertEquals(6, impl.maxSubarraySumCircular(new int[]{1, -2, 1, 5, -3}));
    }
}
