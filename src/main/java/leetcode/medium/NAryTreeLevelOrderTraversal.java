package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/*
https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/613/week-1-august-1st-august-7th/3871/
 */
public class NAryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(Node node) {
        List<List<Integer>> out = new ArrayList<>();
        if (node != null) {
            levelOrder(Collections.singletonList(node), out);
        }
        return out;
    }

    public void levelOrder(List<Node> nodes, List<List<Integer>> outs) {
        if (!nodes.isEmpty()) {
            List<Integer> curLevel = new ArrayList<>();
            List<Node> nextLevel = new ArrayList<>();
            for (Node node : nodes) {
                curLevel.add(node.val);
                nextLevel.addAll(node.children);
            }
            outs.add(curLevel);
            levelOrder(nextLevel, outs);
        }
    }

    @Test
    public void levelOrderTest() {
        Assertions.assertEquals("[]",
                levelOrder(Node.from("[]")).toString().replaceAll(" ", ""));
        Assertions.assertEquals("[[1],[3,2,4],[5,6]]",
                levelOrder(Node.from("[1,null,3,2,4,null,5,6]")).toString().replaceAll(" ", ""));
        Assertions.assertEquals("[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]",
                levelOrder(Node.from("[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]"))
                        .toString().replaceAll(" ", ""));
    }

    @Test
    public void getNodeTest() {
        Assertions.assertNull(Node.from("[]"));
        Assertions.assertEquals("1", Node.from("[1]").toString());
        Assertions.assertEquals("1[2]", Node.from("[1,null,2]").toString());
        Assertions.assertEquals("1[2,3]", Node.from("[1,null,2,3]").toString());
        Assertions.assertEquals("1[2,3]", Node.from("[1,null,2,3,null,null,null]").toString());
        Assertions.assertEquals("1[3[5,6],2,4]", Node.from("[1,null,3,2,4,null,5,6]").toString());
        Assertions.assertEquals("1[2,3[6,7[11[14]]],4[8[12]],5[9[13],10]]",
                Node.from("[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]").toString());
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }

    @Override
    public String toString() {
        String childrenString = (children == null || children.isEmpty()) ? "" : children.toString();
        return (val + childrenString).replaceAll(" ", "");
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node)) {
            return false;
        }
        Node other = (Node) obj;
        if (this.val != other.val) {
            return false;
        }
        if (this.children == null ^ other.children == null) {
            return false;
        }
        if (this.children != null) {
            if (this.children.size() != other.children.size()) {
                return false;
            }
            for (int i = 0; i < this.children.size(); i++) {
                if (!this.children.get(i).equals(other.children.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    // "[1,null,3,2,4,null,5,6]"
    static Node from(String string) {
        string = string
                .replaceAll("\\[", "")
                .replaceAll("\\]", "");
        Node root = new Node(-1, new ArrayList<>());
        Node cur = root;
        Queue<Node> childrenQ = new ArrayDeque<>();
        childrenQ.add(cur);
        String[] tokens = string.split(",");
        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i];
            if (s.equals("")) {
                return null;
            } else if (s.equals("null")) {
                cur = childrenQ.poll();
            } else {
                int v = Integer.parseInt(s);
                if (i == 0) {
                    root.val = v;
                } else {
                    Node kid = new Node(v, new ArrayList<>());
                    cur.children.add(kid);
                    childrenQ.add(kid);
                }
            }
        }
        return root;
        // queue  FIFO
        // stack  LIFO
    }
};
