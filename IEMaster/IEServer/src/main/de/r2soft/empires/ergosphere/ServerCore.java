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

package de.r2soft.empires.ergosphere;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import de.r2soft.empires.framework.network.ConnectionPackage;
import de.r2soft.empires.framework.network.Host;
import de.r2soft.empires.framework.network.NetworkHelper;
import de.r2soft.empires.framework.players.Player;

/**
 * @author ***REMOVED*** <***REMOVED***>
 */
public class ServerCore {
  private Server server;

  public ServerCore() throws IOException {
	server = new Server() {
	  protected Connection newConnection() {
		return new ServerConnection();

	  }
	};
	server.start();
	server.bind(Network.PORT_TCP, Network.PORT_UDP);
	System.out.println("Server started successfully.");

	NetworkHelper.register(server);

	server.addListener(new Listener() {

	  public void received(Connection c, Object obj) {
		System.out.println("I just got something...");
		ServerConnection connection = (ServerConnection) c;

		System.out.println("Incoming package: " + obj.getClass().getSimpleName());

		if (obj instanceof ConnectionPackage) {
		  System.out.println("Somebody just connected to me. I don't know who yet!");

		  /**
		   * Extracts information from the Connection Package and checks if the player is registered on the server. If
		   * it isn't an empty package is being sent back
		   * 
		   * TODO: Check in server database if a player is registered with the server. So far every player with any
		   * password is accepted.
		   */
		  String newPlayer = ((ConnectionPackage) obj).getPlayer();
		  Host host = ((ConnectionPackage) obj).getHost();

		  connection.setHost(host);
		  connection.setPlayer(newPlayer);

		  // TODO: Come up with way to generate server key
		  ((ConnectionPackage) obj).setServerKeyResponse("Ergosphere".hashCode());
		  ((ConnectionPackage) obj).setServerNameResponse("Ergosphere");
		  c.sendTCP(obj); // Sends back the package with new data
		}

	  }

	  public void disconnected(Connection c) {
		ServerConnection connection = (ServerConnection) c;

	  }
	});
  }

  public static void main(String[] args) throws IOException {
	Log.set(Log.LEVEL_DEBUG);
	new ServerCore();
  }
}