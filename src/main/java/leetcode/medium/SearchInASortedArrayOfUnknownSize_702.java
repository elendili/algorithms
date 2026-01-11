package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static helpers.TestHelper.extract1dIntegerArrayFromBracketedString;

public class SearchInASortedArrayOfUnknownSize_702 {
    public int search(final ArrayReader reader, final int target) {
        int l = 0, r = 1;
        while (reader.get(r) <= target) {
            r <<= 1;
        }
        while (l < r) {
            int mid = l + (r - l) / 2;
            int v = reader.get(mid);
            if (v == target) {
                return mid;
            } else if (v == Integer.MAX_VALUE || v > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    record ArrayReader(int[] array) {
        ArrayReader(String s) {
            this(extract1dIntegerArrayFromBracketedString(s));
        }

        int get(int index) {
            if (index < 0 || index >= array.length) {
                return Integer.MAX_VALUE;
            }
            return array[index];
        }
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """              
                    expected | nums                 | k 
                    -1       | [1,2]                | 0 
                    0        | [1,2]                | 1 
                    1        | [1,2]                | 2 
                    -1       | [1,2]                | 3 
                    4        | [-1,0,3,5,9,12]      | 9 
                    -1       | [-1,0,3,5,9,12]      | 2 
                    0        | [2147483646]         | 2147483646 
                    """
    )
    public void test(int expected, String nums, int k) {
        int actual = search(new ArrayReader(nums), k);
        Assertions.assertEquals(expected, actual);
    }
}
