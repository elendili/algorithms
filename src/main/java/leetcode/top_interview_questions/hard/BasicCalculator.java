package leetcode.top_interview_questions.hard;

import org.junit.jupiter.api.Test;

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
    int charIndex = 0;
    char[] s;
    StringBuilder numberAccum;
    public int calculate(String string) {
        charIndex = 0;
        s = string.toCharArray();
        numberAccum = new StringBuilder();
        return calcRecursive();
    }

    public int calcRecursive() {
        int leftV = 0;
        int rightV;
        char c;
        final char defaultCurOp = '+';
        char curOp = defaultCurOp;

        while (charIndex < s.length
                && (c = s[charIndex++]) != ')') {
            if (c == '(') {
                rightV = calcRecursive();
                leftV = applyCurrentOperation(curOp,leftV,rightV);
                curOp = defaultCurOp;
            } else if (c == '+' || c == '-') {
                rightV = getNumberFromAccumulator();
                leftV = applyCurrentOperation(curOp,leftV,rightV);
                curOp = c;
            } else if (c == ' ') {
                // skip
            } else {
                numberAccum.append(c);
            }
        }

        rightV = getNumberFromAccumulator();
        leftV = applyCurrentOperation(curOp,leftV,rightV);

        return leftV;
    }

    int applyCurrentOperation(char curOp, int leftV, int rightV) {
        return leftV + (curOp == '-' ? -rightV : rightV);
    }

    Integer getNumberFromAccumulator() {
        if (numberAccum.isEmpty()) {
            return 0;
        }
        Integer out = Integer.valueOf(numberAccum.toString());
        numberAccum.setLength(0);
        return out;
    }

    @Test
    public void testFromSite() {
        assertEquals(2, calculate("(4-(0)-2)"));
        assertEquals(-3, calculate("(4-(2)-5)"));
        assertEquals(-3, calculate("(4-(4-2)-5)"));
        assertEquals(-3, calculate("(4-(1+(3)-2)-5)"));
        assertEquals(-15, calculate("1-(3+5-2+(3+19-(3-1-4+(9-4-(4-(1+(3)-2)-5)+8-(3-5)-1)-4)-5)-4+3-9)-4-(3+2-5)-10"));
    }

    @Test
    public void test() {
        assertEquals(2, calculate("1+1"));
        assertEquals(-1, calculate("-1"));
        assertEquals(-2, calculate("-1-1"));
        assertEquals(-3, calculate("-1-1-1"));
        assertEquals(2, calculate("1 + 1"));
        assertEquals(3, calculate("4 - 1"));
        assertEquals(3, calculate("4-1"));
        assertEquals(3, calculate("-1+4"));
        assertEquals(3, calculate("1+1+1"));
        assertEquals(3, calculate(" 2-1 + 2 "));
        assertEquals(-2, calculate("-(1+1)"));
        assertEquals(-1, calculate("-(1)"));
        assertEquals(3, calculate("5-(1+1)"));
        assertEquals(-2, calculate("(-(1+1))"));
        assertEquals(4, calculate("(5)-1"));
        assertEquals(3, calculate("(2+(3))-(1+1)"));
        assertEquals(-3, calculate("((2-3))-(1+1)"));
        assertEquals(0, calculate("(2-2)-((1-1))"));
        assertEquals(-1, calculate("(((-1)))"));
        assertEquals(1, calculate("(((1)))"));
        assertEquals(2, calculate("(1+(3)-2)"));
        assertEquals(2, calculate("(4-(1+(3)-2))"));
        assertEquals(-3, calculate("((1+(3)-2)-5)"));
        assertEquals(2, calculate("(4-(3-1))"));
        assertEquals(23, calculate("(1+(4+5+2)-3)+(6+8)"));
    }


}
