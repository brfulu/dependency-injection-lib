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

    Class getImplementation(Class parent) {
        return dependencies.get(parent);
    }
}
