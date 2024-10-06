package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleNumberII_137 {
    public int singleNumber(int[] nums) {
        int intSize = 32;
        int out = 0;
        // count set bits in every number
        for (int i = 0; i < intSize; i++) {
            int maskSetBit = 1 << i; //set bit
            // count bits
            int bitsCount = 0;
            for (int n : nums) {
                int maskApplied = n & maskSetBit;
                // need to zero all except set bit
                bitsCount += (maskApplied != 0) ? 1 : 0;
            }
            if (bitsCount % 3 > 0) {
                out = out | (1 << i);
            }
        }
        return out;
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    expected          | numbers
                    3                 | 2, 2, 3, 2
                    99                | 0,1,0,1,0,1,99
                    2147483647        | 2147483647,0,1,0,1,0,1
                    1                 | -1,0,-1,0,-1,0,1
                    -1                | -1,0,1,0,1,0,1
                    -2147483648       | -2147483648,1,1,1
                    """
    )
    public void test(int expected, String numbersString) {
        int[] a = Arrays.stream(numbersString.split(", *"))
                .mapToInt(Integer::parseInt).toArray();
        assertEquals(expected, singleNumber(a));
    }

    public static String binaryString(int i) {
        return String.format("%" + Integer.SIZE + "s", Integer.toBinaryString(i)).replace(' ', '0');
    }
}
