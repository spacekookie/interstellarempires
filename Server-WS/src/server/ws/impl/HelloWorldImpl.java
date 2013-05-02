package server.ws.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import server.ws.interfaces.HelloWorld;

@WebService(serviceName = "HelloWorld", endpointInterface = "server.ws.interfaces.HelloWorld", targetNamespace = "http://leandersabel.de/")
public class HelloWorldImpl implements HelloWorld {

  @WebMethod()
  public String sayHello(String name) {

	return "Hello " + name + "!";

  }
}
