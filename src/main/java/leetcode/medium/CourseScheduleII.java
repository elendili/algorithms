package leetcode.medium;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // create adj list

        int[] indegree = new int[numCourses];// count of required courses before given
        Object[] adjList = new Object[numCourses];
        for (int[] prereq : prerequisites) {
            int dest = prereq[0];
            int src = prereq[1];
            if (adjList[src] == null) {
                adjList[src] = new ArrayList<>();
            }
            ((List<Integer>)adjList[src]).add(dest);
            indegree[dest]++;
        }

        // queue of zero in-degree nodes. Queue of courses where no prerequisites is required
        Queue<Integer> zeroInDegrees = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                zeroInDegrees.add(i);
            }
        }
        int[] topologicalOrder = new int[numCourses];
        int i = 0;
        while (!zeroInDegrees.isEmpty()) {
            int course = zeroInDegrees.poll();
            topologicalOrder[i++] = course;
            if (adjList[course] != null) {
                for (Integer destCourse : ((List<Integer>)adjList[course])) {
                    indegree[destCourse]--;
                    if (indegree[destCourse] == 0) {
                        zeroInDegrees.add(destCourse);
                    }
                }
            }

        }
        if (i == numCourses) {
            return topologicalOrder;
        }
        return new int[]{};
    }

    @org.junit.jupiter.api.Test
    public void test() {
        assertEquals("[0, 1]", Arrays.toString(findOrder(2, new int[][]{{1, 0}})));
        assertEquals("[0, 1, 2, 3]", Arrays.toString(findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
        assertEquals("[0]", Arrays.toString(findOrder(1, new int[][]{})));
    }
}
