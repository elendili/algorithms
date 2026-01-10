package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static helpers.TestHelper.extract1dCharArrayFromBracketedString;

/**
 * https://leetcode.com/problems/find-smallest-letter-greater-than-target/?envType=study-plan-v2&envId=binary-search
 */
public class FindSmallestLetterGreaterThanTarget_744 {
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int l = 0, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            char midV = letters[mid];
            if (midV > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        char out;
        out = letters[0];
        if (l < n) {
            for (int i = l; i < n; ) {
                char cc = letters[i];
                if (cc == target) {
                    i++;
                } else {
                    out = cc;
                    break;
                }
            }
        }
        return out;
    }


    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """              
                    expected | letters              | target 
                    c        | ["c","f","j"]        | a 
                    f        | ["c","f","j"]        | c 
                    x        | ["x","x","y","y"]    | z 
                    """
    )
    public void test(char expected, String letters, char target) {
        Assertions.assertEquals(expected, nextGreatestLetter(extract1dCharArrayFromBracketedString(letters), target));
    }

}
