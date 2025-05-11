package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/first-unique-number
 */
public class FirstUniqueNumber_1429 {

    class FirstUnique {
        Map<Integer, Node> map;
        final Node head, tail;

        public FirstUnique(int[] nums) {
            map = new HashMap<>();
            head = new Node(0);
            tail = new Node(0);
            head.right = tail;
            tail.left = head;
            for (int num : nums) {
                add(num);
            }
        }

        public int showFirstUnique() {
            if (head.right == tail){
                return -1;
            }
            return head.right.val;
        }

        public void add(int value) {
            Node n = map.get(value);
            if (n!=null){
                if (n.left!=null) {
                    // remove from queue
                    n.left.right = n.right;
                    n.right.left = n.left;
                    // make as dupe node
                    n.left = null;
                    n.right = null;
                }
            } else {
                // insert into tail
                n = new Node(value);
                n.right = tail;
                n.left = tail.left;
                tail.left.right = n;
                tail.left = n;
                map.put(value, n);
            }

        }

        static class Node{
            Node left;
            Node right;
            int val;
            public Node(int value) {
                this.val = value;
            }
        }
    }


    @org.junit.jupiter.api.Test
    public void test(){
        FirstUnique sut = new FirstUnique(new int[]{2,3,5});
        assertEquals(2,sut.showFirstUnique());
        sut.add(5);
        assertEquals(2,sut.showFirstUnique());
        sut.add(2);
        assertEquals(3,sut.showFirstUnique());
        sut.add(3);
        assertEquals(-1,sut.showFirstUnique());
    }
    
    @org.junit.jupiter.api.Test
    public void test2(){
        FirstUnique sut = new FirstUnique(new int[]{7,7,7,7,7,7});
        assertEquals(-1,sut.showFirstUnique());
        sut.add(7);
        sut.add(3);
        sut.add(3);
        sut.add(7);
        sut.add(17);
        assertEquals(17,sut.showFirstUnique());
    }
}
