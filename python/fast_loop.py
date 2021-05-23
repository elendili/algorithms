from time import time

from numba import jit


def timer(f):
    def wrapper():
        start = time()
        v = f()
        end = time()
        print(v, (end - start) * 1000, "millis")

    return wrapper


@timer
@jit  # fast loop thanks to JIT of Numba library
def loop():
    n = 0
    for i in range(0, 100_000_000_000):
        n += 1
    return n


loop()
