package leetcode.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static helpers.TestHelper.extract1dIntegerArrayFromBracketedString;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/?envType=study-plan-v2&envId=premium-algo-100
 */
public class CheckIfaNumberIsMajorityElementInaSortedArray_1150 {
    public boolean isMajorityElement(int[] nums, int target) {
        final int indexOfTarget = Arrays.binarySearch(nums, target);
        if (indexOfTarget < 0) {
            return false;
        }
        // find first
        int indexOfFirst = indexOfTarget;
        while (nums[indexOfFirst] == target) {
            int nextIndexOfFirst = Arrays.binarySearch(nums, 0, indexOfFirst, target);
            if (nextIndexOfFirst < 0) {
                break;
            }
            indexOfFirst = nextIndexOfFirst;
        }
        // find last
        int indexOfLast = indexOfTarget;
        while (nums[indexOfLast] == target) {
            int nextIndexOfLast = Arrays.binarySearch(nums, indexOfLast + 1, nums.length, target);
            if (nextIndexOfLast < 0) {
                break;
            }
            indexOfLast = nextIndexOfLast;
        }
        int count = indexOfLast - indexOfFirst + 1;
        return count > nums.length / 2;
    }


    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    expected | n   | nums
                    true     | 5   | [2,4,5,5,5,5,5,6,6]
                    false    | 101 | [10,100,101,101]
                    false    | 3   | [2,4,5,5,5,5,5,6,6]
                    true     | 1   | [1]
                    false    | 1   | [1,2]
                    true     | 1   | [1,1,2]
                    """
    )
    public void test(boolean expected, int n, String nums) {
        assertEquals(expected,
                isMajorityElement(extract1dIntegerArrayFromBracketedString(nums), n));
    }
}
