package com.fit.query.client;

import com.fit.query.common.FastQueryWithSql;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2020-09-04
 */
@Slf4j
public class QueryClientThread2 {
    public static void main(String[] args) {
        try {
            TTransport transport = new TFramedTransport(new TSocket("localhost", 8099));
            TProtocol protocol = new TBinaryProtocol(transport);
            FastQueryWithSql.Client client = new FastQueryWithSql.Client(protocol);
            transport.open();
            int i = 5;
            while (i > 0) {
                System.out.println("client2调用返回：" + client.queryForList(""));
                i--;
            }
            System.out.println("sleep over");
            transport.close();
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
