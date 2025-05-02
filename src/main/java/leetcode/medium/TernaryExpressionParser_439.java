package leetcode.medium;

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
            System.out.println(i+"  "+expression.substring(0,i)+"  "+stack);
            char c = expression.charAt(i);
            if (c == '?') {
                solve = true;
            } else if (c == ':') {
                continue;
            } else if (Character.isDigit(c) || c=='T' || c=='F') {
                stack.push(c);
                if (solve) {
                    solveOnStack(stack);
                    solve = false;
                }
            }
        }
        while(stack.size()>1){
            solveOnStack(stack);
        }
        Character out = stack.pop();
        return out + "";
    }
    
    void solveOnStack(LinkedList<Character> stack) {
        Character condition = stack.peek();
        if (condition== null || condition!='T' && condition!='F') {
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

    @org.junit.jupiter.api.Test
    public void test0() {
        assertEquals("3", parseTernary("3"));
    }

    @org.junit.jupiter.api.Test
    public void test1() {
        assertEquals("2", parseTernary("T?2:3"));
        assertEquals("3", parseTernary("F?2:3"));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals("4", parseTernary("F?1:T?4:5"));
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        assertEquals("F", parseTernary("T?T?F:5:3"));
        assertEquals("5", parseTernary("T?F?F:5:3"));
        assertEquals("3", parseTernary("F?F?F:5:3"));
    }

    @org.junit.jupiter.api.Test
    public void test4() {
        assertEquals("T", parseTernary("T?T:F?1:2"));
    }

    @org.junit.jupiter.api.Test
    public void test5() {
        assertEquals("4", parseTernary("F?T:F?T?1:2:F?3:4"));
        assertEquals("4", parseTernary("F?T:(F?(T?1:2):(F?3:4))"));
    }
    // F?T:F?T?1:2:F?3:4
    // F?T:F?T?1:2:4
    // F?T:F?T?1:2:4
}
