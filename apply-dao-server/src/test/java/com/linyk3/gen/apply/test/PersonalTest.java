package com.linyk3.gen.apply.test;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.linyk3.thrift.apply.ApplyService.Client;
import com.linyk3.thrift.apply.PersonalInfo;
import com.linyk3.thrift.apply.params.FindPersonalInfoParams;
import com.linyk3.thrift.apply.params.ListPersonalInfoParams;

import junit.framework.TestCase;

public class PersonalTest extends TestCase{
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

	public void testFindPersonalInfo() throws TException {
		FindPersonalInfoParams request = new FindPersonalInfoParams();
		request.setPersonalId("56f92c118c12eb99cbcd5b82");
		PersonalInfo response = client.findPersonalInfo(request);
		System.out.println(response);

	}
	
	public void testListPersonalInfo() throws TException {
		ListPersonalInfoParams request = new ListPersonalInfoParams();
		List<PersonalInfo> response = client.listPersonalInfo(request);
		System.out.println(response);

	}


}
