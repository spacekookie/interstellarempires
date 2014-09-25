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

package de.r2soft.empires.framework.types;

import de.r2soft.empires.framework.objects.BaseObject;
import de.r2soft.empires.framework.players.Player;
import de.r2soft.empires.framework.players.Sociable;

/**
 * Holds information on rendering and combat. ENUM values can hold @SELF,
 * 
 * @FRIEND, @FOE and @NEUTRAL
 */
public class Allegience {
	public static enum Allegiance {
		SELF, FRIEND, HOSTILE, NEUTRAL, INFESTED;
	}

	/**
	 * Validates the Allegiance between two players.
	 * 
	 * @param a
	 *          Player A
	 * @param b
	 *          Player B
	 * @return
	 */
	@Deprecated
	public static Allegiance validated(Player a, Player b) {
		if (a.equals(b))
			return Allegiance.SELF;
		else if (a.getAlliance().equals(b.getAlliance()))
			return Allegiance.FRIEND;
		else
			return Allegiance.NEUTRAL;
	}

	/**
	 * Validates the standing between two players
	 * 
	 * @param a
	 *          Player A
	 * @param b
	 *          Player B
	 * @return The Allegiance between two players.
	 */
	public static Allegiance validate(Player a, Player b) {
		if (a.getStandings().containsKey(b))
			if (a.getStandings().get(b).equals(Allegiance.FRIEND))
				return Allegiance.FRIEND;
			else if (a.getStandings().get(b).equals(Allegiance.HOSTILE))
				return Allegiance.HOSTILE;
		return Allegiance.NEUTRAL;
	}

	/**
	 * Validates the Allegiance of two {@link Sociable}. The Allegiance is of Sociable A towards B
	 * (The relations of A towards B, not vice versa). To validate the reverse relationship
	 * 
	 * @param a
	 *          Sociable A
	 * @param b
	 *          Sociable B
	 * @return
	 */
	public static Allegiance validate(Sociable a, Sociable b) {
		if (a.equals(b))
			return Allegiance.SELF;
		else if (a.getStandings().containsKey(b)) {
			if (a.getStandings().get(b).equals(Allegiance.FRIEND))
				return Allegiance.FRIEND;
			else if (a.getStandings().get(b).equals(Allegiance.HOSTILE))
				return Allegiance.HOSTILE;
		}
		else
			return Allegiance.NEUTRAL;
		return null;
	}

	/**
	 * Validates an object for Alien infestation.
	 * 
	 * @param object
	 *          An object to be validated for infection
	 * @return
	 */
	public static Allegiance validate(BaseObject object) {
		return object.isInfested() ? Allegiance.INFESTED : null;
	}
}
