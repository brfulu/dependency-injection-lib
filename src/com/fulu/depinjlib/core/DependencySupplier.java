package com.fulu.depinjlib.core;

import java.util.HashMap;
import java.util.Map;

public class DependencySupplier {
    private static DependencySupplier instance;

    private Map<Class, Class> dependencies;

    private DependencySupplier() {
        dependencies = new HashMap<>();
    }

    public static DependencySupplier getInstance() {
        if (instance == null) {
            instance = new DependencySupplier();
        }
        return instance;
    }

    public void supplyDependency(Class parent, Class child) {
        dependencies.put(parent, child);
    }

    public Object getImplementation(Class parent) throws Exception {
        Class cls = dependencies.get(parent);
        return cls == null ? cls : cls.getConstructor().newInstance();
    }
}
