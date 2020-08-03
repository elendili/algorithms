package leetcode.top_interview_questions.easy.math;
/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/102/math/745/
Given an integer, write a function to determine if it is a power of three.
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PowerOfThree {
    public boolean isPowerOfThree(int n) {
        // number which is power of 3 should divide bigger power of 3 without remainder
        // we can use biggest possible power of 3 and get modulo by given number
        // 1162261467  - biggest possible power of 3 in int type
        //
        return (n > 0) && (1162261467 % n  == 0);
    }
    @Test
    public void test(){
        Assertions.assertTrue(isPowerOfThree(1));
        Assertions.assertTrue(isPowerOfThree(3));
        Assertions.assertTrue(isPowerOfThree(9));
        Assertions.assertTrue(isPowerOfThree(1162261467)); // biggest possible power of 3 in int type
        Assertions.assertFalse(isPowerOfThree(45));
        Assertions.assertFalse(isPowerOfThree(0));
    }
}


