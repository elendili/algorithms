package hackerrank.easy;

import hierarchy.Node;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static helpers.TestHelper.assertStdOutAfterStdInput;
import static hierarchy.TreeUtils.getTreeRootFromSystemInput;

public class TreePostOrderTraversal {

    public static void recursive(Node root) {
        if (root != null) {
            recursive(root.left);
            recursive(root.right);
            System.out.print(root.data + " ");
        }
    }

    public static void loop(Node node) {
        LinkedList<Node> stack1 = new LinkedList<>();
        LinkedList<Node> stack2 = new LinkedList<>();
        stack1.addFirst(node);
        while (!stack1.isEmpty()){
            node=stack1.pop();
            stack2.push(node);
            if(node.left!=null)
                stack1.push(node.left);
            if(node.right!=null)
                stack1.push(node.right);
        }
        while(!stack2.isEmpty()){
            node = stack2.pollFirst();
            System.out.print(node.data + " ");
        }

    }

    @Test
    public void testRecursive() {
        assertStdOutAfterStdInput("6\n1\n2\n5\n3\n6\n4", "4 3 6 5 2 1 ",
                () -> recursive(getTreeRootFromSystemInput()));
    }

    @Test
    public void testLoop() {
        assertStdOutAfterStdInput("6\n1\n2\n5\n3\n6\n4", "4 3 6 5 2 1 ",
                () -> loop(getTreeRootFromSystemInput()));
    }

}

