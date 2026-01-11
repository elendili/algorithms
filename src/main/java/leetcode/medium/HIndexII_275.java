package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static helpers.TestHelper.extract1dIntegerArrayFromBracketedString;

/**
 * https://leetcode.com/problems/h-index-ii/description
 */
public class HIndexII_275 {
    // I solved it somehow without much thinking from first run.
    public int hIndex(int[] citations) {
        int n = citations.length;
        int l = 0, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int v = citations[mid];
            int countOnTheRight = n - mid - 1;
            if (v > countOnTheRight) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return n - l;
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """              
                    expected | citations
                    3        | [0,1,3,5,6]   
                    2        | [1,2,100]
                    3        | [1,2,6,7,9]
                    4        | [3,4,5,8,10]
                    3        | [3,3,5,8,25]
                    """
    )
    public void test(int expected, String citations) {
        Assertions.assertEquals(expected, hIndex(extract1dIntegerArrayFromBracketedString(citations)));
    }

}
