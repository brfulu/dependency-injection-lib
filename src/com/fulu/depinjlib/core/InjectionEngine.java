package com.fulu.depinjlib.core;

import com.fulu.depinjlib.annotation.Autowire;
import com.fulu.depinjlib.annotation.Bean;
import com.fulu.depinjlib.annotation.enums.Scope;
import com.fulu.depinjlib.annotation.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
class InjectionEngine {
    private static InjectionEngine instance;

    private Map<Class, Object> singletons;

    private InjectionEngine() {
        singletons = new HashMap<>();
    }

    static InjectionEngine getInstance() {
        if (instance == null) {
            instance = new InjectionEngine();
        }
        return instance;
    }

    Object createInstance(Class cls) throws Exception {
        Object instance = newInstance(cls);
        if (instance == null) throw new MissingDependencyException();
        cls = instance.getClass();

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            Autowire autowire = field.getAnnotation(Autowire.class);
            if (autowire != null) {
                field.setAccessible(true);
                Object fieldInstance = createInstance(field.getType());
                field.set(instance, fieldInstance);
                field.setAccessible(false);
                if (autowire.verbose()) {
                    System.out.println(getVerboseOutput(field.getType().getSimpleName(), field.getName(),
                            cls.getSimpleName(), fieldInstance.hashCode()));
                }
            }
        }

        return instance;
    }

    private String getVerboseOutput(String fieldType, String fieldName, String parentName, int fieldInstanceHash) {
        return String.format("Initialized %s %s in %s on [%s] with #%d",
                fieldType, fieldName, parentName,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")),
                fieldInstanceHash);
    }

    private Object newInstance(Class cls) throws Exception {
        if (cls.isInterface()) {
            cls = DependencySupplier.getInstance().getImplementation(cls);
            if (cls == null) return null;
        }

        return isSingleton(cls) ? getSingletonInstance(cls) : cls.getConstructor().newInstance();
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
        return bean != null && bean.scope() == Scope.SINGLETON;
    }
}
