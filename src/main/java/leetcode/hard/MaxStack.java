package leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class MaxStack {

    record ValueAndIdx(int val, int idx) {
    }

    Deque<ValueAndIdx> stack;
    PriorityQueue<ValueAndIdx> pq;
    int idx;
    Set<Integer> deleted;

    public MaxStack() {
        stack = new ArrayDeque<>();
        pq = new PriorityQueue<>((a, b) -> {
            int diff = b.val - a.val;
            return diff == 0 ? b.idx - a.idx : diff;
        });
        deleted = new HashSet<>();
    }

    public void push(int x) {
        ValueAndIdx vai = new ValueAndIdx(x, idx++);
        stack.push(vai);
        pq.add(vai);
    }

    public int pop() {
        ValueAndIdx vai = stack.pop();
        deleted.add(vai.idx);
        cleanDeleted();
        return vai.val;
    }

    public int top() {
        return stack.peek().val();
    }

    public int peekMax() {
        return pq.peek().val;
    }

    public int popMax() {
        ValueAndIdx vai = pq.poll();
        deleted.add(vai.idx);
        cleanDeleted();
        return vai.val;
    }

    private void cleanDeleted() {
        while (!pq.isEmpty() && deleted.contains(pq.peek().idx)) {
            deleted.remove(pq.poll().idx);
        }
        while (!stack.isEmpty() && deleted.contains(stack.peek().idx)) {
            deleted.remove(stack.poll().idx);
        }
    }

    @Test
    public void test() {
        /*
        Input
["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
[[], [5], [1], [5], [], [], [], [], [], []]
Output
[null, null, null, null, 5, 5, 1, 5, 1, 5]
         */
        MaxStack ms = new MaxStack();
        ms.push(5);
        ms.push(1);
        ms.push(5);
        Assertions.assertEquals(5, ms.top());
        Assertions.assertEquals(5, ms.popMax());
        Assertions.assertEquals(1, ms.top());
        Assertions.assertEquals(5, ms.peekMax());
        Assertions.assertEquals(1, ms.pop());
        Assertions.assertEquals(5, ms.top());
    }

    @Test
    public void test2() {
        MaxStack ms = new MaxStack();
        ms.push(1);
        ms.push(2);
        ms.push(3);
        Assertions.assertEquals(3, ms.top());
        Assertions.assertEquals(3, ms.popMax());
        Assertions.assertEquals(2, ms.top());
        Assertions.assertEquals(2, ms.peekMax());
        Assertions.assertEquals(2, ms.pop());
        Assertions.assertEquals(1, ms.pop());
    }

    @Test
    public void test3() {
        MaxStack ms = new MaxStack();
        ms.push(3);
        ms.push(3);
        ms.push(2);
        ms.push(2);
        ms.push(1);
        ms.push(1);
        Assertions.assertEquals(1, ms.top());
        Assertions.assertEquals(3, ms.popMax());
        Assertions.assertEquals(1, ms.top());
        Assertions.assertEquals(3, ms.peekMax());
        Assertions.assertEquals(1, ms.pop());
        Assertions.assertEquals(1, ms.pop());
        Assertions.assertEquals(2, ms.pop());
        Assertions.assertEquals(3, ms.popMax());
        Assertions.assertEquals(2, ms.pop());
    }

    @Test
    public void test4() {
        /*
        Input
["MaxStack","push","push","push","popMax","popMax","top"]
[[],[5],[1],[-5],[],[],[]]

Use Testcase
Output
[null,null,null,null,5,1,1]
Expected
[null,null,null,null,5,1,-5]
         */
        MaxStack ms = new MaxStack();
        ms.push(5);
        ms.push(1);
        ms.push(-5);
        Assertions.assertEquals(5, ms.popMax());
        Assertions.assertEquals(1, ms.popMax());
        Assertions.assertEquals(-5, ms.top());
    }
}


/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */