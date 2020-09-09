namespace java com.fit.query.common

service ShortMessageIndb{
    i32 heartbeat();
    bool call(1:map<string,string> msg);
    bool mcall(1:list<map<string,string>> msgs);
}