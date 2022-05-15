package leetcode.top_interview_questions.medium.design;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/112/design/812/
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored
in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree.
There is no restriction on how your serialization/deserialization algorithm should work.
You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Clarification: The input/output format is the same as how LeetCode serializes a binary tree.
You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.



Example 1:

Input: root = [1,2,3,null,null,4,5]
Output: [1,2,3,null,null,4,5]
Example 2:

Input: root = []
Output: []
Example 3:

Input: root = [1]
Output: [1]
Example 4:

Input: root = [1,2]
Output: [1,2]


Constraints:

The number of nodes in the tree is in the range [0, 10^4].
-1000 <= Node.val <= 1000 (2^10)


 */
public class SerializeAndDeserializeBinaryTree {

    // Your Codec object will be instantiated and called as such:
    // Codec ser = new Codec();
    // Codec deser = new Codec();
    // TreeNode ans = deser.deserialize(ser.serialize(root));

    static class ReadableSolution {

        static final char NULL = '#';

        public String serialize(TreeNode root) {
            return serial(root, new StringBuilder()).toString();
        }

        private StringBuilder serial(TreeNode n, StringBuilder sb) {
            if (n == null) {
                sb.append(NULL);
            } else {
                sb.append(intToChar(n.val));
                serial(n.left, sb);
                serial(n.right, sb);
            }
            return sb;
        }

        private char intToChar(int i) {
            return (char) ('0' + 1000 + i);
        }

        private int charToInt(char c) {
            return (int) (c - 1000 - '0');
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            return deserial(data.toCharArray(), new int[]{0});
        }

        private TreeNode deserial(char[] chars, int[] index) {
            char v = chars[index[0]++];
            if (NULL == v) {
                return null;
            } else {
                TreeNode n = new TreeNode(charToInt(v));
                n.left = deserial(chars, index);
                n.right = deserial(chars, index);
                return n;
            }
        }


        @Test
        public void test() {
            TreeNode input = TreeNode.from(1, 2, 3, 4, 5, 6, 7, null, 8, 9, 10, 11, 1000, 900);
            String serialized = serialize(input);
            System.out.println(serialized);
            Assertions.assertEquals(input, deserialize(serialized));
        }

        @Test
        public void negValues() {
            TreeNode input = TreeNode.from(-1, -2, -3, -4, -5, -6, -7, -1000, 1000);
            String serialized = serialize(input);
            System.out.println(serialized);
            Assertions.assertEquals(input, deserialize(serialized));
        }

    }


    static class CryptoSolution {

        /*
        idea:
        max val is less than 2^10
        tree size is 10^4 ~ 2^14
        char size is 2^16
        can we store left/right in char?
         */
        static final int leftBit = 12;
        static final int rightBit = 13;

        public String serialize(TreeNode root) {
            if (root == null) {
                return null;
            }
            return serial(root, new StringBuilder()).toString();
        }

        private StringBuilder serial(TreeNode n, StringBuilder sb) {
            char v = intToChar(n.val);
            if (n.left == null) {
                v = setBit(v, leftBit);
            }
            if (n.right == null) {
                v = setBit(v, rightBit);
            }
            sb.append(v);
            if (n.left != null) {
                serial(n.left, sb);
            }
            if (n.right != null) {
                serial(n.right, sb);
            }
            return sb;
        }

        private char intToChar(int i) {
            return (char) ('0' + 1000 + i);
        }

        private int charToInt(char c) {
            int updated = clearBits(c);
            return (int) (updated - 1000 - '0');
        }

        private int clearBits(char c) {
            return c & ~(3 << leftBit);
        }

        private char setBit(char c, int index) {
            return (char) (c | (1 << index));
        }

        private boolean getBit(char c, int index) {
            return ((c >> index) & 1) > 0;
        }

        public TreeNode deserialize(String data) {
            if (data == null) {
                return null;
            }
            return deserial(data.toCharArray(), new int[]{0});
        }

        private TreeNode deserial(char[] chars, int[] index) {
            char v = chars[index[0]++];
            TreeNode n = new TreeNode(charToInt(v));
            if (!getBit(v, leftBit)) {
                n.left = deserial(chars, index);
            }
            if (!getBit(v, rightBit)) {
                n.right = deserial(chars, index);
            }
            return n;
        }

        @Test
        public void bites() {
            Assertions.assertFalse(getBit('0', leftBit));
            Assertions.assertTrue(getBit(setBit('0', leftBit), leftBit));

            char v = setBit(intToChar(0), leftBit);
            Assertions.assertTrue(getBit(v, leftBit));
            Assertions.assertFalse(getBit(v, rightBit));
            Assertions.assertEquals(0, charToInt(v));

            v = setBit(setBit(intToChar(0), leftBit), rightBit);
            Assertions.assertTrue(getBit(v, leftBit));
            Assertions.assertTrue(getBit(v, rightBit));
            Assertions.assertEquals(0, charToInt(v));
        }

        @Test
        public void test() {
            TreeNode input = TreeNode.from(1, 2, 3, 4, 5, 6, 7, null, 8, 9, 10, 11, 1000, 900);
            String serialized = serialize(input);
            System.out.println(serialized);
            Assertions.assertEquals(input, deserialize(serialized));
        }

        @Test
        public void negValues() {
            TreeNode input = TreeNode.from(-1, -2, -3, -4, -5, -6, -7, -1000, 1000);
            String serialized = serialize(input);
            System.out.println(serialized);
            Assertions.assertEquals(input, deserialize(serialized));
        }
    }


}
