package com.fit;

import com.google.common.base.Joiner;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    }
}
