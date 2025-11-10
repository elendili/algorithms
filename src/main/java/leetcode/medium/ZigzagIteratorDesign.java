package leetcode.medium;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZigzagIteratorDesign {
    public class ZigzagIterator {
        private final List<?>[] lists;
        private final int[] indexes;
        private int currentList = 0;

        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            this.lists = new List[]{v1, v2};
            this.indexes = new int[2];
        }

        public int next() {
            int initList = currentList;
            while (indexes[currentList] >= lists[currentList].size()) {
                currentList = (currentList + 1) % lists.length;
                if (currentList == initList) {
                    return -1; // all lists are exhausted
                }
            }
            List<Integer> l = (List<Integer>) lists[currentList];
            int out = l.get(indexes[currentList]);
            indexes[currentList]++;
            currentList = (currentList + 1) % lists.length;
            return out;
        }

        public boolean hasNext() {
            for (int i = 0; i < lists.length; i++) {
                List<?> list = lists[i];
                if (indexes[i] < list.size()) {
                    return true;
                }
            }
            return false;
        }
    }

    @org.junit.jupiter.api.Test
    public void test() {
        ZigzagIterator zigzagIterator = new ZigzagIterator(List.of(1, 2), List.of(3, 4, 5, 6));
        String actual = "";
        while (zigzagIterator.hasNext()) {
            actual += zigzagIterator.next() + ",";
        }
        assertEquals("1,3,2,4,5,6,", actual);
    }
}
