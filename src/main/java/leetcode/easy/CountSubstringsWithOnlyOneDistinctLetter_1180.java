package leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;
// https://leetcode.com/problems/count-substrings-with-only-one-distinct-letter
public class CountSubstringsWithOnlyOneDistinctLetter_1180 {
    public int countLetters(String s) {
        if (s.isEmpty()) return 0;
        int n = s.length();
        // iterate keeping last letter and counter
        int counter = 1;
        char prev = s.charAt(0);
        int out = 0;
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            if (c==prev) {
                counter++;
            } else {
                // calc
                int curCount = counter*(counter+1)/2;  // gauss formulae
                out +=curCount;
                // reset
                counter=1;
                prev=c;
            }
        }
        int curCount = counter*(counter+1)/2;
        out +=curCount;
        return out;
    }

    @org.junit.jupiter.api.Test
    public void test(){
        assertEquals(55, countLetters("aaaaaaaaaa"));
        assertEquals(8, countLetters("aaaba"));
    }
}
