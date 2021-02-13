package yandex.Feb52021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MaxWeightOfGoodSetFromNumbers {
    int maxWeightOfGoodSetFromNumbers(int[] nums) {
        Arrays.sort(nums);
        int max = Integer.MIN_VALUE;
        int accSum = 0;
        for (int l = 0, r = 0; r < nums.length; r++) {
            int v = nums[r];
            while (r - l > 1 && nums[l] + nums[l + 1] < v) {
                accSum -= nums[l];
                l++;
            }
            accSum += v;
            max = Math.max(max, accSum);
        }
        return max;
    }

    @Test
    public void test() {
        Assertions.assertEquals(101,
                maxWeightOfGoodSetFromNumbers(new int[]{100, 1, 1, 1}));
        Assertions.assertEquals(101,
                maxWeightOfGoodSetFromNumbers(new int[]{1, 1, 1, 100}));
        Assertions.assertEquals(301,
                maxWeightOfGoodSetFromNumbers(new int[]{100, 1, 100, 1, 100}));
        Assertions.assertEquals(22,
                maxWeightOfGoodSetFromNumbers(new int[]{1, 7, 3, 15, 2, 5, 2, 1, 4}));
    }
}
