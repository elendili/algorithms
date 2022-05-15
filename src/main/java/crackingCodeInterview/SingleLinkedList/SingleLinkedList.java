package crackingCodeInterview.SingleLinkedList;

import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

public class SingleLinkedList {
    private final Node root;

    public SingleLinkedList(Node root) {
        this.root = root;
    }

    public Node root() {
        return root;
    }



    public String toString() {
        StringBuilder out = new StringBuilder();
        Node node = root;
        while (node != null) {
            out.append(node.data);
            node = node.next;
            if(node!=null){
                out.append("->");
            }
        }
        return out.toString();
    }

    public LinkedList<Integer> toValueDeque(){
        LinkedList<Integer> list = new LinkedList<>();
        Node node = root();
        while(node!=null){
            list.add(node.data);
            node = node.next;
        }
        return list;
    }

    public static SingleLinkedList from(Integer... ints) {
        return from(asList(ints));
    }

    public static SingleLinkedList from(List<Integer> ints) {
        Node root = new Node();
        Node n = root;
        for (int i = 0; i < ints.size(); i++) {
            n.data(ints.get(i));
            if (i != ints.size() - 1) {
                Node prev = n;
                n = new Node();
                prev.next(n);
            }

        }
        return new SingleLinkedList(root);
    }

}
