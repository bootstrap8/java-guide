package com.fit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-09-05
 */
public class CollectionUtil {


    public static List<List<Map>> splitList(List<Map> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }
        List<List<Map>> result = new ArrayList<>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<Map> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }
}
