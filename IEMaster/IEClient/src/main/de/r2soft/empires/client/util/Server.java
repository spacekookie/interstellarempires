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

package de.r2soft.empires.client.util;

import lombok.Data;

/**
 * @author spacekookie
 */
public @Data class Server {
  private String name, url;
  private int portTCP, portUDP;
  private boolean empty = false;

  public Server(String name, String url, int port, int port2) {
	this.name = name;
	this.url = url;
	this.portTCP = port;
	this.portUDP = port2;
  }

  public Server(boolean empty) {
	this.empty = empty;
  }

  public Server(String complete) {
	String[] tmp = complete.split("\\:");
	this.name = tmp[0];
	this.url = tmp[1];
	this.portTCP = Integer.parseInt(tmp[2]);
	this.portUDP = Integer.parseInt(tmp[3]);
  }

  public String toCompactString() {
	return name + ":" + url + ":" + portTCP + ":" + portUDP;
  }

  public String toString() {
	if (empty)
	  return "Server-List empty";
	return name + ":      " + url + ":" + portTCP + "/" + portUDP;
  }

}
