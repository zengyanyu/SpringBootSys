package com.zengyanyu.system.util;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtils {

    private DateUtils() {
    }

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前时间
     *
     * @return
     */
    public static LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

    /**
     * 时间戳转LocalDateTime
     *
     * @param timestamp 时间戳  说明：调用这个方法时，如果出现格式不正确，大概是时间戳没有除以1000
     * @return LocalDateTime
     */
    public static LocalDateTime getLocalDateTime(long timestamp) {
        // 将时间戳转换为Instant
        Instant instant = Instant.ofEpochSecond(timestamp);
        // 转换为系统默认时区的LocalDateTime
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * @return
     */
    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    public static Date getDate(long timestamp) {
        return new Date(timestamp);
    }

    /**
     * String -> LocalDateTime
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime getLocalDateTime(String dateTime) {
        // 解析为 ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTime);
        // 转换为 LocalDateTime
        return zonedDateTime.toLocalDateTime();
    }

    /**
     * 开始时间
     *
     * @return
     */
    public static LocalDateTime getStartTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.toLocalDate().atStartOfDay();
    }

    /**
     * 结束时间
     *
     * @return
     */
    public static LocalDateTime getEndTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.toLocalDate().atTime(LocalTime.MAX);
    }

    /**
     * LocalDateTime -> Long
     *
     * @param localDateTime
     * @return
     */
    public static Long getDateTimeToLong(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

}
