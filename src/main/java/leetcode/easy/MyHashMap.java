package leetcode.easy;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/design-hashmap
class MyHashMap {
    class Node{
        final int k,v;

        Node(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }
    private final LinkedList<Node>[] a;

    public MyHashMap() {
        a = new LinkedList[1<<10];
    }

    public void put(int key, int value) {
        int id = id(key);
        LinkedList<Node> list = a[id];
        if(list==null){
            list = new LinkedList<>();
        }
        list.push(new Node(key,value));
        a[id]=list;
    }

    public int get(int k) {
        int id = id(k);
        LinkedList<Node> list = a[id];
        if(list!=null) {
            for (Node n : list) {
                if (n.k == k) {
                    return n.v;
                }
            }
        }
        return -1;
    }

    public void remove(int k) {
        int id = id(k);
        LinkedList<Node> list = a[id];
        if(list!=null) {
            list.removeIf(n -> n.k == k);
        }
    }

    private int id(int k){
        return Math.abs(k)%a.length;
    }


    @Test
    public void test(){
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1); // The map is now [[1,1]]
        myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
        assertEquals(1, myHashMap.get(1));    // return 1, The map is now [[1,1], [2,2]]
        assertEquals(-1, myHashMap.get(3));    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
        myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
        assertEquals(1, myHashMap.get(2));    // return 1, The map is now [[1,1], [2,1]]
        myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
        assertEquals(-1, myHashMap.get(2));    // return -1 (i.e., not found), The map is now [[1,1]]
        myHashMap.remove(12);
    }
}
