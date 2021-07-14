package patterns.singleton;

public final class LazyDoubleCheckLockingSingleton {
    private static volatile LazyDoubleCheckLockingSingleton instance = null;

    private LazyDoubleCheckLockingSingleton() {
    }

    public static LazyDoubleCheckLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (LazyDoubleCheckLockingSingleton.class) {
                if (instance == null) {
                    instance = new LazyDoubleCheckLockingSingleton();
                }
            }
        }

        return instance;
    }
}
