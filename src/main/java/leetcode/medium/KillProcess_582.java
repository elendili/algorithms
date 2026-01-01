package leetcode.medium;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/kill-process/description/?envType=study-plan-v2&envId=premium-algo-100
 */
public class KillProcess_582 {
    public List<Integer> killProcess(List<Integer> pids, List<Integer> ppids, int kill) {
        int n = ppids.size();
        // prepare for search
        Map<Integer, List<Integer>> parentToChildren = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int ppid = ppids.get(i);
            int pid = pids.get(i);
            List<Integer> children = parentToChildren.get(ppid);
            if (children == null) {
                children = new ArrayList<>();
                parentToChildren.put(ppid, children);
            }
            children.add(pid);
        }

        List<Integer> out = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        q.add(kill);
        while (!q.isEmpty()) {
            int toKill = q.remove();
            out.add(toKill);
            // search children
            List<Integer> children = parentToChildren.get(toKill);
            if (children != null) {
                q.addAll(parentToChildren.get(toKill));
            }
        }
        return out;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        List<Integer> actual = killProcess(Arrays.asList(1, 3, 10, 5), Arrays.asList(3, 0, 5, 3), 5);
        assertEquals("[5,10]", actual.toString().replaceAll(" ", ""));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        List<Integer> actual = killProcess(Arrays.asList(1), Arrays.asList(0), 1);
        assertEquals("[1]", actual.toString().replaceAll(" ", ""));
    }
}
