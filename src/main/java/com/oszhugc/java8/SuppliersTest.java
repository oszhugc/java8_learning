package com.oszhugc.java8;

import java.util.function.Supplier;

/**
 * Supplier接口产生给定泛型类型的结果
 * 与Function接口不同,Supplier接口不接受参数
 *
 */
public class SuppliersTest {
    public static void main(String[] args) {
        Supplier<Person> personSupplier = Person::new;

        Person person = personSupplier.get();

    }
}
