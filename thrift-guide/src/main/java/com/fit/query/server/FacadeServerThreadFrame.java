package com.fit.query.server;

import com.fit.query.common.FastQueryWithFilter;
import com.fit.query.common.FastQueryWithFilterImpl;
import com.fit.query.common.FastQueryWithSql;
import com.fit.query.common.FastQueryWithSqlImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多接口服务端
 *
 * @author Stone (300482)
 * @version 1.0
 * @since 2020-09-02
 */
@Slf4j
@SuppressWarnings("all")
public class FacadeServerThreadFrame {

    private int serverPort = 8095;

    private FastQueryWithFilter.Iface fastQueryWithFilter;

    private FastQueryWithSql.Iface fastQueryWithSql;

    public void startServer() {
        try {
            log.info("通用查询引擎开始启动,启动端口:{}", serverPort);

//            TNonblockingServerTransport serverSocket = new TNonblockingServerSocket(serverPort);
//            TThreadedSelectorServer.Args args = new TThreadedSelectorServer.Args(serverSocket);
//            args.workerThreads(Runtime.getRuntime().availableProcessors() * 2);
//            //serverParams.selectorThreads(4);
//            args.protocolFactory(new TBinaryProtocol.Factory());
//            TMultiplexedProcessor processor = new TMultiplexedProcessor();
//            processor.registerProcessor("FastQueryWithFilter", new FastQueryWithFilter.Processor<FastQueryWithFilter.Iface>(new FastQueryWithFilterImpl()));
//            processor.registerProcessor("FastQueryWithSql", new FastQueryWithSql.Processor<FastQueryWithSql.Iface>(new FastQueryWithSqlImpl()));
//            args.processor(processor);
//            TServer server = new TThreadedSelectorServer(args); //简单的单线程服务模型，常用于测试
//            server.serve();


            TNonblockingServerSocket socket = new TNonblockingServerSocket(serverPort);
            //多接口的实现
            TThreadedSelectorServer.Args arg = new TThreadedSelectorServer.Args(socket);
            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            processor.registerProcessor("FastQueryWithFilter", new FastQueryWithFilter.Processor<FastQueryWithFilter.Iface>(new FastQueryWithFilterImpl()));
            processor.registerProcessor("FastQueryWithSql", new FastQueryWithSql.Processor<FastQueryWithSql.Iface>(new FastQueryWithSqlImpl()));
            arg.processor(processor);
            arg.protocolFactory(new TCompactProtocol.Factory());
            //如果传输数据量过大,需要修改这个地方的参数,默认16M
            int setment = 8 * 1638400000;
            arg.transportFactory(new TFramedTransport.Factory(setment));
            arg.processorFactory(new TProcessorFactory(processor));
            //监听线程数
            arg.selectorThreads(4);
            //工作线程数
            ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
            arg.executorService(pool);
            arg.getExecutorService();
            TServer server = new TThreadedSelectorServer(arg);
            server.serve();
        } catch (Exception e) {
            log.error("通用查询引擎启动异常:", e);
        }
    }

    public static void main(String[] args) {
        FacadeServerThreadFrame server = new FacadeServerThreadFrame();
        server.startServer();
    }
}
