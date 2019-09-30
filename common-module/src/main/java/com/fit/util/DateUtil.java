package com.fit.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-09-21
 */
public class DateUtil {

    private static ThreadLocal<DateFormat> longFormater = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static String format(Date date) {
        return longFormater.get().format(date);
    }

}
