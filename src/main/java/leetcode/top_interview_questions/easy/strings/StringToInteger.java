package leetcode.top_interview_questions.easy.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/127/strings/884/

Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found.
Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible,
and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number,
which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number,
or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

Note:

Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range:
[−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.

Example 1:
Input: "42"
Output: 42

Example 2:
Input: "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign.
             Then take as many numerical digits as possible, which gets 42.

Example 3:
Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.

Example 4:
Input: "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical
             digit or a +/- sign. Therefore no valid conversion could be performed.

Example 5:
Input: "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
             Thefore INT_MIN (−231) is returned.


 */
public class StringToInteger {
    public int myAtoi(String str) {
        // extract digits
        int[] buffer = new int[str.length()];
        int i=-1;
        int sign=1;
        boolean numberUpcoming=false;
        for(char c:str.toCharArray()){
            if(!numberUpcoming && (c=='-'||c=='+')){
                numberUpcoming=true;
                if(c=='-'){
                    sign=-1;
                }
            } else if(c>='0'&&c<='9'){
                numberUpcoming=true;
                if(i>-1 || c!='0'){
                    buffer[++i]=c-'0';
                }
            } else if(numberUpcoming || i>-1 || (c!=' ' && c != '+')){
                break;
            }
        }
        if(i==-1) { // digit not found
            return 0;
        }
        // convert to int
        int size = i;
        long out = 0;
        for (;i>-1;i--) {
            long multiplier = (long) Math.pow(10,size-i);
            if(multiplier==Long.MAX_VALUE){
                if(sign==1){
                    return Integer.MAX_VALUE;
                } else {
                    return Integer.MIN_VALUE;
                }
            }
            long toAdd = multiplier*buffer[i];
            out+=toAdd;
            if(out>Integer.MAX_VALUE){
                break;
            }
        }

        if(sign>0){
            if(out>Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }
        } else{
            out*=sign;
            if(out<Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }
        }
        return (int)out;
    }

    @Test
    public void test(){
        Assertions.assertEquals(0,myAtoi(" -+2"));
        Assertions.assertEquals(0,myAtoi(" +-2"));
        Assertions.assertEquals(-2,myAtoi(" -2"));
        Assertions.assertEquals(987,myAtoi(" +987 with"));
        Assertions.assertEquals(0,myAtoi("words and 987"));
        Assertions.assertEquals(0,myAtoi(" -w987"));
        Assertions.assertEquals(-43,myAtoi(" -43h"));
        Assertions.assertEquals(12,myAtoi("12"));
        Assertions.assertEquals(-1,myAtoi("-000000000000000000000000000000000000000000000000001"));
        Assertions.assertEquals(1,myAtoi("000000000000000000000000000000000000000000000000001"));
        Assertions.assertEquals(0,myAtoi("0-1"));
        Assertions.assertEquals(Integer.MIN_VALUE,myAtoi("-91283472332"));
        Assertions.assertEquals(Integer.MAX_VALUE,myAtoi("91283472909332"));
        Assertions.assertEquals(Integer.MAX_VALUE,myAtoi("20000000000000000000"));
        Assertions.assertEquals(Integer.MIN_VALUE,myAtoi("-20000000000000000000"));
    }
}
