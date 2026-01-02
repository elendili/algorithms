package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static collections.ArrayUtils.extract1dArrayFromBracketedString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindTheIndexOfTheLargeInteger_1533 {
    interface ArrayReader {
        // Compares the sum of arr[l..r] with the sum of arr[x..y]
        // return 1 if sum(arr[l..r]) > sum(arr[x..y])
        // return 0 if sum(arr[l..r]) == sum(arr[x..y])
        // return -1 if sum(arr[l..r]) < sum(arr[x..y])
        int compareSub(int l, int r, int x, int y);

        // Returns the length of the array
        public int length();
    }

    class ArrayReaderBasic implements ArrayReader {
        int[] a;
        int callCount;

        ArrayReaderBasic(int[] a) {
            this.a = a;
        }

        @Override
        public int compareSub(int l, int r, int x, int y) {
            int ls = getSumForRange(l, r);
            int rs = getSumForRange(x, y);
            int diff = ls - rs;

            int out = Integer.compare(diff, 0);
            System.out.println((++callCount) + ". l=" + l + ", r=" + r + ", x=" + x + ", y=" + y + ":  " + ls + " - " + rs + " -> " + out);
            return out;
        }

        private int getSumForRange(int l, int r) {
            int s = 0;
            for (int i = l; i <= r; i++) {
                s += a[i];
            }
            return s;
        }

        @Override
        public int length() {
            return a.length;
        }
    }

    class Solution {
        public int getIndex(ArrayReader reader) {
            int l = 0, r = reader.length() - 1;
            while (l < r) {
                // consider odd and even count of elements
                boolean evenElementsCount = (r - l + 1) % 2 == 0;
                l = evenElementsCount ? l : l + 1;
                int mid = l + (r - l) / 2;
                int res = reader.compareSub(l, mid, mid + 1, r);
                // left sum minus right sum.
                if (res == 0) {
                    if (!evenElementsCount) {
                        return l - 1;
                    }
                    int countLeft = mid - l + 1;
                    int countRight = r - mid;
                    if (countLeft >= countRight) {
                        // shift right, because left part contains more elements
                        l = mid + 1;
                    } else {
                        // shift left, because right part contains more elements
                        r = mid;
                    }
                } else if (res > 0) {
                    // search in left
                    r = mid;
                } else {
                    // search in right
                    l = mid + 1;
                }
            }
            return l;
        }
    }


    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    expected | nums
                    0        | [2,1]
                    1        | [1,2]
                    0        | [2,1,1]
                    1        | [1,2,1]
                    2        | [1,1,2]
                    1        | [1,2,1,1]
                    2        | [1,1,2,1,1]
                    3        | [1,1,1,2,1]
                    4        | [1,1,1,1,2]
                    4        | [7,7,7,7,10,7,7,7]
                    2        | [6,6,12]
                    20       | [46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,46,57,46,46,46,46]
                    """
    )
    public void test(int expected, String nums) {
        ArrayReader ar = new ArrayReaderBasic(extract1dArrayFromBracketedString(nums));
        assertEquals(expected,
                new Solution().getIndex(ar));
    }
}

