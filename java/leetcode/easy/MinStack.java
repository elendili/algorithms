package leetcode.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// https://leetcode.com/problems/min-stack
public class MinStack {

    static class N {
        @Override
        public String toString() {
            return "N{" + id + ",v=" + val +
                    (smaller == null ? "" : ",s=" + smaller.val) +
                    (bigger == null ? "" : ",b=" + bigger.val) +
                    (next == null ? "" : ",n=" + next.val) +
                    '}';
        }

        N next;
        N smaller;
        N bigger;
        int val;
        int id;
    }

    private int counter;
    N top;
    N min;
    final N mock;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        min = top = mock = new N();
        top.val = Integer.MAX_VALUE;
        top.id = counter++;
    }

    public void push(int x) {
        N n = new N();
        n.val = x;
        n.next = top;
        n.id = counter++;
        top = n;
        // search place in sorted thing
        if (x <= min.val) {
            min.smaller = n;
            n.bigger = min;
            min = n;
        } else {
            N cn = min;
            while (cn.bigger != mock && x > cn.val) {
                cn = cn.bigger;
            }
            n.smaller = cn;
            n.bigger = cn.bigger;
            cn.bigger = n;
        }
    }

    public void pop() {
        if (top != mock) {
            if (top == min) {
                min = min.bigger;
            } else {
                N smaller = top.smaller;
                N bigger = top.bigger;
                if (smaller != null) {
                    smaller.bigger = bigger;
                }
                if (bigger != null) {
                    bigger.smaller = smaller;
                }
            }
            top = top.next;
        }
    }

    public int top() {
        return top.val;
    }

    public int getMin() {
        return min.val;
    }

    @Test
    public void test() {
        MinStack minStack = new MinStack();
        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);
        Assertions.assertEquals(2147483647, minStack.top());
        minStack.pop();
        Assertions.assertEquals(2147483646, minStack.getMin());
        minStack.pop();
        Assertions.assertEquals(2147483646, minStack.getMin());
        minStack.pop();
        minStack.push(2147483647);
        Assertions.assertEquals(2147483647, minStack.top());
        Assertions.assertEquals(2147483647, minStack.getMin());
        minStack.push(-2147483648);
        Assertions.assertEquals(-2147483648, minStack.top());
        Assertions.assertEquals(-2147483648, minStack.getMin());
        minStack.pop();
        Assertions.assertEquals(2147483647, minStack.getMin());
    }

}
