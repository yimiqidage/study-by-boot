package com.jdk8.stream.local;

import java.time.*;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

import static java.time.temporal.ChronoField.*;

public class LocalDateTimeDemo {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
    public static void main(String[] args) {
        java.time.LocalDateTime localDateTime =  java.time.LocalDateTime.now();
//        System.out.println(localDateTime.format(DATE_TIME_FORMATTER));

        System.out.println(Instant.ofEpochSecond(1000));

        System.out.println(Instant.ofEpochSecond(3));
        System.out.println(Instant.ofEpochSecond(3, 0));
        System.out.println(Instant.ofEpochSecond(2, 1_000_000_000));
        System.out.println(Instant.ofEpochSecond(4, -1_000_000_000));


        LocalDate localDate3 = LocalDate.now();
        LocalDate localDate4 = LocalDate.of(2018,1,1);
        System.out.println(localDate3+","+localDate4);


        LocalDate localDate = LocalDate.now();

        LocalDate localDateFormatter = localDate.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(localDateFormatter);
        LocalDate localDateFormatter2 = localDate.with(ChronoField.DAY_OF_MONTH,8);
        System.out.println(localDateFormatter2);

        LocalTime time1 = LocalTime.of(16,1,3);
        LocalTime time2 = LocalTime.of(16,1,5);

        System.out.println(time1+","+time2);

        Duration duration = Duration.between(time1,time2);
        System.out.println("duration 差额："+ duration.getSeconds());

        Duration threeMinutes = Duration.ofMinutes(3);

        Period tenDays = Period.ofDays(10);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
        LocalDate localDate1 = LocalDate.of(2018,1,1);
        LocalDate localDate2 = LocalDate.of(2018,1,3);
        Period betweenDays = Period.between(localDate1,localDate2);
        System.out.println("betweenDays:"+betweenDays.getDays());

        System.out.println(threeMinutes.getSeconds()+","+tenDays.getDays()+","+twoYearsSixMonthsOneDay.getDays());

        LocalDateTime localDateTime1 = LocalDateTime.now();
        String dateFormatter = localDateTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(dateFormatter);

        System.out.println("firstDayOfMonth:"+localDateTime1.with(TemporalAdjusters.firstDayOfMonth()).format(myFormatter()));

    }

    public static DateTimeFormatter myFormatter(){
        return new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendValue(YEAR, 4)
                .appendLiteral("-")
                .appendValue(MONTH_OF_YEAR, 2)
                .appendLiteral("-")
                .appendValue(DAY_OF_MONTH, 2)
                .appendLiteral(" ")
                .appendValue(HOUR_OF_DAY,2)
                .appendLiteral(":")
                .appendValue(MINUTE_OF_HOUR,2)
                .appendLiteral(":")
                .appendValue(SECOND_OF_MINUTE,2)
                .optionalStart()
//                .appendOffset("+HHMMss", "Z")
                .toFormatter(Locale.CHINA);

    }
}
