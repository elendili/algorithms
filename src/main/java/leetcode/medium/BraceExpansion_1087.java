package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BraceExpansion_1087 {
    List<String> outList;

    public String[] expand(String s) {
        outList = new ArrayList<>();
        recursive(s, 0, new StringBuilder());
        return outList.toArray(new String[0]);
    }

    void recursive(String s, int i, StringBuilder curWord) {
        if (i == s.length()) {
            outList.add(curWord.toString());
            return;
        }
        char c = s.charAt(i);
        if (c == '{') {
            // special treatment
            int closingIndex = s.indexOf('}', i + 1);
            char[] letters = new char[(closingIndex - i) / 2];
            int li = 0;
            for (int k = i + 1; k < closingIndex; k++) {
                char letter = s.charAt(k);
                if (letter == ',') {
                    continue;
                }
                letters[li++] = letter;
            }
            Arrays.sort(letters);
            for (int j = 0; j < letters.length; j++) {
                curWord.append(letters[j]);
                recursive(s, closingIndex + 1, curWord);
                curWord.setLength(curWord.length() - 1);
            }
        } else {
            curWord.append(c);
            recursive(s, i + 1, curWord);
            curWord.setLength(curWord.length() - 1);
        }
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    input              | expected
                    a{b,c}             | [ab,ac]
                    {b,c}{x}           | [bx,cx]
                    {a,b}c{d,e}f       | [acdf,acef,bcdf,bcef]
                    abcd               | [abcd]
                    {a,b}{z,x,y}       | [ax,ay,az,bx,by,bz]
                    {a,b,c,d,e,f,g,h}  | [a,b,c,d,e,f,g,h]
                    """
    )
    public void test(String input, String expected) {
        Assertions.assertEquals(expected, Arrays.toString(expand(input)).replaceAll(" ", ""));
    }
}
