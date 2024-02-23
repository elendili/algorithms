package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class FlattenNestedListIterator {


    public static class NestedIterator implements Iterator<Integer> {

        private final List<NestedInteger> nestedList;
        private List<Integer> indexes = new ArrayList<>(Arrays.asList(0));

        public NestedIterator(List<NestedInteger> nestedList) {
            this.nestedList = nestedList;
        }

        @Override
        public Integer next() {
            int out = recurse(nestedList,indexes,new AtomicBoolean(true));
            return out;
        }

        Integer recurse(List<NestedInteger> data, List<Integer> indexes, AtomicBoolean shift) {
            int index = indexes.get(0);
            if(index==data.size()){
                return null;
            }
            NestedInteger d = data.get(index);
            int toReturn;
            if (d.isInteger()) {
                toReturn = d.getInteger();
            } else {
                toReturn = recurse(d.getList(), indexes.subList(1, indexes.size()), shift);
            }
            if(shift.get()){
                if(indexes.size()==1){
                    if(data.size()==index+1){
                        indexes.clear();
                    } else {
                        indexes.set(0, index+1);
                        shift.set(false);
                    }
                }
            }
            return toReturn;
        }

        @Override
        public boolean hasNext() {
            int out = recurse(nestedList,indexes,new AtomicBoolean(false));
            return false;
        }
    }
}


interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}