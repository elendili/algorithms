package leetcode.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/find-the-pivot-integer/
 */
public class FindThePivotInteger_2485 {

    /**
     * Finds the pivot integer x such that sum(1..x) == sum(x..n).
     * Sum(1..x) = x * (x + 1) / 2
     * Sum(x..n) = Sum(1..n) - Sum(1..x-1) = n * (n + 1) / 2 - (x - 1) * x / 2
     * 
     * Equilibrium: x * (x + 1) / 2 = n * (n + 1) / 2 - x * (x - 1) / 2
     * multiplying by 2: x^2 + x = n^2 + n - (x^2 - x)
     * x^2 + x = n^2 + n - x^2 + x
     * 2 * x^2 = n^2 + n
     * x^2 = (n^2 + n) / 2
     */
    public int pivotInteger(int n) {
        int sumTotal = n * (n + 1) / 2;
        int x = (int) Math.sqrt(sumTotal);
        if (x * x == sumTotal) {
            return x;
        }
        return -1;
    }

    @ParameterizedTest
    @CsvSource({
            "8, 6",
            "1, 1",
            "4, -1"
    })
    public void test(int n, int expected) {
        assertEquals(expected, pivotInteger(n));
    }
}
