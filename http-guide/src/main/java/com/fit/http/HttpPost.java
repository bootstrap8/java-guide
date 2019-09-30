package com.fit.http;

import okhttp3.*;

import java.io.IOException;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-09-30
 */
public class HttpPost {
    public static void main(String[] args) {
        String url = "https://www.baidu.com/";
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("键", "值")
                .add("键", "值")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
