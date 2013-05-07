/* #########################################################################
 * Copyright (c) 2013 Random Robot Softworks
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 ######################################################################### */
package de.r2soft.space.client.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import javax.jws.WebMethod;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.log4j.Logger;

import de.r2soft.space.framework.map.IntVec2;
import de.r2soft.space.framework.objects.GameObject;
import de.r2soft.space.framework.objects.PlayerObject;
import de.r2soft.space.server.ws.interfaces.ConnectionService;
import de.r2soft.space.server.ws.interfaces.GameObjectService;

public class WebServiceClient implements ConnectionService, GameObjectService {

  private static WebServiceClient uniqInstance;

  private static String WSDL_BASE_URL = "http://localhost:8080/ServerWS/";
  private final Logger log = Logger.getLogger(this.getClass());

  private ConnectionService connectionClient;
  private GameObjectService gameObjectClient;

  private Integer sessionID;

  private WebServiceClient() {
	try {
	  QName connectionServiceName = new QName("http://2rSoftworks.de/", "ConnectionService");
	  QName gameObjectServiceName = new QName("http://2rSoftworks.de/", "GameObjectService");

	  URL connectionServiceWsdl = new URL(WSDL_BASE_URL + connectionServiceName.getLocalPart()
		  + "?wsdl");
	  URL gameObjectServiceWsdl = new URL(WSDL_BASE_URL + gameObjectServiceName.getLocalPart()
		  + "?wsdl");

	  Service connectionService = Service.create(connectionServiceWsdl, connectionServiceName);
	  Service gameObjectService = Service.create(gameObjectServiceWsdl, gameObjectServiceName);

	  connectionClient = connectionService.getPort(ConnectionService.class);
	  gameObjectClient = gameObjectService.getPort(GameObjectService.class);
	}
	catch (MalformedURLException e) {
	  log.error("Invalid wsdl URL", e);
	}
  }

  /**
   * Get a handle to this singleton WebService client.
   * 
   * @return
   */
  public static synchronized WebServiceClient getInstance() {
	if (uniqInstance == null) {
	  uniqInstance = new WebServiceClient();
	}
	return uniqInstance;
  }

  /**
   * Get the session ID used to request information from the server.
   * 
   * @return
   */
  public Integer getSessionID() {
	return sessionID;
  }

  @Override
  @WebMethod
  public Integer connect(String username, String password) {
	return connectionClient.connect(username, password);
  }

  @Override
  @WebMethod
  public boolean disconnect(Integer sessionID) {
	return connectionClient.disconnect(sessionID);
  }

  @Override
  @WebMethod
  public Set<GameObject> getGlobalGameObjects(Integer sessionID) {
	return gameObjectClient.getGlobalGameObjects(sessionID);
  }

  @Override
  @WebMethod
  public Set<PlayerObject> getPlayerObjects(Integer sessionID) {
	return gameObjectClient.getPlayerObjects(sessionID);
  }

  @Override
  @WebMethod
  public Set<PlayerObject> getObjectsInSystem(Integer sessionID, IntVec2 system) {
	return gameObjectClient.getObjectsInSystem(sessionID, system);
  }
  
  public static void main(String[] args) {
	WebServiceClient client = WebServiceClient.getInstance();
	System.out.println(client.connect("Bob", "*****"));
  }

}
