package leetcode.top_interview_questions.easy.dynamicProgramming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/fibonacci-number/
public class FibonacciNumber {
    public static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        int prev = 0;
        int current = 1;
        for (int i = 1; i < n; i++) {
            current = current + prev;
            prev = current - prev;
        }
        return current;
    }

    @Test
    public void test() {
        assertEquals(0, fib(0));
        assertEquals(1, fib(1));
        assertEquals(1, fib(2));
        assertEquals(2, fib(3));
        assertEquals(3, fib(4));
    }
}
