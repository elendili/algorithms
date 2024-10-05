package leetcode.medium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer/?envType=study-plan-v2&envId=top-interview-150
 */
public class CopyListWithRandomPointer_138 {

    static public class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        @Override
        public String toString() {
            return ""+val;
        }
    }
    public interface CopyListWithRandomPointer {
        Node copyRandomList(final Node head);
    }
    static class RecursiveSolution implements CopyListWithRandomPointer {
        // src node to copied node
        Map<Node, Node> visited = new IdentityHashMap<>();
        @Override
        public Node copyRandomList(Node head) {
            if(head == null) {
                return null;
            }
            if(visited.containsKey(head)){
                return visited.get(head);
            }
            Node copyHead = new Node(head.val);
            visited.put(head, copyHead);
            copyHead.random = copyRandomList(head.random);
            copyHead.next = copyRandomList(head.next);

            return copyHead;
        }
    }
    static class MySolution implements CopyListWithRandomPointer {
        public Node copyRandomList(final Node head) {
            if(head == null) {
                return null;
            }
            // make map of Nodes to their index -> will help to create random reference
            // make list of nodes?
            Map<Node, Integer> srcNodeToIndexMap = new IdentityHashMap<>();
            List<Node> copiedNodesList = new ArrayList<>();

            srcNodeToIndexMap.put(head, 0);

            final Node copiedHead = new Node(head.val);
            copiedNodesList.add(copiedHead);
            Node copiedCur = copiedHead;

            Node srcCur = head.next;
            int index = 1;
            while(srcCur!=null){
                srcNodeToIndexMap.put(srcCur, index++);
                Node next = new Node(srcCur.val);
                copiedCur.next = next;
                copiedNodesList.add(next);

                copiedCur = copiedCur.next;
                srcCur = srcCur.next;
            }

            srcCur = head;
            index = 0;
            while(srcCur!=null){
                Integer randomIndex = srcNodeToIndexMap.get(srcCur.random);
                copiedCur = copiedNodesList.get(index++);
                if(randomIndex!=null) {
                    copiedCur.random = copiedNodesList.get(randomIndex);
                }
                srcCur = srcCur.next;
            }

            return copiedHead;
        }
    }

    public static Stream<CopyListWithRandomPointer> solutions() {
        return Stream.of(
                new MySolution(),
                new RecursiveSolution()
        );
    }

    @ParameterizedTest
    @MethodSource("solutions")
    public void test(CopyListWithRandomPointer solution){
        Node _0 = new Node(7);
        Node _1 = new Node(13);
        Node _2 = new Node(11);
        Node _3 = new Node(10);
        Node _4 = new Node(1);
        _0.random = null;
        _1.random = _0;
        _2.random = _4;
        _3.random = _2;
        _4.random = _0;

        _0.next = _1;
        _1.next = _2;
        _2.next = _3;
        _3.next = _4;
        // Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
        // Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
        assertEquals(convertToString(_0), convertToString(solution.copyRandomList(_0)));
    }
    @ParameterizedTest
    @MethodSource("solutions")
    public void test2(CopyListWithRandomPointer solution){
//        Input: head = [[1,1],[2,1]]
//        Output: [[1,1],[2,1]]
        Node _0 = new Node(1);
        Node _1 = new Node(2);
        _0.random = _1;
        _1.random = _1;
        _0.next = _1;
        assertEquals(convertToString(_0), convertToString(solution.copyRandomList(_0)));
    }
    @ParameterizedTest
    @MethodSource("solutions")
    public void test3(CopyListWithRandomPointer solution){
//Input: head = [[3,null],[3,0],[3,null]]
//Output: [[3,null],[3,0],[3,null]]
        Node _0 = new Node(3);
        Node _1 = new Node(3);
        Node _2 = new Node(3);
        _1.random = _0;
        _0.next = _1;
        _1.next = _2;
        assertEquals(convertToString(_0), convertToString(solution.copyRandomList(_0)));
    }

    String convertToString(final Node node){
        Map<Node, Integer> srcNodeToIndexMap = new IdentityHashMap<>();
        Node cur = node;
        int index = 0;
        while(cur!=null){
            srcNodeToIndexMap.put(cur, index++);
            cur = cur.next;
        }


        StringBuilder sb = new StringBuilder();
        cur = node;
        while(cur!=null){
            sb.append('[');
            sb.append(cur.val);
            sb.append(", ");
            sb.append(Optional.ofNullable(cur.random).map(srcNodeToIndexMap::get).orElse(null));
            sb.append(']');
            cur = cur.next;
            if(cur!=null){
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
