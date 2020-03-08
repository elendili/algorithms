package crackingCodeInterview.SingleLinkedList;

public class Node {
    Node next;
    int data;

    Node data(int i) {
        this.data = i;
        return this;
    }

    Node next(Node n) {
        this.next = n;
        return this;
    }
}
