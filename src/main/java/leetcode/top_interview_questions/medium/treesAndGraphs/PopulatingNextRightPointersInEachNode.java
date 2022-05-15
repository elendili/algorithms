package leetcode.top_interview_questions.medium.treesAndGraphs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/108/trees-and-graphs/789/
You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node.
If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.


Follow up:

You may only use constant extra space.
Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.


Example 1:



Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]
Explanation: Given the above perfect binary tree (Figure A),
your function should populate each next pointer to point to its next right node, just like in Figure B.
The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.


Constraints:

The number of nodes in the given tree is less than 4096.
-1000 <= node.val <= 1000
 */
public class PopulatingNextRightPointersInEachNode {
    public Node connect(Node root) {
        recurse(root,null);
        return root;
    }
    void recurse(Node node, Node parent){
        if(node!=null){
            if(parent!=null){
                if (parent.left==node){
                    node.next = parent.right;
                } else if (parent.right==node && parent.next!=null){
                    node.next=parent.next.left;
                }
            }
            recurse(node.left,node);
            recurse(node.right,node);
        }
    }

    @Test
    public void test(){
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        connect(root);
        Assertions.assertSame(root.left.next, root.right);
        Assertions.assertNull(root.right.next);
        Assertions.assertSame(root.left.left.next,  root.left.right);
        Assertions.assertSame(root.left.right.next, root.right.left);
        Assertions.assertSame(root.right.left.next, root.right.right);
        Assertions.assertNull(root.right.right.next);
    }

    @Test
    public void test2(){
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        connect(root);
        Assertions.assertSame(root.left.next, root.right);
        Assertions.assertNull(root.right.next);
        Assertions.assertSame(root.left.left.next,  root.left.right);
        Assertions.assertSame(root.left.right.next, root.right.left);
        Assertions.assertNull(root.right.left.next);
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
