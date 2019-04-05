package com.oszhugc.java8;

import java.util.Comparator;

/**
 * Comparator是老Java中的经典接口, Java8在此之上添加了多种默认方法
 */
public class ComparatorsTest {

    public static void main(String[] args) {
        Comparator<Person> comparator = (p1,p2)->p1.firstName.compareTo(p2.firstName);

        Person person1 = new Person("John", "Doe");
        Person person2 = new Person("Alice", "Wonderland");

        System.out.println(comparator.compare(person1,person2));//> 0
        System.out.println(comparator.reversed().compare(person1,person2));//<0

    }
}
