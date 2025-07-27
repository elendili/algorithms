package leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/confusing-number/?envType=study-plan-v2&envId=premium-algo-100
 */
public class ConfusingNumber_1056 {
    private static final int[] confusingDigits = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
    public boolean confusingNumber(final int n) {
        // Copy of the original number to process its digits
        int _n = n;
        // This will hold the rotated (confusing) number
        int confusingNumber = 0;
        // Process each digit from right to left
        while(_n > 0){
            int digit = _n % 10; // Extract the last digit
            int confusingDigit = confusingDigits[digit]; // Get the rotated digit
            if (confusingDigit == -1) {
                // If the digit cannot be rotated, it's not a confusing number
                return false;
            } else {
                // Build the rotated number in reverse order
                confusingNumber = confusingNumber * 10 + confusingDigit;
            }
            _n /= 10; // Move to the next digit
        }
        // The number is confusing if the rotated number is different from the original
        return n != confusingNumber;
    }

    @org.junit.jupiter.api.Test
    public void test(){
        assertEquals(true, confusingNumber(6));
        assertEquals(true, confusingNumber(89));
        assertEquals(false, confusingNumber(11));
        assertEquals(true, confusingNumber(8000));
    }
}
