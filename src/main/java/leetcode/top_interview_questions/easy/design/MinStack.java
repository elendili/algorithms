package leetcode.top_interview_questions.easy.design;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-easy/98/design/562/
Design a stack that supports push, pop, top,
and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.

Consider each node in the stack having a minimum value. (Credits to @aakarshmadhavan)

 */
public class MinStack {
    class Node{
        public Node(Node next, Node left_lesser,  Node right_bigger, int value) {
            this.next = next;
            this.right_bigger = right_bigger;
            this.left_lesser = left_lesser;
            this.value = value;
        }

        Node next;
        // minimum linked list
        Node right_bigger; //
        Node left_lesser; // less
        int value;
    }
    Node min;
    Node top;
    final Node bottom;
    /** initialize your data structure here. */
    public MinStack() {
        top=min=bottom=new Node(null,null, null, Integer.MAX_VALUE);
    }

    public void push(int newX) {
        // define min
        Node insertNewToTheLeftOfThis = min;
        // insertNewBeforeThis should be bigger or equal to new value
        while (insertNewToTheLeftOfThis.value<newX) {
            insertNewToTheLeftOfThis = insertNewToTheLeftOfThis.right_bigger;
        }
        // create
        top = new Node(top,insertNewToTheLeftOfThis.left_lesser,insertNewToTheLeftOfThis,newX);
        // fix minimum linked list
        // min <---> left_lesser < --- current --> right_bigger
        // current.lessThanThis < --- newX/top --> current.biggerThanThis
        if(insertNewToTheLeftOfThis.left_lesser!=null){
            insertNewToTheLeftOfThis.left_lesser.right_bigger=top;
            insertNewToTheLeftOfThis.left_lesser=top;
        }
        if(min==insertNewToTheLeftOfThis){
            min=top;
        }
    }

    public void pop() {
        Node removed = top;
        if(removed.next!=null){
            this.top=removed.next;
            if(removed==min){
                min=removed.right_bigger;
            } else {
                // removed.lessThanThis < --- x --> removed.biggerThanThis
                Node left = removed.left_lesser;
                Node right = removed.right_bigger;
                if(left!=null){
                    left.right_bigger =right;
                }
                if(right!=null){
                    right.left_lesser =left;
                }
            }
        }
    }

    public int top() {
        return top!=bottom?top.value:0;
    }

    public int getMin() {
        return min!=bottom?min.value:0;
    }

    @Test
    public void test() {
        MinStack minStack = new MinStack();
        assertEquals(0,minStack.top());
        assertEquals(0,minStack.getMin());
        minStack.push(1);
        assertEquals(1,minStack.top());
        assertEquals(1,minStack.getMin());
        minStack.push(0);
        assertEquals(0,minStack.top());
        assertEquals(0,minStack.getMin());
        minStack.pop();
        assertEquals(1,minStack.top());
        assertEquals(1,minStack.getMin());
        minStack.push(2);
        assertEquals(2,minStack.top());
        assertEquals(1,minStack.getMin());
        minStack.push(3);
        assertEquals(3,minStack.top());
        assertEquals(1,minStack.getMin());
        minStack.push(-1);
        assertEquals(-1,minStack.top());
        assertEquals(-1,minStack.getMin());
        minStack.push(2);
        assertEquals(2,minStack.top());
        assertEquals(-1,minStack.getMin());
        minStack.pop();
        assertEquals(-1,minStack.top());
        assertEquals(-1,minStack.getMin());
        minStack.pop();
        assertEquals(3,minStack.top());
        assertEquals(1,minStack.getMin());
        minStack.pop();
        assertEquals(2,minStack.top());
        assertEquals(1,minStack.getMin());
        minStack.pop();
        assertEquals(1,minStack.top());
        assertEquals(1,minStack.getMin());
    }
}
