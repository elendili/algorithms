package leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/missing-number-in-arithmetic-progression/description/
public class MissingNumberInArithmeticProgression_1228 {
    public int missingNumber(int[] arr) {
        int minDiff = Integer.MAX_VALUE;
        // find min which is actual diff
        for (int i=0; i<2;i++){
            int diff = arr[i+1]-arr[i];
            if (Math.abs(diff)<Math.abs(minDiff)){
                minDiff = diff;
            }
        }
        if (minDiff ==0 ){
            return arr[0];
        }
        for (int i=0; i<arr.length-1;i++){
            int diff = arr[i+1]-arr[i];
            if (diff!=minDiff){
                return arr[i]+minDiff;
            }
        }
        return -1;
    }
    @org.junit.jupiter.api.Test
    public void test(){
        assertEquals(9, missingNumber(new int[]{5,7,11,13}));
        assertEquals(14, missingNumber(new int[]{15,13,12}));
        assertEquals(0, missingNumber(new int[]{0,0,0}));
        assertEquals(43760, missingNumber(new int[]{80387,68178,55969,31551}));
        assertEquals(1, missingNumber(new int[]{1,1,1}));
    }
}
