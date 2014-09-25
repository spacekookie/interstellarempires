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

package de.r2soft.empires.framework.players;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.analysis.function.Log;
import org.apache.log4j.Logger;

import de.r2soft.empires.framework.types.Allegience.Allegiance;

/**
 * 
 * Manages standings between sociable items (Players, Alliances, Cats, ?)
 * 
 * @author ***REMOVED***
 * */
public abstract class Sociable {
	private Logger logger = Logger.getLogger(getClass().getSimpleName());

	private Map<Sociable, Allegiance> standings;

	public Sociable() {
		standings = new HashMap<Sociable, Allegiance>();
	}

	/** Returns a map of all standings of this alliance */
	public Map<Sociable, Allegiance> getStandings() {
		return standings;
	}

	/** Removes a specific standing from the Map */
	public void removeStanding(Sociable subject) {
		if (standings.containsKey(subject))
			standings.remove(subject);
		else
			logger.info("Standing didn't exist.");
	}

	/** Add a specific standing to the Map */
	public void addStanding(Sociable subject, Allegiance standing) {
		standings.put(subject, standing);
	}

	/** Overrides all existing standings with new Map */
	public void setAllStandings(Map<Sociable, Allegiance> standings) {
		this.standings = standings;
	}
}
