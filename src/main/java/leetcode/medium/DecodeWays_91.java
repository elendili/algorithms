package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/decode-ways/
 */
public class DecodeWays_91 {
    interface DecodeWays {
        int numDecodings(String s);
    }

    // 19 - 2
// 199 - 2
// 111 - 3
    static class DecodeWaysImpl implements DecodeWays {
        @Override
        public int numDecodings(String s) {
            Set<String> letters = new HashSet<>();
            for (int i = 1; i < 26; i++) {
                letters.add("" + i);
            }

            int n = s.length();
            int[] dp = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                // 1 digit case
                String d = s.substring(i - 1, i);
                if (letters.contains(d)) {
                    dp[i] += 1;
                    dp[i] += dp[i - 1];
                }
                // 2 digit case
                if (i > 1) {
                    d = s.substring(i - 2, i);
                    if (letters.contains(d)) {
                        dp[i] += 1;
                        dp[i] += dp[i - 2];
                    }
                }
            }
            // sliding window?
            // dp?
            // get part of string and map to counts?
            // base case is 1 string and count=9
            // 11 -> 2
            // 111 -> 3: 11+1, 1+11, 1+1+1
            // 1111 -> 4: 11+11, 1+11+1, 1+1+11, 11+1+1, 1+1+1+1
            // dp(index) -> is_it_letter?1:0 + dp(index-1)  +  is_1_letter?1:0+dp(index-2)
            return dp[n];
        }

    }

    static class TopDown implements DecodeWays {
        static Set<String> letters;

        static {
            letters = new HashSet<>();
            for (int i = 1; i <= 26; i++) {
                letters.add("" + i);
            }
        }

        @Override
        public int numDecodings(String s) {
            Map<Integer, Integer> memo = new HashMap<>();
            return recurse(0, s, memo);
        }

        int recurse(int index, String s, Map<Integer, Integer> memo) {
            if (index == s.length()) {
                return 1;
            } else if (s.charAt(index) == '0') {
                return 0;
            } else if (!memo.containsKey(index)) {
                int out = recurse(index + 1, s, memo);
                if (index < s.length() - 1 && letters.contains(s.substring(index, index + 2))) {
                    out += recurse(index + 2, s, memo);
                }
                memo.put(index, out);
                return out;
            }
            return memo.get(index);
        }

    }

    static class BottomUp implements DecodeWays {
        @Override
        public int numDecodings(String s) {
            int n = s.length();
            int[] dp = new int[n + 1];
            dp[n] = 1;

            for (int i = n - 1; i > -1; i--) {
                if (s.charAt(i) == '0') {
                    continue; // skip numbers started with 0
                }

                int ans = dp[i + 1]; // propagate combination count from previous

                if (i < n - 1 && Integer.parseInt(s.substring(i, i + 2)) < 27) {
                    ans += dp[i + 2]; // increase combination count adding count from before previous
                }

                dp[i] = ans;
            }
            return dp[0];
        }

    }

    public static Stream<DecodeWays> implementationsSource() {
        return Stream.of(new TopDown(), new BottomUp());
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test1(DecodeWays impl) {
        assertEquals(1, impl.numDecodings("1"));
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test12(DecodeWays impl) {
        assertEquals(2, impl.numDecodings("12"));
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test226(DecodeWays impl) {
        assertEquals(3, impl.numDecodings("226"));
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test29(DecodeWays impl) {
        assertEquals(1, impl.numDecodings("29"));
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test20(DecodeWays impl) {
        assertEquals(1, impl.numDecodings("20"));
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void testNeg(DecodeWays impl) {
        assertEquals(0, impl.numDecodings("06"));
    }

    @ParameterizedTest
    @MethodSource("implementationsSource")
    public void test2125(DecodeWays impl) {
        assertEquals(5, impl.numDecodings("2125"));
        // 2, 12, 5
        // 2, 1, 25
        // 2, 1, 2, 5
        // 21, 2, 5
        // 21, 25
    }
}
