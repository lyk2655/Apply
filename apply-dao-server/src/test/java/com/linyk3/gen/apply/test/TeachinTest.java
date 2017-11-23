package com.linyk3.gen.apply.test;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.linyk3.thrift.apply.TeachinInfo;
import com.linyk3.thrift.apply.ApplyService.Client;
import com.linyk3.thrift.apply.params.FindTeachinInfoParams;
import com.linyk3.thrift.apply.params.ListTeachinInfoParams;

import junit.framework.TestCase;

public class TeachinTest extends TestCase{
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

	public void testFindTeachinInfo() throws TException {
		FindTeachinInfoParams request = new FindTeachinInfoParams();
//		request.setTeachinName("南方基金管理有限公司");
		request.setTeachinId("56f92c918c12eb99cbcd5b83");
		TeachinInfo response = client.findTeachinInfo(request);
		System.out.println(response);

	}
	
	public void testListTeachinInfo() throws TException {
		ListTeachinInfoParams request = new ListTeachinInfoParams();
		List<TeachinInfo> response = client.listTeachinInfo(request);
		System.out.println(response);

	}


}
