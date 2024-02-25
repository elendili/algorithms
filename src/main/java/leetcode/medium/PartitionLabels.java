package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/partition-labels/description/
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        // search last indexes of every symbol in array
        // then iterate from left to right and for every met symbol define last index,
        // and when i meet lastIndex then it's one substring
        // got to the next substring from lastIndex+1

        int[] lastIndexes = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndexes[s.charAt(i) - 'a'] = i;
        }

        List<Integer> out = new ArrayList<>();
        int partitionEnd = 0;
        int partitionStart = 0;
        for (int i = 0; i < s.length(); i++) {
            partitionEnd = Math.max(partitionEnd, lastIndexes[s.charAt(i) - 'a']);
            if (partitionEnd == i) {
                int l = partitionEnd - partitionStart + 1;
                out.add(l);
                partitionStart = partitionEnd + 1;
            }
        }
        return out;
    }

    @Test
    public void test() {
        Assertions.assertEquals("[9, 7, 8]",
                partitionLabels("ababcbacadefegdehijhklij").toString());
    }

    @Test
    public void test10() {
        Assertions.assertEquals("[10]",
                partitionLabels("eccbbbbdec").toString());
    }
}
