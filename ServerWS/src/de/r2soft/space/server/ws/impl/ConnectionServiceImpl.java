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

package de.r2soft.space.server.ws.impl;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import de.r2soft.space.framework.players.Player;
import de.r2soft.space.server.ws.interfaces.ConnectionService;
import de.r2soft.space.server.ws.interfaces.SessionManagerLocal;

@WebService(serviceName = "ConnectionService", endpointInterface = "de.r2soft.space.server.ws.interfaces.ConnectionService", targetNamespace = "http://2rSoftworks.de/")
public class ConnectionServiceImpl implements ConnectionService {

	@EJB
	SessionManagerLocal sessionManager;

	/**
	 * Connect a new user session to the server.
	 * 
	 * @param username
	 * @param password
	 */
	@Override
	@WebMethod
	public Integer connect(String username, String password) {
		Player player = new Player(username);
		// TODO: Get user information for user name

		// TODO: Validate password

		Integer sessionID = sessionManager.registerSession(player);
		return sessionID;
	}

	/**
	 * Disconnect an existing session from the server.
	 */
	@Override
	@WebMethod
	public boolean disconnect(Integer sessionID) {
		return sessionManager.unregisterSession(sessionID);
	}
}
