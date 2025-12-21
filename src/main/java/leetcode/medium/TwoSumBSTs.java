package leetcode.medium;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/two-sum-bsts/?envType=study-plan-v2&envId=premium-algo-100
 */
public class TwoSumBSTs {
    static interface TwoSumBSTsInterface {
        boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target);
    }

    static class ViaHashSet implements TwoSumBSTsInterface {
        public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
            Set<Integer> valuesFromRoot1 = new HashSet<>();
            Deque<TreeNode> d = new ArrayDeque<>();
            // gather values
            d.push(root1);
            while (!d.isEmpty()) {
                TreeNode c = d.pop();
                if (c.left != null) {
                    d.push(c.left);
                }
                if (c.right != null) {
                    d.push(c.right);
                }
                valuesFromRoot1.add(c.val);
            }
            // search target
            d.push(root2);
            while (!d.isEmpty()) {
                TreeNode c = d.pop();
                if (c.left != null) {
                    d.push(c.left);
                }
                if (c.right != null) {
                    d.push(c.right);
                }
                if (valuesFromRoot1.contains(target - c.val)) {
                    return true;
                }
            }
            return false;
        }
    }

    static class ViaIterativeBinarySearchInBST implements TwoSumBSTsInterface {
        public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
            Deque<TreeNode> d1 = new ArrayDeque<>();
            Deque<TreeNode> d2 = new ArrayDeque<>();
            d1.push(root1);
            while (!d1.isEmpty()) {
                TreeNode c = d1.pop();
                int x = target - c.val;
                // time to find X in root2
                d2.push(root2);
                while (!d2.isEmpty()) {
                    TreeNode c2 = d2.pop();
                    int diff = x - c2.val;
                    if (diff == 0) {
                        return true;    // found
                    } else if (diff > 0) {
                        if (c2.right != null) {
                            d2.push(c2.right);
                        } else {
                            break;
                        }
                    } else {
                        if (c2.left != null) {
                            d2.push(c2.left);
                        } else {
                            break;
                        }
                    }
                }

                // time to proceed with other nodes in root1
                if (c.left != null) {
                    d1.push(c.left);
                }
                if (c.right != null) {
                    d1.push(c.right);
                }
            }

            return false;
        }
    }

    static class ViaRecursiveBinarySearchInBST implements TwoSumBSTsInterface {

        private boolean binarySearch(TreeNode root2, int target2) {
            if (root2 == null) {
                return false;
            }
            if (root2.val == target2) {
                return true;
            } else if (root2.val > target2) {
                return binarySearch(root2.left, target2);
            } else {
                return binarySearch(root2.right, target2);
            }
        }
        private boolean dfs(TreeNode root1, TreeNode root2, int target) {
            if (root1 == null) {
                return false;
            }
            if (binarySearch(root2, target - root1.val)) {
                return true;
            }
            return dfs(root1.left, root2, target) || dfs(root1.right, root2, target);
        }

        public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
            return dfs(root1, root2, target);
        }
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("HashSet", new ViaHashSet()),
                Arguments.of("IterativeBinarySearch", new ViaIterativeBinarySearchInBST()),
                Arguments.of("RecursiveBinarySearch", new ViaRecursiveBinarySearchInBST())
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("source")
    public void test(String id, TwoSumBSTsInterface sut) {
        TreeNode root1 = new TreeNode(2, new TreeNode(1), new TreeNode(4));
        TreeNode root2 = new TreeNode(1, new TreeNode(0), new TreeNode(3));
        assertEquals(true, sut.twoSumBSTs(root1, root2, 5));
        assertEquals(true, sut.twoSumBSTs(root1, root2, 3));
        assertEquals(true, sut.twoSumBSTs(root1, root2, 5));
        assertEquals(false, sut.twoSumBSTs(root1, root2, 6));
        assertEquals(false, sut.twoSumBSTs(root1, root2, -1));
    }
}
