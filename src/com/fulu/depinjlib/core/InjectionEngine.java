package com.fulu.depinjlib.core;

import com.fulu.depinjlib.annotation.Autowire;
import com.fulu.depinjlib.annotation.Bean;
import com.fulu.depinjlib.annotation.Component;
import com.fulu.depinjlib.annotation.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class InjectionEngine {
    private static InjectionEngine instance;

    private Map<Class, Object> singletons;

    private InjectionEngine() {
        singletons = new HashMap<>();
    }

    public static InjectionEngine getInstance() {
        if (instance == null) {
            instance = new InjectionEngine();
        }
        return instance;
    }

    public Object createInstance(Class cls) throws Exception {
        Object instance = getInstance(cls);
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

    private Object getInstance(Class cls) throws Exception {
        Object instance = null;
        if (cls.isInterface()) {
            instance = DependencySupplier.getInstance().getImplementation(cls);
        } else if (isSingleton(cls)) {
            instance = getSingletonInstance(cls);
        } else {
            instance = cls.getConstructor().newInstance();
        }
        return instance;
    }

    private Object getSingletonInstance(Class cls) throws Exception {
        Object instance = singletons.get(cls);
        if (instance == null) {
            instance = cls.getConstructor().newInstance();
            singletons.put(cls, instance);
        }
        return instance;
    }

    private boolean isSingleton(Class cls) {
        if (cls.getAnnotation(Service.class) != null) {
            return true;
        }

        Bean bean = (Bean) cls.getAnnotation(Bean.class);
        return bean.scope().equals("singleton");
    }
}
