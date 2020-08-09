package leetcode.top_interview_questions.easy.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/92/array/646/

Given an array, rotate the array to the right by k steps, where k is non-negative.

Follow up:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?

 */
public class RotateArray {
    // cyclic replacements
    public void rotate(int[] a, int k) {
        int n =a.length;
        k= k % n;
        if(k>0 && n>1){
            int count=0;
            for(int i=0;count<n;i++){
                int curIndex=i;
                int prevValue=a[curIndex];
                do{
                  int nextIndex=(curIndex+k)%n;
                  int tmp=a[nextIndex];
                  a[nextIndex]=prevValue;
                  prevValue=tmp;
                  curIndex=nextIndex;
                  count++;
                } while(curIndex!=i);
            }
        }
    }

    public void rotateWithCopyInSubArray(int[] a, int k) {
        int n =a.length;
        k= k % n;
        if(k>0){
            if(k<(n-k)){
                int[]b=new int[k];
                // fill b from the end
                for (int i=0;i<k;i++) {
                    b[i]=a[n-k+i];
                }
                // shift beginning to the end
                for (int i=n-1;i>=k;i--) {
                    a[i]=a[i-k];
                }
                // fill a from b
                for (int i=0;i<k;i++) {
                    a[i]=b[i];
                }
            } else {
                int[]b=new int[n-k];
                // fill b from the start
                for (int i=0;i<n-k;i++) {
                    b[i]=a[i];
                }
                // shift end to the beginning
                for (int i=0;i<k;i++) {
                    a[i]=a[i+n-k];
                }
                // fill a from b
                for (int i=k;i<n;i++) {
                    a[i]=b[i-k];
                }
            }

        }
    }
    // 1,2,3 => 2
    // 2,3,1
    // 1,2,3 => 1
    // 3,1,2
    // 1,2,3,4 =>2
    // 3,4,1,2

    @Test
    public void testRotateWithCopyInSubArray(){
        baseTest(new int[]{1,2,3},2, new int[]{2,3,1});
        baseTest(new int[]{1,2,3},1, new int[]{3,1,2});
        baseTest(new int[]{1,2,3,4},1, new int[]{4,1,2,3});
        baseTest(new int[]{1,2,3,4,5,6,7},3, new int[]{5,6,7,1,2,3,4});
        baseTest(new int[]{1,2,3,4,5,6,7},6, new int[]{2,3,4,5,6,7,1});
        baseTest(new int[]{-1,-100,3,99},2, new int[]{3,99,-1,-100});
    }

    void baseTest(int[] input, int k, int[] expected){
        int[] actual = Arrays.copyOf(input,input.length);
        rotateWithCopyInSubArray(actual,k);
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        actual = Arrays.copyOf(input,input.length);
        rotate(actual,k);
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }
}

