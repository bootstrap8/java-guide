package com.fit.thrift.common;

import org.apache.thrift.TException;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2020-08-31
 */
public class HelloWorldServiceImpl implements HelloWorldService.Iface {

    public HelloWorldServiceImpl() {
    }

    @Override
    public String sayHello(String username) throws TException {
        return "hi " + username + ", welcome to thrift world!";
    }
}
