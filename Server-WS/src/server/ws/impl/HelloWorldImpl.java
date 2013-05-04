package server.ws.impl;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import framework.players.Player;

import server.ws.core.ServerWS;
import server.ws.core.SessionManager;
import server.ws.interfaces.HelloWorld;
import server.ws.interfaces.ServerWSLocal;
import server.ws.interfaces.SessionManagerLocal;

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

  @Override
  @WebMethod
  public Integer registerUser(String username) {
	Integer session = sessionManager.registerSession(new Player(username));
	return session;
  }
}
