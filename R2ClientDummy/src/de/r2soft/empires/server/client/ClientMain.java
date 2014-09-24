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

package de.r2soft.empires.server.client;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import de.r2soft.empires.server.packets.Packet1Connect;
import de.r2soft.empires.server.packets.Packet2Line;

/**
 * @author ***REMOVED*** <***REMOVED***>
 */
public class ClientMain {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("=== HELLO ===");

		Client client = new Client();
		client.start();
		try {
			client.connect(5000, "127.0.0.1", 54555, 54777);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Server is not reachable. Can not connect!");
			return;
		}

		client.getKryo().register(Packet1Connect.class);
		client.getKryo().register(Packet2Line.class);

		Packet1Connect p1 = new Packet1Connect();
		p1.name = "SpaceKookie";
		client.sendTCP(p1);

		client.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				if (object instanceof Packet2Line)
					System.out.println("The server says: " + ((Packet2Line) object).line);
			}
		});

		String line = scanner.nextLine();
		Packet2Line pack = new Packet2Line();
		pack.line = line;
		client.sendTCP(pack);

	}
}
