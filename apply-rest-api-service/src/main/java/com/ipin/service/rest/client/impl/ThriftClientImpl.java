package com.ipin.service.rest.client.impl;

import com.ipin.service.rest.client.ThriftClient;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;

import javax.ws.rs.DefaultValue;

/**
 * Created by longman on 1/13/16.
 */
public class ThriftClientImpl implements ThriftClient {
    private String ip;
    private int port;

    public ThriftClientImpl(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public TTransport getTransport(int timeOut) {
        TTransport transport = new TFramedTransport.Factory().getTransport(new TSocket(ip, port, timeOut));
        return transport;
    }

    public void close(TTransport transport) {
        transport.close();
    }

}