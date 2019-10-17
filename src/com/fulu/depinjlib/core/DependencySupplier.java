package com.fulu.depinjlib.core;

import java.util.HashMap;
import java.util.Map;

class DependencySupplier {
    private static DependencySupplier instance;

    private Map<Class, Class> dependencies;

    private DependencySupplier() {
        dependencies = new HashMap<>();
    }

    static DependencySupplier getInstance() {
        if (instance == null) {
            instance = new DependencySupplier();
        }
        return instance;
    }

    void supplyDependency(Class parent, Class child) {
        dependencies.put(parent, child);
    }

    Object getImplementation(Class parent) throws Exception {
        Class cls = dependencies.get(parent);
        return cls == null ? null : cls.getConstructor().newInstance();
    }
}
