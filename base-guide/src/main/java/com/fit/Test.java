package com.fit;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-12-30
 */
public class Test {
    public static void main(String[] args) {
        String[] columns = new String[]{"apple", "oppo", "vivo"};
        System.out.println(Joiner.on(",").join(columns));
        System.out.println(new SimpleDateFormat("yyyyMMdd").format(new Date()));



        long time = System.currentTimeMillis() / 1000 - 3 * 24 * 60 * 60;
        Date date = new Date(time * 1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000);
        System.out.println(calendar.get(Calendar.YEAR));


        String json = "{}";

        Map map = JSON.parseObject(json, Map.class);
        System.out.println(map);
    }
}
