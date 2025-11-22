package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/ternary-expression-parser/description/?envType=study-plan-v2&envId=premium-algo-100
 */
public class TernaryExpressionParser_439 {

    public String parseTernary(String expression) {
        LinkedList<Character> stack = new LinkedList<>();
        boolean solve = false;
        for (int i = expression.length() - 1; i > -1; i--) {
//            System.out.println(i + "  " + expression.substring(0, i) + "  " + stack);
            char c = expression.charAt(i);
            if (c == '?') {
                solve = true;
            } else if (c == ':') {
                continue;
            } else if (Character.isDigit(c) || c == 'T' || c == 'F') {
                stack.push(c);
                if (solve) {
                    solveOnStack(stack);
                    solve = false;
                }
            }
        }
        while (stack.size() > 1) {
            solveOnStack(stack);
        }
        Character out = stack.pop();
        return out + "";
    }

    void solveOnStack(LinkedList<Character> stack) {
        Character condition = stack.peek();
        if (condition == null || condition != 'T' && condition != 'F') {
            return;
        }
        condition = stack.pop();
        char trueVal = stack.pop();
        char falseVal = stack.pop();
        if (condition == 'T') {
            stack.push(trueVal);
        } else {
            stack.push(falseVal);
        }
    }

    @ParameterizedTest
    @CsvSource(value = {"3, 3",
            "T?2:3, 2",
            "F?2:3, 3",
            "F?1:T?4:5, 4",
            "T?T?F:5:3, F",
            "T?F?F:5:3, 5",
            "F?F?F:5:3, 3",
            "T?T:F?1:2, T",
            "F?T:F?T?1:2:F?3:4, 4",
            "F?T:(F?(T?1:2):(F?3:4)), 4"
    }
    )
    public void test(String input, String expected) {
        assertEquals(expected, parseTernary(input));
    }

    // F?T:F?T?1:2:F?3:4
    // F?T:F?T?1:2:4
    // F?T:F?T?1:2:4
}
