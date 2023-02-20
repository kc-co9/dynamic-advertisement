package com.share.co.kcl.dad.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    public static final String FORMAT_COMMON_TIME = "HH:mm:ss";
    public static final String FORMAT_COMMON_DATE = "yyyy-MM-dd";
    public static final String FORMAT_COMMON_DATETIME = "yyyy-MM-dd HH:mm:ss";

    private DateUtils() {
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static LocalDateTime after(long time, ChronoUnit timeUnit) {
        return now().plus(time, timeUnit);
    }

    public static long valueOfSecond(LocalTime time) {
        return time.toSecondOfDay();
    }

    public static long valueOfMilliSecond(LocalTime time) {
        return time.toNanoOfDay() / 1000;
    }

    public static long valueOfSecond(LocalDate time) {
        return time.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
    }

    public static long valueOfMilliSecond(LocalDate time) {
        return time.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static long valueOfSecond(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    public static long valueOfMilliSecond(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


}
