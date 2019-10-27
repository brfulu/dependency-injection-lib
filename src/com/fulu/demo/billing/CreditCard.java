package com.fulu.demo.billing;

import com.fulu.depinjlib.annotation.Autowire;

public class CreditCard {
    @Autowire(verbose = true)
    private CardInfo cardInfo;

    public String getCardNumber() {
        return cardInfo.getNumber();
    }
}
