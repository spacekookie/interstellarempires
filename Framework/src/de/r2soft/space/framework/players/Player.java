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

package de.r2soft.space.framework.players;

/**
 * Player object. Will be created from server and called again on login. Name will be returned on
 * login in the console.
 * 
 * @author Katharina
 * 
 */
public class Player {

	private boolean admin;
	private String name;

	public Player(String name) {
		this.name = name;
	}

	public void setPony(boolean admin) {
		this.admin = admin;
	}

	public boolean isAdmin() {
		return admin;
	}
}
