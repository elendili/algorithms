package yandex;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class AnagramsWith1Error {
    public Map<Character, Integer> getFreq(String s) {
        return Arrays.stream(s.split(""))
                .filter(c -> !c.isEmpty())
                .map(c -> c.charAt(0))
                .collect(Collectors.toMap(
                        c -> c,
                        c -> 1,
                        (v1, v2) -> v2 + v1,
                        HashMap::new
                ));
    }

    public Map<Byte, Integer> getFreq2(String s) {
        Map<Byte, Integer> out = new HashMap<>();
        for (byte b : s.getBytes()) {
            out.compute(b, (k, v) -> v == null ? 1 : v + 1);
        }
        return out;
    }

    public boolean isAnagram(String word1, String word2) {
        Map<Byte, Integer> freq1 = getFreq2(word1);
        Map<Byte, Integer> freq2 = getFreq2(word2);
        int diff = freq2.entrySet().stream().mapToInt(e->{
            int v1 = freq1.getOrDefault(e.getKey(),0);
            return Math.abs(e.getValue()-v1);
        }).sum();
        return diff<=1;
    }

    @Test
    public void isAnagramTest() {
        assertTrue(isAnagram("cat", "cut"));
        assertTrue(isAnagram("ct", "cat"));
        assertTrue(isAnagram("cat", "cats"));
        assertFalse(isAnagram("cat", "catts"));
        assertTrue(isAnagram("cat", "ca"));
        assertFalse(isAnagram("cat", "cutt"));
        assertTrue(isAnagram("", "1"));
        assertFalse(isAnagram("", "12"));
    }

    @Test
    public void getFreqTest() {
        assertEquals("{a=2, b=1}", getFreq("aba").toString());
        assertEquals("{}", getFreq("").toString());
    }

}
