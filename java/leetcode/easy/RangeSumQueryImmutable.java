package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/problems/range-sum-query-immutable/
 */
public class RangeSumQueryImmutable {

    static class NumArray {
        int[] cumSum;

        public NumArray(int[] nums) {
            cumSum = nums;
            for (int i = 1; i < nums.length; i++) {
                cumSum[i] += cumSum[i - 1];
            }
        }

        public int sumRange(int i, int j) {
            if (cumSum.length > 0) {
                int leftV = i > 0 ? cumSum[i - 1] : 0;
                return cumSum[j] - leftV;
            }
            return 0;
        }
    }

    @Test
    public void test() {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        Assertions.assertEquals(1, numArray.sumRange(0, 2));
        Assertions.assertEquals(-1, numArray.sumRange(2, 5));
        Assertions.assertEquals(-3, numArray.sumRange(0, 5));
        Assertions.assertEquals(0, numArray.sumRange(1, 4));
        Assertions.assertEquals(0, numArray.sumRange(2, 4));
    }

    @Test
    public void test2() {
        NumArray numArray = new NumArray(new int[]{});
        Assertions.assertEquals(0, numArray.sumRange(0, 0));
    }

}
