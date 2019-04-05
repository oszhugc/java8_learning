package com.oszhugc.java8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Java8Application {

    public static void main(String[] args) {
//        Formula formula = new Formula() {
//            @Override
//            public double calculate(int a) {
//                return sqrt(a * 100);
//            }
//        };
//
//        System.out.println(formula.calculate(100));
//        System.out.println(formula.sqrt(100));


        lambda2();
    }

    public static void lambda1(){
        List<String> names = Arrays.asList("peter","anna","mike","xenia");
        System.out.println("排序前>> " + names);
//        Collections.sort(names, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o2.compareTo(o1);
//            }
//        });


//        Collections.sort(names,(a,b)-> b.compareTo(a));

        names.sort((a,b) -> b.compareTo(a));
        System.out.println("排序后>>"+names);
    }

    public static void lambda3(){
        //将数字字符串转换为整形类型
        Converter<String,Integer> converter = (from)-> Integer.valueOf(from );

        //静态方法引用
        converter = Integer::valueOf;


        Integer converted = converter.convert("123");
        System.out.println(converted.getClass());//class java.lang.Integer
    }

    public static  void lambda4(){
        //引用对象方法
        Something something = new Something();
        Converter<String,String> converter = something::startWith;
        String convert = converter.convert("java");
        System.out.println(convert);
    }

    public static void lambda5(){
        //使用构造函数引用,而不是手动实现一个完整的工厂
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.crate("Peter", "Parker");
        System.out.println(person);
    }

    public static  void lambda2(){
        //lambda表达式中可以访问外部的局部变量,虽然可以不用声明为final, 但是必须不能被后面的代码修改(具有隐形final语义)
        //否则无法编译通过
        int num = 1;
        Converter<Integer,String> stringConverter = (from) -> String.valueOf(from + num);
//        num = 3;//编译报错, 在lambda表哦大师中视图修改num同样是不允许的
        System.out.println(stringConverter.convert(2));
    }


}
