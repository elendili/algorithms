package leetcode.top_interview_questions.easy.sortingAndSearching;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

/*
https://leetcode.com/explore/interview/card/top-interview-questions-easy/96/sorting-and-searching/774/
You are a product manager and currently leading a team to develop a new product.
Unfortunately, the latest version of your product fails the quality check.
Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad.
Implement a function to find the first bad version. You should minimize the number of calls to the API.

Example:

Given n = 5, and version = 4 is the first bad version.

call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true

Then 4 is the first bad version.
 */
public class FirstBadVersion {


    public int firstBadVersion(int n) {
        return inRange(1,n);
    }
    private int inRange(int start,int end){
        if(end==start){
            return start;
        }
        int middle = start+(end - start)/2;
        if(isBadVersion(middle)){
            return inRange(start,middle);
        } else {
            return inRange(middle+1, end);
        }
    }
    // --------------------------------------

    Predicate<Integer> predicate = i->false;
    boolean isBadVersion(int n){
        return predicate.test(n);
    }

    @Test
    public void test1(){
        boolean[] a = new boolean[]{true};
        predicate = i-> a[i-1];
        Assertions.assertEquals(1,firstBadVersion(1));
    }
    @Test
    public void test2(){
        boolean[] a = new boolean[]{false,true};
        predicate = i-> a[i-1];
        Assertions.assertEquals(2,firstBadVersion(2));
    }
    @Test
    public void test3(){
        boolean[] a = new boolean[]{false,true,true};
        predicate = i-> a[i-1];
        Assertions.assertEquals(2,firstBadVersion(3));
    }
    @Test
    public void test4(){
        boolean[] a = new boolean[]{false,false,true,true};
        predicate = i-> a[i-1];
        Assertions.assertEquals(3,firstBadVersion(4));
    }
    @Test
    public void test4_1(){
        boolean[] a = new boolean[]{false,false,false,true};
        predicate = i-> a[i-1];
        Assertions.assertEquals(4,firstBadVersion(4));
    }

    @Test
    public void test4_2(){
        boolean[] a = new boolean[]{true,true,true,true};
        predicate = i-> a[i-1];
        Assertions.assertEquals(1,firstBadVersion(4));
    }

    @Test
    public void test5(){
        boolean[] a = new boolean[]{false,false,false,true,true};
        predicate = i-> a[i-1];
        Assertions.assertEquals(4,firstBadVersion(5));
    }
}
