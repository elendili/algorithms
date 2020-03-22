package leetcode;

import java.util.*;
import java.util.stream.Collectors;

// https://leetcode.com/problems/3sum/
// ugly solution :-(
public class ThreeSum {
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            // put numbers in hashmap   and be happy
            if(nums==null || nums.length<3){
                return Collections.emptyList();
            }
            Map<Integer,Integer> set = Arrays.stream(nums)
                    .boxed()
                    .collect(Collectors.toMap(i->i,i->1,(v1,v2)->v1+1, HashMap::new));
            Set<List<Integer>> out = new HashSet<>();

            for(int i=0;i<nums.length-1;i++){
                final int v1=nums[i];
                for(int j=i+1;j<nums.length;j++){
                    final int v2=nums[j];
                    final int sum = -(v1+v2);
                    int countOfSame = set.getOrDefault(sum,0);
                    if(sum==v1) {
                        countOfSame--;
                    }
                    if(sum==v2 ){
                        countOfSame--;
                    }
                    if (countOfSame>0) {
                        List<Integer> l = new ArrayList<>(
                                Arrays.asList(v1,v2,sum));
                        Collections.sort(l);
                        out.add(l);
                    }
                }
            }
            return new ArrayList<>(out);
        }
    }
}
