package com.fulu.depinjlib.core;


public class DepInjLib {
    public static void supplyDependency(Class parent, Class child) {
        DependencySupplier.getInstance().supplyDependency(parent, child);
    }

    @SuppressWarnings("unchecked")
    public static <T> T  createInstance(Class<T> cls) throws Exception {
        return (T)InjectionEngine.getInstance().createInstance(cls);
    }
}
