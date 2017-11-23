package com.linyk3.gen.apply.test;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.linyk3.thrift.apply.ApplicationInfo;
import com.linyk3.thrift.apply.ApplyService.Client;
import com.linyk3.thrift.apply.params.FindApplicationInfoParams;
import com.linyk3.thrift.apply.params.ListApplicationInfoParams;

import junit.framework.TestCase;

public class ApplicationTest extends TestCase{
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

	public void testFindApplicationInfo() throws TException {
		FindApplicationInfoParams request = new FindApplicationInfoParams();
		request.setApplicationId("56f91dd78c12eb99cbcd5b7d");
		ApplicationInfo response = client.findApplicationInfo(request);
		System.out.println(response);

	}
	
	public void testListApplicationInfo() throws TException {
		ListApplicationInfoParams request = new ListApplicationInfoParams();
		List<ApplicationInfo> response = client.listApplicationInfo(request);
		System.out.println(response);

	}


}
