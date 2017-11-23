package com.linyk3.apply.server;


import javax.annotation.Resource;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.linyk3.thrift.apply.ApplyService;

@Component
public class ApplyServer {
	public static final Logger logger = LoggerFactory.getLogger(ApplyService.class);

	@Value("#{config['apply.server.port']}")
	private int port;

	@Value("#{config['apply.server.minworkerthreads']}")
	private Integer minWorkerThreads = 10;

	@Value("#{config['apply.server.maxworkerthreads']}")
	private Integer maxWorkerThreads = 100;

	@Resource
	private ApplyService.Iface applyService;


	/**
	 * 启动服务
	 */
//	@PostConstruct
	public void startServer() {
		try {
			ApplyService.Processor process = new ApplyService.Processor(applyService);
			TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(port);
			TServer server = new THsHaServer(new THsHaServer.Args(serverSocket).minWorkerThreads(minWorkerThreads)
					.maxWorkerThreads(maxWorkerThreads).protocolFactory(new TBinaryProtocol.Factory())
					.transportFactory(new TFramedTransport.Factory()).processor(process));
			logger.info("ApplyService server start success... listening port : " + port);
			server.serve();
		} catch (TTransportException e) {
			logger.error("Error occured while starting ApplyService server, message code : " + e.getMessage(), e);
		}
	}

}
