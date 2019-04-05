package com.oszhugc.java8;

import java.util.HashMap;
import java.util.Map;

/**
 * Map类型不支持Streams, 不过Map提供了一些新的有用的方法来处理一些日常任务
 * Map接口本身没有可用的stream()方法,但是你可以在键,值上创建专门的流,
 * 或者通过map.keyset().stream(),map.values.stream()和map.entrySet().stream().
 * 此外,Maps支持各种新的和有用的方法来执行常见任务
 *
 */
public class MapsTest {

    public static Map<Integer,String> map = new HashMap<>();

    static {
        for (int i = 0; i < 10; i++){
            //阻止我们在null检查时写入额外代码
            map.putIfAbsent(i,"val"+i);
        }
    }

    public static void main(String[] args) {
        //接收一个consumer来对map中的每一个元素操作
        map.forEach((id,val)->System.out.println(val));

        //使用函数在map上计算
        map.computeIfPresent(3,(num,val)->val+ num);
        System.out.println(map.get(3));//val33

        map.computeIfPresent(3,(num,val)->null);
        System.out.println(map.get(3));

        map.computeIfAbsent(23,num->"val"+num);
        System.out.println(map.get(23));

        map.computeIfAbsent(3,num->"bam");
        System.out.println(map.get(3));


        //在map里删除一个键值全都匹配的项
        map.remove(3,"val3");
        System.out.println(map.get(3));
        map.remove(3,"bam");
        System.out.println(map.get(3));

        //获取一个默认值
        System.out.println(map.getOrDefault(42,"not found"));

        //对map中的元素做合并操作
        //merge所做的事情就是如果键名不存在则插入,如果存在则对原键值对应的值做合并操作并重新插入到map中
        map.merge(29,"va29",(value,newValue)->value.concat(newValue));
        System.out.println(map.get(29));
        map.merge(29,"contact",(value,newValue)->value.concat(newValue));
        System.out.println(map.get(29));
    }
}
