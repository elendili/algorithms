package leetcode.top_interview_questions.medium.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/114/others/826/

Given a characters array tasks, representing the tasks a CPU needs to do,
where each letter represents a different task. Tasks could be done in any order.
Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.

However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array),
that is that there must be at least n units of time between any two same tasks.

Return the least number of units of times that the CPU will take to finish all the given tasks.


Example 1:

Input: tasks = ['A','A','A','B','B','B'], n = 2
Output: 8
Explanation:
A -> B -> idle -> A -> B -> idle -> A -> B
There is at least 2 units of time between any two same tasks.
Example 2:

Input: tasks = ['A','A','A','B','B','B'], n = 0
Output: 6
Explanation: On this case any permutation of size 6 would work since n = 0.
['A','A','A','B','B','B']
['A','B','A','B','A','B']
['B','B','B','A','A','A']
...
And so on.
Example 3:

Input: tasks = ['A','A','A','A','A','A','B','C','D','E','F','G'], n = 2
Output: 16
Explanation:
One possible solution is
A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A


Constraints:

1 <= task.length <= 104
tasks[i] is upper-case English letter.
The integer n is in the range [0, 100].
 */
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        if (tasks.length < 2 || n == 0) {
            return tasks.length;
        }
        int[] uniqTasks = new int[26];
        int maxFreq = 0;   // most frequent task's frequency
        int maxCount = 0;  // count of most frequent tasks when frequency is equal
        // gather freqs
        for (char c : tasks) {
            int index = c - 'A';
            uniqTasks[index] += 1;
            if (uniqTasks[index] > maxFreq) {
                maxFreq = uniqTasks[index];
                maxCount = 1;
            } else if (uniqTasks[index] == maxFreq) {
                maxCount += 1;
            }
        }
        // calculation
        // great description:
        // https://leetcode.com/explore/featured/card/top-interview-questions-medium/114/others/826/discuss/104500/Java-O(n)-time-O(1)-space-1-pass-no-sorting-solution-with-detailed-explanation
        int notMaxGapsCount = maxFreq - 1;
        int notMaxGapSize = n + 1 - maxCount;
        int notMaxGapsSum = notMaxGapsCount * notMaxGapSize;
        int notMaxTaskCount = tasks.length - maxFreq * maxCount;
        int idles = notMaxGapsSum - notMaxTaskCount;
        return tasks.length + Math.max(idles, 0); // idles can be negative when we have enough of unfrequent tasks to fill gaps
    }

    public int doesntWork(char[] tasks, int n) {
        if (tasks.length < 2 || n == 0) {
            return tasks.length;
        }
        int[] uniqTasks = new int[26];
        int uniqCount = 0;
        // make freqs
        for (char c : tasks) {
            int index = c - 'A';
            if (uniqTasks[index] == 0) {
                uniqCount++;
            }
            uniqTasks[index] += 1;
        }
        Arrays.sort(uniqTasks); // the most frequent task at the end¬
        uniqTasks = Arrays.copyOfRange(uniqTasks, 26 - uniqCount, uniqTasks.length); // the most frequent task at the end¬
        // iterate tasks
        int processedTasksCount = 0;
        int[] lastIndexes = new int[uniqCount];
        Arrays.fill(lastIndexes, Integer.MIN_VALUE);
        int idleCount = 0;

        for (int i = 0; i < tasks.length + idleCount; i++) {
            boolean wasSetFromTasks = false;
            // find max
            int indexMax = uniqCount - 1;
            for (int j = uniqCount - 2; j > -1; j--) {
                if (uniqTasks[j] > uniqTasks[indexMax]) {
                    indexMax = j;
                }
            }
            // find good task to process
            for (int j = indexMax; j > -1; j--) {
                if (uniqTasks[j] > 0 && lastIndexes[j] < i - n) {
                    uniqTasks[j]--;
                    lastIndexes[j] = i;
                    processedTasksCount++;
                    wasSetFromTasks = true;
                    break;
                }

            }
            if (!wasSetFromTasks) {//use idle
                idleCount++;
            }
        }

        return processedTasksCount + idleCount;
    }

    @Test
    public void test1() {
        Assertions.assertEquals(1, leastInterval(new char[]{'X'}, 1));
    }

    @Test
    public void test2() {
        Assertions.assertEquals(2, leastInterval(new char[]{'X', 'Y'}, 1));
    }

    @Test
    public void test2Same() {
        Assertions.assertEquals(3, leastInterval(new char[]{'X', 'X'}, 1));
        Assertions.assertEquals(4, leastInterval(new char[]{'X', 'X'}, 2));
    }

    @Test
    public void test3() {
        Assertions.assertEquals(3, leastInterval(new char[]{'X', 'Y', 'Z'}, 1));
    }

    @Test
    public void test3Same() {
        Assertions.assertEquals(3, leastInterval(new char[]{'X', 'X', 'Z'}, 1));
        Assertions.assertEquals(5, leastInterval(new char[]{'X', 'X', 'X'}, 1));
        Assertions.assertEquals(7, leastInterval(new char[]{'X', 'X', 'X'}, 2));
        Assertions.assertEquals(9, leastInterval(new char[]{'X', 'X', 'X'}, 3));
    }

    @Test
    public void testFromTask1() {
        Assertions.assertEquals(8, leastInterval(new char[]{'X', 'X', 'X', 'Z', 'Z', 'Z'}, 2));
    }

    @Test
    public void testFromTask2() {
        Assertions.assertEquals(7, leastInterval(new char[]{'X', 'X', 'X', 'Z', 'Z', 'Y'}, 2));
        Assertions.assertEquals(9, leastInterval(new char[]{'X', 'X', 'X', 'Z', 'Z', 'Y'}, 3));
    }

    @Test
    public void test() {
        Assertions.assertEquals(5, leastInterval(new char[]{'X', 'X', 'Z', 'Z', 'F'}, 2));
        Assertions.assertEquals(6, leastInterval(new char[]{'X', 'X', 'Z', 'Z', 'F'}, 3));
    }

    @Test
    public void testN0() {
        Assertions.assertEquals(6, leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 0));
    }

    @Test
    public void testOther() {
        Assertions.assertEquals(16, leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2));
    }

    @Test
    public void testOther2() {
        Assertions.assertEquals(12, leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'E'}, 2));
    }
}
