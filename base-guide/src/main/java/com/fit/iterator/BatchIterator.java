package com.fit.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-10-22
 */
public class BatchIterator<E> implements Iterator<List<E>> {
    /**
     * 当前的批次集合返回的元素个数
     */
    private int batchSize;
    private List<E> srcList;
    private int index = 0;
    private List<E> result;
    private int size = 0;

    public BatchIterator(List<E> srcList, int batchSize) {
        if (0 >= batchSize) {
            throw new RuntimeException("batchSize must greater than 0");
        }
        this.batchSize = batchSize;
        this.srcList = srcList;
        this.size = null == srcList ? 0 : srcList.size();
        result = new ArrayList<>(batchSize);
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public List<E> next() {
        result.clear();
        for (int i = 0; i < batchSize && index < size; i++) {
            result.add(srcList.get(index++));
        }
        return result;
    }

    /**
     * 暂不支持移除子集合方法
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}