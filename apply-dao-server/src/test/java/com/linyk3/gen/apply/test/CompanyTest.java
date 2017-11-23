package com.linyk3.gen.apply.test;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.linyk3.thrift.apply.ApplyService.Client;
import com.linyk3.thrift.apply.CompanyInfo;
import com.linyk3.thrift.apply.params.FindCompanyInfoParams;
import com.linyk3.thrift.apply.params.ListCompanyInfoParams;

import junit.framework.TestCase;

public class CompanyTest extends TestCase{
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

	public void testFindCompanyInfo() throws TException {
		FindCompanyInfoParams request = new FindCompanyInfoParams();
//		request.setCompanyName("南方基金管理有限公司");
		request.setCompanyId("56f917b425ecbf1992a3ab0d");
		CompanyInfo response = client.findCompanyInfo(request);
		System.out.println(response);

	}
	
	public void testListCompanyInfo() throws TException {
		ListCompanyInfoParams request = new ListCompanyInfoParams();
		List<CompanyInfo> response = client.listCompanyInfo(request);
		System.out.println(response);

	}

}
