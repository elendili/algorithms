package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/count-univalue-subtrees/?envType=study-plan-v2&envId=premium-algo-100
 */
public class CountUnivalueSubtrees_250 {
    int count = 0;

    public int countUnivalSubtrees(TreeNode n) {
        dfs(n);
        return count;
    }

    public Integer dfs(TreeNode n) {
        if (n == null) {
            return null;
        }
        Integer l = null, r = null;
        if (n.left != null) {
            l = dfs(n.left);
        }
        if (n.right != null) {
            r = dfs(n.right);
        }
        int curVal = n.val;
        if (n.left == null && n.right == null) {
            count += 1;
            return curVal;
        }
        if (
                (n.right == null && l != null && l == curVal)
                        || (n.left == null && r != null && r == curVal)
                        || (l != null && r != null && l == curVal && r == curVal)
        ) {
            count += 1;
            return curVal; // propagate subtree value to parent 
        }
        return null;
    }

    @Test
    public void testNull() {
        assertEquals(0, countUnivalSubtrees(null));
    }

    @Test
    public void testSingle() {
        assertEquals(1, countUnivalSubtrees(new TreeNode(1)));
    }

    @Test
    public void test1() {
        /*
            5
          1    5
         */
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        assertEquals(2, countUnivalSubtrees(n1));
    }

    @Test
    public void test11() {
        /*
            5
          5    1
         */
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(1);
        n1.left = n2;
        n1.right = n3;
        assertEquals(2, countUnivalSubtrees(n1));
    }

    @Test
    public void test2() {
        /*
            5
          5    5
         */
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        assertEquals(3, countUnivalSubtrees(n1));
    }

    @Test
    public void test3() {
        /*
            1
          5
         */
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(5);
        n1.left = n2;
        assertEquals(1, countUnivalSubtrees(n1));
    }

    @Test
    public void test4() {
        /*
            1
              5
         */
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(5);
        n1.right = n2;
        assertEquals(1, countUnivalSubtrees(n1));
    }

    @Test
    public void test() {
        /*
            5
          1    5
        5   5     5
         */
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(5);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(5);

        n1.left = n2;
        n1.right = n3;
        n3.right = n6;
        n2.left = n4;
        n2.right = n5;
        assertEquals(4, countUnivalSubtrees(n1));
    }

    @Test
    public void test5() {
        /*
            5
          5    5
        5   5     5
         */
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(5);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(5);

        n1.left = n2;
        n1.right = n3;
        n3.right = n6;
        n2.left = n4;
        n2.right = n5;
        assertEquals(6, countUnivalSubtrees(n1));
    }

    @Test
    public void test6() {
        /*
            1
          5    5
        5   5     5
         */
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(5);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(5);

        n1.left = n2;
        n1.right = n3;
        n3.right = n6;
        n2.left = n4;
        n2.right = n5;
        assertEquals(5, countUnivalSubtrees(n1));
    }

    @Test
    public void test7() {
        /*
              5
          5       5
        5   5   5   5
         */
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(5);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(5);
        TreeNode n7 = new TreeNode(5);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;

        assertEquals(7, countUnivalSubtrees(n1));
    }

    @Test
    public void test9() {
        /*
              5
          1       5
        5   5   5   5
         */
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(5);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(5);
        TreeNode n7 = new TreeNode(5);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;

        assertEquals(5, countUnivalSubtrees(n1));
    }

    @Test
    public void test10() {
        /*
              5
          5       5
        5           5
         */
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(5);
        TreeNode n4 = new TreeNode(5);
        TreeNode n7 = new TreeNode(5);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n3.right = n7;

        assertEquals(5, countUnivalSubtrees(n1));
    }

    @Test
    public void test13() {
        /*
              5
          5       5
            5    5
         */
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(5);
        TreeNode n4 = new TreeNode(5);
        TreeNode n7 = new TreeNode(5);

        n1.left = n2;
        n1.right = n3;
        n2.right = n4;
        n3.left = n7;

        assertEquals(5, countUnivalSubtrees(n1));
    }

    @Test
    public void test12() {
        /*
              1
          5       5
        5           5
         */
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(5);
        TreeNode n4 = new TreeNode(5);
        TreeNode n7 = new TreeNode(5);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n3.right = n7;

        assertEquals(4, countUnivalSubtrees(n1));
    }

    @Test
    public void test8() {
        /*
              5
          5       1
        5   5   5   5
         */
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(5);
        TreeNode n7 = new TreeNode(5);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;

        assertEquals(5, countUnivalSubtrees(n1));
    }
}
