package leetcode.top_interview_questions.easy.dynamicProgramming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/fibonacci-number/
public class TribonacciNumber {
    public static int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        int t0 = 0;
        int t1 = 0;
        int current = 1;
        for (int i = 1; i < n; i++) {
            int oldc = current;
            current = current+t1+t0;
            t0=t1;
            t1=oldc;
        }
        return current;
    }

    @Test
    public void test() {
        assertEquals(0, tribonacci(0));
        assertEquals(1, tribonacci(1));
        assertEquals(1, tribonacci(2));
        assertEquals(2, tribonacci(3));
        assertEquals(4, tribonacci(4));
        assertEquals(7, tribonacci(5));
        assertEquals(13, tribonacci(6));
        assertEquals(1389537, tribonacci(25));
    }
}
