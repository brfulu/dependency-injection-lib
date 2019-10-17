package com.fulu.depinjlib.core;

import com.fulu.depinjlib.annotation.Autowire;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class InjectionEngine {
    private static InjectionEngine instance;

    private InjectionEngine() {
    }

    public static InjectionEngine getInstance() {
        if (instance == null) {
            instance = new InjectionEngine();
        }
        return instance;
    }

    public Object createInstance(Class cls) throws Exception {
        Object instance = cls.isInterface() ?
                DependencySupplier.getInstance().getImplementation(cls) : cls.getConstructor().newInstance();

        if (instance == null) throw new MissingDependencyException();

        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            Class fieldType = f.getType();
            Annotation autowire = f.getAnnotation(Autowire.class);

            if (autowire != null) {
                Object fieldInstance = createInstance(fieldType);
                f.setAccessible(true);
                f.set(instance, fieldInstance);
            }
        }

        return instance;
    }
}
