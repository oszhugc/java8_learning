package com.oszhugc.java8;

import java.util.function.Consumer;

/**
 * Consumer接口表示要对单个输入参数执行的操作
 *
 */
public class ConsumersTest {

    public static void main(String[] args) {
        Consumer<Person> greeter = (p) -> System.out.println("hello " + p.firstName);
        greeter.accept(new Person("luke ","skywalker"));
    }
}
