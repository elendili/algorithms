package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Scanner;

/*
https://leetcode.com/problems/compare-version-numbers/
Given two version numbers, version1 and version2, compare them.

Version numbers consist of one or more revisions joined by a dot '.'. Each revision consists of digits and may contain leading zeros. Every revision contains at least one character. Revisions are 0-indexed from left to right, with the leftmost revision being revision 0, the next revision being revision 1, and so on. For example 2.5.33 and 0.1 are valid version numbers.

To compare version numbers, compare their revisions in left-to-right order. Revisions are compared using their integer value ignoring any leading zeros. This means that revisions 1 and 001 are considered equal. If a version number does not specify a revision at an index, then treat the revision as 0. For example, version 1.0 is less than version 1.1 because their revision 0s are the same, but their revision 1s are 0 and 1 respectively, and 0 < 1.

Return the following:

If version1 < version2, return -1.
If version1 > version2, return 1.
Otherwise, return 0.


Example 1:

Input: version1 = "1.01", version2 = "1.001"
Output: 0
Explanation: Ignoring leading zeroes, both "01" and "001" represent the same integer "1".
Example 2:

Input: version1 = "1.0", version2 = "1.0.0"
Output: 0
Explanation: version1 does not specify revision 2, which means it is treated as "0".
Example 3:

Input: version1 = "0.1", version2 = "1.1"
Output: -1
Explanation: version1's revision 0 is "0", while version2's revision 0 is "1". 0 < 1, so version1 < version2.
Example 4:

Input: version1 = "1.0.1", version2 = "1"
Output: 1
Example 5:

Input: version1 = "7.5.2.4", version2 = "7.5.3"
Output: -1


Constraints:

1 <= version1.length, version2.length <= 500
version1 and version2 only contain digits and '.'.
version1 and version2 are valid version numbers.
All the given revisions in version1 and version2 can be stored in a 32-bit integer.
*/
public class CompareVersionNumbers {
    public int compareVersion2(String version1, String version2) {
        Scanner sc1 = new Scanner(version1).useDelimiter("\\.");
        Scanner sc2 = new Scanner(version2).useDelimiter("\\.");
        while (sc1.hasNext() || sc2.hasNext()) {
            int i1 = sc1.hasNext() ? sc1.nextInt() : 0;
            int i2 = sc2.hasNext() ? sc2.nextInt() : 0;
            if (i1 > i2) {
                return 1;
            } else if (i1 < i2) {
                return -1;
            }
        }
        return 0;
    }

    public int compareVersion(String version1, String version2) {
        Scanner sc1 = new Scanner(version1).useDelimiter("\\.");
        Scanner sc2 = new Scanner(version2).useDelimiter("\\.");
        while (sc1.hasNext() && sc2.hasNext()) {
            int i1 = sc1.nextInt();
            int i2 = sc2.nextInt();
        }

        int[] a1 = Arrays.stream(version1.split("\\.")).mapToInt(Integer::valueOf).toArray();
        int[] a2 = Arrays.stream(version2.split("\\.")).mapToInt(Integer::valueOf).toArray();
        int count = Math.min(a1.length, a2.length);
        for (int i = 0; i < count; i++) {
            if (a1[i] > a2[i]) {
                return 1;
            } else if (a1[i] < a2[i]) {
                return -1;
            }
        }
        if (count < a1.length) {
            while (count < a1.length) {
                if (a1[count++] > 0) {
                    return 1;
                }
            }
        }
        if (count < a2.length) {
            while (count < a2.length) {
                if (a2[count++] > 0) {
                    return -1;
                }
            }
        }
        return 0;
    }

    @Test
    public void test2() {
        Assertions.assertEquals(-1, compareVersion("0.1", "1.1"));
    }

    @Test
    public void test6() {
        Assertions.assertEquals(1, compareVersion("0.1", "0.0.1"));
    }

    @Test
    public void test4() {
        Assertions.assertEquals(0, compareVersion("1.0", "1.0.0.0.0"));
    }

    @Test
    public void test5() {
        Assertions.assertEquals(0, compareVersion("1.01", "1.001"));
    }

    @Test
    public void test3() {
        Assertions.assertEquals(1, compareVersion("1.0.1", "1"));
    }

    @Test
    public void test() {
        Assertions.assertEquals(-1, compareVersion("7.5.2.4", "7.5.3"));
    }
}
