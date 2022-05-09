package leetcode.top_interview_questions.hard;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/basic-calculator/
 * <p>
 * 1 <= s.length <= 3 * 10^5
 * s consists of digits, '+', '-', '(', ')', and ' '.
 * s represents a valid expression.
 * '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
 * '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
 * There will be no two consecutive operators in the input.
 * Every number and running calculation will fit in a signed 32-bit integer.
 */
public class BasicCalculator {

    public int calculate(String s) {
        ArrayDeque<Object> tokens = tokenize(s);
        calculate(tokens);
        return (int) tokens.getFirst();
    }

    public void calculate(ArrayDeque<Object> tokens) {
        int out = 0;
        Object t;
        final char defaultCurOp = '+';
        char curOp = defaultCurOp;
        while (!tokens.isEmpty()
                && !(t = tokens.pollFirst()).equals(')')) {
            if (t.equals('(')) {
                calculate(tokens);
                t = tokens.pollFirst();
            }
            if (t instanceof Integer) {
                if (curOp == '+') {
                    out = out + ((Integer) t);
                } else {
                    out = out - ((Integer) t);
                }
                curOp=defaultCurOp;
            } else {
                curOp = (char) t;
            }
        }
        tokens.push(out);
    }

    ArrayDeque<Object> tokenize(String s) {
        ArrayDeque<Object> out = new ArrayDeque<>();
        StringBuilder digAcc = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                //skip
            } else if (c == '-') {
                if (!digAcc.isEmpty()) { // tokenize number
                    out.add(toInteger(digAcc));
                    out.add(c);
                } else {
                    digAcc.append(c);
                }
            } else if (c >= '0' && c <= '9') {
                digAcc.append(c);
            } else {
                if (!digAcc.isEmpty()) {
                    if (digAcc.length() == 1 && digAcc.charAt(0) == '-') {
                        // minus
                        out.add(digAcc.charAt(0));
                        digAcc.setLength(0);
                    } else {
                        // number
                        out.add(toInteger(digAcc));
                    }
                }
                out.add(c);
            }
        }
        if (!digAcc.isEmpty()) { // tokenize number
            out.add(toInteger(digAcc));
        }
        return out;
    }

    Integer toInteger(StringBuilder sb) {
        Integer out = Integer.valueOf(sb.toString());
        sb.setLength(0);
        return out;
    }

    @Test
    public void testFromSite() {
        assertEquals(List.of('(', 4, '-', '(', 1, '+', '(', 3, ')', -2, ')', -5, ')'),
                tokenize("(4-(1+(3)-2)-5)").stream().toList());
        assertEquals(2, calculate("(4-(0)-2)"));
        assertEquals(-3, calculate("(4-(2)-5)"));
        assertEquals(-3, calculate("(4-(4-2)-5)"));
        assertEquals(-3, calculate("(4-(1+(3)-2)-5)"));
        assertEquals(-15, calculate("1-(3+5-2+(3+19-(3-1-4+(9-4-(4-(1+(3)-2)-5)+8-(3-5)-1)-4)-5)-4+3-9)-4-(3+2-5)-10"));
    }

    @Test
    public void test() {
        assertEquals(-3, calculate("-1-1-1"));
        assertEquals(2, calculate("1 + 1"));
        assertEquals(3, calculate("4 - 1"));
        assertEquals(3, calculate("4-1"));
        assertEquals(3, calculate("-1+4"));
        assertEquals(3, calculate("1+1+1"));
        assertEquals(3, calculate(" 2-1 + 2 "));
        assertEquals(23, calculate("(1+(4+5+2)-3)+(6+8)"));
        assertEquals(3, calculate("5-(1+1)"));
        assertEquals(-2, calculate("-(1+1)"));
        assertEquals(-2, calculate("(-(1+1))"));
        assertEquals(3, calculate("(2+(3))-(1+1)"));
        assertEquals(-3, calculate("((2-3))-(1+1)"));
        assertEquals(0, calculate("(2-2)-((1-1))"));
        assertEquals(-1, calculate("(((-1)))"));
        assertEquals(1, calculate("(((1)))"));
        assertEquals(2, calculate("(1+(3)-2)"));
        assertEquals(2, calculate("(4-(1+(3)-2))"));
        assertEquals(-3, calculate("((1+(3)-2)-5)"));
        assertEquals(2, calculate("(4-(3-1))"));
    }
    @Test
    public void testTokenize() {
        assertEquals(List.of(-123), tokenize("-123").stream().toList());
        assertEquals(List.of('-', '(', 1, '+', 1, ')'), tokenize("-(1 + 1)").stream().toList());
        assertEquals(List.of(1, '+', 1), tokenize("1 + 1").stream().toList());
        assertEquals(List.of(2, '-', 1, '+', 2), tokenize(" 2-1 + 2 ").stream().toList());
        assertEquals(List.of(-1, '-', 1, '-', 1), tokenize("-1-1-1").stream().toList());
        assertEquals(List.of('(', 1, '+', '(', 4, '+', 5, '+', 2, ')', -3, ')', '+', '(', 6, '+', 8, ')'),
                tokenize("(1+(4+5+2)-3)+(6+8)").stream().toList());
    }

}
