package com.fit.query.server;

import com.fit.query.common.FastQueryWithSql;
import com.fit.query.common.FastQueryWithSqlImpl;
import com.fit.thrift.common.HelloWorldService;
import com.fit.thrift.common.HelloWorldServiceImpl;
import com.fit.thrift.server.HelloServer;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2020-08-31
 */
public class QueryServer {
    public static final int SERVER_PORT = 8091;

    public void startServer() {
        try {
            System.out.println("FastQuery server start ....");

            //在这里调用了 HelloWorldImpl 规定了接受的方法和返回的参数
            TProcessor tprocessor = new FastQueryWithSql.Processor<FastQueryWithSql.Iface>(new FastQueryWithSqlImpl());

            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tprocessor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();

        } catch (Exception e) {
            System.out.println("Server start error!!!");
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        QueryServer server = new QueryServer();
        server.startServer();
    }
}
