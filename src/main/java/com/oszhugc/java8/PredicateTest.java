package com.oszhugc.java8;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Predicate接口时只有一个参数的返回布尔类型值的断言型接口.
 * 该接口包含多种默认方法来将Predicate组合成其他复杂的逻辑(比如;与,或,非)
 */
public class PredicateTest {

    public static void main(String[] args) {
        Predicate<String> predicate = (s) -> s.length() > 0;
        System.out.println(predicate.test("foo"));//true
        System.out.println(predicate.negate().test("foo")); //false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }
}
