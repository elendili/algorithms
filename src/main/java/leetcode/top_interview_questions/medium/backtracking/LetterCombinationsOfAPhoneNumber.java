package leetcode.top_interview_questions.medium.backtracking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/109/backtracking/793/
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class LetterCombinationsOfAPhoneNumber {
    private static final String[] mapping = new String[]{
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz",
    };

    public List<String> letterCombinations(String digits) {
        if(digits==null || digits.isEmpty()){
            return Collections.emptyList();
        }
        List<String> out = new ArrayList<>();
        StringBuilder sb = new StringBuilder(digits.length());
        recursive(digits,0,sb,out);
        return out;
    }

    private void recursive(String digits, int index, StringBuilder sb, List<String> out) {
        if(index >= digits.length()){
            out.add(sb.toString());
        } else {
            String letters = mapping[ digits.charAt(index)-'0' ];
            if(!letters.isEmpty()){
                for (char c:letters.toCharArray()){
                    sb.append(c);
                    recursive(digits,index+1,sb,out);
                    sb.deleteCharAt(index);
                }
            }
        }
    }

    @Test
    public void test(){
        check("23", Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));

        check("",emptyList());
        check("2",asList("a", "b", "c"));
        check("9",asList("w", "x", "y", "z"));
        check("1",emptyList());

        Assertions.assertEquals(Math.pow(3, 5), letterCombinations("23456").size());
        Assertions.assertEquals(Math.pow(3, 6) * 4 * 4, letterCombinations("23456789").size());


    }

    void check(String input, List<String> expected){
        List<String> actual = letterCombinations(input).stream().sorted().collect(Collectors.toList());
        expected = expected.stream().sorted().collect(Collectors.toList());
        Assertions.assertEquals(expected.toString(),actual.toString());
    }
}
