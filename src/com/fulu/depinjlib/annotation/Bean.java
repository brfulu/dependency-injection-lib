package com.fulu.depinjlib.annotation;

import com.fulu.depinjlib.annotation.enums.Scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Bean {
    Scope scope() default Scope.PROTOTYPE;
}
