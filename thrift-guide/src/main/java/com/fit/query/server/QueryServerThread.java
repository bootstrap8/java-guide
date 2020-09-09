package com.fit.query.server;

import com.fit.query.common.FastQueryWithSql;
import com.fit.query.common.FastQueryWithSqlImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2020-09-04
 */
@Slf4j
public class QueryServerThread {
    public static void main(String[] args) {
        try {
            TNonblockingServerTransport serverSocket = new TNonblockingServerSocket(8099);
            TThreadedSelectorServer.Args serverParams = new TThreadedSelectorServer.Args(serverSocket);
            System.out.println("-->" + Runtime.getRuntime().availableProcessors());
            serverParams.workerThreads(Runtime.getRuntime().availableProcessors() * 2);
            //serverParams.selectorThreads(4);
            serverParams.protocolFactory(new TBinaryProtocol.Factory());
            serverParams.processor(new FastQueryWithSql.Processor<FastQueryWithSql.Iface>(new FastQueryWithSqlImpl()));
            TServer server = new TThreadedSelectorServer(serverParams); //简单的单线程服务模型，常用于测试
            System.out.println("start server");
            server.serve();
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
