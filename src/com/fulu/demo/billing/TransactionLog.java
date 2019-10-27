package com.fulu.demo.billing;

public interface TransactionLog {
    void logChargeResult(ChargeResult chargeResult);

    void logException(RuntimeException e);
}
