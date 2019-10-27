package com.fulu.demo.person;

import com.fulu.depinjlib.core.DepInjLib;


public class Main {
    public static void main(String[] args) {
        try {
            DepInjLib.supplyDependency(Job.class, Developer.class);
            Person person = DepInjLib.createInstance(Person.class);
            System.out.println(person.getSalary());

            Person person2 = DepInjLib.createInstance(Person.class);
            Person person3 = DepInjLib.createInstance(Person.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
