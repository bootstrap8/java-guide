package com.fit.encoder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-10-29
 */
public class Encoder {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(URLEncoder.encode("xx|oo", "utf-8"));

    }
}
