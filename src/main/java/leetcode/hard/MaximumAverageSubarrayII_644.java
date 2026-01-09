package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static helpers.TestHelper.extract1dArrayFromBracketedString;

/**
 * https://leetcode.com/problems/maximum-average-subarray-ii/description/?envType=study-plan-v2&envId=premium-algo-100
 */
public class MaximumAverageSubarrayII_644 {
    public double findMaxAverage(int[] nums, int k) {
        double max_average = Integer.MIN_VALUE;
        double min_average = Integer.MAX_VALUE;
        for (int n : nums) {
            max_average = Math.max(max_average, n);
            min_average = Math.min(min_average, n);
        }
        double prev_mid = max_average, diff = Integer.MAX_VALUE;
        while (diff > 1e-5) { // converging possible average values
            double mid = min_average + (max_average - min_average) / 2;
            if (givenAverageIsAchievable(nums, mid, k))
                min_average = mid;
            else
                max_average = mid;
            diff = Math.abs(prev_mid - mid);
            prev_mid = mid;
        }
        return min_average;
    }

    public boolean givenAverageIsAchievable(int[] nums, double expected_average, int k) {
        //Check whether we can find a subarray whose average is bigger than expected_average
        double sum = 0, prev = 0, min_sum = 0;
        for (int i = 0; i < k; i++)
            sum += nums[i] - expected_average;
        if (sum >= 0)
            return true;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - expected_average;
            prev += nums[i - k] - expected_average;
            min_sum = Math.min(prev, min_sum);
            if (sum >= min_sum)
                return true;
        }
        return false;
    }


    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """              
                    expected | nums                 | k 
                    12.75    | [1,12,-5,-6,50,3]    | 4 
                    5        | [5]                  | 1 
                    """
    )
    public void test(double expected, String nums, int k) {
        double actual = findMaxAverage(extract1dArrayFromBracketedString(nums), k);
        Assertions.assertEquals(expected, actual, 1e-5);
    }
}
