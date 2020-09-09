package com.fit.query.common;

import org.apache.thrift.TException;

import java.util.List;
import java.util.Map;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2020-09-02
 */
public class FastQueryWithFilterImpl implements FastQueryWithFilter.Iface {

    @Override
    public int heartbeat() throws TException {
        return 1;
    }

    @Override
    public int count(List<String> indices, List<String> types, List<String> routings, String timeKey, long s, long e, String rule) throws TException {
        return 0;
    }

    @Override
    public List<Map<String, String>> queryForList(List<String> indices, List<String> types, List<String> routings, List<String> fields, String timeKey, long s, long e, String rule, int f, int size) throws TException {
        return null;
    }

    @Override
    public List<Map<String, String>> exportList(List<String> indices, List<String> types, List<String> routings, List<String> fields, String timeKey, long s, long e, String rule, String firstField) throws TException {
        return null;
    }

    @Override
    public List<Map<String, String>> exportListWithBuss(List<String> indices, List<String> types, List<String> routings, List<String> fields, String timeKey, long s, long e, String rule, String excludeRule, String firstField, List<String> bussFields) throws TException {
        return null;
    }
}
