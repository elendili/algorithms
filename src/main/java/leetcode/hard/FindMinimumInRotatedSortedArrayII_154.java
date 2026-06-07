package leetcode.hard;

import helpers.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FindMinimumInRotatedSortedArrayII_154 {
    public int findMin(int[] nums) {
        int lastV = nums[nums.length - 1];
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int midV = nums[mid];
            if (midV > lastV) {
                l = mid + 1;
                continue;
            }
            int v1 = mid == nums.length - 1 ? midV : nums[mid + 1];
            if (midV == v1) {
                // duplicate
                r = mid;
            } else if (midV < v1) {
                r = mid;
            } else {
                // v0 > v1. break here
                return v1;
            }
        }
        return nums[l];
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    input                 | expected
                    [1,3,5]               | 1
                    [1,1,1]               | 1
                    [1,0,1]               | 0
                    [2,0,1,2]             | 0
                    [2,2,2,0,1]           | 0
                    [2,0,1]               | 0
                    [1,2,0]               | 0
                    [2,2,0,0,1,1]         | 0
                    [2,0,0,0]             | 0
                    [0,0,0,2]             | 0
                    [3,3,3,3,3,3,3,1,3,3] | 1
                    [3,3,3,3,3,3,3,3,1,3] | 1
                    [3,3,3,3,3,3,3,3,3,1] | 1
                    """
    )
    public void test(String input, int expected) {
        int[] a = TestHelper.extract1dIntegerArrayFromBracketedString(input);
        Assertions.assertEquals(expected, findMin(a));
    }
}
