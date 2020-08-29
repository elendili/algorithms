package leetcode.top_interview_questions.easy.trees;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

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
    }

    public static TreeNode from(Integer... ints) {
        int n = ints.length;
        assert n > 0;
        final List<TreeNode> list = Arrays.stream(ints)
                .map(i -> i != null ? new TreeNode(i) : null)
                .collect(toList());

        for (int i = 0; i < n; i++) {
            TreeNode v = list.get(i);
            if (v != null) {
                int leftI = 2 * i + 1;
                if (leftI < n && list.get(leftI)!=null) {
                    v.left = list.get(leftI);
                }
                int rightI = 2 * i + 2;
                if (rightI < n && list.get(rightI)!=null) {
                    v.right = list.get(rightI);
                }
            }
        }

        return list.get(0);
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
            Assertions.assertEquals("[1,null,3,null,null,null,6]", head.toString());
        }

        @Test
        public void testFrom() {
            Assertions.assertEquals("[1,2]", TreeNode.from(1, 2).toString());
            Assertions.assertEquals("[1,2,null,4]", TreeNode.from(1, 2,null,4).toString());
            Assertions.assertEquals("[1,2,null,4,5]", TreeNode.from(1, 2,null,4,5,null,null).toString());
            Assertions.assertEquals("[1,null,3,null,null,null,6]", TreeNode.from(1, null,3,null,null,null,6).toString());
//            Assertions.assertEquals("[5,4,1,null,1,null,4,2,null,2,null]", TreeNode.from(5,4,1,null,1,null,4,2,null,2,null).toString());
        }
    }
}
