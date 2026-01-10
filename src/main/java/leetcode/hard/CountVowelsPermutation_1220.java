package leetcode.hard;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/count-vowels-permutation/
 * <p>
 * Given an integer n, your task is to count how many strings of length n can be formed under the following rules:
 * <p>
 * Each character is a lower case vowel ('a', 'e', 'integer', 'o', 'u')
 * Each vowel 'a' may only be followed by an 'e'.
 * Each vowel 'e' may only be followed by an 'a' or an 'integer'.
 * Each vowel 'integer' may not be followed by another 'integer'.
 * Each vowel 'o' may only be followed by an 'integer' or a 'u'.
 * Each vowel 'u' may only be followed by an 'a'.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 */
public class CountVowelsPermutation_1220 {
    interface CountVowelsPermutation {
        int countVowelPermutation(int i);
    }

    static class CountVowelsPermutationImpl implements CountVowelsPermutation {
        @Override
        public int countVowelPermutation(int n) {
            // 0 - 'a'
            // 1 - 'e'
            // 2 - 'integer'
            // 3 - 'o'
            // 4 - 'u'
            int divider = (1_000_000_000 + 7);
            long[] current = new long[5], prev = new long[5];
            Arrays.fill(current, 1);

            for (int i = n; i > 1; i--) {
                // from current to prev
                System.arraycopy(current, 0, prev, 0, 5);
                /*
                 * Each character is a lower case vowel ('a', 'e', 'integer', 'o', 'u')
                 * Each vowel 'a' may only be followed by an 'e'.
                 * Each vowel 'e' may only be followed by an 'a' or an 'integer'.
                 * Each vowel 'integer' may not be followed by another 'integer'.
                 * Each vowel 'o' may only be followed by an 'integer' or a 'u'.
                 * Each vowel 'u' may only be followed by an 'a'.
                 * "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua"
                 */
                current[0] = prev[1]; //  "ae"
                current[1] = prev[0] + prev[2]; // "ea", "ei"
                current[2] = prev[0] + prev[1] + prev[3] + prev[4]; // "ia", "ie", "io", "iu"
                current[3] = prev[2] + prev[4]; // "oi", "ou"
                current[4] = prev[0]; // "ua"

                for (int j = 0; j < 5; j++) {
                    current[j]=current[j] % divider;
                }
            }
            // get sum
            long sum = current[0];
            for (int i = 1; i < 5; i++) {
                sum += current[i];
            }
            int out = (int) (sum % divider);
            return out;
        }

    }

    public static Stream<CountVowelsPermutation> implementationsSource() {
        return Stream.of(new CountVowelsPermutationImpl());
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test(CountVowelsPermutation impl) {
        assertEquals(5, impl.countVowelPermutation(1));
        assertEquals(10, impl.countVowelPermutation(2));
        assertEquals(68, impl.countVowelPermutation(5));
        assertEquals(18208803, impl.countVowelPermutation(144));
    }
}
