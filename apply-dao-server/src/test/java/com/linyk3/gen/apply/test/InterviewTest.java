package com.linyk3.gen.apply.test;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.linyk3.thrift.apply.ApplyService.Client;
import com.linyk3.thrift.apply.InterviewInfo;
import com.linyk3.thrift.apply.params.FindInterviewInfoParams;
import com.linyk3.thrift.apply.params.ListInterviewInfoParams;

import junit.framework.TestCase;

public class InterviewTest extends TestCase{
	protected TTransport transport;
	protected Client client = null;

	public void setUp() throws Exception {
		 transport = new TFramedTransport(new TSocket("127.0.0.1", 9393));
//		transport = new TFramedTransport(new TSocket("192.168.1.81", 9192));
		transport.open();

		TProtocol protocol = new TBinaryProtocol(transport);
		client = new Client(protocol);
	}

	public void setDown() {
		transport.close();
	}

	public void testFindInterviewInfo() throws TException {
		FindInterviewInfoParams request = new FindInterviewInfoParams();
		request.setInterviewId("56f92a298c12eb99cbcd5b80");
		InterviewInfo response = client.findInterviewInfo(request);
		System.out.println(response);

	}
	
	public void testListInterviewInfo() throws TException {
		ListInterviewInfoParams request = new ListInterviewInfoParams();
		List<InterviewInfo> response = client.listInterviewInfo(request);
		System.out.println(response);

	}


}
