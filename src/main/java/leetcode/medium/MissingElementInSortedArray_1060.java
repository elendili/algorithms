package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static helpers.TestHelper.extract1dIntegerArrayFromBracketedString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MissingElementInSortedArray_1060 {
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = n - 1;

        while (left < right) {
            int mid = right - (right - left) / 2;
            if (nums[mid] - nums[0] - mid < k) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return nums[0] + k + left;
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    expected | k   | nums
                    2        | 1   | [1]
                    3        | 1   | [1,2]
                    5        | 1   | [4,7,9,10]
                    8        | 3   | [4,7,9,10]
                    6        | 3   | [1,2,4]
                    """
    )
    public void test(int expected, int n, String nums) {
        assertEquals(expected,
                missingElement(extract1dIntegerArrayFromBracketedString(nums), n));
    }
}
