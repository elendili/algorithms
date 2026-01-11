package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static helpers.TestHelper.extract1dIntegerArrayFromBracketedString;

public class KthMissingPositiveNumber_1539 {
    public int findKthPositive(int[] arr, int k) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int missingIntegersBeforeMid = arr[mid] - mid - 1;
            if (missingIntegersBeforeMid < k) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l + k;
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """              
                    expected  | arr               | k
                    2         | [1,3]             | 1
                    9         | [2,3,4,7,11]      | 5
                    6         | [1,2,3,4]         | 2
                    """
    )
    public void test(int expected, String arr, int k) {
        Assertions.assertEquals(expected,
                findKthPositive(extract1dIntegerArrayFromBracketedString(arr), k));
    }
}
