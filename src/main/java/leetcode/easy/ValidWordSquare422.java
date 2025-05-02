package leetcode.easy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/valid-word-square/description/?envType=study-plan-v2&envId=premium-algo-100
 */
public class ValidWordSquare422 {
    public boolean validWordSquare(List<String> words) {
        int n = words.size();
        for (int i = 0; i < n; i++) {
            String word = words.get(i);
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (j>=n) {
                    return false;
                }
                String sword = words.get(j);
                if (i>=sword.length()){
                    return false;
                }
                char cc = sword.charAt(i);
                if (c!=cc) {
                    return false;
                }
            }
        }
        return true;
    }
    @org.junit.jupiter.api.Test
    public void test(){
        assertEquals(true, validWordSquare(List.of("abcd","bnrt","crm","dt")));
        assertEquals(false, validWordSquare(List.of("ball","area","read","lady")));
        assertEquals(true, validWordSquare(List.of("ab","b")));
        assertEquals(true, validWordSquare(List.of("aa","ab")));
        assertEquals(false, validWordSquare(List.of("a","ab")));
        assertEquals(false, validWordSquare(List.of("ab","a")));
        assertEquals(true, validWordSquare(List.of("a")));
        assertEquals(false, validWordSquare(List.of(
                "balle",
                "aset",
                "le",
                "lt",
                "ec")));
    }
}
