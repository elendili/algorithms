package hierarchy;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class TreeUtils {

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static Node getTreeRootFromSystemInput() {
        try (Scanner scan = new Scanner(System.in)) {
            int t = scan.nextInt();
            Node root = null;
            while (t-- > 0) {
                int data = scan.nextInt();
                root = insert(root, data);
            }
            return root;
        }
    }

    public static Node getTreeRootFromNlString(String string) {
        try (Scanner scan = new Scanner(new StringReader(string))) {
            int t = scan.nextInt();
            Node root = null;
            while (t-- > 0) {
                int data = scan.nextInt();
                root = insert(root, data);
            }
            return root;
        }
    }

    public static Node getTreeRootFromSpacedString(String string) {
        AtomicReference<Node> root = new AtomicReference<>();
        Arrays.stream(string.split("\\s+"))
                .map(Integer::valueOf).forEach(i ->
                root.set(insert(root.get(), i))
        );
        return root.get();
    }

}
