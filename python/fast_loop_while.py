from statistics import mean
from time import time


def timer(f):
    def wrapper():
        start = time()
        f()
        end = time()
        return (end - start) * 1000

    return wrapper


def average(f, times):
    return mean([timer(f)() for _ in range(times)])


def for_loop(size):
    n = 0
    for i in range(0, size):
        n += 1


def while_loop(size):
    n = 0
    while n < size:
        n += 1
        # size -= 1


print(average(lambda: for_loop(100_000), 100))  # 56.61353588104248
print(average(lambda: while_loop(100_000), 100))  # 52.318782806396484
