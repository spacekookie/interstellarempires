/* #########################################################################
 * Copyright (c) 2014 RANDOM ROBOT SOFTWORKS
 * (See @authors file)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ######################################################################### */

package de.r2soft.empires.client.networking;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Hashtable;

import lombok.Setter;

import org.apache.log4j.Logger;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import de.r2soft.empires.client.resources.Values;
import de.r2soft.empires.client.util.Server;
import de.r2soft.empires.framework.network.ConnectionPackage;
import de.r2soft.empires.framework.network.Host;
import de.r2soft.empires.framework.network.Host.IP;
import de.r2soft.empires.framework.network.NetworkHelper;

/**
 * Main connection handler to hold a Client-Server communication.
 * 
 * Pushes data onto the game screens to be dispayed and exchanges certificates with server.
 * 
 * @author Katharina Fey <kookie@spacekookie.de>
 */
public class ConnectionHandler {
  private static Logger logger = Logger.getLogger(ConnectionHandler.class.getSimpleName());
  private @Setter int timeoutTimeInMillis = 5000;
  private static ConnectionHandler self;
  private Client client;
  private Thread handlerThread;

  private static enum Response {
	CONNECTION_RESPONSE, AUTHENTICATION_RESPONSE, HANDSHAKE_RESPONSE;
  }

  private Hashtable<Response, Boolean> responses;

  public static ConnectionHandler getInstance() {
	if (self == null)
	  self = new ConnectionHandler();
	return self;
  }

  private ConnectionHandler() {
	System.out.println("Building ConnectionHandler");

	/** Sets up the responses coming back from the server */
	responses = new Hashtable<>();
	responses.put(Response.CONNECTION_RESPONSE, false);
	responses.put(Response.AUTHENTICATION_RESPONSE, false);
	responses.put(Response.HANDSHAKE_RESPONSE, false);

	/** Creates and starts the client */
	client = new Client();
	client.start();

	/** Sets up the client to listen */
	NetworkHelper.register(client);
	this.setupListener();
  }

  private void setupListener() {
	client.addListener(new Listener() {
	  public void received(Connection connection, Object obj) {
		if (obj instanceof ConnectionPackage) {
		  System.out.println("Got response back from server!");

		  // Checks that the server added information to the package and it is thus valid!
		  if (((ConnectionPackage) obj).getServerNameResponse() != null
			  && ((ConnectionPackage) obj).getServerKeyResponse() != 0) {
			System.out.println("It's a valid response...");
			responses.put(Response.CONNECTION_RESPONSE, true);
			handlerThread.interrupt();
		  }
		}
	  }
	});
  }

  public boolean isConnected() {
	return client.isConnected();
  }

  public boolean handshake() {
	if (client.isConnected()) {

	}
	return false;
  }

  public boolean connectInit(Server server) {
	handlerThread = Thread.currentThread();
	try {
	  System.out.println("Attempting to connect to: " + server.getUrl() + " " + server.getPortTCP() + "/"
		  + server.getPortUDP());
	  client.connect(timeoutTimeInMillis, server.getUrl(), server.getPortTCP(), server.getPortUDP());
	  return true;
	}
	catch (IOException e) {
	  logger.error("Server " + server.toCompactString() + " is currently not available!");
	  return false;
	}
  }

  public boolean connectHandshake() {
	InetAddress address = null;
	try {
	  address = InetAddress.getByName("localhost");
	}
	catch (UnknownHostException e) {
	  e.printStackTrace();
	}
	System.out.println(address.getHostName() + "=" + address.getHostAddress());

	ConnectionPackage cp = new ConnectionPackage(Values.PLAYER.getName(), new Host(null, new IP(1, 2, 3, 4)));
	client.sendTCP(cp);

	try {
	  Thread.sleep(timeoutTimeInMillis);
	}
	catch (InterruptedException e) {}
	finally {}

	if (responses.get(Response.CONNECTION_RESPONSE)) {
	  System.out.println("The response was positive!");
	  return true;
	}
	else
	  return false;
  }

  public boolean connectFinish() {
	return false;
  }

  public void stopConnection() {
	client.close();
  }

  public void logout() {
	client.sendTCP(null); // Send signout package.
	client.close();
  }
}
