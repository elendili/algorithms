package collections;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.IntStream;

import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KSmallest {
    /*
        Reuses Priority Queue but requires extra memory
     */
    <T extends Comparable<T>> Collection<T> findKElementsByPriorityQueue(Comparator<T> comparator, int maxSize, List<T> input) {
        PriorityQueue<T> priorityQueue = new PriorityQueue<>(comparator);
        priorityQueue.addAll(input);
        return IntStream.range(0, maxSize).boxed().map(e -> priorityQueue.poll()).collect(toList());
    }

    /*
        Best of the best, because allows to store more values for less memory,
        because of values counter.
     */
    <T extends Comparable<T>> Collection<T> findKElementsByConcurrentSkipListMap(Comparator<T> comparator, int maxSize, List<T> input) {
        ConcurrentSkipListMap<T, Integer> cslm =
                new ConcurrentSkipListMap<>(comparator.reversed());
        input.forEach(e ->
                cslm.compute(e, (k, v) -> {
                    if (v == null) {
                        /*
                        to keep size need to track lowest and highest entries
                        * */
                        if (cslm.size() >= maxSize) {
                            T lowerKeyToReplace = cslm.lowerKey(k);
                            if (lowerKeyToReplace == null) {
                                // new key is too small to be stored
                                return null;
                            } else {
                                // remove lowest to insert new key
                                cslm.descendingKeySet().pollLast();
                                return 1;
                            }
                        }
                        return 1;
                    } else {
                        return v + 1;
                    }
                }));
        List<T> out = cslm.descendingKeySet().stream().limit(maxSize)
                .flatMap(k -> IntStream.range(0, cslm.get(k)).limit(maxSize).mapToObj(i -> k))
                .limit(maxSize).collect(toList());
        return out;
    }

    /*
      Reuses Linked list with fixed size and
      ListIterator for iterating and inserting
     */
    <T extends Comparable<T>> Collection<T> findKElementsByLinkedList(Comparator<T> comparator, int maxSize, List<T> input) {
        LinkedList<T> list = new LinkedList<>();
        input.forEach(e -> {
            if (list.isEmpty()) {
                list.add(e);
            } else {
                T first = list.peekFirst();
                T last = list.peekLast();
                if (comparator.compare(e, first) < 0) {
                    list.addFirst(e);
                    if (list.size() > maxSize) {
                        list.removeLast();
                    }
                } else if (comparator.compare(e, last) < 0) {
                    ListIterator<T> listIterator = list.listIterator();
                    while (listIterator.hasNext()) {
                        T e2 = listIterator.next();
                        if (comparator.compare(e, e2) < 0) {
                            listIterator.previous();
                            listIterator.add(e);
                            if (list.size() > maxSize) {
                                list.removeLast();
                            }
                            break;
                        }
                    }
                } else if (list.size() < maxSize && comparator.compare(e, last) > 0) {
                    list.addLast(e);
                }
            }
        });
        return list;
    }

    List<Integer> l = IntStream.of(1, 5, 4, 6, 6, 3, 2, 7, 1, 9, 11)
            .boxed().collect(toList());

    @Test
    public void test_findKElementsByPriorityQueue() {
        assertEquals("[1, 1, 2]",
                findKElementsByPriorityQueue(naturalOrder(), 3, l).toString());
        assertEquals("[11, 9, 7, 6, 6]",
                findKElementsByPriorityQueue(reverseOrder(), 5, l).toString());
    }

    @Test
    public void test_findKElementsByLinkedList() {
        assertEquals("[1, 1, 2]", findKElementsByLinkedList(naturalOrder(), 3, l)
                .toString());
        assertEquals("[11, 9, 7, 6, 6]",
                findKElementsByLinkedList(reverseOrder(), 5, l).toString());
    }

    @Test
    public void test_findKElementsByConcurrentSkipListMap() {
        assertEquals("[1, 1, 2]", findKElementsByConcurrentSkipListMap(naturalOrder(), 3, l)
                .toString());
        assertEquals("[11, 9, 7, 6, 6]",
                findKElementsByConcurrentSkipListMap(reverseOrder(), 5, l).toString());
    }
}
