package com.fulu.demo.billing;

import com.fulu.depinjlib.annotation.Autowire;
import com.fulu.depinjlib.annotation.Bean;
import com.fulu.depinjlib.annotation.enums.Scope;

@Bean(scope = Scope.SINGLETON)
public class BankCreditCardProcessor implements CreditCardProcessor {
    @Autowire(verbose = true)
    private Bank bank;

    @Override
    public ChargeResult charge(CreditCard creditCard, Double amount) {
        // Bank debits the amount
        bank.debit(amount);

        return new ChargeResult(true, amount);
    }
}
