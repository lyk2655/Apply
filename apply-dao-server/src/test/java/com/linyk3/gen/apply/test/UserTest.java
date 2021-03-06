package com.linyk3.gen.apply.test;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.linyk3.thrift.apply.ApplyService.Client;
import com.linyk3.thrift.apply.UserInfo;
import com.linyk3.thrift.apply.params.FindUserInfoParams;
import com.linyk3.thrift.apply.params.ListUserInfoParams;

import junit.framework.TestCase;

public class UserTest extends TestCase {

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

	// MajorIdBaseInfo findMajorIdBaseInfo(FindMajorIdBaseInfoParams
	public void testFindUserInfo() throws TException {
		FindUserInfoParams request = new FindUserInfoParams();
		request.setName("apply");
		UserInfo response = client.findUserInfo(request);
		System.out.println(response);

	}
	
	public void testListUserInfo() throws TException {
		ListUserInfoParams request = new ListUserInfoParams();
		List<UserInfo> response = client.listUserInfo(request);
		System.out.println(response);

	}

}
