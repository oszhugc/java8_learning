package com.oszhugc.java8;

/**
 * lambda表达式访问局部变量时,要求局部变量时final的,不能修改
 * 与局部变量相比, 我们队lambda表达式中的实例字段和静态变量都有读写权限,该行为和匿名对象是一致的
 *
 */
public class Lambda4 {

    static int  outerStaticNum;
    int outerNum;

    void testScopes(){
        Converter<Integer,String > stringConverter1 = (from)->{
          outerNum = 23;
          return String.valueOf(from+outerNum);
        };

        Converter<Integer,String> stringConverter2 = (from)->{
            outerStaticNum = 32;
            return String.valueOf(outerStaticNum+from);
        };

        System.out.println(stringConverter1.convert(1));
        System.out.println(stringConverter2.convert(1));
    }

    public static void main(String[] args) {
        new Lambda4().testScopes();;
    }
}
