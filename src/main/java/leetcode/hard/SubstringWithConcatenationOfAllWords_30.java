package leetcode.hard;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubstringWithConcatenationOfAllWords_30 {
    public List<Integer> findSubstring(String s, String[] words) {
        int n = s.length();
        final int l = words[0].length();
        final int count = words.length;
        final int permutationLength = l * count;
        final Map<String, Integer> freqsIndexes = new HashMap<>();
        for (String w : words) {
            freqsIndexes.compute(w, (k, v) -> {
                v = v == null ? 0 : v;
                v += 1;
                return v;
            });
        }
        int uniqWordsCount = freqsIndexes.size();
        int[] freqArray = new int[uniqWordsCount];
        int i=0;
        for(Map.Entry<String,Integer> e:freqsIndexes.entrySet()){
            freqArray[i]=e.getValue();
            e.setValue(i);
            i++;
        }

        int[] workingFreqs = Arrays.copyOf(freqArray, uniqWordsCount);
        int left = 0, right = l;
        int curCountOfWordsInWindow = 0;
        List<Integer> out = new ArrayList<>();
        for (;
             right <= n && left <= (n - permutationLength);
             right += l) {
            String supposedWord = s.substring(right - l, right);
            // check word for existence in dictionary
            Integer index = freqsIndexes.get(supposedWord);
            if (index != null && workingFreqs[index]>0) {
                workingFreqs[index]--;
                curCountOfWordsInWindow += 1;
                if (curCountOfWordsInWindow == count) {
                    out.add(left);

                    // reset
                    if(curCountOfWordsInWindow>0) {
                        workingFreqs = Arrays.copyOf(freqArray, uniqWordsCount);
                        curCountOfWordsInWindow = 0;
                    }
                    left += 1;
                    right = left;
                }
            } else {
                // reset
                if(curCountOfWordsInWindow>0) {
                    workingFreqs = Arrays.copyOf(freqArray, uniqWordsCount);
                    curCountOfWordsInWindow = 0;
                }
                left += 1;
                right = left;
            }
        }
        return out;
    }


    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """
                    s                                    | words                       | expected
                    "bada"                               | "a","b"                     | [0]
                    "baab"                               | "a","b"                     | [0, 2]
                    "adab"                               | "a","b"                     | [2]
                    "abab"                               | "a","b"                     | [0, 1, 2]
                    "addb"                               | "a","b"                     | []
                    "dabd"                               | "a","b"                     | [1]
                    "daada"                              | "a","a","a"                 | []
                    "daaaa"                              | "a","a","a"                 | [1, 2]
                    "baabba"                             | "ab","ba"                   | [0, 2]
                    "baabba"                             | "baabba"                    | [0]
                    "baabab"                             | b,a                         | [0, 2, 3, 4]
                    "barfoothefoobarman"                 | "foo","bar"                 | [0, 9]
                    "wordgoodgoodgoodbestword"           | "word","good","best",word   | []
                    "barfoofoobarthefoobarman"           | "bar","foo","the"           | [6, 9, 12]
                    "aaaaaaaaaaaaaa"                     | "aa","aa"                   | [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
                    """
    )
    public void test(String s, String words, String expected) {
        s = s.replaceAll("\"", "");
        String[] wordsA = Arrays.stream(words.split(","))
                .map(e -> e.replaceAll("\"", ""))
                .toList()
                .toArray(new String[0]);
        assertEquals(expected, findSubstring(s, wordsA).toString());
    }
}
