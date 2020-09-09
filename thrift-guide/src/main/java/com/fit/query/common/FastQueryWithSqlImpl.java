package com.fit.query.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.thrift.TException;

import java.util.List;
import java.util.Map;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2020-08-31
 */
public class FastQueryWithSqlImpl implements FastQueryWithSql.Iface {


    @Override
    public int heartbeat() throws TException {
        return 0;
    }

    @Override
    public List<Map<String, String>> queryForListByPage(String sql, int s, int r) throws TException {
        return null;
    }

    @Override
    public List<Map<String, String>> queryForList(String sql) throws TException {
        List<Map<String, String>> list = Lists.newArrayList();
        Map<String, String> map = Maps.newHashMap();
        map.put("name", "Jack");
        map.put("age", "18");
        list.add(map);
        return list;
    }

    @Override
    public List<Map<String, String>> queryForListByGroupByPage(String sql, int s, int r) throws TException {
        return null;
    }

    @Override
    public List<Map<String, String>> queryForListByGroup(String sql) throws TException {
        return null;
    }

    @Override
    public int queryCount(String sql) throws TException {
        return 0;
    }
}

