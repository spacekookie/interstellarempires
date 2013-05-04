/* 

 * Copyright (c) 2012 Leander Sabel
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

/**
 * 
 */
package framework.objects;

import framework.players.Player;

/**
 * @author Leander
 * 
 */
public class Unit extends MovingObject {

	/** For testing only */
	public static enum TYPE {
		FLEET, SHIP, DEBRIS, PONY;
	}

	public static enum SIZE {
		TINY, SMALL, MEDIUM, LARGE, MASSIVE;
	}

	private TYPE type;
	private String flag;
	private Player claim;
	private int count;

	public Unit() {
		count = 1;
	}

	/**
	 * Determines what icon size will be used for rendering.
	 * 
	 * @return enum for fleet SIZE.
	 */
	public SIZE getFleetSize() {
		if (count < 10)
			return SIZE.TINY;
		if (count < 25)
			return SIZE.SMALL;
		if (count < 50)
			return SIZE.MEDIUM;
		if (count < 100)
			return SIZE.LARGE;
		if (count < 500)
			return SIZE.MASSIVE;
		else
			return null;
	}

	public void addShip() {
		count++;
	}

	public int getCount() {
		return count;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Player getClaim() {
		return claim;
	}

	public void setClaim(Player claim) {
		this.claim = claim;
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

}
