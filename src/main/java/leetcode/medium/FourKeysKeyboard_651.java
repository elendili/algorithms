package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/4-keys-keyboard/?envType=study-plan-v2&envId=premium-algo-100
 *
 * Imagine you have a special keyboard with the following keys:
 *
 * A: Print one 'A' on the screen.
 * Ctrl-A: Select the whole screen.
 * Ctrl-C: Copy selection to buffer.
 * Ctrl-V: Print buffer on screen appending it after what has already been printed.
 * Given an integer n, return the maximum number of 'A' you can print on the screen with at most n presses on the keys.
 */
public class FourKeysKeyboard_651 {
    int maxSize = 0;
    public int maxA(int n) {
        // 2 actions:
        // print letter - weight 1
        // and select-paste - weight 3
        // and paste - weight 1
        recursive(0, 0, n);
        return maxSize;
    }

    private void recursive(int printedSize, int bufferSize, int pressesLeft) {
//        System.out.println("printedSize=" + printedSize + ", bufferSize=" + bufferSize + ", pressesLeft=" + pressesLeft);
        if (pressesLeft == 0) {
            maxSize = Math.max(maxSize, printedSize);
            return;
        }
        // try print 'A'
        recursive(printedSize + 1, bufferSize, pressesLeft - 1);
        // select all
        if (printedSize > 0 && pressesLeft > 2) {
            // copy to buffer and paste
            recursive(printedSize*2, printedSize, pressesLeft - 3);
        }
        if (bufferSize > 0) {
            // paste from buffer
            recursive(printedSize + bufferSize, bufferSize, pressesLeft - 1);
        }
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
    })
    public void test(int n, int expected) {
        assertEquals(expected, maxA(n));
    }
}
