package com.oszhugc.java8;

import java.util.Optional;

/**
 * Optionals不是函数式接口,而是用于防止NPE的漂亮工具.
 * Optionals是一个简单的容器,其值可能是null或者不是null.
 * 在java8之前一般某个函数应该返回非空对象但是有时却什么也没有返回
 * 在java8中,你应该返回optional而不是null
 *
 */
public class OptionalsTest {

    public static void main(String[] args) {
        //of(): 为非null的值创建一个optional
        Optional<String> optional = Optional.of("bam");
        //isPresent(): 如果值存在返回true,否则返回false
        boolean present = optional.isPresent();//true
        System.out.println(present);
        //get(): 如果有值则返回,否则抛出NoSuchElementException
        String s = optional.get();
        System.out.println(s);
        //orElse(): 如果有值返回,否则返回指定的其他值
        String fallback = optional.orElse("fallback ");
        System.out.println(fallback);
        //ifPresent(): 如果Optional实例有值则为其调用consuemr,否则不做处理
        optional.ifPresent((string)->System.out.println(string.charAt(0)));


    }
}
