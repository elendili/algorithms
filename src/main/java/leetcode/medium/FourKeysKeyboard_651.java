package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/4-keys-keyboard/?envType=study-plan-v2&envId=premium-algo-100
 * <p>
 * Imagine you have a special keyboard with the following keys:
 * <p>
 * A: Print one 'A' on the screen.
 * Ctrl-A: Select the whole screen.
 * Ctrl-C: Copy selection to buffer.
 * Ctrl-V: Print buffer on screen appending it after what has already been printed.
 * Given an integer n, return the maximum number of 'A' you can print on the screen with at most n presses on the keys.
 */
public class FourKeysKeyboard_651 {
    record BufferKey(int printedSize, int bufferSize, int pressesLeft) {
    }

    static Map<BufferKey, Integer> buffer = new HashMap();

    public int maxA(int n) {
        // 2 actions:
        // print letter - weight 1
        // and select-paste - weight 3
        // and paste - weight 1
        return recursive(0, 0, n);
    }

    private int recursive(int printedSize, int bufferSize, int pressesLeft) {
        BufferKey key = new BufferKey(printedSize, bufferSize, pressesLeft);
        Integer out = buffer.get(key);
        if (out != null) {
            return out;
        }
        if (pressesLeft == 0) {
            out = printedSize;
        } else {
            // try print 'A'
            out = recursive(printedSize + 1, bufferSize, pressesLeft - 1);
            // select all
            if (printedSize > 0 && pressesLeft > 2) {
                // copy to buffer and paste
                out = Math.max(out, recursive(printedSize * 2, printedSize, pressesLeft - 3));
            }
            if (bufferSize > 0) {
                // paste from buffer
                out = Math.max(out, recursive(printedSize + bufferSize, bufferSize, pressesLeft - 1));
            }
        }
        buffer.put(key, out);
        return out;
    }


    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "2, 2",
            "3, 3",
            "4, 4",
            "5, 5",
            "6, 6",
            "7, 9",
            "8, 12",
            "9, 16",
            "10, 20",
            "11, 27",
            "12, 36",
            "13, 48",
            "14, 64",
            "15, 81",
            "16, 108",
            "17, 144",
            "18, 192",
            "19, 256",
            "20, 324",
            "50, 1327104",
//            "60, 1327104",
    })
    public void test(int n, int expected) {
        assertEquals(expected, maxA(n));
        System.out.println(buffer.size());
    }
}
