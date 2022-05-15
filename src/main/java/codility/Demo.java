package codility;

import java.util.Arrays;
/*
Write a function:
class Solution { public int solution(int[] A); }
that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
Given A = [1, 2, 3], the function should return 4.
Given A = [−1, −3], the function should return 1.
Write an efficient algorithm for the following assumptions:
N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].
 */
public class Demo {
    public int solution(int[] A) {
        int[] list = Arrays.stream(A)
                .filter(i -> i > 0).distinct()
                .sorted().toArray();
        for (int i = 1; i <= list.length; i++) {
            if (i < list[i - 1]) {
                return i;
            }
        }
        if (list.length > 0) {
            return list.length + 1;
        }
        return 1;
    }
}
