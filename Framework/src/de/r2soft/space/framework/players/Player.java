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

import java.util.HashSet;
import java.util.Set;

import de.r2soft.space.framework.map.SolarSystem;
import de.r2soft.space.framework.objects.Planet;

/**
 * Player object. Will be created from server and called again on login. Name
 * will be returned on login in the console.
 * 
 * @author ***REMOVED***
 * 
 */
public class Player {

	private boolean admin;
	private String name;
	private Alliance alliance;
	private Set<Planet> colonies;
	private Set<SolarSystem> soveregenty;

	{
		this.colonies = new HashSet<Planet>();
		this.soveregenty = new HashSet<SolarSystem>();
	}

	public void setAlliance(Alliance alliance) {
		this.alliance = alliance;
	}

	/** TODO: Replace with .getIndipendance() method */
	public Alliance getAlliance() {
		return alliance != null ? alliance
				: new Alliance("Indipendant", "INDI");
	}

	/**
	 * 
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * Empty constructor required for JavaEE
	 */
	public Player() {
	}

	public void setPony(boolean admin) {
		this.admin = admin;
	}

	public boolean isPony() {
		return admin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addPlanet(Planet p) {
		colonies.add(p);
	}

	public boolean hasPlanets() {
		return !colonies.isEmpty();
	}

	public Planet getCapital() {
		for (Planet p : colonies) {
			if (p.isCapital())
				return p;
		}
		return null;
	}

	public void addSystem(SolarSystem s) {
		soveregenty.add(s);
	}

	public boolean hasSystems() {
		return !soveregenty.isEmpty();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		else if (obj instanceof Player) {
			Player other = (Player) obj;

			if (other.getName().equals(this.getName())) {
				return true;
			}

		} else
			return false;
		return false;
	}
}
