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

package de.r2soft.empires.server.core;

import java.io.IOException;
import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import de.r2soft.empires.server.core.Network.RegisterName;
import de.r2soft.empires.server.core.Network.ServerMessage;
import de.r2soft.empires.server.core.Network.UpdateNames;

/**
 * @author Katharina Fey <kookie@spacekookie.de>
 */
public class ServerMain {
  private Server server;

  public ServerMain() throws IOException {
	server = new Server() {
	  protected Connection newConnection() {
		return new ServerConnection();

	  }
	};
	server.start();
	server.bind(Network.PORT_TCP, Network.PORT_UDP);
	System.out.println("Server started successfully.");

	Network.register(server);

	server.addListener(new Listener() {

	  public void received(Connection c, Object obj) {
		ServerConnection connection = (ServerConnection) c;

		if (obj instanceof RegisterName) {

		  if (connection.name != null)
			return;

		  String name = ((RegisterName) obj).name;
		  if (name == null)
			return;
		  name = name.trim();

		  if (name.length() == 0)
			return;

		  connection.name = name;

		  ServerMessage message = new ServerMessage();
		  message.text = name + " connected.";
		  server.sendToAllExceptTCP(connection.getID(), message);
		  updateNames();
		  return;
		}
	  }

	  public void disconnected(Connection c) {
		ServerConnection connection = (ServerConnection) c;
		if (connection.name != null) {
		  // Announce to everyone that someone (with a registered name) has left.
		  ServerMessage message = new ServerMessage();
		  message.text = connection.name + " disconnected.";
		  server.sendToAllTCP(message);
		  updateNames();
		}
	  }
	});
  }

  private void updateNames() {
	Connection[] connections = server.getConnections();
	ArrayList<String> names = new ArrayList<String>(connections.length);

	for (int i = connections.length - 1; i >= 0; i--) {
	  ServerConnection connection = (ServerConnection) connections[i];
	  names.add(connection.name);
	}

	UpdateNames updatedNames = new UpdateNames();
	updatedNames.names = (String[]) names.toArray(new String[names.size()]);
	server.sendToAllTCP(updatedNames);

  }

  static class ServerConnection extends Connection {
	public String name;
  }

  public static void main(String[] args) throws IOException {
	Log.set(Log.LEVEL_DEBUG);
	new ServerMain();
  }
}