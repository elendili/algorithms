package leetcode.medium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/string-compression/
 */
public class StringCompression {

    public int compress(char[] chars) {
        int n = chars.length;
        if (n < 2) {
            return n;
        }
        int outIndex = 0;
        char prevChar = chars[0];
        int prevCharCount = 1;
        for (int i = 1; i <= n; i++) {
            if (i < n && chars[i] == prevChar) {
                prevCharCount++;
                continue;
            }

            chars[outIndex++] = prevChar;
            if (prevCharCount > 1) {
                outIndex = numberToCharArrayFromIndex(chars, outIndex, prevCharCount);
            }

            if (i < n) {
                prevChar = chars[i];
                prevCharCount = 1;
            }
        }
        return outIndex;
    }

    int numberToCharArrayFromIndex(char[] chars, int outIndex, int number) {
        int numberLength = (int) Math.log10(number);
        int left = number;
        while (numberLength >= 0) {
            int tenInPower = (int) Math.pow(10, numberLength);
            int toWrite = left / tenInPower;
            left = left % tenInPower;
            chars[outIndex++] = (char) ('0' + toWrite);
            numberLength--;
        }
        return outIndex;
    }

    @Test
    public void testNumberToCharArrayFromIndex() {
        char[] a = new char[3];
        assertEquals(3, numberToCharArrayFromIndex(a, 0, 123));
        assertEquals("123", new String(a));
        a = new char[]{'a', 'a', 'a', 'a'};
        assertEquals(4, numberToCharArrayFromIndex(a, 1, 123));
        assertEquals("a123", new String(a));
    }

    @Test
    public void test() {
        char[] input = new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        assertEquals(6, compress(input));
        String actual = new String(input).substring(0, 6);
        assertEquals("a2b2c3", actual);

        input = new char[]{'a'};
        assertEquals(1, compress(input));
        actual = new String(input).substring(0, 1);
        assertEquals("a", actual);

        input = new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        assertEquals(4, compress(input));
        actual = new String(input).substring(0, 4);
        assertEquals("ab12", actual);

    }

}
