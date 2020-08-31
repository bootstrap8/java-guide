package com.fit.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2020-07-07
 */
public class RegexText {
    public static void main(String[] args) {
        String json = "{\"m_AttributeName\":\"shipMsg\",\"m_DataType\":4,\"m_Value\":\"{\\\"name\\\":\\\"/Alarm/Abims/RelationShip\\\",\\\"topic\\\":\\\"AlarmRelation-RelationShip\\\",\\\"text\\\":\\\"\\\",\\\"subNodes\\\":[{\\\"name\\\":\\\"relationShip\\\",\\\"text\\\":\\\"relationShip\\\",\\\"subNodes\\\":[],\\\"nodeAttrs\\\":{\\\"daltime\\\":1594175767,\\\"relationtype\\\":\\\"3\\\",\\\"rulename\\\":\\\"同一网元同时产生多条BBU光模块收发异常告警衍生一条告警\\\",\\\"padaltime\\\":1594175781,\\\"childalarm\\\":\\\"guizhou_170_143813031\\\",\\\"parentalarm\\\":\\\"11_guizhou_170_143813031_2020264\\\",\\\"paserialno\\\":\\\"11_guizhou_170_143813031_2020264\\\",\\\"serialno\\\":\\\"guizhou_170_143813031\\\"}}],\\\"nodeAttrs\\\":{}}\"}]}]";
        Pattern pattern = Pattern.compile("(.*)\\{\\\"name(.*)");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            System.out.println("0-->" + matcher.group(0));
            System.out.println("1-->" + matcher.group(1));
            System.out.println("2-->" + matcher.group(2));
        } else {
            System.out.println("Not");
        }
        System.out.println(json.substring(json.indexOf("{\\\"name")));
        String nodeJson = json.substring(json.indexOf("{\\\"name"));
        //System.out.println(json.substring(json.indexOf("paserialno")));
        nodeJson = nodeJson.substring(0, nodeJson.length() - 5);
        System.out.println(nodeJson);
    }
}
