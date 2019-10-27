package com.fulu.demo.person;

import com.fulu.depinjlib.core.DepInjLib;


public class Main {
    public static void main(String[] args) {
        try {
            DepInjLib.supplyDependency(Job.class, Architect.class);
            Person person = DepInjLib.createInstance(Person.class);
            System.out.println(person.getSalary());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
