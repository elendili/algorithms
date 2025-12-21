package leetcode.medium;

public class CloneNaryTree_1490 {
    public Node cloneTree(Node root) {
        if (root==null) {
            return root;
        }
        Node newRoot = new Node(root.val);
        for (Node n :root.children){
            newRoot.children.add(cloneTree(n));
        }
        return newRoot;
    }
}
