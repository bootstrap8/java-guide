package com.fit.query.server;

import com.fit.query.common.FastQueryWithFilter;
import com.fit.query.common.FastQueryWithFilterImpl;
import com.fit.query.common.FastQueryWithSql;
import com.fit.query.common.FastQueryWithSqlImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;

/**
 * 多接口服务端
 *
 * @author Stone (300482)
 * @version 1.0
 * @since 2020-09-02
 */
@Slf4j
public class FacadeServerThread {

    private int serverPort = 8095;

    private FastQueryWithFilter.Iface fastQueryWithFilter;

    private FastQueryWithSql.Iface fastQueryWithSql;

    public void startServer() {
        try {
            log.info("通用查询引擎开始启动,启动端口:{}", serverPort);

            TNonblockingServerTransport serverSocket = new TNonblockingServerSocket(serverPort);
            TThreadedSelectorServer.Args serverParams = new TThreadedSelectorServer.Args(serverSocket);
            serverParams.workerThreads(Runtime.getRuntime().availableProcessors() * 2);
            //serverParams.selectorThreads(4);
            serverParams.protocolFactory(new TBinaryProtocol.Factory());
            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            processor.registerProcessor("FastQueryWithFilter", new FastQueryWithFilter.Processor<FastQueryWithFilter.Iface>(new FastQueryWithFilterImpl()));
            processor.registerProcessor("FastQueryWithSql", new FastQueryWithSql.Processor<FastQueryWithSql.Iface>(new FastQueryWithSqlImpl()));
            serverParams.processor(processor);
            TServer server = new TThreadedSelectorServer(serverParams); //简单的单线程服务模型，常用于测试
            server.serve();
//            TMultiplexedProcessor processor = new TMultiplexedProcessor();
//            TServerTransport t = new TServerSocket(serverPort);
//            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(t).processor(processor));
//            processor.registerProcessor("FastQueryWithFilter", new FastQueryWithFilter.Processor<FastQueryWithFilter.Iface>(new FastQueryWithFilterImpl()));
//            processor.registerProcessor("FastQueryWithSql", new FastQueryWithSql.Processor<FastQueryWithSql.Iface>(new FastQueryWithSqlImpl()));
//            server.serve();
        } catch (Exception e) {
            log.error("通用查询引擎启动异常:", e);
        }
    }

    public static void main(String[] args) {
        FacadeServerThread server = new FacadeServerThread();
        server.startServer();
    }
}
