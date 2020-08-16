package leetcode.top_interview_questions.easy.sortingAndSearching;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/*
https://leetcode.com/explore/interview/card/top-interview-questions-easy/96/sorting-and-searching/587/

Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]


Constraints:

-10^9 <= nums1[i], nums2[i] <= 10^9
nums1.length == m + n
nums2.length == n
 */
public class MergeSortedArray {
    public void merge(int[] a, int ae, int[] b, int be) {
        int i=0,j=0;
        while(i<ae && j<be){
            if(a[i]>b[j]) {
                // use b[j]
                shiftValues(a,i);
                a[i]=b[j];
                j++;
                ae++;
            }
            i++;
        }
        while(j<be){
            a[i]=b[j];
            j++;
            i++;
        }
    }
    private void shiftValues(int[] a, int fromIndex){
        for(int i=a.length-1;i>fromIndex;i--){
            a[i]=a[i-1];
        }
    }
    int[] a,b;
    @Test
    public void test() {
        a = new int[]{2, 3, 0, 0};
        b = new int[]{1, 4};
        merge(a, 2, b, 2);
        Assertions.assertEquals(asList(1, 2, 3, 4).toString(), Arrays.toString(a));
    }
    @Test
    public void test2(){
        a = new int[]{0,0,0,0};
        b = new int[]{1,2,3,4};
        merge(a,0,b,4);
        Assertions.assertEquals(asList(1,2,3,4).toString(), Arrays.toString(a));
    }
    @Test
    public void test3(){
        a = new int[]{1,2,3,4};
        b = new int[]{0,0};
        merge(a,4,b,0);
        Assertions.assertEquals(asList(1,2,3,4).toString(), Arrays.toString(a));
    }
    @Test
    public void test4(){
        a = new int[]{1,4,0,0};
        b = new int[]{2,3};
        merge(a,2,b,2);
        Assertions.assertEquals(asList(1,2,3,4).toString(), Arrays.toString(a));
    }

    @Test
    public void test5(){
        a = new int[]{1,2,0,0};
        b = new int[]{3,4};
        merge(a,2,b,2);
        Assertions.assertEquals(asList(1,2,3,4).toString(), Arrays.toString(a));
    }

    @Test
    public void test6(){
        a = new int[]{3,4,0,0};
        b = new int[]{1,2};
        merge(a,2,b,2);
        Assertions.assertEquals(asList(1,2,3,4).toString(), Arrays.toString(a));
    }

    @Test
    public void test7(){
        a = new int[]{1,3,4,0};
        b = new int[]{2};
        merge(a,3,b,1);
        Assertions.assertEquals(asList(1,2,3,4).toString(), Arrays.toString(a));
    }
}
