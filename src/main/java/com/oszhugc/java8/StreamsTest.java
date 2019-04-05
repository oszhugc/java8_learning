package com.oszhugc.java8;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * java.util.Stream表示能应用在一组元素上一次执行的操作序列.
 * Stream操作分为中间操作和最终操作两种,最终操作返回一特定类型的计算结果.而中间操作返回Stream本身
 * 这样你就可以将多个操作依次串起来.
 * Stream的创建需要指定一个数据源,比如java.util.Collection的子类,List或者Set. Map不支持.
 * Stream的操作可以串行或者并行执行
 * java8扩展了集合类,可以通过Collection.stream()或者Collection.parallelStream()来创建一个Stream.
 * */
public class StreamsTest {

    public static List<String> stringCollection
            = Arrays.asList("ddd2","aaa2","bbb1","aaa1","bbb3","ccc","bbb2","ddd1");


    public static void main(String[] args) {
        testParallelStream();
    }


    /**
     * Filter
     * 过滤通过一个predicate接口来过滤并只保留符合条件的元素,该操作属于中国操作,所以我们可以在过滤后的结果来应用
     * 其他Stream操作(比如forEach). forEach需要一个函数来对过滤后的元素一次执行. forEach是一个最终操作,
     * 所以我们不能在forEach之后来执行其他Stream操作
     */
    public static void  testFilter(){
        stringCollection.stream().filter((s)->s.startsWith("a")).forEach(System.out::println);
    }

    /**
     * 排序是一个中间操作,返回的是排序好的Stream,如果你不指定一个自定义的Comparator,则会使用默认排序
     * 需要注意的是,排序只是创建了一个排好序的Stream, 而不影响原来的数据源. 排序后源数据不会被修改
     */
    public static void testSorted(){
        stringCollection.stream()
                .sorted()
                .filter((s)->s.startsWith("a"))
                .forEach(System.out::println);

        System.out.println(stringCollection);
    }

    /**
     * 中间操作map会将元素根据指定的Function接口来依次将元素转成另外的对象
     * 下面的示例展示了将字符串转换为大写字符串
     * 你也可以通过map来将对象转换成其他类型,map返回的Stream类型是根据你map传递进去的函数的返回值决定的
     */
    public static void testMap(){
        stringCollection.stream()
                .map(String::toUpperCase)
                .sorted((a,b)->a.compareTo(b))
                .forEach(System.out::println);
    }


    /**
     * Stream提供了多种匹配操作,允许检测指定的predicate是否匹配整个Stream. 所有的匹配操作都是最终操作,
     * 并返回一个boolean类型的值
     */
    public static void testMatch(){
        boolean anyMatch = stringCollection.stream()
                .anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyMatch);

        boolean allMatch = stringCollection.stream().allMatch((s) -> s.startsWith("a"));
        System.out.println(allMatch);

        boolean noneMatch = stringCollection.stream().noneMatch((s) -> s.startsWith("z"));
        System.out.println(noneMatch);
    }


    /**
     * count
     * 计数是一个最终操作, 返回Stream中元素的个数,返回值类型是long
     */
    public static void testCount(){
        long count = stringCollection.stream().filter((s) -> s.startsWith("b")).count();
        System.out.println(count);
    }


    /**
     * Reduce
     * 这是一个最终操作,允许通过指定的函数来将stream中的多个元素规约为一个元素,规约后的结果是通过Optional接口来表示的
     *
     */
    public static void testReduce(){
        Optional<String> reduce
                = stringCollection
                .stream()
                .sorted()
                .reduce((s1, s2) -> s1 + "#" + s2);
        reduce.ifPresent(System.out::println);
    }

    /**
     * Reduce的主要作用是把Stream元素组合起来, 它提供一个起始值(种子),然后依照运算规则(BianaryOperator),
     * 和前面Stream的第一个,第二个,第n个元素组合.
     * 从这个意义上说,字符串的拼接,数值sum,min,max,average都是特殊的reduce.
     * 如下案例,第一个reduce(),第一个参数(空白字符)即为起始值,第二个参数(String::concat)为BinaryOperator.
     * 这类有起始值的reduce()都返回具体的对象.
     * 而对于第四个示例没有起始值的reduce(),由于可能没有足够的元素,返回的是Optional.
     */
    public static void testReduce2(){
        //字符串连接,concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println(concat);

        //求最小值 min=-3.0
        Double min = Stream.of(-1.4, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println(min);

        //求和 有起始值 sum = 10
        Integer sum1 = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println(sum1);

        //求和 没有起始值 sum= 10
        Integer sum2 = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println(sum2);

    }


    /**
     * Stream有串行和并行两种,
     * 串行Stream上的操作是在一个线程中一次完成的,而并行Stream则是在多个线程上同时执行
     */
    public static void testParallelStream(){
        //创建一个没有重复元素的达标
        int max = 1000000;
        ArrayList<String> values = new ArrayList<>(max);
        for (int i = 0; i<max; i++){
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        new Thread(()->{
            long t0 = System.nanoTime();
            long count = values.stream().sorted().count();
            System.out.println(count);
            long t1 = System.nanoTime();
            long millis = TimeUnit.NANOSECONDS.toMillis(t1-t0);
            System.out.println(String.format("sequential sort took : %d ms",millis));
        }).start();

        new Thread(()->{
            long t0 = System.nanoTime();
            long count = values.parallelStream().sorted().count();
            System.out.println(count);
            long t1 = System.nanoTime();
            long millis = TimeUnit.NANOSECONDS.toMillis(t1-t0);
            System.out.println(String.format("parallel sort took : %d ms",millis));
        }).start();
    }


}

