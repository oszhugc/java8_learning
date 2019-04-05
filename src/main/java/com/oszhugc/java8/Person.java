package com.oszhugc.java8;

public class Person {

    String firstName;
    String lastName;

    Person(){}

    Person(String firstName,String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return this.firstName + " " +this.lastName;
    }
}
