package leetcode.top_interview_questions.easy.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*

https://leetcode.com/explore/featured/card/top-interview-questions-easy/92/array/770/

You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Given input matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
Example 2:

Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
],

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
 */
public class RotateImage {
    public void rotate(int[][] aa) {
        if(aa.length>1){
            int n = aa.length;// size of matrix
            int last = n-1;
            // loop for circles
            int tmpToSave; // to preserve
            int tmpToInsert; // to insert
            for(int i=0;i<n/2;i++){
                // attempt to work in 1 loop
                // copy right vertical line in buffer
                // copy top line in the place of right vertical line and fill buffer
                for(int j=i;j<last-i;j++){
                    // from top line to right line
                    tmpToSave=aa[j][last-i];
                    aa[j][last-i]=aa[i][j];

                    // from right line to bottom line
                    tmpToInsert=tmpToSave; // preserve for replace
                    tmpToSave=aa[last-i][last-j];
                    aa[last-i][last-j]=tmpToInsert;

                    // from bottom line to left line
                    tmpToInsert=tmpToSave; // preserve for replace
                    tmpToSave=aa[last-j][i];
                    aa[last-j][i]=tmpToInsert;

                    aa[i][j]=tmpToSave;
                }
//
//                System.out.println("---------------------"+i+" after");
//                System.out.println(convert2dToString(aa));

            }
        }
    }

    @Test
    public void test2() {
        int[][] actual, expected;
        actual = new int[][]{
                {1, 2},
                {3, 4},
        };
        expected = new int[][]{
                {3, 1},
                {4, 2},
        };
        rotate(actual);
        assertEquals(convert2dToString(expected), convert2dToString(actual));
    }

    @Test
    public void test3() {
        int[][] actual, expected;
        actual = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        expected = new int[][]{
                {7, 4, 1},
                {8, 5, 2},
                {9, 6, 3}
        };
        rotate(actual);
        assertEquals(convert2dToString(expected), convert2dToString(actual));
    }

    @Test
    public void test4() {
        int[][] actual, expected;
        actual = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        expected = new int[][]{
                {13, 9, 5, 1},
                {14, 10, 6, 2},
                {15, 11, 7, 3},
                {16, 12, 8, 4}
        };
        rotate(actual);
        assertEquals(convert2dToString(expected), convert2dToString(actual));
    }

    @Test
    public void test5() {
        int[][] actual, expected;
        actual = new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        expected = new int[][]{
                {21, 16, 11, 6, 1},
                {22, 17, 12, 7, 2},
                {23, 18, 13, 8, 3},
                {24, 19, 14, 9, 4},
                {25, 20, 15, 10, 5}
        };
        rotate(actual);
        assertEquals(convert2dToString(expected), convert2dToString(actual));
    }

    @Test
    public void test6() {
        int[][] actual, expected;
        actual=new int[][]{
                { 1, 2, 3, 4, 5, 6},
                { 7, 8, 9,10,11,12},
                {13,14,15,16,17,18},
                {19,20,21,22,23,24},
                {25,26,27,28,29,30},
                {31,32,33,34,35,36},
        };
        expected=new int[][]{
                {31,25,19,13, 7, 1},
                {32,26,20,14, 8, 2},
                {33,27,21,15, 9, 3},
                {34,28,22,16,10, 4},
                {35,29,23,17,11, 5},
                {36,30,24,18,12, 6},
        };
        rotate(actual);
        assertEquals(convert2dToString(expected), convert2dToString(actual));
    }

    private String convert2dToString(int[][] matrix){
        return Arrays.stream(matrix).map(
                a->Arrays.stream(a).mapToObj(i->String.format("%2d",i)).collect(joining(","))
        ).collect(Collectors.joining("\n"));
    }
}
