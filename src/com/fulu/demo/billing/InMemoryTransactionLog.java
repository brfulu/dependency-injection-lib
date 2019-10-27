package com.fulu.demo.billing;

import com.fulu.depinjlib.annotation.Bean;
import com.fulu.depinjlib.annotation.Component;
import com.fulu.depinjlib.annotation.enums.Scope;

@Bean(scope = Scope.SINGLETON)
public class InMemoryTransactionLog implements TransactionLog {
    @Override
    public void logChargeResult(ChargeResult chargeResult) {
        System.out.println("Charge has been processed for " + chargeResult.getAmount());
    }

    @Override
    public void logException(RuntimeException e) {
        e.printStackTrace();
    }
}
