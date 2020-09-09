namespace java com.fit.query.common

service FastQueryWithFilter{

    i32 heartbeat();

  	i32 count( 1:list<string> indices, 2:list<string> types, 3:list<string> routings, 4:string timeKey, 5:i64 s, 6:i64 e, 7:string rule );

    list<map<string,string>> queryForList( 1:list<string> indices, 2:list<string> types, 3:list<string> routings, 4:list<string> fields, 5:string timeKey, 6:i64 s, 7:i64 e, 8:string rule, 9:i32 f, 10:i32 size );

    list<map<string,string>> exportList( 1:list<string> indices, 2:list<string> types, 3:list<string> routings, 4:list<string> fields, 5:string timeKey, 6:i64 s, 7:i64 e, 8:string rule, 9:string firstField );

    list<map<string,string>> exportListWithBuss( 1:list<string>  indices, 2:list<string>  types, 3:list<string>  routings, 4:list<string>  fields, 5:string timeKey, 6:i64 s, 7:i64 e, 8:string rule, 9:string excludeRule, 10:string firstField , 11:list<string> bussFields);

}