package com.fit.webservice.jdk;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-10-29
 */
public class CallWebService {

    @WebService(name = "ServiceStub", targetNamespace = "http://jdk.webservice.fit.com/")
    interface ServiceStub {

        @WebMethod
        @WebResult(name = "helloName")
        String sayHello(@WebParam(name = "name") String name);
    }

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:9001/service/say?wsdl");
        String tns = "http://jdk.webservice.fit.com/";
        QName sqn = new QName(tns, "SayWebServiceService");
        ServiceStub stub = Service.create(url, sqn).getPort(new QName(tns, "SayWebServicePort"), ServiceStub.class);
        String name = "Jack";
        System.out.println("param:" + name);
        String result = stub.sayHello(name);
        System.out.println("result:" + result);
    }
}
