package leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
https://leetcode.com/problems/the-dining-philosophers/

Five silent philosophers sit at a round table with bowls of spaghetti. Forks are placed between each pair of adjacent philosophers.

Each philosopher must alternately think and eat. However, a philosopher can only eat spaghetti when they have both left and right forks.
Each fork can be held by only one philosopher and so a philosopher can use the fork only if it is not being used by another philosopher.
After an individual philosopher finishes eating, they need to put down both forks so that the forks become available to others.
A philosopher can take the fork on their right or the one on their left as they become available,
but cannot start eating before getting both forks.

Eating is not limited by the remaining amounts of spaghetti or stomach space; an infinite supply and an infinite demand are assumed.

Design a discipline of behaviour (a concurrent algorithm) such that no philosopher will starve;
i.e., each can forever continue to alternate between eating and thinking, assuming that no philosopher
can know when others may want to eat or think.

The problem statement and the image above are taken from wikipedia.org

The philosophers' ids are numbered from 0 to 4 in a clockwise order.
Implement the function void wantsToEat(philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork) where:

philosopher is the id of the philosopher who wants to eat.
pickLeftFork and pickRightFork are functions you can call to pick the corresponding forks of that philosopher.
eat is a function you can call to let the philosopher eat once he has picked both forks.
putLeftFork and putRightFork are functions you can call to put down the corresponding forks of that philosopher.
The philosophers are assumed to be thinking as long as they are not asking to eat (the function is not being called with their number).
Five threads, each representing a philosopher, will simultaneously use one object of your class to simulate the process.
The function may be called for the same philosopher more than once, even before the last call ends.



Example 1:

Input: n = 1
Output: [[4,2,1],[4,1,1],[0,1,1],[2,2,1],[2,1,1],[2,0,3],[2,1,2],[2,2,2],[4,0,3],[4,1,2],[0,2,1],[4,2,2],[3,2,1],[3,1,1],[0,0,3],[0,1,2],[0,2,2],[1,2,1],[1,1,1],[3,0,3],[3,1,2],[3,2,2],[1,0,3],[1,1,2],[1,2,2]]
Explanation:
n is the number of times each philosopher will call the function.
The output array describes the calls you made to the functions controlling the forks and the eat function, its format is:
output[i] = [a, b, c] (three integers)
- a is the id of a philosopher.
- b specifies the fork: {1 : left, 2 : right}.
- c specifies the operation: {1 : pick, 2 : put, 3 : eat}.


Constraints:

1 <= n <= 60


 */
public class TheDiningPhilosophers {
    /**
     * NOTE: really test on leetcode side doesn't consider different ordering of left/right, which can be really correct.
     * So hard to guess.
     * Used workaround: tests passed via simple synchronized(this){} for entire method body
     */
    final static int count = 5;
    final List<Semaphore> semaphores = IntStream.range(0, count)
            .mapToObj(i -> new Semaphore(1)).collect(Collectors.toList());

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int id,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        Semaphore right = semaphores.get(id);
        Semaphore left = id == 0 ? semaphores.get(4) : semaphores.get(id - 1);

        if (id % 2 == 0) {
            left.acquire();
            pickLeftFork.run();
            right.acquire();
            pickRightFork.run();
        } else {
            right.acquire();
            pickRightFork.run();
            left.acquire();
            pickLeftFork.run();
        }

        eat.run();

        if (id % 2 == 0) {
            left.release();
            putLeftFork.run();
            right.release();
            putRightFork.run();
        } else {
            right.release();
            putRightFork.run();
            left.release();
            putLeftFork.run();
        }
    }

}

class TestPhilosophers {
    private final int noFork = 0;
    private final int leftFork = 1;
    private final int rightFork = 2;
    private final int pick = 1;
    private final int put = 2;
    private final int eat = 3;

    @Test
    public void test1() {
        String actual = ztestBody(1).stream().map(Object::toString).sorted().collect(Collectors.toList()).toString().replaceAll(" ", "");
        Assertions.assertEquals(
                "[[0,0,3],[0,1,1],[0,1,2],[0,2,1],[0,2,2],[1,0,3],[1,1,1],[1,1,2],[1,2,1],[1,2,2],[2,0,3],[2,1,1],[2,1,2],[2,2,1],[2,2,2],[3,0,3],[3,1,1],[3,1,2],[3,2,1],[3,2,2],[4,0,3],[4,1,1],[4,1,2],[4,2,1],[4,2,2]]",
                actual);
    }

    private List<List<Integer>> ztestBody(int howManyCalls) {
        List<List<Integer>> out = new CopyOnWriteArrayList<>();
        TheDiningPhilosophers sut = new TheDiningPhilosophers();
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Future<?> f = runPhilosopher(out, sut, i, howManyCalls);
            futures.add(f);
        }
        futures.forEach(f -> {
            try {
                f.get(100, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        });
        return out;
    }

    private Future<?> runPhilosopher(List<List<Integer>> out, TheDiningPhilosophers theDiningPhilosophers, int id, int howManyCalls) {
        return Executors.newSingleThreadExecutor().submit(() -> {
            for (int i = 0; i < howManyCalls; i++) {
                try {
                    theDiningPhilosophers.wantsToEat(id,
                            () -> out.add(Arrays.asList(id, leftFork, pick)), // pick left
                            () -> out.add(Arrays.asList(id, rightFork, pick)), // pick right
                            () -> out.add(Arrays.asList(id, noFork, eat)), // eat
                            () -> out.add(Arrays.asList(id, leftFork, put)), // put left
                            () -> out.add(Arrays.asList(id, rightFork, put)) // put right
                    );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Test
    public void test2() {
        String actual = ztestBody(2).stream().map(Object::toString).sorted().collect(Collectors.toList()).toString().replaceAll(" ", "");
        Assertions.assertEquals(
                "[[0,0,3],[0,0,3],[0,1,1],[0,1,1],[0,1,2],[0,1,2],[0,2,1],[0,2,1],[0,2,2],[0,2,2],[1,0,3],[1,0,3],[1,1,1],[1,1,1],[1,1,2],[1,1,2],[1,2,1],[1,2,1],[1,2,2],[1,2,2],[2,0,3],[2,0,3],[2,1,1],[2,1,1],[2,1,2],[2,1,2],[2,2,1],[2,2,1],[2,2,2],[2,2,2],[3,0,3],[3,0,3],[3,1,1],[3,1,1],[3,1,2],[3,1,2],[3,2,1],[3,2,1],[3,2,2],[3,2,2],[4,0,3],[4,0,3],[4,1,1],[4,1,1],[4,1,2],[4,1,2],[4,2,1],[4,2,1],[4,2,2],[4,2,2]]",
                actual);
    }

}
