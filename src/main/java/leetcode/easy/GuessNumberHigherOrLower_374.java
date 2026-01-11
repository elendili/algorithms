package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * https://leetcode.com/problems/guess-number-higher-or-lower/?envType=study-plan-v2&envId=binary-search
 */
public class GuessNumberHigherOrLower_374 {
    int chosenNumber;

    int guess(int num) {
        return chosenNumber - num;
    }

    public int guessNumber(int n) {
        int l = 1, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int guessResult = guess(mid);
            if (guessResult == 0) {
                return mid;
            } else if (guessResult < 0) {      // num > pick
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """              
                    count | int picked | expected
                    1     | 1          | 1
                    10    | 1          | 1
                    10    | 10         | 10
                    10    | 11         | 10
                    10    | -1         | 1
                    """
    )
    public void test(int count, int picked, int expected) {
        this.chosenNumber = picked;
        Assertions.assertEquals(expected, guessNumber(count));
    }
}
