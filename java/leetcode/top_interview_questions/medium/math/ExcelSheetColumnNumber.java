package leetcode.top_interview_questions.medium.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/113/math/817/

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
    ...
Example 1:

Input: "A"
Output: 1
Example 2:

Input: "AB"
Output: 28
Example 3:

Input: "ZY"
Output: 701


Constraints:

1 <= s.length <= 7
s consists only of uppercase English letters.
s is between "A" and "FXSHRXW".
 */
public class ExcelSheetColumnNumber {
    private static final int base = 26;
    public int titleToNumber(String s) {
        char[] a = s.toCharArray();
        int out=0;
        for(int i=0;i<a.length;i++){
            char c = a[i];
            int n = c-'A'+1;
            int toAdd = ((int)Math.pow(base,(a.length-1-i)))*n;
            out+=toAdd;
        }
        return out;
    }

    @Test
    public void one() {
        Assertions.assertEquals(1, titleToNumber("A"));
        Assertions.assertEquals(26, titleToNumber("Z"));
    }

    @Test
    public void more(){
        Assertions.assertEquals(28,titleToNumber("AB"));
        Assertions.assertEquals(701,titleToNumber("ZY"));
    }
}
