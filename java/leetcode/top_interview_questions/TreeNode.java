package leetcode.top_interview_questions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val &&
                Objects.equals(left, treeNode.left) &&
                Objects.equals(right, treeNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right);
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public String toString() {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(this);
        LinkedList<Integer> out = new LinkedList<>();
        while (!q.isEmpty()) {
            Optional<TreeNode> t = Optional.ofNullable(q.poll());
            out.add(t.map(n -> n.val).orElse(null));
            t.ifPresent(n -> {
                        q.add(n.left);
                        q.add(n.right);
                    }
            );
        }
        while (out.peekLast() == null) {
            out.removeLast();
        }
        return out.toString().replaceAll(" ", "");
    }
/*
    public String toString() {
        List<Integer> list = new ArrayList<>();
        Queue<Optional<TreeNode>> q = new ArrayDeque<>();
        q.add(Optional.of(this));
        list.add(val);
        int i=-1;
        while (!q.isEmpty()) {
            i++;
            Optional<TreeNode> oc = q.poll();
            if (oc.isPresent()) {
                TreeNode c = oc.get();
                int leftI  = 2*i+1;
                int rightI = 2*i+2;

                if(rightI>list.size()-1){
                    for(int z = rightI-list.size();z>0;z--){
                        list.add(null);
                    }
                }

                list.add(leftI, c.left!=null?c.left.val:null);
                list.add(rightI,c.right!=null?c.right.val:null);

                q.add(Optional.ofNullable(c.left));
                q.add(Optional.ofNullable(c.right));
            }

        }
        for (int j=list.size()-1;list.get(j)==null;j--){
            list.remove(j);
        }
        return list.stream().map(e -> String.valueOf(e)).collect(Collectors.joining(",", "[", "]"));
    }*/

    public int[] inOrderArray() {
        List<Integer> list = new ArrayList<>();
        recursiveInOrder(this,list);
        return list.stream().mapToInt(i -> i).toArray();
    }

    private void recursiveInOrder(TreeNode n, List<Integer> list) {
        if(n!=null){
            recursiveInOrder(n.left,list);
            list.add(n.val);
            recursiveInOrder(n.right,list);
        }
    }

    public int[] preOrderArray() {
        List<Integer> list = new ArrayList<>();
        recursivePreOrder(this,list);
        return list.stream().mapToInt(i -> i).toArray();
    }

    private void recursivePreOrder(TreeNode n, List<Integer> list) {
        if (n != null) {
            list.add(n.val);
            recursiveInOrder(n.left, list);
            recursiveInOrder(n.right, list);
        }
    }

    public static TreeNode from(Integer... arr) {
        TreeNode root = null;
        Queue<TreeNode> q = new LinkedList<>();
        int i = 0;
        TreeNode t = arr[i] == null ? null : new TreeNode(arr[i]);
        root = t;
        q.add(root);
        i++;
        while (!q.isEmpty() && i < arr.length) {
            TreeNode t1 = q.poll();
            if (t1 != null) {
                t1.left = arr[i] == null ? null : new TreeNode(arr[i]);
                q.add(t1.left);
                i++;
                if (i >= arr.length) {
                    break;
                }
                t1.right = arr[i] == null ? null : new TreeNode(arr[i]);
                q.add(t1.right);
                i++;
            }
        }
//        printLevelOrder(root);
        return root;
    }

    private void printLevelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            TreeNode t = q.poll();
            sb.append(t == null ? "null" : t.val).append(", ");
            if (t != null) {
                q.add(t.left);
                q.add(t.right);
            }
        }
        System.out.println(sb.toString());
    }

    public static class TreeNodeTest {
        @Test
        public void testToString() {
            TreeNode head;
            head = new TreeNode(1, new TreeNode(2), new TreeNode(3));
            Assertions.assertEquals("[1,2,3]", head.toString());
            head = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3));
            Assertions.assertEquals("[1,2,3,4,5]", head.toString());
            head = new TreeNode(1, new TreeNode(2, null, new TreeNode(5)), new TreeNode(3));
            Assertions.assertEquals("[1,2,3,null,5]", head.toString());
            head = new TreeNode(1, new TreeNode(2, null, new TreeNode(5)), null);
            Assertions.assertEquals("[1,2,null,null,5]", head.toString());
            head = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5, new TreeNode(6), new TreeNode(7))), new TreeNode(3));
            Assertions.assertEquals("[1,2,3,4,5,null,null,null,null,6,7]", head.toString());

            head = new TreeNode(1, null, new TreeNode(3, null, new TreeNode(6)));
            Assertions.assertEquals("[1,null,3,null,6]", head.toString());
        }

        @Test
        public void testFrom() {
            Assertions.assertEquals("[1,2]", TreeNode.from(1, 2).toString());
            Assertions.assertEquals("[1,2,null,4]", TreeNode.from(1, 2, null, 4).toString());
            Assertions.assertEquals("[1,2,null,4,5]", TreeNode.from(1, 2, null, 4, 5, null, null).toString());
            Assertions.assertEquals("[1,null,3]", TreeNode.from(1, null, 3, null, null, null, 6).toString());
            Assertions.assertEquals("[5,4,1,null,1,null,4,2,null,2]", TreeNode.from(5, 4, 1, null, 1, null, 4, 2, null, 2, null).toString());
        }
    }
}
