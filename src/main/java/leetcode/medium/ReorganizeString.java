package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/reorganize-string/
 */
public class ReorganizeString {
    public String reorganizeString(String s) {
        // if count of most frequent char is > (n/2)+1 then return ""
        // otherwise generate string by getting most frequent 2 chars and alternate them
        int n = s.length();
        int limit = (n / 2) + 1;
        int[][] freqs = new int[26][];
        for (int i = 0; i < n; i++) {
            int charIndex = s.charAt(i) - 'a';
            if (freqs[charIndex] == null) {
                freqs[charIndex] = new int[]{charIndex, 1};
            } else {
                freqs[charIndex][1]++;
            }
            if (freqs[charIndex][1] > limit) {
                return "";
            }
        }
        // sort by freq
        Arrays.sort(freqs, Comparator.comparingInt(a -> a == null ? -1 : a[1]));
        //
        if (freqs[25][1] - 1 > (n - freqs[25][1])) {
            return "";
        }
        // fill out
        char[] out = new char[n];
        distributeInFreePlaces(out, freqs, 1);
        distributeInFreePlaces(out, freqs, 0);
        return new String(out);
    }

    void distributeInFreePlaces(char[] out, int[][] freqs, int charLimit) {
        int n = out.length;
        for (int ci = 25; ci > -1; ci--) {
            if (freqs[ci] != null) { // check char exist in freqs
                int i = 0;
                char c = (char) (freqs[ci][0] + 'a');
                // insert char
                while (freqs[ci][1] > charLimit) {
                    // search free place to insert char
                    while (out[i] > 0 || (i > 0 && out[i - 1] == c) || (i < n - 2 && out[i + 1] == c)) {
                        i += 1;
                        if (i > n - 1) {
                            i = 0;
                        }
                    }
                    out[i] = c;
                    freqs[ci][1]--;
                    i += 2;
                    if (i > n - 1) {
                        i = 0;
                    }
                }
            }
        }
    }

    @Test
    public void test() {
        Assertions.assertEquals("aba", reorganizeString("aab"));
        Assertions.assertEquals("zba", reorganizeString("zab"));
        Assertions.assertEquals("zba", reorganizeString("zba"));
        Assertions.assertEquals("zbzaz", reorganizeString("zzzba"));
        Assertions.assertEquals("zczbzaz", reorganizeString("zzzzbac"));
        Assertions.assertEquals("zqzqzqz", reorganizeString("zzzzqqq"));
        Assertions.assertEquals("zqzqzq", reorganizeString("zzzqqq"));
        Assertions.assertEquals("", reorganizeString("aaab"));
    }

    @Test
    public void test2() {
        Assertions.assertEquals("zqfzqf", reorganizeString("zzqqff"));
    }

    @Test
    public void test3() {
        Assertions.assertEquals("jxjxjxjxjxjxjxjxjxjxjxjxjxjxjxjxjxjxjxjxjxjxjxjxjqjqjqjqjqlqlqlqlqlqlqlqlqlqlqlqlqlqlqlqlqlqlqlqlnlnlnlnenenenenenenenenenenenenenenenenenenecececacacacacacacacacacacacacacacacacacacavavavovovovovovovovovovovovovovovovovovoyoyoypypypypypypypypypypypypypypypypypupupurururururururururururururururgrgrgsgsgsgsgsgsgsgsgsgsgsgsgsgsgskskhkhkhkhkhkhkhkhkhkhkhkhkhkhkhdhdzdzdzdzdzdzdzdzdzdzdzdzdzdzdztitititititititititititititifwfwfwfwfwfwfwfwfwfwfwfwfwfbmbmbmbmbmbmbmbmbmbmbmbmbjxqlnecavoypurgskhdztifwbm", reorganizeString("gpneqthatplqrofqgwwfmhzxjddhyupnluzkkysofgqawjyrwhfgdpkhiqgkpupgdeonipvptkfqluytogoljiaexrnxckeofqojltdjuujcnjdjohqbrzzzznymyrbbcjjmacdqyhpwtcmmlpjbqictcvjgswqyqcjcribfmyajsodsqicwallszoqkxjsoskxxstdeavavnqnrjelsxxlermaxmlgqaaeuvneovumneazaegtlztlxhihpqbajjwjujyorhldxxbdocklrklgvnoubegjrfrscigsemporrjkiyncugkksedfpuiqzbmwdaagqlxivxawccavcrtelscbewrqaxvhknxpyzdzjuhvoizxkcxuxllbkyyygtqdngpffvdvtivnbnlsurzroxyxcevsojbhjhujqxenhlvlgzcsibcxwomfpyevumljanfpjpyhsqxxnaewknpnuhpeffdvtyjqvvyzjeoctivqwann"));
    }

}
