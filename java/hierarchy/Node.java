package hierarchy;

import java.util.Optional;

public class Node {
    public Node left;
    public Node right;
    public int data;

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    @Override
    public String toString() {
        return " " + this.data + ": [" +
                Optional.ofNullable(left).map(n -> "" + n.data).orElse("")
                + "," +
                Optional.ofNullable(right).map(n -> "" + n.data).orElse("")
                + "]";
    }
}
