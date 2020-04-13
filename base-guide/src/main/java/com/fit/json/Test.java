package com.fit.json;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2020-03-27
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        List<User> list = Lists.newArrayList();
        User user = new User();
        user.setName("jack");
        list.add(user);
        user = new User();
        user.setName("stone");
        list.add(user);
        String json = JSON.toJSONString(list);
        System.out.println(json);

        String arrayJson = "[{\"name\":\"jack\"},{\"name\":\"stone\"}]";
        User[] users;
        try {
            users = JSON.parseObject(arrayJson, User[].class);
            System.out.println("-->" + users.length);
        } catch (Exception e) {
            log.error(String.format("解析报文异常: %s", arrayJson), e);
            return;
        }


        arrayJson = "{\"name\":\"jack\"}";
        try {
            users = JSON.parseObject(arrayJson, User[].class);
            System.out.println("-->" + users.length);
        } catch (Exception e) {
            log.error(String.format("解析报文异常: %s", arrayJson), e);
            return;
        }

    }

    @Data
    static class User {
        private String name;
    }
}
