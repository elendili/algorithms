package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//https://leetcode.com/problems/single-number
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int out =0 ;
        for(int i :nums){
            out ^=i;
        }
        return out;
    }
    @Test
    public void test() {
        Assertions.assertEquals(2, singleNumber(new int[]{13,13,2,4,4}));
        Assertions.assertEquals(2, singleNumber(new int[]{2}));
        Assertions.assertEquals(0, singleNumber(new int[]{0}));
    }
}
