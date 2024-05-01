package leetcode.hard;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlienDictionary {

    public String alienOrder(String[] words) {

        Map<Character, List<Character>> adjListKeyFromValueTo = new HashMap<>();
        Map<Character, Integer> inDegreeCounts = new HashMap<>();
        // init data structures
        for (String word : words) {
            for (char c : word.toCharArray()) {
                inDegreeCounts.put(c, 0);
                adjListKeyFromValueTo.put(c, new ArrayList<>());
            }
        }
        // find edges
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];

            if (w1.length() > w2.length() && w1.startsWith(w2)) {
                return "";
            }

            int l = Math.min(w1.length(), w2.length());
            for (int j = 0; j < l; j++) {
                char from = w1.charAt(j);
                char to = w2.charAt(j);
                if (from != to) {
                    inDegreeCounts.compute(to, (k, v) -> v == null ? 1 : v + 1);
                    adjListKeyFromValueTo.get(from).add(to);
                    break;
                }
            }
        }

        // Topologically sort dependenciesKeyToValueFrom. Kahn algorithm
        // init queue
        Queue<Character> queue = new ArrayDeque<>();
        inDegreeCounts.forEach((k, v) -> {
            if (v.equals(0)) {
                queue.add(k);
            }
        });
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            for (Character next : adjListKeyFromValueTo.get(c)) {
                inDegreeCounts.put(next, inDegreeCounts.get(next) - 1);
                if (inDegreeCounts.get(next).equals(0)) {
                    queue.add(next);
                }
            }
        }

        if (sb.length() < inDegreeCounts.size()) {
            return "";
        }
        return sb.toString();
    }

    @Test
    public void test() {
        assertEquals("abc", alienOrder(new String[]{"abc"}));
        assertEquals("abcxz", alienOrder(new String[]{"cbazxc"}));
        assertEquals("zx", alienOrder(new String[]{"z", "x"}));
    }

    @Test
    public void test2() {
        assertEquals("abc", alienOrder(new String[]{"ab", "ac"}));
        assertEquals("acxibu", alienOrder(new String[]{"ax", "bc", "ui"}));
    }

    @Test
    public void test31() {
        assertEquals("wertf", alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
    }

    @Test
    public void test3() {
        assertEquals("abc", alienOrder(new String[]{"a", "b", "c"}));
    }

    @Test
    public void test4() {
        assertEquals("rtwjk", alienOrder(new String[]{"wrt", "wrtkj"}));
    }

    @Test
    public void test5() {
        assertEquals("aczb", alienOrder(new String[]{"ac", "ab", "zc", "zb"}));
    }

    @Test
    public void testNegative2() {
        assertEquals("", alienOrder(new String[]{"abc", "ab"}));
    }

    @Test
    public void testNegative() {
        assertEquals("", alienOrder(new String[]{"z", "x", "z"}));
    }

    @Test
    public void testNegative3() {
        assertEquals("", alienOrder(new String[]{"z", "x", "a", "zb", "zx"}));
    }

    @Test
    public void test41() {
        assertEquals("qbtsa", alienOrder(new String[]{"qb", "qts", "qs", "qa", "s"}));
    }

    @Test
    public void test42() {
        assertEquals("bcfghiklnrstwxvdeyupmj", alienOrder(new String[]{
                "lwyni", "ve", "ddhvrviun", "eilylrt", "y", "ulkiulds", "pnfpftmpig", "mbmwylfnyx", "jsgmcvwhh"}));
    }
}
