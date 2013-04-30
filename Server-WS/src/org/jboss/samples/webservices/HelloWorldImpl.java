package org.jboss.samples.webservices;

import interfaces.HelloWorld;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService()
public class HelloWorldImpl implements HelloWorld {

  @WebMethod()
  public String sayHello(String name) {
      System.out.println("Hello: " + name);
      return "Hello " + name + "!";
  }
}
