package com.fulu.demo.person;

import com.fulu.depinjlib.annotation.Autowire;
import com.fulu.depinjlib.annotation.Bean;

@Bean(scope = "singleton")
public class Person {
    @Autowire
    private Job job;

    public long getSalary() {
        return job.getSalary();
    }
}
