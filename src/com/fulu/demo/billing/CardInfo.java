package com.fulu.demo.billing;

public class CardInfo {
    private String firstName;
    private String lastName;
    private String number;

    public CardInfo() {
        this.firstName = "test1";
        this.lastName = "test2";
        this.number = "1111-2222-3333-4444";
    }

    public String getNumber() {
        return number;
    }
}
