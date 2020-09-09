package com.fit.query;

import java.util.List;
import java.util.Map;

public interface FastQueryWithSql {

    List<Map> queryForListByPage(String sql, int s, int r);

    List<Map> queryForList(String sql);

    List<Map> queryForListByGroupByPage(String sql, int s, int r);

    List<Map> queryForListByGroup(String sql);

    int queryCount(String sql);
}
