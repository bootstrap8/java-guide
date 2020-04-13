package com.fit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static void main(String[] args) throws ParseException {
        System.out.println(format((new Date())));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        Date begin = new Date(calendar.getTimeInMillis());
        System.out.println(format(begin));
        calendar.add(Calendar.MONTH, 3);
        Date end = new Date(calendar.getTimeInMillis());
        System.out.println(format(end));
        System.out.println("days:" + daysBetween(begin, end));


        System.out.println("-------------------------------");
        System.out.println(timestamp());
    }

    public static String timestamp() {
        return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    }

    public static int daysBetween(Date smdate, Date bdate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        smdate = sdf.parse(sdf.format(smdate));

        bdate = sdf.parse(sdf.format(bdate));

        Calendar cal = Calendar.getInstance();

        cal.setTime(smdate);

        long time1 = cal.getTimeInMillis();

        cal.setTime(bdate);

        long time2 = cal.getTimeInMillis();

        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));

    }
}
