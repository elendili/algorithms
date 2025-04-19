package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-anagram-mappings/?envType=study-plan-v2&envId=premium-algo-100
 */
public class FindAnagramMappings_760 {
    public int[] anagramMappings(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // hash nums2 indexes
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums2[i], i);
        }
        int[] out= new int[n];
        for (int i = 0; i < n; i++) {
            out[i] = map.get(nums1[i]);
        }
        return out;
    }
}
