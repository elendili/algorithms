package leetcode.medium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/?envType=study-plan-v2&envId=premium-algo-100
 */
public class InsertIntoSortedCircularLinkedList_708 {
    private static class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }

        @Override
        public String toString() {
            return "{"+ val + " -> " + next.val + '}';
        }
    }

    public Node insert(Node head, int insertVal) {
        if (head == null) {
            head = new Node(insertVal);
            head.next = head;
        } else {
            Node prev = head;
            Node cur = prev.next;
            boolean toInsert = false;
            do {
                if (prev.val <= insertVal && insertVal <= cur.val) {
                    toInsert = true;
                } else if (prev.val > cur.val) {
                    if (prev.val <= insertVal || insertVal <= cur.val) {
                        toInsert = true;
                    }
                } else if (cur == head) {
                    toInsert = true;
                }
                if (toInsert) {
                    prev.next = new Node(insertVal, cur);
                    break;
                }
                prev = cur;
                cur = cur.next;
            } while (prev != head);
        }
        return head;
    }

    @Test
    public void test0() {
        Node n = insert(null, 1);
        assertEquals(1, n.val);
    }

    @Test
    public void test1() {
        Node head = oneNode();
        insert(head, 1);
        assertNotSame(head, head.next);
        assertEquals(head.val, head.next.val);
    }

    @Test
    public void test2() {
        Node head = oneNode();
        insert(head, 2);
        assertEquals(1, head.val);
        assertEquals(2, head.next.val);
    }


    @Test
    public void test3() {
        Node head = oneNode();
        insert(head, -1);
        assertEquals(1, head.val);
        assertEquals(-1, head.next.val);
    }

    @Test
    public void test4() {
        Node head = twoNode();
        insert(head, 1);
        assertEquals(1, head.val);
        assertEquals(1, head.next.val);
        assertEquals(2, head.next.next.val);
        assertEquals(1, head.next.next.next.val);
    }

    @Test
    public void test5() {
        Node head = twoNode();
        insert(head, 2);
        assertEquals(1, head.val);
        assertEquals(2, head.next.val);
        assertEquals(2, head.next.next.val);
        assertEquals(1, head.next.next.next.val);
    }

    @Test
    public void test6() {
        Node head = twoNode();
        insert(head, -1);
        assertEquals(1, head.val);
        assertEquals(2, head.next.val);
        assertEquals(-1, head.next.next.val);
        assertEquals(1, head.next.next.next.val);
    }
    
    @Test
    public void test61() {
        Node head = twoNode().next;
        insert(head, -1);
        assertEquals(2, head.val);
        assertEquals(-1, head.next.val);
        assertEquals(1, head.next.next.val);
        assertEquals(2, head.next.next.next.val);
    }
    @Test
    public void test341() {
        Node n1=new Node(1);
        Node n4=new Node(4, n1);
        Node n3=new Node(3, n4);
        n1.next = n3;
        Node head = n3;
        
        insert(head, 2);
        assertEquals(3, head.val);
        assertEquals(4, head.next.val);
        assertEquals(1, head.next.next.val);
        assertEquals(2, head.next.next.next.val);
    }

    @Test
    public void test7() {
        Node head = twoNode();
        insert(head, 3);
        assertEquals(1, head.val);
        assertEquals(2, head.next.val);
        assertEquals(3, head.next.next.val);
        assertEquals(1, head.next.next.next.val);
    }

    Node oneNode() {
        Node head = new Node(1);
        head.next = head;
        return head;
    }

    Node twoNode() {
        Node head = new Node(1);
        head.next = new Node(2, head);
        return head;
    }
}
