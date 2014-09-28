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

package de.r2soft.empires.server.dummy;

import java.io.IOException;
import java.util.HashMap;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import de.r2soft.empires.framework.network.Packet;
import de.r2soft.empires.framework.network.Packet1Connect;
import de.r2soft.empires.framework.network.Packet2ClientConnected;
import de.r2soft.empires.framework.network.Packet3ClientDisconnect;
import de.r2soft.empires.framework.network.Packet4Chat;

/**
 * @author ***REMOVED*** <***REMOVED***>
 */
public class ServerMain {

	private static HashMap<String, Connection> clients = new HashMap<String, Connection>();

	public static void main(String[] args) throws IOException {
		final Server server = new Server();
		server.start();
		server.bind(23900, 23901);

		server.getKryo().register(Packet.class);
		server.getKryo().register(Packet1Connect.class);
		server.getKryo().register(Packet2ClientConnected.class);
		server.getKryo().register(Packet3ClientDisconnect.class);
		server.getKryo().register(Packet4Chat.class);

		server.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				if (object instanceof Packet1Connect) {
					Packet1Connect p1 = (Packet1Connect) object;
					clients.put(p1.userame, connection);
					Packet2ClientConnected p2 = new Packet2ClientConnected();
					p2.clientName = p1.userame;
					server.sendToAllExceptTCP(connection.getID(), p2);
				}
				else if (object instanceof Packet3ClientDisconnect) {
					Packet3ClientDisconnect p3 = (Packet3ClientDisconnect) object;
					System.out.println("Client " + p3.clientName + " disconnected");
					if (clients.containsKey(p3.clientName))
						clients.remove(p3.clientName);
					server.sendToAllExceptTCP(connection.getID(), p3); // clients.get(p3.clientName).getID()
				}
				else if (object instanceof Packet4Chat) {
					Packet4Chat p4 = (Packet4Chat) object;
					System.out.println("From client <" + p4.username + "> " + p4.message);
					server.sendToAllTCP(p4);
				}
			}
		});
	}
}
