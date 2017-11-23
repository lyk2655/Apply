package com.ipin.service.rest.client;

import org.apache.thrift.transport.TTransport;

/**
 * Created by longman on 1/13/16.
 */
public interface ThriftClient {
    public TTransport getTransport(int timeOut);

    public void close(TTransport transport);
}
