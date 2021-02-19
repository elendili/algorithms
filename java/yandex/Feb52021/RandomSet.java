package yandex.Feb52021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.Arrays.asList;

/*
Написать множество, хранящее целые числа, поддерживающее следующие операции:
1) добавление значения в множество
2) проверка есть ли значение в множестве
3) удаление значения из множества
4) получение случайного значения из множества.

Все операции должны работать за O(1)
*/
public class RandomSet {
    private final List<Integer> list = new ArrayList<>(); // values for random
    private final Map<Integer, Integer> map = new HashMap<>(); // value to index in list
    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    public void add(int v) {
        if (!map.containsKey(v)) {
            int i = list.size();
            list.add(v);
            map.put(v, i);
        }
    }

    public boolean contains(int v) {
        return map.containsKey(v);
    }

    public int getRandom() {
        assert !list.isEmpty() : "set is empty";
        int i = random.nextInt(list.size());
        return list.get(i);
    }

    public void remove(int v) {
        if (map.containsKey(v)) {
            if (list.size() == 1) {
                map.clear();
                list.clear();
            } else {
                int i = map.get(v);
                map.remove(v);
                if (i == list.size() - 1) {
                    list.remove(list.size() - 1);
                } else {
                    // swap with last
                    int lastV = list.get(list.size() - 1);
                    list.remove(list.size() - 1);
                    list.set(i, lastV);
                    // save new index for what was last before
                    map.put(lastV, i);
                }
            }
        }
    }

    @Test
    public void test() {
        RandomSet rs = new RandomSet();
        rs.remove(1);
        rs.add(1);
        Assertions.assertEquals(1, rs.getRandom());
        rs.remove(1);
        rs.add(2);
        Assertions.assertEquals(2, rs.getRandom());
        Assertions.assertTrue(rs.contains(2));
        rs.add(3);
        rs.add(4);
        Assertions.assertTrue(asList(2, 3, 4).contains(rs.getRandom()));
        rs.remove(2);
        rs.remove(3);
        Assertions.assertEquals(4, rs.getRandom());
        Assertions.assertTrue(rs.contains(4));
        rs.remove(4);
        Assertions.assertFalse(rs.contains(4));
        Assertions.assertThrows(AssertionError.class, rs::getRandom);
    }
}
