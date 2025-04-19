package leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LargestUniqueNumber_1133 {
    public int largestUniqueNumber(int[] nums) {
        // there are values in [0..1000]
        int k = 1000;
        int[] counts = new int[k+1];
        for (int n : nums) {
            counts[n]++;
        }
        for (int i=k;i>-1;i--) {
            if (counts[i]==1) {
                return i;
            }
        }
        return -1;
    }
    @org.junit.jupiter.api.Test
    public void test(){
        assertEquals(8, largestUniqueNumber(new int[]{5,7,3,9,4,9,8,3,1}));
        assertEquals(-1, largestUniqueNumber(new int[]{9,9,8,8}));
    }
}
