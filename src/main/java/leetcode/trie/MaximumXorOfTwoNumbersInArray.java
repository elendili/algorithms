package leetcode.trie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/explore/learn/card/trie/149/practical-application-ii/1057/
 * Given an integer array nums, return the maximum result of nums[i] XOR nums[j],
 * where 0 <= i <= j < n.
 */
public class MaximumXorOfTwoNumbersInArray {

    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        class Trie {
            final Trie[] children = new Trie[2];
        }
        Trie trieBuilder, complement,
                root = new Trie();
        int out = 0;

        for (int n : nums) {
            trieBuilder = complement = root;
            int curValue = 0;

            // iterate all bits
            for (int i = 31; i > -1; i --) {
                int bit = (n >>> i) & 1;

                // create next trie if required
                if(trieBuilder.children[bit]==null){
                    trieBuilder.children[bit] = new Trie();
                }
                // shift
                trieBuilder = trieBuilder.children[bit];

                // search bit to add to result
                if (complement.children[bit^1]!=null){
                    // previously saved bit which under XOR gives 1,
                    // thus affecting resulting xor sum
                    complement = complement.children[bit^1];
                    curValue += (1<<i);
                } else {
                    complement = complement.children[bit];
                }
            }
            out = Math.max(out, curValue);
        }
        return out;
    }

    @Test
    public void test() {
        assertEquals(28, findMaximumXOR(new int[]{5, 25}));
        assertEquals(28, findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
    }

    @Test
    public void test0() {
        assertEquals(0, findMaximumXOR(new int[]{0}));
    }

    @Test
    public void test6() {
        assertEquals(6, findMaximumXOR(new int[]{2, 4}));
    }

    @Test
    public void test10() {
        assertEquals(10, findMaximumXOR(new int[]{8, 10, 2}));
    }

    @Test
    public void test127() {
        assertEquals(127,
                findMaximumXOR(new int[]{14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70}));
    }

    @Test
    public void testMAX_VALUE() {
        assertEquals(0,
                findMaximumXOR(new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE}));
    }

    @Test
    public void testMIN_VALUE() {
        assertEquals(0,
                findMaximumXOR(new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE}));
    }

}
