package leetcode.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/self-dividing-numbers/
 */
public class SelfDividingNumbers_728 {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isSelfDividing(i)) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean isSelfDividing(int n) {
        int temp = n;
        while (temp > 0) {
            int digit = temp % 10;
            // 1. Cannot contain 0
            // 2. Must be divisible by each of its digits
            if (digit == 0 || n % digit != 0) {
                return false;
            }
            temp /= 10;
        }
        return true;
    }

    @ParameterizedTest
    @CsvSource({
            "1, 22, '1,2,3,4,5,6,7,8,9,11,12,15,22'",
            "47, 85, '48,55,66,77'"
    })
    public void test(int left, int right, String expectedStr) {
        List<Integer> expected = new ArrayList<>();
        for (String s : expectedStr.split(",")) {
            expected.add(Integer.parseInt(s.trim()));
        }
        assertEquals(expected, selfDividingNumbers(left, right));
    }
}
