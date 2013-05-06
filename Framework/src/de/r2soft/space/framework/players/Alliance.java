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

package de.r2soft.space.framework.players;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * An alliance is a group of players that fight together.
 * 
 * @author Leander
 * 
 */
public class Alliance {

	public enum ALLEGIANCE {
		PLAYER, HOSTILE, NEUTRAL, FRIENDLY, UNKNOWN;
	}

	private Set<Player> members;

	private String name;

	private Map<Role, Set<Player>> permissions;

	private String tag;

	private ALLEGIANCE allegiance; // Only used by game client.
	
	public Alliance() {
	  
	}

	/**
	 * Create a new alliance.
	 * 
	 * @param name
	 * @param tag
	 */
	public Alliance(String name, String tag) {
		this.name = name;
		this.tag = tag;
	}

	/**
	 * Debugging contructor
	 * 
	 * @param a
	 *         object allegiance relative to the player.
	 */
	public Alliance(ALLEGIANCE a) {
		this.allegiance = a;
	}

	/**
	 * Debug method.
	 * 
	 * @return object allegiance, relative to the player.
	 */
	public ALLEGIANCE getAllegiance() {
		return allegiance;
	}

	/**
	 * Add a new player to the alliance.
	 * 
	 * @param player
	 */
	public void addPlayer(Player player) {
		members.add(player);
	}

	/**
	 * Add a player to an existing rule. Make sure the rule exits
	 * 
	 * @param player
	 * @param role
	 */
	public void addPlayerToRole(Player player, Role role) {
		if (permissions.containsKey(role))
			{
				permissions.get(role).add(player);
			} else
			{
				// Log the failed attempt to add a player to a role that did not exist.
			}
	}

	/**
	 * Add a new possible role to the alliance
	 * 
	 * @param role
	 */
	public void addRole(Role role) {
		if (!permissions.containsKey(role))
			{
				permissions.put(role, new HashSet<Player>());
			} else
			{
				// Log the failed attempt to add an existing rule.
			}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param name
	 *         the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param tag
	 *         the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

}
