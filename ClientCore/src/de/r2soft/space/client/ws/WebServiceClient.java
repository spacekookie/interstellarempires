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
import javax.jws.WebMethod;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.log4j.Logger;

import de.r2soft.space.server.ws.interfaces.ConnectionService;

public class WebServiceClient implements ConnectionService {
  private static WebServiceClient uniqInstance;

  private static String WSDL_BASE_URL = "http://localhost:8080/ServerWS/";
  private final Logger log = Logger.getLogger(this.getClass());

  private ConnectionService connectionClient;

  private Integer sessionID;

  private WebServiceClient() {
	// Initialize variables
	sessionID = -1;

	try {
	  QName connectionServiceName = new QName("http://2rSoftworks.de/", "ConnectionService");

	  URL connectionServiceWsdl = new URL(WSDL_BASE_URL + connectionServiceName.getLocalPart()
		  + "?wsdl");

	  Service connectionService = Service.create(connectionServiceWsdl, connectionServiceName);

	  connectionClient = connectionService.getPort(ConnectionService.class);
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

  public static void main(String[] args) {
	WebServiceClient client = WebServiceClient.getInstance();
	System.out.println(client.connect("Bob", "*****"));
  }

}
