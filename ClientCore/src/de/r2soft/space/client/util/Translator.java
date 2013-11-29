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
package de.r2soft.space.client.util;

import de.r2soft.space.framework.players.Player;
import de.r2soft.space.framework.types.Allegience.ALLEGIANCE;

/**
 * Utility methods to translate stuff into other stuff.
 * 
 * @author Katharina
 */
public class Translator {

	/**
	 * This will be called each time a GUI element needs to be colour coded.
	 * TODO: Implement alliances.
	 * 
	 * @param p
	 *            the player owning the object, system, etc
	 * @param q
	 *            the player logged into the server
	 * @return The relative Allegiance between object and current player
	 * 
	 */
	public static ALLEGIANCE friendOrFoe(Player p, Player q) {

		if (p != null) {
			if (p.getAlliance() != null) {
				if (p.getAlliance().equals(q.getAlliance())) {
					return ALLEGIANCE.FRIEND;
				}
			}
			if (p.equals(q)) {
				return ALLEGIANCE.SELF;
			} else {
				return ALLEGIANCE.FOE;
			}

		}
		return ALLEGIANCE.NEUTRAL;

	}
}
