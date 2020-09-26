package leetcode.top_interview_questions.medium.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/114/others/823/

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation:
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22

 */
public class EvaluateReversePolishNotation {

    public int evalRPN(String[] a) {
        if (a.length == 1) {
            return toNumber(a[0]);
        }
        assert a.length % 2 == 1 : "length of expression should be odd";
        return evalRPN(a, new int[]{a.length - 1});
    }

    public int evalRPN(String[] a, int[] indexToRead) {
        final String token = a[indexToRead[0]];
        assert token != null && !token.isEmpty() : "token should be a number or +, -, *, /";
        if (isNumber(token)) {
            return toNumber(token);
        }
        indexToRead[0] -= 1;
        int first = evalRPN(a, indexToRead);
        indexToRead[0] -= 1;
        int second = evalRPN(a, indexToRead);
        return switch (token) {
            case "*" -> second * first;
            case "+" -> second + first;
            case "-" -> second - first;
            case "/" -> second / first;
            default -> throw new AssertionError("Unknown symbol" + token);
        };
    }

    private static boolean isNumber(String s) {
        char lastCharOfToken = s.charAt(s.length() - 1);
        return lastCharOfToken >= '0' && lastCharOfToken <= '9';
    }

    private static int toNumber(String s) {
        int i = 0;
        int out = 0;
        boolean neg = false;
        if (s.charAt(0) == '-') {
            i++;
            neg = true;
        }
        for (; i < s.length(); i++) {
            out *= 10;
            out += s.charAt(i) - '0';
        }
        return neg ? -out : out;
    }

    @Test
    public void single() {
        Assertions.assertEquals(2, evalRPN(new String[]{"2"}));
    }

    @Test
    public void test() {
        Assertions.assertEquals(9, evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        Assertions.assertEquals(6, evalRPN(new String[]{"4", "13", "5", "/", "+"}));
        Assertions.assertEquals(22, evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }

    @Test
    public void _2steps() {
        Assertions.assertEquals(4 + (13 / 5), evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }

    @Test
    public void division() {
        Assertions.assertEquals(2, evalRPN(new String[]{"13", "5", "/"}));
    }

    @Test
    public void add() {
        Assertions.assertEquals(13 + 5, evalRPN(new String[]{"13", "5", "+"}));
    }

    @Test
    public void subtraction() {
        Assertions.assertEquals(13 - 5, evalRPN(new String[]{"13", "5", "-"}));
    }

    @Test
    public void multiplication() {
        Assertions.assertEquals(13 * 5, evalRPN(new String[]{"13", "5", "*"}));
    }
}
