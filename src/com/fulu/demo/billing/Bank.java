package com.fulu.demo.billing;

import com.fulu.depinjlib.annotation.Autowire;
import com.fulu.depinjlib.annotation.Service;

@Service
public class Bank {
    @Autowire(verbose = true)
    private Object test;

    public void debit(Double amount) {
        System.out.println(test.hashCode());
        System.out.println("Bank debits amount=" + amount);
    }
}
