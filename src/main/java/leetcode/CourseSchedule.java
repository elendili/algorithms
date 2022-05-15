package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

// https://leetcode.com/problems/course-schedule
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] p : prerequisites) {
            int key = p[0];
            int val = p[1];
            graph.computeIfAbsent(key, k -> new ArrayList<>());
            graph.computeIfPresent(key, (k, v) -> {
                v.add(val);
                return v;
            });
        }

        Set<Integer> visited = new HashSet<>();
        for (Integer course : graph.keySet()) {
            if (dfs_hasCycle(graph, visited, course))
                return false; // can not complete course
        }
        return true;
    }

    private boolean dfs_hasCycle(Map<Integer, List<Integer>> graph,
                                 Set<Integer> visited,
                                 int course) {
        visited.add(course);
        if (graph.containsKey(course)) {
            List<Integer> list = graph.get(course);
            for (Integer childCourse : list) {
                if (visited.contains(childCourse)
                        || dfs_hasCycle(graph, visited, childCourse)) {
                    return true;
                }
            }
        }
        visited.remove(course);
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
