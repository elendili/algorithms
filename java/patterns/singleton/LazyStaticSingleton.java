package patterns.singleton;

public final class LazyStaticSingleton {

    private LazyStaticSingleton() {
    }

    private static class Holder {
        static final LazyStaticSingleton INSTANCE = new LazyStaticSingleton();
    }

    public static LazyStaticSingleton getInstance() {
        return Holder.INSTANCE;
    }
}
