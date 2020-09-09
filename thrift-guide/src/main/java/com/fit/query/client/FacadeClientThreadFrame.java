package com.fit.query.client;

import com.fit.query.common.FastQueryWithFilter;
import com.fit.query.common.FastQueryWithSql;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * 多接口客户端
 *
 * @author Stone (300482)
 * @version 1.0
 * @since 2020-09-02
 */
@Slf4j
@SuppressWarnings("all")
public class FacadeClientThreadFrame {

    public static void main(String[] args) throws TException {

        try {
            final TSocket tSocket = new TSocket("localhost", 8095);
            int setment = 8 * 1638400000;
            final TTransport tTransport = new TFramedTransport(tSocket, setment);


            //TTransport transport = new TFramedTransport(new TSocket("localhost", 8095), 4 * 1638400000);
            TProtocol protocol = new TCompactProtocol(tTransport);

            //获取FastQueryWithFilter服务
            TMultiplexedProtocol fastQueryWithFilter = new TMultiplexedProtocol(protocol, "FastQueryWithFilter");
            FastQueryWithFilter.Client fastQueryWithFilterService = new FastQueryWithFilter.Client(fastQueryWithFilter);
            //获取FastQueryWithSql服务
            TMultiplexedProtocol fastQueryWithSql = new TMultiplexedProtocol(protocol, "FastQueryWithSql");
            FastQueryWithSql.Client FastQueryWithSqlService = new FastQueryWithSql.Client(fastQueryWithSql);

            //FastQueryWithSql.Client client = new FastQueryWithSql.Client(protocol);
            tTransport.open();
            int i = 5;
            while (i > 0) {
                System.out.println("client调用返回：" + FastQueryWithSqlService.queryForList(""));
                System.out.println("client调用返回2：" + fastQueryWithFilterService.heartbeat());
                i--;
            }
            System.out.println("sleep over");
            tTransport.close();
        } catch (Exception e) {
            log.error("Fxxk2-->", e);
        }
       /* TSocket transport = new TSocket("localhost", 8095);
        TBinaryProtocol protocol = new TBinaryProtocol(transport);
        //获取FastQueryWithFilter服务
        TMultiplexedProtocol fastQueryWithFilter = new TMultiplexedProtocol(protocol, "FastQueryWithFilter");
        FastQueryWithFilter.Client fastQueryWithFilterService = new FastQueryWithFilter.Client(fastQueryWithFilter);

        //获取FastQueryWithSql服务
        TMultiplexedProtocol fastQueryWithSql = new TMultiplexedProtocol(protocol, "FastQueryWithSql");
        FastQueryWithSql.Client FastQueryWithSqlService = new FastQueryWithSql.Client(fastQueryWithSql);
        transport.open();
        System.out.println("fastQueryWithFilterService-->" + fastQueryWithFilterService.heartbeat());
        System.out.println("FastQueryWithSqlService-->" + FastQueryWithSqlService.heartbeat());
        transport.close();*/
    }
}
