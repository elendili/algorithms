package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        // get last indexes of chars
        int[] lastIndexes = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndexes[getIndex(s.charAt(i))] = i;
        }
        // contains chars forming output string
        Deque<Character> solution = new ArrayDeque<>();
        // to preserve duplicate letters in solution
        boolean[] addedToSolution = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int ci = getIndex(c);
            if (!addedToSolution[ci]) {             // check char is not already in solution
                // make sure that current char is lexicographically less than chars in solution
                //make sure that char in solution can be removed (not the latest one in string)
                while (!solution.isEmpty() && c < solution.peekLast() && lastIndexes[getIndex(solution.peekLast())] > i) {
                    char cc = solution.removeLast();
                    addedToSolution[getIndex(cc)] = false;
                }
                solution.addLast(c);
                addedToSolution[ci] = true;
            }
        }

        // convert to output
        StringBuilder sb = new StringBuilder(solution.size());
        for (char c : solution) sb.append(c);
        return sb.toString();
    }

    int getIndex(char c) {
        return c - 'a';
    }

    @Test
    public void test() {
        Assertions.assertEquals("abc", removeDuplicateLetters("bcabc"));
        Assertions.assertEquals("acdb", removeDuplicateLetters("cbacdcbc"));
    }

    @Test
    public void test2() {
        Assertions.assertEquals("eacb", removeDuplicateLetters("ecbacba"));
    }
}
