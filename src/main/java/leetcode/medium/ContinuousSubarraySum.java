package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/continuous-subarray-sum/
 */
public class ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {

        /*
        new int[]{23, 2, 4, 6, 7}, 6));
        23,25,29,35,42
        5,  1, 5, 5, 0

        new int[]{23, 2, 6, 4, 7}, 6));
        23,25,31,35,42
        5,  1, 1, 5, 0
        */
        int n = nums.length;
        int modSum = 0;
        Map<Integer, Integer> prevSumIndexes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            modSum += nums[i];
            modSum %= k;
            // base case: current mod sum from the nums beginning is 0 (divisible by k)
            if (modSum == 0 && i > 0) {
                return true;
            }
            // whether before we met same current mod sum?
            // 
            // if yes, then range [prevSumIndexes[modSum]+1, i] contains sum divisible by k
            // example:
            // new int[]{23, 2, 6, 4, 7}, 6));
            //           23,25,31,35,42
            //           5,  1, 1, 5, 0
            // 2+6+4=12 and
            //
            // new int[]{23, 2, 4, 6, 7}, 6));
            //           23,25,29,35,42
            //           5,  1, 5, 5, 0
            // need to check that distance between cur and before at least 2

            Integer prevSumIndex = prevSumIndexes.get(modSum);
            if (prevSumIndex != null && i - prevSumIndex > 1) {
                return true;
            }
            // save found sum
            if (prevSumIndex == null) {
                prevSumIndexes.put(modSum, i);
            }
        }

        return false;
    }

    @Test
    public void test() {
        Assertions.assertTrue(checkSubarraySum(new int[]{1, 2, 3}, 5));
        Assertions.assertTrue(checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6));
        Assertions.assertTrue(checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6));
        Assertions.assertFalse(checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 13));
    }
}
