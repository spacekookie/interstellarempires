/* 
 * Copyright (c) 2013 Leander Sabel
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package server.ws.core;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.log4j.Logger;


import framework.util.UtilFactory;

import server.core.Server;

@Startup
@Singleton
public class ServerWS {

  /** Private fields **/
  private final Logger log = UtilFactory.createLogger(this);
  private Server server;

  /**
   * Create a new ServerWS. This method is called by JBoss once the server is started and the module
   * deployed due to the @Startup annotation.
   */
  private ServerWS() {
	log.info("Creating main server web service");
	initializeServerWS();
  }

  /**
   * Initialize the ServerWS
   */
  private void initializeServerWS() {
	server = new Server();
  }

  @PostConstruct
  public void start() {
	log.info("Starting main server web service");	
  }

  public void test() {
	log.debug("Test function");
  }

}
