package leetcode.easy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/word-pattern/?envType=study-plan-v2&envId=top-interview-150
 */
public class WordPattern_290 {

    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        Map<Character, Integer> charMap = new HashMap<>();
        Map<String, Integer> wordMap = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            Integer oldCI = charMap.put(c,i);
            String w = words[i];
            Integer oldWI = wordMap.put(w, i);
            if(!Objects.equals(oldCI, oldWI)){
                return false;
            }
        }
        return true;
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    arg1         | arg2             | expected
                    abba         | dog cat cat dog  | true
                    abba         | dog cat cat fish | false
                    aaaa         | dog cat cat dog  | false
                    aaaa         | dog dog dog dog  | true
                    aaa          | aa aa aa aa      | false
                    """
    )
    public void test(String arg1, String arg2, boolean expected) {
        assertEquals(expected, wordPattern(arg1, arg2));
    }


}
