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

package de.r2soft.space.server.ws.core;

import java.util.HashMap;
import java.util.PriorityQueue;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import de.r2soft.space.framework.players.Player;
import de.r2soft.space.server.ws.interfaces.SessionManagerLocal;

/**
 * This class manages the sessions used for connecting users.
 * 
 * @author Leander
 * 
 */
@Startup
@Singleton
public class SessionManager implements SessionManagerLocal {

	private Integer nextFree;
	private PriorityQueue<Integer> freeIDs;
	private HashMap<Integer, Player> activeSessions;

	/**
	 * Create a new SessionManager. This is called by JBoss due to the @Startup annotation.
	 */
	public SessionManager() {
		nextFree = 1000;
		freeIDs = new PriorityQueue<Integer>();
		activeSessions = new HashMap<Integer, Player>();
	}

	/**
	 * Registers a new player connection and returns a session ID.
	 * 
	 * @param player
	 * @return
	 */
	@Lock(LockType.WRITE)
	public Integer registerSession(Player player) {
		// Get a new session ID from the pool or assign a new one.
		Integer newID = freeIDs.poll();
		if (newID == null)
			newID = nextFree++;

		// Validate the success
		assert newID == null : "Could not create a valid session ID for player " + player;

		activeSessions.put(newID, player);
		return newID;
	}

	/**
	 * Unregister an existing session ID and return the session ID to the pool of available IDs.
	 * 
	 * @param sessionID
	 * @return true if the session ID existed
	 * @return false if it did not exist
	 */
	@Lock(LockType.WRITE)
	public boolean unregisterSession(Integer sessionID) {
		if (activeSessions.containsKey(sessionID))
			{
				activeSessions.remove(sessionID);
				freeIDs.add(sessionID);
				return true;
			} else
			{
				return false;
			}
	}

}
