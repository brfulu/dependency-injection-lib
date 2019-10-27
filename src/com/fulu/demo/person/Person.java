package com.fulu.demo.person;

import com.fulu.depinjlib.annotation.Autowire;
import com.fulu.depinjlib.annotation.Bean;
import com.fulu.depinjlib.annotation.enums.Scope;

@Bean(scope = Scope.SINGLETON)
public class Person {
    @Autowire(verbose = true)
    private Job job;

    public long getSalary() {
        return job.getSalary();
    }
}
