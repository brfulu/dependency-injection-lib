package com.fulu.demo.billing;

public class PaypalCreditCardProcessor implements CreditCardProcessor {
    @Override
    public ChargeResult charge(CreditCard creditCard, Double amount) {
        // Paypal debits the amount
        return new ChargeResult(true, amount);
    }
}
