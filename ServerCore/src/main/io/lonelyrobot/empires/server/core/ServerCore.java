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

package io.lonelyrobot.empires.server.core;

/**
 * Main server launcher and thread handler for the Interstellar Empires server.
 * 
 * @author ***REMOVED*** <***REMOVED***>
 */
public class ServerCore {
  // private static ServerCore instance;
  // private Scanner input;
  //
  // public KryoThread server;
  // public Thread physics, db, combat;
  // private Timer scheduler;
  //
  // public static ServerCore getInstance() {
  // if (instance == null)
  // instance = new ServerCore();
  // return instance;
  // }
  //
  // private ServerCore() {
  // input = new Scanner(System.in);
  //
  // server = new KryoThread();
  // server.start();
  //
  // physics = new PhysicsThread();
  // physics.start();
  //
  // // TODO: Implement Database thread
  // // db = new DataBaseThread();
  // // db.start();
  //
  // // TODO: Implement combat simulation thread
  // // combat = new CombatSimThread();
  // // combat.start();
  //
  // /** Opens input pipeline */
  // System.out.println("[SELF]: Type 'help' to get a list of server commands.");
  // new Thread() {
  // @Override
  // public void run() {
  // while (true) {
  // String line = input.nextLine();
  //
  // switch (line) {
  // case "exit":
  // System.out.println();
  //
  // break;
  //
  // case "help":
  // System.out.println("These are valid server commands:");
  // System.out.println("help\t\tPrints this help dialogue");
  // System.out.println("exit\t\tAttemts to stop the server. Run with '-f' to force terminate");
  // break;
  //
  // case "status":
  // break;
  //
  // default:
  // System.out.println("Unrecognized command.");
  // ;
  // }
  // }
  // }
  // }.start();
  //
  // scheduler = new Timer();
  // scheduler.scheduleAtFixedRate(ServerTask.getInstance(), 0L, 1000L);
  //
  // server.self.addListener(new Listener() {
  // public void received(Connection c, Object obj) {
  //
  // }
  // });
  //
  // }
  //
  // static class ServerTask extends TimerTask {
  // private static ServerTask instance;
  // private long cycle = 0;
  //
  // public static ServerTask getInstance() {
  // if (instance == null)
  // instance = new ServerTask();
  // return instance;
  // }
  //
  // private ServerTask() {
  //
  // }
  //
  // @Override
  // public void run() {
  // // System.out.println("[MASTER]: Beginning update cycle #" + cycle);
  // ServerCore.instance.physics.run();
  // try {
  // Thread.sleep(750);
  // }
  // catch (InterruptedException e) {
  // System.out.println("[MASTER]: FATAL TASK SCHEDULING FAILURE!\n");
  // System.out.println(e.toString());
  // return;
  // }
  // finally {
  // PhysicsThread.yield();
  //
  // cycle++;
  // }
  // }
  // }
  //
  // class KryoThread extends Thread {
  // private final Server self;
  //
  // // PhysicsThread physics;

  // public KryoThread() {
  // System.out.println("=== Welcome to the " + Constants.NAME + " version " + Constants.VERSION + " ===");
  //
  // // self = new Server() {
  // // protected Connection newConnection() {
  // // return new R2ServerConnection(null, null, System.currentTimeMillis());
  // // }
  // // };
  //
  // // self = new Server();
  // // this.physics = (PhysicsThread) physics;
  //
  // self.addListener(new Listener() {
  //
  // public void received(Connection c, Object obj) {
  // // R2ServerConnection con = (R2ServerConnection) c;
  //
  // System.out.println(obj);
  //
  // // ServerConnection connection = (ServerConnection) c;
  // //
  // // if (obj instanceof RegisterName) {
  // //
  // // if (connection.name != null)
  // // return;
  // //
  // // String name = ((RegisterName) obj).name;
  // // if (name == null)
  // // return;
  // // name = name.trim();
  // //
  // // if (name.length() == 0)
  // // return;
  // //
  // // connection.name = name;
  // //
  // // ServerMessage message = new ServerMessage();
  // // message.text = name + " connected.";
  // // server.sendToAllExceptTCP(connection.getID(), message);
  // // updateNames();
  // // return;
  // // }
  // }
  //
  // public void disconnected(Connection c) {
  // // ServerConnection connection = (ServerConnection) c;
  // // if (connection.name != null) {
  // // // Announce to everyone that someone (with a registered name) has left.
  // // ServerMessage message = new ServerMessage();
  // // message.text = connection.name + " disconnected.";
  // // server.sendToAllTCP(message);
  // // updateNames();
  // // }
  // }
  //
  // });
  // }
  //
  // @Override
  // public void run() {
  // try {
  // System.out.print("[KRYONET]: Binding ports TCP:" + Constants.PORT_TCP + " and UDP:" + Constants.PORT_UDP
  // + "...");
  // self.bind(Constants.PORT_TCP, Constants.PORT_UDP);
  // System.out.println("[DONE]");
  // }
  // catch (IOException e) {
  // System.out.println("[FAILED] ==> TERMINATING SERVER");
  // self.stop();
  // // this.interrupt();
  // e.printStackTrace();
  // }
  // System.out.println("[KRYONET]: Starting Server.");
  // self.start(); // Doesn't return!
  // System.out.println("[KRYONET]: Server was terinated. Is this right?");
  // }
  //
  // @Override
  // public void start() {
  // super.start();
  // }
  //
  // /** Pushes changes to all active connections */
  // public void push(HashSet<Object> objects) {
  //
  // }
  //
  // @Override
  // public void interrupt() {
  // self.sendToAllTCP(null); // Send force disconnect and sign-out packages
  // self.close(); // Closes all connections and server ports
  // self.stop(); // Stops the server instance internal threads
  // super.interrupt(); // Kills server wrapping thread
  // }
  // }
  //
  // public static void main(String[] args) {
  // ServerCore.getInstance();
  // }
}
