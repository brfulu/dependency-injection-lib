package com.fulu.demo.billing;

import com.fulu.depinjlib.core.DepInjLib;

public class Main {
    public static void main(String[] args) throws Exception {
        DepInjLib.supplyDependency(CreditCardProcessor.class, BankCreditCardProcessor.class);
        DepInjLib.supplyDependency(TransactionLog.class, InMemoryTransactionLog.class);

        BillingService billingService = DepInjLib.createInstance(RealBillingService.class);

        PizzaOrder pizzaOrder = new PizzaOrder("Very-Veggie", 100d);
        CreditCard creditCard = DepInjLib.createInstance(CreditCard.class);

        Receipt receipt1 = billingService.chargeOrder(pizzaOrder, creditCard);
        System.out.println(receipt1.getReceiptMessage());

        billingService = DepInjLib.createInstance(RealBillingService.class);
        Receipt receipt2 = billingService.chargeOrder(pizzaOrder, creditCard);
        System.out.println(receipt2.getReceiptMessage());
    }
}
