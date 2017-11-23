package com.linyk3.gen.apply.test;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.linyk3.thrift.apply.ApplyService.Client;
import com.linyk3.thrift.apply.ExaminationInfo;
import com.linyk3.thrift.apply.params.FindExaminationInfoParams;
import com.linyk3.thrift.apply.params.ListExaminationInfoParams;

import junit.framework.TestCase;

public class ExaminationTest extends TestCase{
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

	public void testFindExaminationInfo() throws TException {
		FindExaminationInfoParams request = new FindExaminationInfoParams();
		request.setExaminationId("56f929ba8c12eb99cbcd5b7e");
		ExaminationInfo response = client.findExaminationInfo(request);
		System.out.println(response);

	}
	
	public void testListExaminationInfo() throws TException {
		ListExaminationInfoParams request = new ListExaminationInfoParams();
		List<ExaminationInfo> response = client.listExaminationInfo(request);
		System.out.println(response);

	}


}
