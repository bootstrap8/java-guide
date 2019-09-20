package com.fit.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-09-19
 */
public class DateUtil {

    private static ThreadLocal<SimpleDateFormat> formatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static String format(Date date) {
        return formatThreadLocal.get().format(date);
    }

    public static void main(String[] args) {
        System.out.println(format((new Date())));
    }
}
