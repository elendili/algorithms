package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/* https://leetcode.com/problems/house-robber-ii/
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers nums representing the amount of money of each house,
return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.

Example 2:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 3:
Input: nums = [0]
Output: 0

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 1000
 */

// start and end houses are adjacent
public class HouseRobber2 {
    public int rob(int[] a) {
        final int n = a.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return a[0];
        }
        int first = a[0];
        a[0]=0;
        int minusFirstR = simpleRob(a);
        a[0]=first;
        a[n-1]=0;
        int minusLastR = simpleRob(a);
        return Math.max(minusFirstR,minusLastR);
    }

    int simpleRob(int[] a){
        final int n = a.length;
        int accumulatedToMinus1 = 0;
        int accumulatedToMinus2 = 0;
        int accumulatedCur = 0;
        for (int i = 0; i < n; i++) {
            if (accumulatedToMinus1 > accumulatedToMinus2 + a[i]) {
                accumulatedCur = accumulatedToMinus1;
            } else {
                accumulatedCur = accumulatedToMinus2 + a[i];
            }
            // shift
            accumulatedToMinus2 = accumulatedToMinus1;
            accumulatedToMinus1 = accumulatedCur;
        }
        return accumulatedCur;
    }

    @Test
    public void test() {
        Assertions.assertEquals(4, rob(new int[]{1,2,3,1}));
        Assertions.assertEquals(4, rob(new int[]{1,3,2,1}));

        Assertions.assertEquals(0, rob(new int[]{}));
        Assertions.assertEquals(9, rob(new int[]{9}));
        Assertions.assertEquals(9, rob(new int[]{1, 9}));
        Assertions.assertEquals(9, rob(new int[]{1, 9, 1}));
        Assertions.assertEquals(5, rob(new int[]{4, 1, 5}));
        Assertions.assertEquals(9, rob(new int[]{4, 1, 5, 1}));
        Assertions.assertEquals(6, rob(new int[]{4, 1, 1, 5}));
        Assertions.assertEquals(9, rob(new int[]{1, 4, 1, 5}));
        Assertions.assertEquals(9, rob(new int[]{1, 4, 1, 5, 1}));
        Assertions.assertEquals(9, rob(new int[]{4, 1, 1, 5, 1}));
        Assertions.assertEquals(6, rob(new int[]{4, 1, 0, 1, 5}));
        Assertions.assertEquals(6, rob(new int[]{4, 1, 0, 1, 1, 4}));
    }
}
