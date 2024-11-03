package leetcode.medium;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/minimum-genetic-mutation
 */
public class MinimumGeneticMutation_433 {
    static final char[] letters = new char[]{'A', 'C', 'G', 'T'};

    public int minMutation(String startGene, String endGene, String[] bank) {
        if (startGene == null || endGene == null || startGene.length() != endGene.length()) {
            return -1;
        }
        if (startGene.equals(endGene)) {
            return 0;
        }
        Set<String> goodMutations = new HashSet<>(Arrays.asList(bank));
        if (!goodMutations.contains(endGene)) {
            return -1;
        }
        final int wordLength = startGene.length();
        Set<String> visited = new HashSet<>();
        Queue<String> q = new ArrayDeque<>();
        q.add(startGene);
        visited.add(startGene);
        int levelCount = 0;

        while (!q.isEmpty()) {
            int levelSize = q.size();
            while (levelSize > 0 && !q.isEmpty()) {
                final String curGene = q.poll();
                levelSize -= 1;
                if (curGene.equals(endGene)) {
                    return levelCount;
                }
                // mutate
                char[] curArr = curGene.toCharArray();
                for (int i = 0; i < wordLength; i++) {
                    char oldChar = curArr[i];
                    for (char newChar : letters) {
                        if (oldChar != newChar) {
                            curArr[i] = newChar;
                            String newW = new String(curArr);
                            if (goodMutations.contains(newW) && visited.add(newW)) {
                                q.add(newW);
                            }
                        }
                    }
                    curArr[i] = oldChar;
                }
            }
            levelCount += 1;
        }
        return -1;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(1, minMutation("AACCGGTT", "AACCGGTA", new String[]{"AACCGGTA"}));
        assertEquals(-1, minMutation("AACCGGTT", "ACCCGGTA", new String[]{"AACCGGTA"}));
        assertEquals(2, minMutation("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"}));
    }
}
