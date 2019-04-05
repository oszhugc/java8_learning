package com.oszhugc.java8;

import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

/**
 * java8在java.time包下包含一个全新的日期和时间API
 *      抽象类ZoneId表示一个区域标识符. 它有一个名为getAvailableZoneIds的静态方法,返回所有区域标志符.
 * LocalDate/LocalDateTiem/DateTimeFormatter:
 *      java8中新增的LocalDate和LocalDateTime等类来解决日期处理方法,同时引入一个新的类DateTimeFormatter来解决
 *      日期格式化问题. 可以使用instant代替Date,LocalDateTime代替Calendar,DateTimeFormatter代替SimpleDateFormat
 *
 */
public class DateAPITest {

    public static void main(String[] args) {
        testTimezones();
    }


    /**
     * Clock类:
     *      提供了访问当前日期和时间的方法,Clock是时区敏感的,可以用来取代System.currentTimeMilles()来获取
     *      当前的微妙数.某个特定的时间点也可以用Instant类来表示,Instant类也可以用来创建旧版本的java.util.Date对象
     */
    public static void testClock(){

        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);

        Instant instant = clock.instant();
        System.out.println(instant);

        java.util.Date date = Date.from(instant);
        System.out.println(date
        );

    }


    /**
     * 在新API中时区使用 ZoneId 来表示。时区可以很方便的使用静态方法of来获取到。
     * 抽象类ZoneId（在java.time包中）表示一个区域标识符。
     * 它有一个名为getAvailableZoneIds的静态方法，它返回所有区域标识符。
      */
    public static void testTimezones(){
        //输出所有区域标识符
        System.out.println(ZoneId.getAvailableZoneIds());

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());
    }


}
