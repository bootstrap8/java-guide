package com.fit.query.client;

import com.fit.query.common.FastQueryWithSql;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.List;
import java.util.Map;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2020-08-31
 */
public class QueryClient2 {
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 8091;
    public static final int TIMEOUT = 5;

    /**
     * @param userName
     */
    public void startClient(String userName) {
        TTransport transport = null;
        try {
            transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);

            FastQueryWithSql.Client client = new FastQueryWithSql.Client(protocol);
            transport.open();
            //String result = client.sayHello(userName);
            //System.out.println("Thrift client result =: " + result);
            int count = client.queryCount("select count(1) from dual");
            System.out.println("Thrift client result =: " + count);

            List<Map<String, String>> list = client.queryForList("");
            System.out.println("query -->" + list);

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        QueryClient2 client = new QueryClient2();
        client.startClient("stone");

    }
}
