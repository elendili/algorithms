package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * https://leetcode.com/problems/valid-perfect-square/description/?envType=study-plan-v2&envId=binary-search
 */
public class ValidPerfectSquare_367 {
    public boolean isPerfectSquare(int num) {
        // find square root without lib function
        int l = 1, r = num;
        while (l < r) {
            int mid = l + (r - l) / 2;
            long trySquare = (long)mid * mid;
            if (trySquare == num) {
                return true;
            } else if (trySquare > num) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l * l == num;
    }


    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """              
                    num          | expected
                    1            | true
                    2            | false
                    3            | false
                    4            | true
                    5            | false
                    6            | false
                    7            | false
                    8            | false
                    9            | true
                    10           | false
                    11           | false
                    12           | false
                    13           | false
                    14           | false
                    15           | false
                    16           | true
                    2147483647   | false
                    2147395600   | true
                    """
    )
    public void test(int num, boolean expected) {
        // max int = 2147483647
        // 2147483647-(46340^2)
        // 2147395600
        Assertions.assertEquals(expected, isPerfectSquare(num));
    }
}
