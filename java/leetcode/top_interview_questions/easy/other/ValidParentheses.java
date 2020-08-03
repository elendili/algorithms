package leetcode.top_interview_questions.easy.other;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/99/others/721/

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

 */
public class ValidParentheses {

    static char getOpeningParenthesisForClosing(char c){
        switch(c){
            case ')':
                return '(';
            case '}':
                return '{';
            case ']':
                return '[';
        }
       return 0;
    }

    public boolean isValid(String s) {
        if(s.isEmpty()){
            return true;
        }
        Deque<Character> q = new ArrayDeque<>(); // save opening
        for(char c:s.toCharArray()){
            char opening = getOpeningParenthesisForClosing(c);
            if (opening!=0) { // which means it is closing })]
                if(q.isEmpty() || !q.pollFirst().equals(opening)){
                    return false;
                }
            } else {
                q.addFirst(c);
            }
        }
        return q.isEmpty();
    }

    @Test
    public void test() {
        assertTrue(isValid(""));
        assertTrue(isValid("()"));
        assertTrue(isValid("()[]{}"));
        assertTrue(isValid("{[]}"));
        assertTrue(isValid("(((())))()"));

        assertFalse(isValid("("));
        assertFalse(isValid("(]"));
        assertFalse(isValid("([)]"));
    }
}
