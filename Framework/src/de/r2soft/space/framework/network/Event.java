/* #########################################################################
 * Copyright (c) 2013 Random Robot Softworks
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
 * 
 ######################################################################### */

package de.r2soft.space.framework.network;

import java.io.Serializable;

import de.r2soft.space.framework.util.TimeUtil;

/**
 * This class stores event information for the event log.
 * 
 * @author Leander
 * 
 */
public class Event implements Serializable {

	/**
	 * enum "Types" immer danach benennen was sie beschreiben. Wir haben schon über 20 "Types" im
	 * Framework. Das wird sonst nur Verwirrend
	 * 
	 * @author Katharina
	 */
	public static enum EventType {
		/**
		 * UNIT_MOVE stores information about the movement of a unit.
		 */
		UNIT_MOVE,
		/**
		 * UNIT_CREATE stores information about the creation of a new unit.
		 */
		UNIT_CREATE,
		/**
		 * UNIT_DESTROY stores information about the destruction of a new unit.
		 */
		UNIT_DESTROY,
		/**
		 * PLAYER_NEW stores information about a new player that joined the game.
		 */
		PLAYER_NEW
	}

	// Private fields
	private final long id = Event.getNextID();
	private final long timestamp = TimeUtil.getTimeNow().getTime();

	private EventType type;

	public Event(EventType type) {
		this.type = type;
	}

	/***
	 * Get the next ID for an event.
	 * 
	 * @return
	 */
	synchronized private static long getNextID() {
		return Event.nextID++;
	}

	// Do not directly access this field
	private static long nextID = 0;
	private static final long serialVersionUID = -7633799844798975118L;

}
