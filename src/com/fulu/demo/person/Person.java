package com.fulu.demo.person;

import com.fulu.depinjlib.annotation.Autowire;

public class Person {
    @Autowire
    private Job job;

    public long getSalary() {
        return job.getSalary();
    }
}
