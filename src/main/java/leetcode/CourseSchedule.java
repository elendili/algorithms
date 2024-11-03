package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/course-schedule
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int[] p : prerequisites) {
            int key = p[0];
            int val = p[1];
            if (graph[key] == null) {
                graph[key] = new ArrayList<>();
            }
            graph[key].add(val);
        }

        boolean[] visited = new boolean[numCourses];
        for (int course = 0; course < numCourses; course++) {
            if (dfs_hasCycle(graph, visited, course))
                return false; // can not complete course
        }
        return true;
    }

    private boolean dfs_hasCycle(List<Integer>[] graph,
                                 boolean[] visited,
                                 int course) {
        visited[course] = true;
        if (graph[course] != null) {
            List<Integer> list = graph[course];
            for (Integer childCourse : list) {
                if (visited[childCourse]
                        || dfs_hasCycle(graph, visited, childCourse)) {
                    return true;
                }
            }
        }
        visited[course] = false;
        graph[course] = null;
        return false;
    }

    @Test
    public void test() {
        Assertions.assertTrue(canFinish(2,
                new int[][]{{0, 1}}));
        Assertions.assertTrue(canFinish(4,
                new int[][]{{0, 1}, {1, 2}, {2, 3}}));
        Assertions.assertTrue(canFinish(4,
                new int[][]{{0, 1}, {2, 1}, {3, 1}}));

        Assertions.assertTrue(canFinish(8,
                new int[][]{{1, 0}, {2, 6}, {1, 7}, {6, 4}, {7, 0}, {0, 5}}));

        Assertions.assertFalse(canFinish(4,
                new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}}));
        Assertions.assertFalse(canFinish(2,
                new int[][]{{0, 1}, {1, 0}}));
        Assertions.assertFalse(canFinish(3,
                new int[][]{{1, 0}, {1, 2}, {0, 1}}));
    }
}
