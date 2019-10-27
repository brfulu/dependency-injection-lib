package com.fulu.demo.billing;

import com.fulu.depinjlib.annotation.Autowire;
import com.fulu.depinjlib.annotation.Bean;

/**
 * Real billing service implementation
 */

public class RealBillingService implements BillingService {
    @Autowire(verbose = true)
    private CreditCardProcessor processor;
    @Autowire(verbose = true)
    private TransactionLog transactionLog;

    public CreditCardProcessor getProcessor() {
        return processor;
    }

    public TransactionLog getTransactionLog() {
        return transactionLog;
    }

    public Receipt chargeOrder(PizzaOrder order, CreditCard creditCard) {
        try {
            ChargeResult result = processor.charge(creditCard, order.getAmount());
            transactionLog.logChargeResult(result);

            return result.wasSuccessful()
                    ? Receipt.forSuccessfulCharge(order.getAmount())
                    : Receipt.forDeclinedCharge(result.getDeclineMessage());
        } catch (RuntimeException e) {
            transactionLog.logException(e);
            return Receipt.forSystemFailure(e.getMessage());
        }
    }
}