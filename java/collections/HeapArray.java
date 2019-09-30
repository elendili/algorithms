package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class HeapArray<T extends Comparable<T>> {
    private final ArrayList<T> items = new ArrayList<>();
    private final Comparator<T> comparator;
    private final int maxSize;

    public HeapArray() {
        this(Comparator.naturalOrder(), Integer.MAX_VALUE);
    }

    public HeapArray(Comparator<T> comparator, int maxSize) {
        this.comparator = comparator;
        this.maxSize = maxSize;
    }

    public List<T> items() {
        return new ArrayList<>(items);
    }

    public void add(T... ts) {
        Arrays.stream(ts).forEach(this::add);
    }

    public void add(T t) {
        if (items.size() >= maxSize) {
            T firstEl = poll();
            T lastEl = items.remove(items.size() - 1);
            if (comparator.compare(firstEl, lastEl) < 0) {
                items.add(firstEl);
                siftUp();
            } else {
                items.add(lastEl);
                siftUp();
            }
        }
        items.add(t);
        siftUp();
    }

    public T poll() {
        if (items.size() == 1) {
            return items.remove(0);
        }

        T tmp = items.get(0);
        items.set(0, items.remove(items.size() - 1));
        siftDown();
        return tmp;
    }

    private void siftUp() {
        int k = items.size() - 1;

        while (k > 0) {
            int p = (k - 1) / 2;
            T child = items.get(k);
            T parent = items.get(p);
            if (comparator.compare(child, parent) > 0) {
                //swap
                items.set(k, parent);
                items.set(p, child);
                //adjust k
                k = p;
            } else {
                break;
            }
        }
    }

    private void siftDown() {
        int k = 0;
        int left = 1;

        while (left < items.size()) {
            int max = left;
            int right = left + 1;
            if (right < items.size()) {
                if (comparator.compare(items.get(right), items.get(left)) > 0) {
                    max = right;
                }
            }
            T parent = items.get(k);
            T child = items.get(max);

            if (comparator.compare(parent, child) < 0) {
                //swap
                items.set(k, child);
                items.set(max, parent);

                k = max;
                left = 2 * k + 1;
            } else {
                break;
            }
        }
    }

}

