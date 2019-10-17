package com.fulu.depinjlib.core;

import java.lang.reflect.InvocationTargetException;

public class DepInjLib {

    public static void supplyDependency(Class parent, Class child) {
        DependencySupplier.getInstance().supplyDependency(parent, child);
    }

    public static Object createInstance(Class cls) throws Exception {
        return InjectionEngine.getInstance().createInstance(cls);
    }
}