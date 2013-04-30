package org.jboss.samples.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService()
public class HelloWorld {

  @WebMethod()
  public String sayHello(String name) {
      System.out.println("Hello: " + name);
      return "Hello " + name + "!";
  }
}
