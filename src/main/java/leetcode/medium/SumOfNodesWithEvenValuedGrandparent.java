package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/
public class SumOfNodesWithEvenValuedGrandparent {

    public int sumEvenGrandparent(TreeNode n) {
        if (n == null) return 0;
        int out = n.val % 2 == 0 ? sumGrandChildren(n, 2) : 0;
        out += sumEvenGrandparent(n.left) + sumEvenGrandparent(n.right);
        return out;
    }

    int sumGrandChildren(TreeNode n, int targetDepth) {
        if (n == null) return 0;
        if (targetDepth == 0) {
            return n.val;
        } else {
            return sumGrandChildren(n.left, targetDepth - 1)
                    + sumGrandChildren(n.right, targetDepth - 1);
        }
    }

    @Test
    public void test() {
        Assertions.assertEquals(18,
                sumEvenGrandparent(TreeNode.from(6, 7, 8, 2, 7, 1, 3, 9, null, 1, 4, null, null, null, 5)));
    }
}
