package com.test.sth_useful.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateUtil {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final ThreadLocal<DateFormat> dayFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(YYYY_MM_DD);
        }
    };

    public static final ThreadLocal<DateFormat> secondFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        }
    };



}
