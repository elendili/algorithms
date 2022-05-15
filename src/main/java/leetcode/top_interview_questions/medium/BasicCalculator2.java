package leetcode.top_interview_questions.medium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/basic-calculator-ii/
 * 1 <= s.length <= 3 * 10^5
 * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * s represents a valid expression.
 * All the integers in the expression are non-negative integers in the range [0, 2^31 - 1].
 * The answer is guaranteed to fit in a 32-bit integer.
 */
public class BasicCalculator2 {

    public int calculate(String string) {
        char[] s = (string+'n').toCharArray(); // added value for last iteration
        int curNumber = 0;
        int lastValue = 0;
        int out = 0;
        char op='+';

        for (char c : s) {
            if (c >= '0' && c <= '9') {
                curNumber = curNumber*10 + (c-'0');
            } else if(c==' '){
                // skip
            } else {
                if(op=='+'){
                    out+=lastValue;
                    lastValue=curNumber;
                } else if (op=='-'){
                    out+=lastValue;
                    lastValue=-curNumber;
                } else if(op=='*'){
                    lastValue=lastValue*curNumber;
                }else if(op=='/'){
                    lastValue=lastValue/curNumber;
                }
                op=c;
                curNumber=0;
            }
        }
        out+=lastValue;
        return out;
    }

    @Test
    public void testFromTestCasesOfSite() {
        assertEquals(4, calculate("1+2*5/3"));
        assertEquals(2, calculate("6/4*2"));
        assertEquals(6, calculate("1+2*5/3+6/4*2"));
    }


    @Test
    public void testFromTestCasesOfSite2() {
        assertEquals(32, calculate("1*2-3/4+5*6"));
        assertEquals(-24, calculate("1*2-3/4+5*6-7*8+9/10"));
    }

    @Test
    public void testFromTaskDescription() {
        assertEquals(7, calculate("3+2*2"));
        assertEquals(1, calculate(" 3/2 "));
        assertEquals(5, calculate(" 3+5 / 2 "));
        assertEquals(4, calculate(" 3+5 / 2-1"));
    }

    @Test
    public void test() {
        assertEquals(1, calculate("1"));
        assertEquals(2, calculate("1+1"));
        assertEquals(0, calculate("1-1"));
    }

    @Test
    public void test2() {
        assertEquals(-1, calculate("1-1-1"));
        assertEquals(2, calculate("1 + 1"));
        assertEquals(3, calculate("4 - 1"));
        assertEquals(3, calculate("4-1"));
        assertEquals(5, calculate("1+4"));
        assertEquals(3, calculate("1+1+1"));
        assertEquals(3, calculate(" 2-1 + 2 "));
    }


}
