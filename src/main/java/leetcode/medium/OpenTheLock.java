package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
https://leetcode.com/problems/open-the-lock/

You have a lock in front of you with 4 circular wheels.
Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
Each move consists of turning one wheel one slot.

The lock initially starts at '0000', a string representing the state of the 4 wheels.

You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
the wheels of the lock will stop turning and you will be unable to open it.

Given a target representing the value of the wheels that will unlock the lock,
return the minimum total number of turns required to open the lock, or -1 if it is impossible.



Example 1:

Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation:
A sequence of valid movesCount would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".
Example 2:

Input: deadends = ["8888"], target = "0009"
Output: 1
Explanation:
We can turn the last wheel in reverse to move from "0000" -> "0009".
Example 3:

Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
Output: -1
Explanation:
We can't reach the target without getting stuck.
Example 4:

Input: deadends = ["0000"], target = "8888"
Output: -1


Constraints:

1 <= deadends.length <= 500
deadends[i].length == 4
target.length == 4
target will not be in the list deadends.
target and deadends[i] consist of digits only.

 */
public class OpenTheLock {
    public int openLock(String[] deadends, String target) {
        if (target.equals("0000")) {
            return 0;
        }
        int t = Integer.parseInt(target);
        Set<Integer> visited = new HashSet<>();
        for (String d : deadends) {
            visited.add(Integer.valueOf(d));
        }
        if (!visited.contains(0)) {
            int level = 1;
            Queue<Integer> q = new LinkedList<>();
            q.add(0);
            q.add(-1); // boundary of level
            while (!q.isEmpty()) {
                int n = q.poll();
                if (n < 0) {
                    if (q.isEmpty()) {
                        break;
                    } else {
                        level++;
                        q.add(-1);  // boundary of level
                    }
                } else {
                    // generate new candidates
                    for (int i = 0; i < 4; i++) {
                        for (int d = -1; d < 2; d += 2) {
                            int newCandidate = rotateDigitInNumber(n, i, d);
                            if (t == newCandidate) {
                                return level;
                            } else if (!visited.contains(newCandidate)) {
                                q.add(newCandidate);
                                visited.add(newCandidate);
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    // rotates digit up or down, like 1111=>1211 or  1111=>1011
    int rotateDigitInNumber(int a, int i, int delta) {
        int multi = (int) Math.pow(10, i);
        int digit = (a / multi) % 10;
        int changed = digit + delta;
        changed = changed > 9 ? 0 : changed;
        changed = changed < 0 ? 9 : changed;
        return a - digit * multi + changed * multi;
    }

    @Test
    public void test2() {
        Assertions.assertEquals(6, openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202"));
    }

    @Test
    public void test7() {
        Assertions.assertEquals(2, openLock(new String[]{}, "0002"));
        Assertions.assertEquals(2, openLock(new String[]{}, "0200"));
        Assertions.assertEquals(2, openLock(new String[]{}, "0011"));
        Assertions.assertEquals(2, openLock(new String[]{}, "0101"));
        Assertions.assertEquals(2, openLock(new String[]{}, "1001"));
    }

    @Test
    public void test10() {
        Assertions.assertEquals(4, openLock(new String[]{"0001"}, "0002"));
    }

    @Test
    public void test12() {
        Assertions.assertEquals(4, openLock(new String[]{}, "0006"));
    }

    @Test
    public void test8() {
        Assertions.assertEquals(3, openLock(new String[]{}, "0111"));
        Assertions.assertEquals(4, openLock(new String[]{}, "1111"));
    }

    @Test
    public void test9() {
        Assertions.assertEquals(5, openLock(new String[]{}, "2111"));
        Assertions.assertEquals(5, openLock(new String[]{}, "1112"));
        Assertions.assertEquals(8, openLock(new String[]{}, "2222"));
        Assertions.assertEquals(4 * 3, openLock(new String[]{}, "3333"));
        Assertions.assertEquals(4 * 4, openLock(new String[]{}, "4444"));
        Assertions.assertEquals(4 * 5, openLock(new String[]{}, "5555"));
        Assertions.assertEquals(4 * 4, openLock(new String[]{}, "6666"));
        Assertions.assertEquals(4 * 3, openLock(new String[]{}, "7777"));
    }

    @Test
    public void test3() {
        Assertions.assertEquals(-1, openLock(new String[]{"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"}, "8888"));
    }

    @Test
    public void test4() {
        Assertions.assertEquals(-1, openLock(new String[]{"0000"}, "8888"));
    }

    @Test
    public void test11() {
        Assertions.assertEquals(4 * 2, openLock(new String[]{}, "8888"));
    }

    @Test
    public void test() {
        Assertions.assertEquals(1, openLock(new String[]{"8888"}, "0009"));
    }
}
