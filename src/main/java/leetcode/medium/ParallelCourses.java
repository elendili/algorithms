package leetcode.medium;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParallelCourses {

    public int minimumSemesters(int n, int[][] relations) {
        int[] prerequisiteCounts = new int[n + 1];
        Map<Integer, List<Integer>> prevCourseToNextCourseMap = new HashMap<>();
        for (int[] relation : relations) {
            int fromCourse = relation[0];
            int toCourse = relation[1];
            List<Integer> list = prevCourseToNextCourseMap.computeIfAbsent(fromCourse, k -> new ArrayList<>());
            list.add(toCourse);
            prerequisiteCounts[toCourse]++;
        }
        System.out.println(Arrays.toString(prerequisiteCounts));
        System.out.println(prevCourseToNextCourseMap);
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < n+1; i++) {
            if (prerequisiteCounts[i] == 0) {
                q.add(i);
            }
        }

        int semesterCount = 1;
        while (!q.isEmpty() && q.peek() != null) {
            System.out.println(q);
            q.add(null);
            while (q.peek() != null) {
                int c = q.poll();
                List<Integer> nextCourses = prevCourseToNextCourseMap.get(c);
                if (nextCourses != null && !nextCourses.isEmpty()) {
                    for (int next : nextCourses) {
                        prerequisiteCounts[next]--;
                        if (prerequisiteCounts[next] == 0) {
                            q.add(next);
                        }
                    }
                }
            }
            q.poll();
            if (!q.isEmpty()) {
                semesterCount++;
            }
        }
        for (int i = 1; i < n; i++) {
            if (prerequisiteCounts[i] > 0) {
                return -1;
            }
        }
        return semesterCount;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals(2, minimumSemesters(3, new int[][]{{1, 3}, {2, 3}}));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        assertEquals(1, minimumSemesters(3, new int[][]{}));
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        assertEquals(2, minimumSemesters(2, new int[][]{{2, 1}}));
    }

    @org.junit.jupiter.api.Test
    public void testNegative() {
        assertEquals(-1, minimumSemesters(3, new int[][]
                {{1, 2}, {2, 3}, {3, 1}}
        ));
    }
}
