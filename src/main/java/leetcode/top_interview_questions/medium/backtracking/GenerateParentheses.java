package leetcode.top_interview_questions.medium.backtracking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/109/backtracking/794/

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

 */

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
         List<String> out = new ArrayList<>();
         recurse(n,n,new StringBuilder(),out);
         return out;
    }

    void recurse(int open,int closed, StringBuilder sb, List<String> out){
        if(open<=0 && closed<=0){
            out.add(sb.toString());
        } else{
            if(open>0){
                sb.append("(");
                recurse(open-1,closed,sb,out);
                sb.deleteCharAt(sb.length()-1);
            }
            if(open<closed){
                sb.append(")");
                recurse(open,closed-1,sb,out);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }

    @Test
    public void test(){
        Assertions.assertEquals("[]",generateParenthesis(0).toString());
        Assertions.assertEquals("[()]",generateParenthesis(1).toString());
        Assertions.assertEquals("[((()))," +
                " (()())," +
                " (())()," +
                " ()(())," +
                " ()()()]",generateParenthesis(3).toString());
    }
}
