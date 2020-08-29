package leetcode.top_interview_questions.easy.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/127/strings/886/
The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.

Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence. You can do so recursively, in other words from the previous member read off the digits, counting the number of digits in groups of the same digit.

Note: Each term of the sequence of integers will be represented as a string.

Example 1:
Input: 1
Output: "1"
Explanation: This is the base case.
Example 2:

Input: 4
Output: "1211"
Explanation: For n = 3 the term was "21" in which we have two groups "2" and "1", "2" can be read as "12" which means frequency = 1 and value = 2, the same way "1" is read as "11", so the answer is the concatenation of "12" and "11" which is "1211".
   Hide Hint #1
The following are the terms from n=1 to n=10 of the count-and-say sequence:
 1.     1
 2.     11
 3.     21
 4.     1211
 5.     111221
 6.     312211
 7.     13112221
 8.     1113213211
 9.     31131211131221
10.     13211311123113112211
   Hide Hint #2
To generate the nth term, just count and say the n-1th term.

 */
public class CountAndSay {
    private static final List<String> cache =
        new ArrayList<>(Arrays.asList(
                "1"
                ,"11", "21", "1211", "111221", "312211",
                "13112221","1113213211","31131211131221",
                "13211311123113112211"
        ));
    public String countAndSay(int n) {
        if(n<=cache.size()){
            return cache.get(n-1);
        }
        String curS = cache.get(cache.size()-1);
        for(int i=cache.size();i<n;i++){
            curS = countAndSay(curS);
            cache.add(curS);
        }
        return curS;
    }

    private String countAndSay(String s){
        int counter=1;
        char prev=s.charAt(0);
        StringBuilder sb = new StringBuilder();
        for (int i=1;i<s.length();i++) {
            char c = s.charAt(i);
            if(c==prev){
                counter++;
            } else {
                sb.append(counter).append(prev);
                counter=1;
            }
            prev = c;
        }
        sb.append(counter).append(prev);
        return sb.toString();
    }

    @Test
    public void test(){
        Assertions.assertEquals("1",countAndSay(1));
        Assertions.assertEquals("11",countAndSay(2));
        Assertions.assertEquals("21",countAndSay(3));
        Assertions.assertEquals("1211",countAndSay(4));
        Assertions.assertEquals("111221",countAndSay(5));
        Assertions.assertEquals("13211311123113112211",countAndSay(10));
        Assertions.assertEquals("11131221131211132221232112111312111213111213211231132132211211131221131211221321123113213221123113112221131112311332211211131221131211132211121312211231131112311211232221121321132132211331121321231231121113112221121321133112132112312321123113112221121113122113121113123112112322111213211322211312113211",countAndSay(20));
    }

}
