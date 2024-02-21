package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class FindDuplicateSubtrees {

    public static class TreeNode {
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
    }

    String inOrderTraversalWithFillingMaps(TreeNode root,
                                           Map<String, List<TreeNode>> idMap,
                                           Map<TreeNode, String> visited) {
        if (root == null) {
            return "";
        }
        String id = visited.get(root);
        if (id != null) {
            return id;
        }
        StringBuilder sb = new StringBuilder();
        if (root.left != null) {
            sb.append("L").append(inOrderTraversalWithFillingMaps(root.left, idMap, visited));
        }
        sb.append('V').append(root.val);
        if (root.right != null) {
            sb.append("R").append(inOrderTraversalWithFillingMaps(root.right, idMap, visited));
        }
        id = sb.toString();
        idMap.compute(id, (k, v) -> {
            v = v == null ? new ArrayList<>() : v;
            v.add(root);
            return v;
        });
        visited.put(root, id);
        return id;
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {

        // create map of node values to nodes itself
        Map<Integer, List<TreeNode>> valueMap = new HashMap<>();
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode cur = s.pop();
            if (cur.left != null) {
                s.push(cur.left);
            }
            if (cur.right != null) {
                s.push(cur.right);
            }
            valueMap.compute(cur.val, (k, v) -> {
                v = v == null ? new ArrayList<>() : v;
                v.add(cur);
                return v;
            });
        }
        // find nodes with same value and create id map for them
        Map<String, List<TreeNode>> idMap = new HashMap<>();
        Map<TreeNode, String> visited = new IdentityHashMap<>();
        for (List<TreeNode> list : valueMap.values()) {
            if (list.size() > 1) {
                for (TreeNode n : list) {
                    inOrderTraversalWithFillingMaps(n, idMap, visited);
                }
            }
        }

        // convert id map to output list
        List<TreeNode> out = new ArrayList<>();
        for (List<TreeNode> list : idMap.values()) {
            if (list.size() > 1) {
                out.add(list.get(0));
            }
        }
        return out;
    }


    @Test
    public void test() {
        // Input: root = [1,2,3,4,null,2,4,null,null,4]
        //Output: [[2,4],[4]]
        TreeNode leftFromRoot = new TreeNode(2, new TreeNode(4), null);
        TreeNode rightFromRoot = new TreeNode(3, new TreeNode(2, new TreeNode(4), null), new TreeNode(4));
        TreeNode root = new TreeNode(1, leftFromRoot, rightFromRoot);
        String actual = findDuplicateSubtrees(root).stream().map(n -> n.val).map(String::valueOf)
                .collect(Collectors.joining(","));
        Assertions.assertEquals("2,4", actual);
    }

    @Test
    public void test1() {
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(1));
        String actual = findDuplicateSubtrees(root).stream().map(n -> n.val).map(String::valueOf)
                .collect(Collectors.joining(","));
        Assertions.assertEquals("1", actual);
    }

    @Test
    public void test2() {
        TreeNode left = new TreeNode(2, new TreeNode(3), null);
        TreeNode right = new TreeNode(2, new TreeNode(3), null);
        TreeNode root = new TreeNode(2, left, right);
        String actual = findDuplicateSubtrees(root).stream().map(n -> n.val).map(String::valueOf)
                .collect(Collectors.joining(","));
        Assertions.assertEquals("2,3", actual);
    }
}
