package com.fit.webservice.jdk;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-10-29
 */
@WebService
public class SayWebService {

    @WebMethod(operationName = "sayHello")
    @WebResult(name = "helloName")
    public String sayHello(@WebParam(name = "name") String name) {
        return "hello " + name;
    }

    /**
     * main方法中发布
     */
    public static void main(String[] args) {
        String address = "http://localhost:9001/service/say";
        Endpoint.publish(address, new SayWebService());
        System.out.println("发布成功");
    }
}