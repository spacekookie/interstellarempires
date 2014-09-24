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

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import de.r2soft.empires.server.packets.Packet1Connect;
import de.r2soft.empires.server.packets.Packet2Line;

/**
 * @author ***REMOVED*** <***REMOVED***>
 */
public class Main {
	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.start();
		server.bind(54555, 54777);
		server.getKryo().register(Packet1Connect.class);
		server.getKryo().register(Packet2Line.class);
		server.getKryo().register(Package.class);

		server.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				if (object instanceof Packet1Connect)
					System.out.println("Now connecting: " + ((Packet1Connect) object).name);
				if (object instanceof Packet2Line) {
					Packet2Line p2 = (Packet2Line) object;
					System.out.println("The client says: " + p2.line);
					p2.line = "You said: " + p2.line;

					connection.sendTCP(p2);

				}
			}
		});
	}
}
