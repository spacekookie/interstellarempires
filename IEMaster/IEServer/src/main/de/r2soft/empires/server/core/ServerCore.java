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
import java.util.HashSet;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import de.r2soft.empires.server.utils.Constants;

/**
 * Main server launcher and thread handler for the Interstellar Empires server.
 * 
 * @author Katharina Fey <kookie@spacekookie.de>
 */
public class ServerCore {
  private static ServerCore instance;
  private Scanner input;

  public KryoThread server;
  public Thread physics, db, combat;
  private Timer scheduler;

  public static ServerCore getInstance() {
	if (instance == null)
	  instance = new ServerCore();
	return instance;
  }

  private ServerCore() {
	input = new Scanner(System.in);

	server = new KryoThread();
	server.start();

	physics = new PhysicsThread();
	physics.start();

	// TODO: Implement Database thread
	// db = new DataBaseThread();
	// db.start();

	// TODO: Implement combat simulation thread
	// combat = new CombatSimThread();
	// combat.start();

	/** Opens input pipeline */
	new Thread() {
	  @Override
	  public void run() {
		while (true) {
		  String line = input.next();
		  if (line.equalsIgnoreCase("exit")) {
			// Do exit-y things here
		  }
		}
	  }
	}.start();

	scheduler = new Timer();
	scheduler.scheduleAtFixedRate(ServerTask.getInstance(), 0L, 1000L);
  }

  static class ServerTask extends TimerTask {
	private static ServerTask instance;
	private long cycle = 0;

	public static ServerTask getInstance() {
	  if (instance == null)
		instance = new ServerTask();
	  return instance;
	}

	private ServerTask() {

	}

	@Override
	public void run() {
	  System.out.println("[MASTER]: Beginning update cycle #" + cycle);
	  ServerCore.instance.physics.run();
	  try {
		Thread.sleep(750);
	  }
	  catch (InterruptedException e) {
		System.out.println("[MASTER]: FATAL TASK SCHEDULING FAILURE!\n");
		System.out.println(e.toString());
		return;
	  }
	  finally {
		PhysicsThread.yield();

		cycle++;
	  }
	}
  }

  class KryoThread extends Thread {
	private final Server self;
	PhysicsThread physics;

	public KryoThread() {
	  System.out.println("=== Welcome to the " + Constants.NAME + " version " + Constants.VERSION + " ===");
	  self = new Server();
	  this.physics = (PhysicsThread) physics;

	  self.addListener(new Listener() {
		// Connection listener for the server
	  });
	}

	@Override
	public void run() {
	  try {
		System.out.print("[KRYONET]: Binding ports TCP:" + Constants.PORT_TCP + " and UDP:" + Constants.PORT_UDP
			+ "...");
		self.bind(Constants.PORT_TCP, Constants.PORT_UDP);
		System.out.println("[DONE]");
	  }
	  catch (IOException e) {
		System.out.println("[FAILED] ==> TERMINATING SERVER");
		self.stop();
		// this.interrupt();
		e.printStackTrace();
	  }
	  System.out.println("[KRYONET]: Starting Server.");
	  self.run(); // Doesn't return!
	  System.out.println("[KRYONET]: Server was terinated. Is this right?");
	}

	@Override
	public void start() {
	  super.start();
	}

	/** Pushes changes to all active connections */
	public void push(HashSet<Object> objects) {

	}

	@Override
	public void interrupt() {
	  self.sendToAllTCP(null); // Send force disconnect and sign-out packages
	  self.close(); // Closes all connections and server ports
	  self.stop(); // Stops the server instance internal threads
	  super.interrupt(); // Kills server wrapping thread
	}
  }

  public static void main(String[] args) {
	ServerCore.getInstance();
  }
}
