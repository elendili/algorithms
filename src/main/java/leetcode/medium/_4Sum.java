package leetcode.medium;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/4sum
 * <p>
 * solution below is sum for any positive number of elements
 */
public class _4Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        return kSum(nums, target, 4);
    }

    public List<List<Integer>> kSum(int[] nums, int target, int count) {
        Arrays.sort(nums);
        return new KSumSolution(nums, target, count).kSum();
    }

    record KSumSolution(int[] nums, int target, int count) {

        KSumSolution {
            Arrays.sort(nums);
        }

        public List<List<Integer>> kSum() {
            Set<List<Integer>> set = new HashSet<>();
            if (count <= nums.length) {
                kSumRec(new int[count], 0, set);
            }
            List<List<Integer>> out = set.stream().toList();
            return out;

        }

        public void kSumRec(int[] indexes, int indexInIndexes, Set<List<Integer>> out) {
            int level = count - indexInIndexes;
            if (level == 1) {
                Arrays.stream(nums).filter(i -> i == target).forEach(e -> out.add(Collections.singletonList(e)));
            } else if (level == 2) {
                find2AdditionalNumbers(indexes, indexInIndexes, out);
            } else {
                int n = nums.length;
                int start = indexInIndexes == 0 ? 0 : indexes[indexInIndexes - 1] + 1;
                int end = (n - count + indexInIndexes + 1);
                for (int i = start; i < end; i++) {
                    if (i > start && nums[i] == nums[i - 1]) {
                        continue;
                    }
                    indexes[indexInIndexes] = i;
                    kSumRec(indexes, indexInIndexes + 1, out);
                }
            }
        }

        void find2AdditionalNumbers(int[] indexes, int indexInIndexes, Set<List<Integer>> out) {
            // two pointers search
            int end = nums.length - 1;
            int start = indexInIndexes == 0 ? 0 : indexes[indexInIndexes - 1] + 1;
            while (start < end) {
                indexes[indexInIndexes] = start;
                indexes[indexInIndexes + 1] = end;
                long sum = getSumFromArray(indexes);
                if (sum == target) {
                    List<Integer> toAdd = getListFromArray(indexes);
                    boolean isNewOne = out.add(toAdd);
                    start++;
                    end--;
                } else if (sum < target) {
                    start++;
                } else {
                    end--;
                }
            }
        }

        long getSumFromArray(int[] indexes) {
            long out = 0;
            for (int i : indexes) {
                out += nums[i];
            }
            return out;
        }

        List<Integer> getListFromArray(int[] indexes) {
            List<Integer> out = new ArrayList<>();
            for (int i : indexes) {
                out.add(nums[i]);
            }
            return out;
        }
    }


    void assertLists(String expected, List<List<Integer>> actual) {
        String actualString = actual.stream().map(Object::toString).sorted().toList().toString();
        assertEquals(expected, actualString);
    }

    @Test
    public void test() {
        assertLists("[[-1, 0, 0, 1], [-2, -1, 1, 2], [-2, 0, 0, 2]]",
                fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        assertEquals("[]",
                fourSum(new int[]{1, 2, 3}, 0).toString());
    }

    @Test
    public void test2() {
        assertEquals("[[2, 2, 2, 2]]",
                fourSum(new int[]{2, 2, 2, 2, 2, 2}, 8).toString());
    }

    @Test
    public void test0() {
        assertEquals("[[0, 0, 0, 0]]",
                fourSum(new int[]{0, 0, 0, 0}, 0).toString());
    }

    @Test
    public void test1000000000() {
        assertEquals("[]",
                fourSum(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296).toString());
    }

    @Test
    public void testKSum() {
        assertEquals("[[2, 6]]",
                kSum(new int[]{6, 2, 2, 2, 2}, 8, 2).toString());
        assertEquals("[[2, 6]]",
                kSum(new int[]{6, 2}, 8, 2).toString());
        assertEquals("[]",
                kSum(new int[]{}, 8, 2).toString());
        assertEquals("[[2, 2, 4]]",
                kSum(new int[]{2, 2, 4, 2}, 8, 3).toString());
        assertLists("[[1, 2, 5], [1, 3, 4]]",
                kSum(new int[]{1, 2, 3, 4, 5}, 8, 3));
        assertEquals("[[0, 1, 2, 2, 3]]",
                kSum(new int[]{1, 2, 3, 4, 5, 4, 3, 2, 0}, 8, 5).toString());
    }

    @Test
    public void testKSum1() {
        assertEquals("[[2]]",
                kSum(new int[]{6, 2, 2, 2, 2}, 2, 1).toString());
    }


}
