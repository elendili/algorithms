package leetcode.hard;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/basic-calculator-iii/description/?envType=study-plan-v2&envId=premium-algo-100
 */
public class BasicCalculatorIII {
    public int calculate(String s) {
        // need to have 2 stacks for values and operators?
        Deque<Integer> vs = new ArrayDeque<>();
        Deque<Character> os = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                vs.push(num);
                while (!os.isEmpty() && (os.peek() == '*' || os.peek() == '/' )) {
                    computeOnStackTop(vs, os);
                }
            } else if (c == '+' || c == '-') {
                while (!os.isEmpty() && (os.peek() == '+' || os.peek() == '-' || os.peek() == '*' || os.peek() == '/')) {
                    computeOnStackTop(vs, os);
                }
                os.push(c);
            } else if (c == '*' || c == '/') {
                os.push(c);
            } else if (c == '(') {
                os.push(c);
            } else if (c == ')') {
                while (!os.isEmpty() && os.peek() != '(') {
                    computeOnStackTop(vs, os);
                }
                os.pop(); // pop '('
                while (!os.isEmpty() && (os.peek() == '*' || os.peek() == '/')) {
                    computeOnStackTop(vs, os);
                }
            }
        }
        while (!os.isEmpty()) {
            computeOnStackTop(vs, os);
        }
        return vs.pop();
    }

    void computeOnStackTop(Deque<Integer> vs, Deque<Character> os) {
        int b = vs.pop();
        int a = vs.pop();
        char op = os.pop();
        int res = 0;
        switch (op) {
            case '+' -> res = a + b;
            case '-' ->  res = a - b;
            case '*' -> res = a * b;
            case '/' -> res = a / b;
        }
        vs.push(res);
    }

    @ParameterizedTest
    @CsvSource( delimiter = '|', value = {
            "1+1                 | 2",
            "6-4/2               | 4",
            "2*(5+5*2)/3+(6/2+8) | 21",
            "2-1+2               | 3",
            "(5-2)*2             | 6",
            "(5-2)*(4-2)         | 6",
            "2*(5-2)             | 6",
            "((7-3)+1)*2         | 10",
            "(((7-3)))           | 4",
            "(1+(4+5+2)-3)+(6+8) | 23",
    })
    public void test(String expression, int expected) {
        assertEquals(expected, calculate(expression));
    }
}
