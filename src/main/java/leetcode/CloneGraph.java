package leetcode;

import org.junit.jupiter.api.Test;

import java.util.*;

/*

Test case format:
For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.
Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

1 <= Node.val <= 100
Node.val is unique for each node.
Number of Nodes will not exceed 100.
There is no repeated edges and no self-loops in the graph.
The Graph is connected and all nodes can be visited starting from the given node.
* */
public class CloneGraph {
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if(node==null){
            return null;
        }
        return cloneGraph(node, new Node[101]);
    }

    public Node cloneGraph(Node node, Node[] cloned) {
        if (cloned[node.val]!=null) {
            return cloned[node.val];
        }
        Node outN = new Node(node.val);
        cloned[outN.val] = outN;
        ArrayList<Node> clonedNeighbors = new ArrayList<>();
        for (Node n : node.neighbors) {
            Node cN = cloneGraph(n, cloned);
            clonedNeighbors.add(cN);
        }
        outN.neighbors = clonedNeighbors;
        return outN;
    }


    public Node cloneGraphBFS(Node node) {
        if (node==null){return null;}
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node); // for original ones

        Node[] cloned = new Node[101];
        Node root = new Node(node.val);
        cloned[node.val] = root; // track with started cloning

        while(!queue.isEmpty()){
            Node n = queue.poll();
            for(Node n2:n.neighbors){
                if(cloned[n2.val]==null){
                    queue.add(n2);
                    Node nn2 = new Node(n2.val);
                    cloned[n2.val]=nn2;
                }
                cloned[n.val].neighbors.add( cloned[n2.val] );
            }
        }
        return root;
    }

    @Test
    public void test(){
        Node _1 = new Node(1);
        Node _2 = new Node(2);
        _1.neighbors.add(_2);
        _2.neighbors.add(_1);
        cloneGraph(_1);
        cloneGraphBFS(_1);
    }
}
