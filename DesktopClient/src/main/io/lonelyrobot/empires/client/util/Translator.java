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
package io.lonelyrobot.empires.client.util;

import io.lonelyrobot.empires.framework.players.Player;
import io.lonelyrobot.empires.framework.types.Allegience.Allegiance;

/**
 * Utility methods to translate stuff into other stuff.
 * 
 * @author ***REMOVED***
 */
@Deprecated
public class Translator {

	/**
	 * This will be called each time a GUI element needs to be colour coded. TODO: Implement
	 * alliances.
	 * 
	 * @param p
	 *          the player owning the object, system, etc
	 * @param q
	 *          the player logged into the server
	 * @return The relative Allegiance between object and current player
	 * 
	 */
	public static Allegiance friendOrFoe(Player p, Player q) {

		if (p != null) {
			if (p.getAlliance() != null) {
				if (p.getAlliance().equals(q.getAlliance())) {
					return Allegiance.FRIEND;
				}
			}
			if (p.equals(q)) {
				return Allegiance.SELF;
			}
			else {
				return Allegiance.HOSTILE;
			}

		}
		return Allegiance.NEUTRAL;

	}
}
