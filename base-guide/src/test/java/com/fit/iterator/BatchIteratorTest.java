package com.fit.iterator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-10-22
 */
public class BatchIteratorTest {

    @Test
    public void hasNext() {
        List<Integer> numbox = new ArrayList<Integer>();
        Collections.addAll(numbox, 1, 2, 3, 4, 5, 6, 7);
        Iterator<List<Integer>> batchIter = new BatchIterator(numbox, 3);
        while (batchIter.hasNext()) {
            List<Integer> nums = batchIter.next();
            System.out.println(nums);
        }

        List<Map<String, Object>> parentList = Lists.newArrayList();
        for (int i = 0; i <10 ; i++) {
            Map<String,Object> map = Maps.newHashMap();
            map.put(String.valueOf(i),String.valueOf(i));
            parentList.add(map);
        }

        BatchIterator<Map<String, Object>> batchIterator = new BatchIterator(parentList, 2);
        while (batchIterator.hasNext()) {
            List<Map<String, Object>> itemList = batchIterator.next();
            System.out.println(itemList);
        }
    }

}