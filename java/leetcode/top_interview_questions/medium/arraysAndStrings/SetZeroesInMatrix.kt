package leetcode.top_interview_questions.medium.arraysAndStrings

import java.util.Arrays
import java.util.function.IntFunction
import java.util.stream.Collectors
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
https://leetcode.com/problems/set-matrix-zeroes/solution/
Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.

Follow up:
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?


Example 1:
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]

Example 2:
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]

 */
class SetZeroesInMatrix {
    fun setZeroes(matrix: Array<IntArray>?) {
        if (matrix == null || matrix.size == 0 || matrix.size == 1 && matrix[0].size == 1) {
            return
        }
        val H = matrix.size
        val W: Int = matrix[0].size
        var firstRowToZero = false
        var firstColumnToZero = false
        //check matrix and use first row + column as storage
        for (row in 0 until H) {
            for (col in 0 until W) {
                if (matrix[row][col] == 0) {
                    if (row == 0) {
                        firstRowToZero = true
                    }
                    if (col == 0) {
                        firstColumnToZero = true
                    }
                    matrix[0][col] = 0
                    matrix[row][0] = 0
                }
            }
        }
        //zeroing matrix
        for (row in 1 until H) {
            for (col in 1 until W) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0
                }
            }
        }
        // zero first row
        if (firstRowToZero) {
            Arrays.fill(matrix[0], 0)
        }
        // zero first column
        if (firstColumnToZero) {
            for (row in 0 until H) {
                matrix[row][0] = 0
            }
        }
    }

    @Test
    fun test() {
        check("0", arrayOf(intArrayOf(0)));
        check("1", arrayOf(intArrayOf(1)));
        check("1,0,1\n0,0,0\n1,0,1", arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 0, 1), intArrayOf(1, 1, 1)));
        check("0,0,0,0\n0,4,5,0\n0,3,1,0", arrayOf(intArrayOf(0, 1, 2, 0), intArrayOf(3, 4, 5, 2), intArrayOf(1, 3, 1, 5)));
        check("1,0\n3,0\n0,0", arrayOf(intArrayOf(1, 2), intArrayOf(3, 4), intArrayOf(5, 0)));
        check("1\n2\n3", arrayOf(intArrayOf(1), intArrayOf(2), intArrayOf(3)));
        check("0\n0\n0", arrayOf(intArrayOf(1), intArrayOf(2), intArrayOf(0)))
        check("1,2,3", arrayOf(intArrayOf(1, 2, 3)))
        check("0,0,0", arrayOf(intArrayOf(1, 2, 0)))
    }

    fun check(expected: String?, a: Array<IntArray>?) {
        setZeroes(a)
        val actual = Arrays.stream(a).map { aa: IntArray? -> Arrays.stream(aa).mapToObj { i: Int -> "" + i }.collect(Collectors.joining(",")) }.collect(Collectors.joining("\n"))
        Assertions.assertEquals(expected, actual)
    }
}