package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnapshotArray_1146 {
    class Elem {
        int snapId;
        int val;

        Elem(int s, int v) {
            snapId = s;
            val = v;
        }
    }

    class SnapshotArray {
        List<Elem>[] snapArr;
        int currSnapId;

        public SnapshotArray(int length) {
            snapArr = new List[length];
            currSnapId = 0;
        }

        public void set(int index, int val) {
            if (snapArr[index] == null) {
                snapArr[index] = new ArrayList<Elem>();
            }

            List<Elem> elems = snapArr[index];

            if (elems.isEmpty() || elems.get(elems.size() - 1).snapId != currSnapId) {
                elems.add(new Elem(currSnapId, val));
            } else {
                elems.get(elems.size() - 1).val = val;
            }
        }

        public int snap() {
            return currSnapId++;
        }

        public int get(int index, int snap_id) {
            List<Elem> elems = snapArr[index];
            if (elems == null) { return 0; }

            int low = 0;
            int high = elems.size() - 1;

            while (low <= high) {
                int mid = (low + high) / 2;

                if (elems.get(mid).snapId <= snap_id) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return high < 0 ? 0 : elems.get(high).val;
        }
    }

    /**
     * Input: ["SnapshotArray","set","snap","set","get"]
     * [[3],[0,5],[],[0,6],[0,0]]
     * Output: [null,null,0,null,5]
     * Explanation:
     * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
     * snapshotArr.set(0,5); ¡ // Set array[0] = 5
     * snapshotArr.snap();  // Take a snapshot, return snap_id = 0
     * snapshotArr.set(0,6);
     * snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
     */
    @org.junit.jupiter.api.Test
    public void test() {
        SnapshotArray array = new SnapshotArray(3);
        array.set(0, 5);
        assertEquals(5, array.get(0, 0));
        assertEquals(0, array.snap());
        array.set(0, 6);
        assertEquals(5, array.get(0, 0));
        assertEquals(6, array.get(0, 1));
        array.set(0, 8);
        assertEquals(8, array.get(0, 1));
        assertEquals(1, array.snap());
        array.set(0, 9);
        assertEquals(5, array.get(0, 0));
        assertEquals(8, array.get(0, 1));
        assertEquals(9, array.get(0, 2));
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        SnapshotArray array = new SnapshotArray(2);
        assertEquals(0, array.snap());
        assertEquals(0, array.get(1, 0));
        assertEquals(0, array.get(0, 0));
        array.set(1, 8);
        assertEquals(0, array.get(1, 0));
        array.set(0, 20);
        assertEquals(0, array.get(0, 0));
        array.set(0, 7);
    }

}
