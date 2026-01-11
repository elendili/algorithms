package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * https://leetcode.com/problems/arranging-coins/description/?envType=study-plan-v2&envId=binary-search
 */
public class ArrangingCoins_441 {
    /**
     x             1 1
     xx            2 3
     xxx           3 6
     xxxx          4 10
     xxxxx         5 15
     xxxxxx        6 21         6*6/2=18 + 6/2=21
     xxxxxxx       7 28         7*7/2=24 + (7+1)/2=28
     xxxxxxxx      8 36         8*8/2=32 + 8/2=36
     xxxxxxxxx     9 45         9*9/2=40 + (9+1)/2=45
     xxxxxxxxxx   10 55       10*10/2=50 + 10/2=55
     2a^2=c^2


     x*x//2 + (x+1)//2 = n
     x*x//2 + (x+1)//2 - n = 0

     (x^2 + x + 1)/2 = n
     x^2 + x + 1 = 2*n
     x^2 + x - (2*n-1) = 0
     x(x+1)/2 <= n

     x= (-b+sqrt(b*b-4ac))/2a
     x= (-1+sqrt(1-4+8*n) )))/2
     x = (-1+sqrt(8*n-3))/2
     */
    public int arrangeCoins(int n) {
        return (int) ((Math.sqrt(8.0 * n + 1) - 1) / 2);
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """              
                    num          | expected
                    1            | 1
                    2            | 1
                    3            | 2
                    4            | 2
                    5            | 2
                    6            | 3
                    7            | 3
                    8            | 3
                    9            | 3
                    10           | 4
                    15           | 5
                    """
    )
    public void test(int num, int expected) {
        Assertions.assertEquals(expected, arrangeCoins(num));
    }
}
