package leetcode.top_interview_questions.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/* https://leetcode.com/explore/featured/card/top-interview-questions-easy/97/dynamic-programming/569/
You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

My note: it's a fibonacci sequence
And I wonder why it's the fibonacci sequence?)

*/
public class ClimbingStairs {

    static int stepPerms(int n) {
        if (n < 2) {
            return 1;
        }
        int out = 2, n_1 = 2, n_2 = 1;
        for (int i = 0; i < n - 2; i++) {
            out = n_1 + n_2;
            n_2 = n_1;
            n_1 = out;
        }
        return out;
    }

    public int climbStairs(int n) {
        return stepPerms(n);
    }

    @Test
    public void test(){
        Assertions.assertEquals(2, climbStairs(2));
        Assertions.assertEquals(3, climbStairs(3));
        Assertions.assertEquals(5, climbStairs(4));
        Assertions.assertEquals(8, climbStairs(5));
}

}
