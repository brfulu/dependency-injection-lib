package com.fulu.demo.person;

import com.fulu.depinjlib.annotation.Service;

@Service
public class Architect implements Job {
    @Override
    public long getSalary() {
        return 3200;
    }
}
