package com.fulu.demo.person;

import com.fulu.depinjlib.annotation.Component;

@Component
public class Developer implements Job {
    @Override
    public long getSalary() {
        return 2000;
    }
}
