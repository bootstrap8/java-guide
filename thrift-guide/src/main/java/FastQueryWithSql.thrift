namespace java com.fit.query.common

service FastQueryWithSql{

    i32 heartbeat();

    list<map<string,string>> queryForListByPage(1:string sql, 2:i32 s, 3:i32 r);

    list<map<string,string>> queryForList(1:string sql);

    list<map<string,string>> queryForListByGroupByPage(1:string sql, 2:i32 s, 3:i32 r);

    list<map<string,string>> queryForListByGroup(1:string sql);

    i32 queryCount(1:string sql);
}