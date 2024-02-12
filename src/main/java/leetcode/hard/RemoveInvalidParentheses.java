package leetcode.hard;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveInvalidParentheses {

    static class InvalidParenthesesRemoval {
        private final String s;
        private HashSet<String> validExpressions = new HashSet<>();
        private StringBuilder expr = new StringBuilder();

        InvalidParenthesesRemoval(String s) {
            this.s = s;
        }

        public List<String> removeInvalidParentheses() {

            int left = 0, right = 0;

            // First, we find out the number of misplaced left and right parentheses.
            for (int i = 0; i < s.length(); i++) {

                // Simply record the left one.
                if (s.charAt(i) == '(') {
                    left++;
                } else if (s.charAt(i) == ')') {
                    // If we don't have a matching left, then this is a misplaced right, record it.
                    right = left == 0 ? right + 1 : right;

                    // Decrement count of left parentheses because we have found a right
                    // which CAN be a matching one for a left.
                    left = left > 0 ? left - 1 : left;
                }
            }
            this.recurse(0, 0, 0, left, right);
            return new ArrayList<>(validExpressions);
        }


        private void recurse(
                int index,
                int openCount,
                int closeCount,
                int openRem,
                int closeRem) {

            // If we reached the end of the string, just check if the resulting expression is
            // valid or not and also if we have removed the total number of left and right
            // parentheses that we should have removed.
            if (index == s.length()) {
                if (openRem == 0 && closeRem == 0) {
                    validExpressions.add(expr.toString());
                }

            } else {
                char character = s.charAt(index);
                int length = expr.length();

                // The discard case. Note that here we have our pruning condition.
                // We don't recurse if the remaining count for that parenthesis is == 0.
                if (character == '(' && openRem > 0) {
                    this.recurse(
                            index + 1,
                            openCount,
                            closeCount,
                            openRem - 1,
                            closeRem);
                } else if (character == ')' && closeRem > 0) {
                    this.recurse(
                            index + 1,
                            openCount,
                            closeCount,
                            openRem,
                            closeRem - 1);
                }

                expr.append(character);

                // Simply recurse one step further if the current character is not a parenthesis.
                if (character != '(' && character != ')') {
                    this.recurse(index + 1, openCount, closeCount, openRem, closeRem);
                } else if (character == '(') {
                    // Consider an opening bracket.
                    this.recurse(index + 1, openCount + 1, closeCount, openRem, closeRem);
                } else if (closeCount < openCount) {
                    // Consider a closing bracket.
                    // not go when closing ")" equal or more than open,
                    // prevent from incorrect expression
                    this.recurse(index + 1, openCount, closeCount + 1, openRem, closeRem);
                }

                // Delete for backtracking.
                expr.deleteCharAt(length);
            }
        }
    }

    public List<String> removeInvalidParentheses(String s) {
        return new InvalidParenthesesRemoval(s).removeInvalidParentheses();
    }

    @Test
    public void test() {
        assertEquals("[()()]", removeInvalidParentheses("())()").toString());
        assertEquals("[()()(), (())()]", removeInvalidParentheses("()())()").toString());
    }

    @Test
    public void test01() {
        assertEquals("[()]", removeInvalidParentheses("())").toString());
        assertEquals("[()]", removeInvalidParentheses("()))").toString());
    }

    @Test
    public void test02() {
        assertEquals("[()]", removeInvalidParentheses("(()").toString());
    }

    @Test
    public void test021() {
        assertEquals("[()]", removeInvalidParentheses("((()").toString());
    }

    @Test
    public void test2() {
        assertEquals("[(a)()(), (a())()]", removeInvalidParentheses("(a)())()").toString());
    }

    @Test
    public void test3() {
        assertEquals("[]", removeInvalidParentheses(")(").toString());
    }

    @Test
    public void test4() {
        assertEquals("[()()]", removeInvalidParentheses("()()").toString());
        assertEquals("[()]", removeInvalidParentheses("()").toString());
    }
}
