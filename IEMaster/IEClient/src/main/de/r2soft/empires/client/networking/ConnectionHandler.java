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

import org.apache.log4j.Logger;

import com.esotericsoftware.kryonet.Client;

import de.r2soft.empires.client.util.Server;

/**
 * Main connection handler to hold a Client-Server communication.
 * 
 * Pushes data onto the game screens to be dispayed and exchanges certificates with server.
 * 
 * @author Katharina Fey <kookie@spacekookie.de>
 */
public class ConnectionHandler {
  private static Logger logger = Logger.getLogger(ConnectionHandler.class.getSimpleName());
  private static ConnectionHandler self;
  private Client client;

  public static ConnectionHandler getInstance() {
	if (self == null)
	  self = new ConnectionHandler();
	return self;
  }

  private ConnectionHandler() {
	client = new Client();
	client.start();
  }

  public boolean isConnected() {
	return client.isConnected();
  }

  public boolean handshake() {
	if (client.isConnected()) {

	}
	return false;
  }

  public void connect(Server server) {
	try {
	  System.out.println("Attempting to connect to: " + server.getUrl() + " " + server.getPortTCP() + "/"
		  + server.getPortUDP());
	  client.connect(5000, server.getUrl(), server.getPortTCP(), server.getPortUDP());
	}
	catch (IOException e) {
	  logger.error("Server " + server.toCompactString() + " is currently not available!");
	  return;
	}
  }

  public void stopConnection() {
	client.close();
  }

  public void logout() {
	client.sendTCP(null); // Send signout package.
	client.close();
  }
}
