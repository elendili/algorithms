package yandex;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;

// it's about whether Levenshtein distance = 1 or not for 2 words.
public class LevenshteinDistance {

    static int calculateLevenshteinDistanceRecursivelyWithMemoization(String x, String y) {
        return calculateLevenshteinDistanceRecursivelyWithMemoization(x, y, 0, 0,
                new int[x.length()][y.length()]);
    }

    static int calculateLevenshteinDistanceRecursivelyWithMemoization(
            String x, String y, int xi, int yi, int[][] memo
    ) {
        if (xi == x.length()) {
            return y.length() - yi;
        }
        if (yi == y.length()) {
            return x.length() - xi;
        }
        if (memo[xi][yi] > 0) {
            return memo[xi][yi] - 1;
        }
        int substitutionCost = x.charAt(xi) == y.charAt(yi) ? 0 : 1;
        int substitution = calculateLevenshteinDistanceRecursivelyWithMemoization(x, y, xi + 1, yi + 1, memo) + substitutionCost;
        int insertion = calculateLevenshteinDistanceRecursivelyWithMemoization(x, y, xi, yi + 1, memo) + 1;
        int deletion = calculateLevenshteinDistanceRecursivelyWithMemoization(x, y, xi + 1, yi, memo) + 1;

        int v = min(substitution, insertion, deletion);
        memo[xi][yi] = v + 1;//add 1 to mark that value saved, so no need to init memo array
        return v;
    }

    static int calculateDynamicProgrammingIterative(String x, String y) {
        int[][] dp = new int[x.length() + 1][y.length() + 1];

        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int substitutionCost = x.charAt(i - 1) == y.charAt(j - 1) ? 0 : 1;
                    dp[i][j] = min(dp[i - 1][j - 1] + substitutionCost,
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
        }
        String print = Arrays.stream(dp).map(a -> Arrays.stream(a).mapToObj(i -> "" + i).collect(joining(","))).collect(joining("\n"));
        System.out.println("-----");
        System.out.println(print);
        System.out.println("-----");
        return dp[x.length()][y.length()];
    }

    public static int min(int... numbers) {
        IntStream.of(1,2,3).min();
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }

    @Test
    public void levenshteinDistance() {
        assertEquals(0, calculateLevenshteinDistanceRecursivelyWithMemoization("", ""));
        assertEquals(1, calculateLevenshteinDistanceRecursivelyWithMemoization("", "a"));
        assertEquals(1, calculateLevenshteinDistanceRecursivelyWithMemoization("a", ""));

        assertEquals(1, calculateLevenshteinDistanceRecursivelyWithMemoization("a", "b"));
        assertEquals(0, calculateLevenshteinDistanceRecursivelyWithMemoization("ab", "ab"));
        assertEquals(1, calculateLevenshteinDistanceRecursivelyWithMemoization("ab", "ac"));
        assertEquals(1, calculateLevenshteinDistanceRecursivelyWithMemoization("ab", "a"));
        assertEquals(1, calculateLevenshteinDistanceRecursivelyWithMemoization("a", "ab"));


        assertEquals(2, calculateLevenshteinDistanceRecursivelyWithMemoization("", "ab"));
        assertEquals(2, calculateLevenshteinDistanceRecursivelyWithMemoization("ab", ""));
        assertEquals(2, calculateLevenshteinDistanceRecursivelyWithMemoization("ab", "cc"));
        assertEquals(2, calculateLevenshteinDistanceRecursivelyWithMemoization("abc", "a"));
        assertEquals(2, calculateLevenshteinDistanceRecursivelyWithMemoization("a", "abc"));
        assertEquals(4, calculateLevenshteinDistanceRecursivelyWithMemoization("dcba", "abcd"));
    }
}
