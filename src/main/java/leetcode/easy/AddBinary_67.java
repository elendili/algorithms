package leetcode.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddBinary_67 {
    public String addBinary(String a, String b) {
        int an = a.length(), bn = b.length();
        int maxLength = Math.max(an, bn);
        char[] outArray = new char[maxLength + 1];
        int carry = 0;
        for (int i = 0; i < maxLength; i++) {
            int av = (i < an && (a.charAt(an - 1 - i) == '1')) ? 1 : 0;
            int bv = (i < bn && (b.charAt(bn - 1 - i) == '1')) ? 1 : 0;
            int locSum = carry + av + bv;
            outArray[maxLength - i] = (char) ('0' + (locSum % 2));
            carry = locSum > 1 ? 1 : 0;
        }
        if (carry == 1) {
            outArray[0] = '1';
            return new String(outArray);
        }
        return new String(outArray, 1, maxLength);
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            textBlock = """
                    a,  b, expected
                    11, 1, 100
                    11, 11, 110
                    10, 10, 100
                    10, 11, 101
                    11, 10, 101
                    101, 100, 1001
                    101, 101, 1010
                    1010, 1011, 10101
                    0, 0, 0
                    0, 1, 1
                    1, 1, 10
                    10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101, 110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011, 110111101100010011000101110110100000011101000101011001000011011000001100011110011010010011000000000
                    """
    )
    public void test(String a, String b, String expected) {
        assertEquals(expected, addBinary(a, b));
    }
}
