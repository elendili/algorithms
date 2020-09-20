package leetcode.top_interview_questions.medium.design;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.Matchers.is;

/*
https://leetcode.com/explore/featured/card/top-interview-questions-medium/112/design/813/

Implement the RandomizedSet class:

bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
int getRandom() Returns a random element from the current set of elements
(it's guaranteed that at least one element exists when this method is called).
Each element must have the same probability of being returned.
Follow up: Could you implement the functions of the class with each function works in average O(1) time?



Example 1:

Input
["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
[[], [1], [2], [2], [], [1], [2], []]
Output
[null, true, false, true, 2, true, false, 2]

Explanation
RandomizedSet randomizedSet = new RandomizedSet();
randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
randomizedSet.insert(2); // 2 was already in the set, so return false.
randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.


Constraints:

-2^31 <= val <= 2^31 - 1
At most 10^5 calls will be made to insert, remove, and getRandom.
There will be at least one element in the data structure when getRandom is called
 */
public class InsertDeleteGetRandomO1 {
    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */

    static class RandomizedSet {
        private static final ThreadLocalRandom tlr = ThreadLocalRandom.current();
        private HashMap<Integer, Integer> map = new HashMap<>();
        private ArrayList<Integer> list = new ArrayList<>(1 << 10);

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            } else {
                list.add(val);
                map.put(val, list.size() - 1);
                return true;
            }
        }

        public boolean remove(int val) {
            if (map.containsKey(val)) {
                int index = map.remove(val);
                if (index < list.size() - 1) {
                    list.set(index, list.get(list.size() - 1)); // copy last to new place
                    map.put(list.get(index), index); // update index of previously last element
                }
                list.remove(list.size() - 1); //  remove last
                return true;
            } else {
                return false;
            }
        }

        public int getRandom() {
            return list.get(tlr.nextInt(list.size()));
        }
    }

    @Test
    public void test() {
        RandomizedSet rs = new RandomizedSet();
        Assertions.assertTrue(rs.insert(1)); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
        Assertions.assertFalse(rs.remove(2)); // Returns false as 2 does not exist in the set.
        Assertions.assertTrue(rs.insert(2)); // Inserts 2 to the set, returns true. Set now contains [1,2].
        MatcherAssert.assertThat(rs.getRandom(), Matchers.either(is(1)).or(is(2)));
        Assertions.assertTrue(rs.remove(1)); // Removes 1 from the set, returns true. Set now contains [2].
        Assertions.assertFalse(rs.insert(2)); // 2 was already in the set, so return false.
        Assertions.assertEquals(2, rs.getRandom()); // Since 2 is the only number in the set, getRandom() will always return 2.
        Assertions.assertTrue(rs.remove(2));
        Assertions.assertFalse(rs.remove(2));

        Assertions.assertTrue(rs.insert(1));
        Assertions.assertTrue(rs.insert(2));
        Assertions.assertTrue(rs.remove(2));
    }

    @Test
    public void t() {
        Assertions.assertEquals(3, (23) % 20);
        Assertions.assertEquals(-3, (-23) % 20);
    }

}

