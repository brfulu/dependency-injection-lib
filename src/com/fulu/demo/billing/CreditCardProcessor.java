package com.fulu.demo.billing;

public interface CreditCardProcessor {
    ChargeResult charge(CreditCard creditCard, Double amount);
}
