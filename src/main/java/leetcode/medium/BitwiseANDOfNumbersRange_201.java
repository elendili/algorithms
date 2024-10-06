package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitwiseANDOfNumbersRange_201 {
    public int rangeBitwiseAnd(int left, int right) {
        /*
        // bit is set only when all numbers in range have this bit set
        // how to define whether bit is set or not for numbers in range?
        // 1 bit switches every 2 time
        // 2 bit switches every 4 time
        // 0 0 0 0      - 0
        // 0 0 0 1      - 1
        // 0 0 1 0      - 2
        // 0 0 1 1      - 3
        // 0 1 0 0      - 4
        // 0 1 0 1      - 5
        // 0 1 1 0      - 6
        // 0 1 1 1      - 7
        // 1 0 0 0      - 8
        // 1 0 0 1      - 9
        // 1 0 1 0      - 10
        // 1 0 1 1      - 11
        // 1 1 0 0      - 12
        // 1 1 0 1      - 13
        // 1 1 1 0      - 14
        // 1 1 1 1      - 15

        [2..4]
        diff = (4-2+1)     = 3
        if diff (r-l+1) more than 1^(bit index) than should be 0, othwerwise check that bits at bit index of both left and right are 1

        // 4 bit switches every 4 time
        */
        int out = 0;
        long diff = right - left;
        for (int i = 0; i < 32; i++) {
            int mask = 1 << i;
            boolean leftBitSet = (left & mask) != 0;
            boolean rightBitSet = (right & mask) != 0;
            if (leftBitSet & rightBitSet) {
                if (diff < mask) {
                    out |=mask;
                }
            }
        }
        return out;
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    left          | right           | expected
                    1             | 1               | 1
                    4             | 4               | 4
                    5             | 7               | 4
                    4             | 7               | 4
                    8             | 15              | 8
                    0             | 0               | 0
                    1             | 2147483647      | 0
                    -2147483648   | 2147483647      | 0
                    """
    )
    public void test(int left, int right, int expected) {
        assertEquals(expected, rangeBitwiseAnd(left, right));
    }
}
