package leetcode.dynamicProgramming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * You are climbing a staircase.
 * It takes n steps to reach the top.
 * <p>
 * Each time you can either climb 1 or 2 steps.
 * In how many distinct ways can you climb to the top?
 * <p>
 * https://leetcode.com/problems/climbing-stairs/
 * https://leetcode.com/study-plan/dynamic-programming/?progress=rwu3ukr
 *
 * Proof that it's fibonacci:
 *
 /*
 4
 1 1 1 1
 2 1 1
 1 2 1
 1 1 2
 2 2

 5
 1 1 1 1 1
 2 1 1 1
 1 2 1 1
 1 1 2 1
 1 1 1 2
 2 2 1
 2 1 2
 1 2 2

 6
 1 1 1 1 1 1
 2 1 1 1 1
 1 2 1 1 1
 1 1 2 1 1
 1 1 1 2 1
 1 1 1 1 2
 2 2 1 1
 2 1 2 1
 2 1 1 2
 1 2 2 1
 1 2 1 2
 1 1 2 2
 2 2 2
 */
public class ClimbStairs {
    // seems it's fibonacci sequence
    public int climbStairs(int n) {
        int prev = 1;
        int out = 1;
        for (int i = 1; i < n; i++) {
            out = out + prev;
            prev = out - prev;
            // 2
            // i=1: out=2, prev = 1

            // 3
            // i=1: out=2, prev=1
            // i=2: out=3, prev=2

            // 4
            // i=1: out=2, prev=1
            // i=2: out=3, prev=2
            // i=3: out=5, prev=3
        }
        return out;
    }

    @Test
    public void test() {
        assertEquals(1, climbStairs(1));
        assertEquals(2, climbStairs(2));
        assertEquals(3, climbStairs(3));
        assertEquals(5, climbStairs(4));
        assertEquals(8, climbStairs(5));
        assertEquals(13, climbStairs(6));
    }
}
