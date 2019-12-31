package com.fit.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * json工具
 */
public class JsonUtil {

    /**
     * 对象转Json
     *
     * @param o
     * @return
     */
    public static String toJson(Object o) {
        return JSON.toJSONString(o);
    }

    /**
     * json转bean
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toBean(String json, Class<T> clazz) {
        JSONObject userJson = JSONObject.parseObject(json);
        T object = JSON.toJavaObject(userJson, clazz);
        return object;
    }

}
