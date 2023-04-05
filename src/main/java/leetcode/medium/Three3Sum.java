package leetcode.medium;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//https://leetcode.com/problems/3sum
public class Three3Sum {
    /**
     * solution with worst case O(n^2) and best case O(log(n))
     * it's alternative to hashing approach
     */
    public List<List<Integer>> threeSum(int[] a) {
        if(a==null || a.length<3){
            return Collections.emptyList();
        }
        Arrays.sort(a);
        int n = a.length;
        List<List<Integer>> out = new ArrayList<>();
        for(int i=0;i<n-2;i++){
            if(i==0 || a[i]!=a[i-1]) { // required because array has duplicates
                int start = i + 1;
                int end = n - 1;
                while (start < end) {
                    int sum = a[i] + a[start] + a[end];
                    if (sum == 0) {
                        out.add(Arrays.asList(a[i], a[start], a[end]));
                        do {
                            start++;
                        } while (start<end && a[start] == a[start - 1]); // required because array has duplicates

                        do {
                            end--;
                        } while (start<end && a[end] == a[end + 1]);// required because array has duplicates
                    } else if (sum > 0) {
                        do {
                            end--;
                        } while (start<end && a[end] == a[end + 1]);// required because array has duplicates
                    } else {
                        do {
                            start++;
                        } while (start<end && a[start] == a[start - 1]);// required because array has duplicates
                    }
                }
            }
        }
        return out;
    }

    @Test
    public void test(){
        assertEquals("[[-1,-1,2],[-1,0,1]]",
                threeSum(new int[]{-1,0,1,2,-1,-4})
                        .toString().replaceAll(" ",""));
        assertEquals("[]",
                threeSum(new int[]{0,1,1})
                        .toString().replaceAll(" ",""));
        assertEquals("[[0,0,0]]",
                threeSum(new int[]{0,0,0})
                        .toString().replaceAll(" ",""));
    }
}
