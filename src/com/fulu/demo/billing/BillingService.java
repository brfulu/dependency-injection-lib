package com.fulu.demo.billing;

/**
 * Billing Service.
 */
public interface BillingService {

    /**
     * @param order      order
     * @param creditCard cc
     * @return Receipt
     */
    Receipt chargeOrder(PizzaOrder order, CreditCard creditCard);
}