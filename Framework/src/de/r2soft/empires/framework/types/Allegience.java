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

import de.r2soft.empires.framework.players.Player;

/**
 * Holds information on rendering and combat. ENUM values can hold @SELF,
 * 
 * @FRIEND, @FOE and @NEUTRAL
 */
public class Allegience {
  public static enum Allegiance {
	SELF, FRIEND, FOE, NEUTRAL, UNKNOWN;
  }

  /** TODO: Add the checking for war declarations and FOES on the map */
  public static Allegiance validate(Player a, Player b) {
	if (a.equals(b))
	  return Allegiance.SELF;
	else if (a.getAlliance().equals(b.getAlliance()))
	  return Allegiance.FRIEND;
	else
	  return Allegiance.NEUTRAL;
  }

}
