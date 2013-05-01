package org.jboss.samples.webservices;


import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import server.ws.core.ServerWS;
import server.ws.interfaces.HelloWorld;

@WebService()
public class HelloWorldImpl implements HelloWorld {

//  @EJB
//  ServerWS server;
  
  @WebMethod()
  public String sayHello(String name) {
      System.out.println("Hello: " + name);
      
//      server.test();

      return "Hello " + name + "!";
     
      
  }
}
