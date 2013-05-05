package de.r2soft.space.server.ws.impl;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import de.r2soft.space.framework.players.Player;
import de.r2soft.space.server.ws.core.ServerWS;
import de.r2soft.space.server.ws.core.SessionManager;
import de.r2soft.space.server.ws.interfaces.HelloWorld;
import de.r2soft.space.server.ws.interfaces.ServerWSLocal;
import de.r2soft.space.server.ws.interfaces.SessionManagerLocal;



@WebService(serviceName = "HelloWorld", endpointInterface = "server.ws.interfaces.HelloWorld", targetNamespace = "http://leandersabel.de/")
public class HelloWorldImpl implements HelloWorld {
  
  @EJB
  ServerWSLocal server;
  
  @EJB
  SessionManagerLocal sessionManager;

  @Override
  @WebMethod()
  public String sayHello(String name) {

	return "Hello " + name + "!";

  }

 
}
