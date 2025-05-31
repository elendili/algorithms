package leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuessTheMajorityInaHiddenArray_1538 {
    /**
     *   0 - fair
     *   2 - 1 vs 3
     *   4 - all
     *
     *   1,0,1,0,1
     *   0:3->0
     *   0:1+3:4->0
     *   0:2+4-> 2
     *   0+2:4-> 2
     *
     *   1,1,0,0,1
     *   0,0
     *
     */
    int cntEqual = 1, cntDiffer = 0, indexDiffer = -1;
    private void f(boolean equal, int i) {
        if (equal) {
            cntEqual++;
        } else {
            cntDiffer++;
            indexDiffer = i;
        }
    }
    public int guessMajority(ArrayReader reader) {
        int n = reader.length();
        int query0123 = reader.query(0, 1, 2, 3);
        int query1234 = reader.query(1, 2, 3, 4);
        f(query1234 == query0123, 4);
        for (int i = 5; i < n; i++) {
            f(reader.query(1, 2, 3, i) == query0123, i);
        }
        f(reader.query(0, 2, 3, 4) == query1234, 1);
        f(reader.query(0, 1, 3, 4) == query1234, 2);
        f(reader.query(0, 1, 2, 4) == query1234, 3);
        return cntEqual > cntDiffer ? 0 : cntDiffer > cntEqual ? indexDiffer : -1;
    }

    @org.junit.jupiter.api.Test
    public void test(){
        assertEquals(0, guessMajority(new ArrayReader(new int[]{1,0,1,0,1})));
        assertEquals(2, guessMajority(new ArrayReader(new int[]{0,0,1,0,1,1,1,1})));
        assertEquals(0, guessMajority(new ArrayReader(new int[]{0,0,1,1,0})));
        assertEquals(-1, guessMajority(new ArrayReader(new int[]{1,0,1,0,1,0,1,0})));
    }

}

class ArrayReader {
    private final int[] array;

    ArrayReader(int[] array) {
        this.array = array;
    }

    int length() {
        return array.length;
    }

    int query(int a, int b, int c, int d) {
        int count = 0;
        if (array[a] == 1) {
            count++;
        }
        if (array[b] == 1) {
            count++;
        }
        if (array[c] == 1) {
            count++;
        }
        if (array[d] == 1) {
            count++;
        }
        if (count == 0 || count == 4) {
            return 4;
        }
        if (count == 1 || count == 3) {
            return 2;
        }
        if (count == 2) {
            return 0;
        }
        throw new RuntimeException("oh no");
    }
}