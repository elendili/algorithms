package leetcode.top_interview_questions.easy.trees;

import leetcode.top_interview_questions.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/94/trees/627/

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
    1
   / \
  2   2
 / \ / \
3  4 4  3


But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3


Follow up: Solve it both recursively and iteratively.

 */
public class SymmetricTree {
    static class InOrderTraversal{
        /*
            I tried Inorder traversal and BreadthFirst with creating list of values and checking lists' symmetry.
            But this is a shit, only mirroring works:
                    left.left == right.right && left.right == right.left
        */

        public static boolean isSymmetric_InOrderRecursive(TreeNode node) {
            if(node==null || (node.left==null && node.right==null)){
                return true;
            }
            // apply in-order traversal
            List<Integer> list = new ArrayList<>();
            recursiveInOrderHelper(node,list);
            return checkSymmetry(list);
        }

        public static boolean isSymmetric_InOrderIterative(TreeNode node) {
            if(node==null || (node.left==null && node.right==null)){
                return true;
            }
            // apply in-order traversal
            List<Integer> list = new ArrayList<>();
            Deque<TreeNode> q = new ArrayDeque<>();
            while (!q.isEmpty() || node!=null){
                if(node!=null){
                    q.addFirst(node);
                    node = node.left;
                } else {
                    node = q.removeFirst();
                    if(node.left==null && node.right!=null){
                        list.add(null);
                    }
                    list.add(node.val);
                    if(node.left!=null && node.right==null){
                        list.add(null);
                    }
                    node = node.right;
                }
            }
            return checkSymmetry(list);
        }

        public static boolean isSymmetric_Mirroring_Iterative(TreeNode node) {
            if(node==null || (node.left==null && node.right==null)){
                return true;
            }
            Deque<TreeNode> q = new LinkedList<>();
            q.add(node);
            q.add(node);
            while (!q.isEmpty()) {
                TreeNode left = q.pollFirst();
                TreeNode right = q.pollFirst();
                if (left == null && right == null) continue;
                if (left == null || right == null) return false;
                if (left.val != right.val) return false;
                q.add(left.left);
                q.add(right.right);
                // mirror
                q.add(left.right);
                q.add(right.left);
            }
            return true;

        }

        public static boolean isSymmetric_Mirroring_Recursive(TreeNode node) {
            if(node==null || (node.left==null && node.right==null)){
                return true;
            }
            return isSymmetric_Mirroring_Helper(node.left,node.right);

        }

        static boolean isSymmetric_Mirroring_Helper(TreeNode left, TreeNode right){
            if(left==null || right==null){
                return left==right; // true if both are null
            }
            return left.val == right.val
                    && isSymmetric_Mirroring_Helper(left.left,right.right)
                    && isSymmetric_Mirroring_Helper(left.right,right.left);
        }

        public static boolean isSymmetric_BreadthFirst(TreeNode node) {
            if(node==null || (node.left==null && node.right==null)){
                return true;
            }
            // apply in-order traversal
            List<List<Integer>> lists = new ArrayList<>();
            recursiveBreadthFirstHelper(node,0,lists);
            for(List<Integer> list:lists){
                if(!checkSymmetry(list)){
                    return false;
                }
            }
            return true;
        }

        private static void recursiveBreadthFirstHelper(TreeNode node, int layer, List<List<Integer>> lists){
            if(node!=null) {
                if(layer>lists.size()-1){
                    lists.add(new ArrayList<>());
                }
                List<Integer> list = lists.get(layer);
                recursiveBreadthFirstHelper(node.left,layer+1,lists);
                if(node.left==null && node.right!=null){
                    list.add(null);
                }
                list.add(node.val);
                if(node.left!=null && node.right==null){
                    list.add(null);
                }
                recursiveBreadthFirstHelper(node.right,layer+1,lists);
            }
        }

        private static void recursiveInOrderHelper(TreeNode node, List<Integer> list){
            if(node!=null){
                recursiveInOrderHelper(node.left,list);

                // add central value need to add null
                if(node.left==null && node.right!=null){
                    list.add(null);
                }
                // add central value need to add null
                list.add(node.val);
                // to check graph asymmetry
                if(node.left!=null && node.right==null){
                    list.add(null);
                }

                recursiveInOrderHelper(node.right,list);
            }
        }

        static boolean checkSymmetry(List<Integer> list){
            int n = list.size();
            for(int i=0;i<n/2;i++){
                Integer l = list.get(i);
                Integer r = list.get(n-1-i);
                if(
                        (l!=null && !l.equals(r)) || (r!=null && !r.equals(l))
                ){
                    return false;
                }
            }
            return true;
        }

        public static Stream<Arguments> predicateStream() {
            return Stream.of(
                    Arguments.arguments("inOrder_iterative", (Predicate<TreeNode>) tn -> InOrderTraversal.isSymmetric_InOrderRecursive(tn)),
                    Arguments.arguments("inOrder_recursive", (Predicate<TreeNode>) tn -> InOrderTraversal.isSymmetric_InOrderIterative(tn)),
                    Arguments.arguments("BreadthFirst", (Predicate<TreeNode>) tn -> InOrderTraversal.isSymmetric_BreadthFirst(tn)),
                    Arguments.arguments("Mirroring_Recursive", (Predicate<TreeNode>) tn -> InOrderTraversal.isSymmetric_Mirroring_Recursive(tn)),
                    Arguments.arguments("Mirroring_Iterative", (Predicate<TreeNode>) tn -> InOrderTraversal.isSymmetric_Mirroring_Iterative(tn)));
        }

        @ParameterizedTest(name="{index}. {0}")
        @MethodSource("predicateStream")
        public void test(String name,Predicate<TreeNode> methodUnderTest){
            Assertions.assertEquals(true,methodUnderTest.test(TreeNode.from(1)));
            Assertions.assertEquals(true,methodUnderTest.test(TreeNode.from(1,2,2)));
            Assertions.assertEquals(true,methodUnderTest.test(TreeNode.from(1,2,2,3,4,4,3)));
            Assertions.assertEquals(true,methodUnderTest.test(TreeNode.from(1,2,2,3,null,null,3)));
            Assertions.assertEquals(true,methodUnderTest.test(TreeNode.from(1,2,2,null,3,3,null)));

            Assertions.assertEquals(false,methodUnderTest.test(TreeNode.from(1,2,2,null,3,null,3)));
        }

        @ParameterizedTest(name="{index}. {0}")
        @MethodSource("predicateStream")
        public void assymetric(String name,Predicate<TreeNode> methodUnderTest){
            Assertions.assertEquals(false,methodUnderTest.test(TreeNode.from(1,1,null)));
            Assertions.assertEquals(false,methodUnderTest.test(TreeNode.from(1,null,1)));
            Assertions.assertEquals(false,methodUnderTest.test(TreeNode.from(1,2,2,null,3,null,3)));
            Assertions.assertEquals(false,methodUnderTest.test(TreeNode.from(1,2,2,2,null,2)));
            Assertions.assertEquals(false,methodUnderTest.test(TreeNode.from(1,1,1,null,1,null,1)));
            Assertions.assertEquals(false,methodUnderTest.test(TreeNode.from( 5,4,1,null,1,null,4,2,null,2,null )));
        }

        @ParameterizedTest(name="{index}. {0}")
        @MethodSource("predicateStream")
        public void specialCase1(String name,Predicate<TreeNode> methodUnderTest){
            TreeNode head = TreeNode.from( 5,4,1);
            head.left.right=new TreeNode(1);
            head.left.right.left =new TreeNode(2);
            head.right.right=new TreeNode(4);
            head.right.right.left =new TreeNode(2);
            Assertions.assertEquals(false,methodUnderTest.test(head));
//        Assertions.assertEquals(false,methodUnderTest.test(TreeNode.from( 5,4,1,null,1,null,4,2,null,2,null )));
        }

        @ParameterizedTest(name="{index}. {0}")
        @MethodSource("predicateStream")
        public void specialCase2(String name,Predicate<TreeNode> methodUnderTest){
//            TreeNode head = TreeNode.from( 5,4,1);
//            head.left.right=new TreeNode(1);
//            head.left.right.left =new TreeNode(2);
//            head.right.right=new TreeNode(4);
//            head.right.right.left =new TreeNode(2);
//            Assertions.assertEquals(false,methodUnderTest.test(head));
              Assertions.assertEquals(false,methodUnderTest.test(TreeNode.from( 2,3,3,4,5,null,4 )));
        }

    }




}
