package org.jboss.samples.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

import server.ws.interfaces.HelloWorld;

@WebService()
public class HelloWorldImpl implements HelloWorld {

  @WebMethod()
  public String sayHello(String name) {

	return "Hello " + name + "!";

  }
}
