package leetcode.hard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int open = 0, close = 0, max = 0;
        // count form left to right
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                open++;
            } else if (c == ')') {
                close++;
            }
            if (open == close) {
                max = Math.max(max, open * 2);
            } else if (close > open) {
                open = close = 0;
            }
        }
        // count form right to left
        open = close = 0;
        for (int i = n - 1; i > -1; i--) {
            char c = s.charAt(i);
            if (c == '(') {
                open++;
            } else if (c == ')') {
                close++;
            }
            if (open == close) {
                max = Math.max(max, open * 2);
            } else if (open > close) {
                open = close = 0;
            }
        }
        return max;
    }

    @Test
    public void test() {
        assertEquals(2, longestValidParentheses("(()"));
        assertEquals(4, longestValidParentheses(")()())"));
        assertEquals(0, longestValidParentheses(""));
        assertEquals(0, longestValidParentheses(")("));
        assertEquals(4, longestValidParentheses("())()()"));
        assertEquals(4, longestValidParentheses("()(()()"));
        assertEquals(6, longestValidParentheses("()()(()()()"));
        assertEquals(10, longestValidParentheses("()(()())()"));

    }

    @Test
    public void test2() {
        assertEquals(2, longestValidParentheses("(()(((()"));
    }
}
