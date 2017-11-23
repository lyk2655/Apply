package com.linyk3.gen.apply.test;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.linyk3.thrift.apply.ApplyService.Client;
import com.linyk3.thrift.apply.EmployInfo;
import com.linyk3.thrift.apply.params.FindEmployInfoParams;
import com.linyk3.thrift.apply.params.ListEmployInfoParams;

import junit.framework.TestCase;

public class EmployTest extends TestCase{
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

	public void testFindEmployInfo() throws TException {
		FindEmployInfoParams request = new FindEmployInfoParams();
		request.setEmployId("56f91b3e25ecbf1992a3ab0e");
		EmployInfo response = client.findEmployInfo(request);
		System.out.println(response);

	}
	
	public void testListEmployInfo() throws TException {
		ListEmployInfoParams request = new ListEmployInfoParams();
		List<EmployInfo> response = client.listEmployInfo(request);
		System.out.println(response);

	}

}
