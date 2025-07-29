package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

;

/**
 * https://leetcode.com/problems/add-bold-tag-in-string/description/?envType=study-plan-v2&envId=premium-algo-100
 */
public class AddBoldTagInString_616 {
    public String addBoldTag(String s, String[] words) {
        int n = s.length();
        boolean[] bold = new boolean[n];

        for (String word : words) {
            int start = s.indexOf(word);
            while (start != -1) {
                for (int i = start; i < start + word.length(); i++)
                    bold[i] = true;
                start = s.indexOf(word, start + 1);
            }
        }

        StringBuilder res = new StringBuilder();
        int i = 0;
        while (i < n) {
            if (bold[i]) {
                res.append("<b>");
                while (i < n && bold[i])
                    res.append(s.charAt(i++));
                res.append("</b>");
            } else {
                res.append(s.charAt(i++));
            }
        }
        return res.toString();
    }

    @ParameterizedTest
    @CsvSource({
            "aa, a, <b>aa</b>",
            "aaaa, a;aa, <b>aaaa</b>",
            "aabaa, a;aa, <b>aa</b>b<b>aa</b>",
            "abcxyz123, abc;123, <b>abc</b>xyz<b>123</b>",
            "aaabbb, aa;b, <b>aaabbb</b>",
            "aaabbcc, aaa;aab;bc;aaabbcc, <b>aaabbcc</b>",
            "aaabbcc, aaa;aab;bc, <b>aaabbc</b>c"
    })
    void testAddBoldTag(String input, String dictStr, String expected) {
        String[] dict = dictStr.split(";");
        assertEquals(expected, addBoldTag(input, dict));
    }
}
