package leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/armstrong-number/description/?envType=study-plan-v2&envId=premium-algo-100
 */
public class ArmstrongNumber_1134 {
    public boolean isArmstrong(int n) {
        int digitsCount = ((int)Math.log10(n)) + 1;
        int nn = n;
        int result = 0;
        while (nn>0) {
            int tenths = nn/10;
            int digit = nn-tenths*10;
            result+=(int)Math.pow(digit, digitsCount);
            nn = nn/10;
        }
        return result == n;
    }

    @org.junit.jupiter.api.Test
    public void test(){
        assertEquals(true, isArmstrong(153));
        assertEquals(false, isArmstrong(123));
    }
}
