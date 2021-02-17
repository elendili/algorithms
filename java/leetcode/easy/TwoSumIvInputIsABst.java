package leetcode.easy;

import leetcode.top_interview_questions.TreeNode;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
public class TwoSumIvInputIsABst {
    boolean out;
    int k;
    Set<Integer> set;

    public boolean findTarget(TreeNode root, int k) {
        out = false;
        this.k = k;
        set = new HashSet<>();
        dfs(root);
        return out;
    }

    void dfs(TreeNode n) {
        if (!out && n != null) {
            if (set.contains(k - n.val)) {
                out = true;
            } else {
                set.add(n.val);
                dfs(n.left);
                dfs(n.right);
            }
        }
    }
}
