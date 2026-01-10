package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;

/**
 * https://leetcode.com/problems/rearrange-string-k-distance-apart/?envType=study-plan-v2&envId=premium-algo-100
 * <p>
 * Given a string s and an integer k, rearrange s such that the same characters are at least distance k from each other.
 * If it is not possible to rearrange the string, return an empty string "".
 */
public class RearrangeStringKDistanceApart_358 {
    boolean debug = true;

    record CharacterToInteger(char character, int integer) {
    }

    public String rearrangeString(String s, int k) {
        int[] freq = new int[26];
        // Store the frequency for each character.
        for (char c : s.toCharArray()) {
            freq[c - 'a'] += 1;
        }

        Comparator<CharacterToInteger> comparator = (a, b) -> {
            if (b.integer() == a.integer()) {
                return Character.compare(a.character(), b.character());
            }
            return b.integer() - a.integer();
        };
        PriorityQueue<CharacterToInteger> free =
                new PriorityQueue<>(comparator);

        // Insert the characters with their frequencies in the max heap.
        for (int i = 0; i < 26; i++) {
            int count = freq[i];
            if (count>0) {
                free.offer(new CharacterToInteger((char) ('a' + i), count));
            }
        }

        StringBuilder ans = new StringBuilder();
        // This queue stores the characters that cannot be used now.
        Queue<CharacterToInteger> busy = new LinkedList<>();
        while (ans.length() != s.length()) {
            int index = ans.length();

            // Insert the character that could be used now into the free heap.
            if (!busy.isEmpty() && (index - busy.peek().integer()) >= k) {
                CharacterToInteger q = busy.remove();
                free.offer(new CharacterToInteger(q.character(), freq[q.character() - 'a']));
            }

            // If the free heap is empty, it implies no character can be used at this index.
            if (free.isEmpty()) {
                return "";
            }

            Character currChar = free.peek().character();
            free.remove();
            ans.append(currChar);

            // Insert the used character into busy queue with the current index.
            freq[currChar - 'a'] -= 1;
            if (freq[currChar - 'a'] > 0) {
                busy.add(new CharacterToInteger(currChar, index));
            }
        }

        return ans.toString();
    }


    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true,
            delimiter = '|',
            textBlock = """              
                    expected     | string    | k
                    abcabc       | aabbcc    | 3
                    ''           | aaabc     | 3
                    abacabcd     | aaadbbcc  | 2
                    bab          | abb       | 2
                    abaca        | aaabc     | 2
                    """
            // aaadbbcc  ->  abacabcd
    )
    public void test(String expected, String string, int k) {
        Assertions.assertEquals(
                expected,
                rearrangeString(string, k), string + " -> " + expected);
    }
}
