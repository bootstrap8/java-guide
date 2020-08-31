package com.fit.http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2020-03-12
 */
@Slf4j
public class HttpUtil {

    private static String GET = "get";
    private static String POST = "post";
    private static int DEFAULT_TIMEOUT = 60;
    private static final String MEDIA_TYPE_JSON = "application/json;charset=utf-8";

    private HttpUtil() {
    }


    public static String get(String url, Map<String, String> params) {
        return doRequest(GET, url, params);
    }


    public static String post(String url, Map<String, String> params) {
        return doRequest(POST, url, params);
    }


    public static String doRequest(String method, String url, Map<String, String> params) {

        if (method == null) {
            throw new RuntimeException("请求方法不能为空");
        }

        if (url == null) {
            throw new RuntimeException("url不能为空");
        }

        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();

        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                httpBuilder.addQueryParameter(param.getKey(), param.getValue());
            }
        }

        Request request = new Request.Builder().url(httpBuilder.build()).method(method, new FormBody.Builder().build()).build();

        try {
            OkHttpClient client = new OkHttpClient.Builder().readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            log.error("", e);
            return null;
        }
    }


    public static String postByJson(String url, String json) {

        RequestBody body = RequestBody.create(MediaType.parse(MEDIA_TYPE_JSON), json);
        Request request = new Request.Builder().url(url).post(body).build();

        try {
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            log.error("", e);
            return null;
        }
    }

    public static void main(String[] args) {

    }

}
