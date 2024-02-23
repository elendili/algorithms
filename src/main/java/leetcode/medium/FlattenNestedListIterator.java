package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static leetcode.medium.NestedInteger.ni;
import static leetcode.medium.NestedInteger.niList;

public class FlattenNestedListIterator {


    public static class NestedIterator implements Iterator<Integer> {

        private LinkedList<NestedInteger> queue = new LinkedList<>();
        private Integer next;

        public NestedIterator(List<NestedInteger> nestedList) {
            for (NestedInteger ni : nestedList) {
                queue.addLast(ni);
            }
        }

        @Override
        public Integer next() {
            assignNext();
            Integer out = this.next;
            this.next = null;
            return out;
        }

        private Integer getNextOrNull() {
            while (!queue.isEmpty()) {
                NestedInteger c = queue.pollFirst();
                if (c.isInteger()) {
                    return c.getInteger();
                } else {
                    List<NestedInteger> l = c.getList();
                    // add at the beginning of queue in reversed order
                    // thus first element extracted from q will be first element of list
                    for (int i = l.size() - 1; i > -1; i--) {
                        queue.addFirst(l.get(i));
                    }
                }
            }
            return null;
        }

        private void assignNext() {
            if (this.next == null) {
                this.next = getNextOrNull();
            }
        }

        @Override
        public boolean hasNext() {
            assignNext();
            return next != null;
        }

    }

    @Test
    public void test1() {
        List<NestedInteger> input = Arrays.asList(niList(ni(1)));
        Assertions.assertEquals("[1]", toListViaIterator(input).toString());
        input = Arrays.asList(niList());
        Assertions.assertEquals("[]", toListViaIterator(input).toString());
    }

    @Test
    public void test11211() {
//        [[1,1],2,[1,1]]
        List<NestedInteger> input = Arrays.asList(niList(ni(1), ni(1)), ni(2), niList(ni(1), ni(1)));
        Assertions.assertEquals("[1, 1, 2, 1, 1]", toListViaIterator(input).toString());
    }

    @Test
    public void test146() {
//       [1,[4,[6]]]
        List<NestedInteger> input = Arrays.asList(ni(1), niList(ni(4), niList(ni(6))));
        Assertions.assertEquals("[1, 4, 6]", toListViaIterator(input).toString());
    }

    List<Integer> toListViaIterator(List<NestedInteger> input) {
        List<Integer> out = new ArrayList<>();
        NestedIterator iterator = new NestedIterator(input);
        while (iterator.hasNext()) {
            out.add(iterator.next());
        }
        return out;
    }
}


class NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    Integer integer;
    List<NestedInteger> list;

    NestedInteger(Integer integer, List<NestedInteger> list) {
        this.integer = integer;
        this.list = list;
    }

    static NestedInteger ni(Integer integer) {
        return new NestedInteger(integer, null);
    }

    static NestedInteger niList(NestedInteger... varargs) {
        return new NestedInteger(null, Arrays.asList(varargs));
    }

    public boolean isInteger() {
        return integer != null;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return integer;
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return list;
    }
}