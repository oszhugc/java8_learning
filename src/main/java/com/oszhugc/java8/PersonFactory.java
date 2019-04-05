package com.oszhugc.java8;

@FunctionalInterface
public interface PersonFactory<P extends Person> {

    P crate(String firstName,String lastName);
}
