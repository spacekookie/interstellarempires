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

import java.net.URL;
import java.util.Properties;

import javax.jws.WebMethod;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.log4j.Logger;

import com.google.common.collect.Lists;

import de.r2soft.space.client.util.WebServiceUtil;
import de.r2soft.space.server.ws.interfaces.ConnectionService;

public class WebServiceClient implements ConnectionService {
  private static final WebServiceClient instance = new WebServiceClient();;

  private final Logger log = Logger.getLogger(this.getClass());

  private ConnectionService connectionClient;

  private Integer sessionID;

  private WebServiceClient() {
	this.sessionID = -1;

	QName conServiceQ = ConnectionService.ServiceName;
	Properties properties = WebServiceUtil.loadProperties("server_config.properties");
	String serverLocation = properties.getProperty("server", "http://localhost:8080/ServerWS/");

	URL conServiceWsld = WebServiceUtil.buildURL(Lists.newArrayList(serverLocation,
		conServiceQ.getLocalPart(), "?wsdl"));
	Service connectionService = Service.create(conServiceWsld, conServiceQ);

	connectionClient = connectionService.getPort(ConnectionService.class);
  }

  /**
   * Get a handle to this singleton WebService client.
   * 
   * @return
   */
  public static synchronized WebServiceClient getInstance() {
	return instance;
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
