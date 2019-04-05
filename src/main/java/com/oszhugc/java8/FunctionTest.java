package com.oszhugc.java8;

import java.util.function.Function;

/**
 * Function接口接受一个参数并生成结果. 默认方法可用于将多个函数链接在一起(compose,andThen)
 */
public class FunctionTest {

    public static void main(String[] args) {
        Function<String,Integer> toInteger = Integer::valueOf;
        Function<String,String> backToString = toInteger.andThen(String::valueOf);

        System.out.println(backToString.apply("123").getClass());
    }
}
