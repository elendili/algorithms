package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/range-sum-query-mutable/description/
 */
public class RangeSumQueryMutable {
    interface NumArray {
        void update(int index, int val);

        int sumRange(int left, int right);
    }

    static class NumArray_ShortSegmentTree implements NumArray {
        private int[] tree;
        private int n;

        public NumArray_ShortSegmentTree(int[] nums) {
            if (nums.length > 0) {
                n = nums.length;
                tree = new int[n * 2];
                System.arraycopy(nums, 0, tree, n, n); // copy to the end
                // build the tree
                for (int i = n - 1; i > 0; i--) {
                    tree[i] = tree[2 * i] + tree[2 * i + 1];
                }
            }
        }

        public void update(int index, int val) {
            index += n;
            tree[index] = val;
            while (index > 0) {
                int left = index;
                int right = index;
                if (index % 2 == 0) {
                    right += 1;
                } else {
                    left -= 1;
                }
                index /= 2;
                tree[index] = tree[left] + tree[right];
            }
        }

        public int sumRange(int left, int right) {
            int l = left + n;
            int r = right + n;
            int sum = 0;
            while (l <= r) {
                if (l % 2 == 1) {
                    sum += tree[l];
                    l++;
                }
                if (r % 2 == 0) {
                    sum += tree[r];
                    r--;
                }
                l /= 2;
                r /= 2;
            }
            return sum;
        }
    }

    static class NumArray_BinaryTree implements NumArray {
        private static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
        }

        private int n;
        private TreeNode root;
        private int[] values;

        public NumArray_BinaryTree(int[] nums) {
            if (nums.length > 0) {
                n = nums.length;
                values = nums;
                root = new TreeNode();
                buildTree(root, 0, n - 1);
            }
        }

        private int buildTree(TreeNode node, int l, int r) {
            if (l == r) {
                node.val = values[l];
            } else {
                node.left = new TreeNode();
                node.right = new TreeNode();
                int mid = l + (r - l) / 2;
                int sum = buildTree(node.left, l, mid) +
                        buildTree(node.right, mid + 1, r);
                node.val = sum;
            }
            return node.val;
        }

        public void update(int index, int val) {
            if (n > 0) {
                int diff = val - values[index];
                values[index] = val;
                update(root, index, diff, 0, n - 1);
            }
        }

        private void update(TreeNode node, int i, int diff, int l, int r) {
            if (node != null && i >= l && i <= r) {
                node.val += diff;
                if (l < r) {
                    int mid = l + (r - l) / 2;
                    update(node.left, i, diff, l, mid);
                    update(node.right, i, diff, mid + 1, r);
                }
            }
        }

        public int sumRange(int left, int right) {
            return sumRange(root, left, right, 0, n - 1);
        }

        private int sumRange(TreeNode node, int targetL, int targetR, int l, int r) {
            if (node == null || l > targetR || r < targetL) {  // searching outside of required range
                return 0;
            } else if (l >= targetL && r <= targetR) { // searching inside of required range
                return node.val;
            } else {
                int mid = l + (r - l) / 2;
                // combining results of mixed ranges
                return sumRange(node.left, targetL, targetR, l, mid)
                        + sumRange(node.right, targetL, targetR, mid + 1, r);
            }
        }
    }

    /*
     * BIT[] as a binary tree:
     *            ______________*
     *            ______*
     *            __*     __*
     *            *   *   *   *
     * indices: 0 1 2 3 4 5 6 7 8
     * https://en.wikipedia.org/wiki/Fenwick_tree
     */
    static class NumArray_BinaryIndexedTree_FenwickTree implements NumArray {
        int[] BIT; // binary indexed tree, 
        int[] values;

        NumArray_BinaryIndexedTree_FenwickTree(int[] a) {
            values = new int[a.length];
            BIT = new int[a.length + 1];
            for (int i = 0; i < a.length; i++) {
                update(i, a[i]);
            }
        }

        @Override
        public void update(int i, int val) {
            int diff = val - values[i];
            values[i] = val;
            i += 1;
            while (i < BIT.length) {
                BIT[i] += diff;
                i += getLeastSignificantSetBit(i & -i);  // magic of getting index in a tree
            }
        }

        /**
         * https://en.wikipedia.org/wiki/Find_first_set
         */
        private int getLeastSignificantSetBit(int i){
            int out = i & -i;
            return out;
        }
        @Override
        public int sumRange(int left, int right) {
            int toRightInclusiveSum = getSum(right);
            int beforeLeftSum = getSum(left - 1); // exclusive
            return toRightInclusiveSum-beforeLeftSum;
        }

        private int getSum(int i) {
            int out = 0;
            i += 1;
            while (i > 0) {
                out += BIT[i];
                i -= getLeastSignificantSetBit(i);
            }
            return out;
        }
    }

    public static Stream<Class<? extends NumArray>> predicateStream() {
        return Stream.of(
                NumArray_ShortSegmentTree.class
                ,
                NumArray_BinaryTree.class
                ,
                NumArray_BinaryIndexedTree_FenwickTree.class
        );
    }

    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("predicateStream")
    public void test(Class<NumArray> klazz) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        NumArray instance;
        instance = klazz.getDeclaredConstructor(int[].class).newInstance(new int[]{1, 2, 3});
        assertEquals(6, instance.sumRange(0, 2));
        instance.update(0, 2);
        assertEquals(7, instance.sumRange(0, 2));
        assertEquals(5, instance.sumRange(1, 2));
        instance = klazz.getDeclaredConstructor(int[].class).newInstance(new int[]{1, 2, 3, 4});
        assertEquals(10, instance.sumRange(0, 3));
        instance.update(3, 0);
        assertEquals(6, instance.sumRange(0, 3));
        assertEquals(3, instance.sumRange(2, 2));
    }

    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("predicateStream")
    public void test2(Class<NumArray> klazz) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        NumArray instance;
        instance = klazz.getDeclaredConstructor(int[].class).newInstance(new int[]{-2, 0, 3, -5, 2, -1});
        assertEquals(1, instance.sumRange(0, 2));
        assertEquals(-1, instance.sumRange(2, 5));
        assertEquals(-3, instance.sumRange(0, 5));
        assertEquals(0, instance.sumRange(1, 4));
        assertEquals(0, instance.sumRange(2, 4));
    }

}
