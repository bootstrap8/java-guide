package com.fit.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
    }

}