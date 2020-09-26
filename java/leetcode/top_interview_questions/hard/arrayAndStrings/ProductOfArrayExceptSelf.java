package leetcode.top_interview_questions.hard.arrayAndStrings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
https://leetcode.com/explore/interview/card/top-interview-questions-hard/116/array-and-strings/827/

Given an array nums of n integers where n > 1,
return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] a) {
        if (a.length < 2) {
            return a;
        }
        int[] out = new int[a.length];
        out[0] = 1;
        for (int i = 1; i < out.length; i++) {
            out[i] = a[i - 1] * out[i - 1]; // creates list of prefixes
        }
        // [1,2,3,4]
        // [1,1,2,6] first loop
        // [24,12,8,6] second loop and result
        int suffix = a[a.length - 1]; // accumulates suffix
        for (int i = out.length - 2; i > -1; i--) {
            out[i] = suffix * out[i]; // suffix * prefix = production of all elements without current
            suffix *= a[i]; // update for next step
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals(Arrays.asList(24).toString(),
                Arrays.toString(productExceptSelf(new int[]{24})));

        Assertions.assertEquals(Arrays.asList(24, 12, 8, 6).toString(),
                Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));

        Assertions.assertEquals(Arrays.asList(6, 0, 0).toString(),
                Arrays.toString(productExceptSelf(new int[]{0, 2, 3})));

        Assertions.assertEquals(Arrays.asList(2, 3).toString(),
                Arrays.toString(productExceptSelf(new int[]{3, 2})));

        Assertions.assertEquals(Arrays.asList(-8, -12, 6).toString(),
                Arrays.toString(productExceptSelf(new int[]{3, 2, -4})));

        Assertions.assertEquals(Arrays.asList(-8, 12, -6).toString(),
                Arrays.toString(productExceptSelf(new int[]{-3, 2, -4})));
    }

}
