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

package de.r2soft.space.framework.map;

import java.util.HashSet;
import java.util.Set;

import de.r2soft.space.framework.objects.Planet;
import de.r2soft.space.framework.objects.Star;
import de.r2soft.space.framework.objects.Structure;
import de.r2soft.space.framework.objects.Unit;
import de.r2soft.space.framework.players.Player;


/**
 * Object holding solar-system information.
 * 
 * TODO: Either let @SolarSystem extend @GameObject OR keep the @radius variable inside.
 * 
 * @author ***REMOVED***
 * 
 */
public class SolarSystem {

	private Player claimed;
	private Set<Planet> planets;
	private Set<Unit> units;
	private Set<Structure> structures;
	private Star star;
	private float radius;

	/** Initialises the Sets */
	public SolarSystem() {
		units = new HashSet<Unit>();
		planets = new HashSet<Planet>();
		structures = new HashSet<Structure>();
	}

	/** @return: the systems radius for rendering and calculations. */
	public float getRadius() {
		return radius;
	}

	/**
	 * Sets the radius.
	 * 
	 * @param systemSizeBlueGiant
	 *         the systems radius for rendering and calculations.
	 */
	public void setRadius(float systemSizeBlueGiant) {
		this.radius = systemSizeBlueGiant;
	}

	/** @return: the systems star details. */
	public Star getStar() {
		return star;
	}

	/**
	 * Sets the star.
	 * 
	 * @param s
	 *         the systems star details.
	 */
	public void setStar(Star s) {
		this.star = s;
	}

	/** @return: the systems owner if exists. */
	public Player getSovereignty() {
		// TODO: Kann man das in eine Zeile abk??rzen? Mir irgendwelchem fancy Syntax? :P
		if (claimed != null)
			return claimed;
		else
			return null;
	}

	/**
	 * Sets the owning player of a system.
	 * 
	 * @param p
	 *         the owning player. @Null if system is neutral.
	 */
	public void setSovereignty(Player p) {
		this.claimed = p;
	}

	/**
	 * To add an entire set of units into the solar system
	 * 
	 * @param units
	 *         set of units
	 */
	public void addUnits(Set<Unit> units) {
		this.units = units;
	}

	/** @return: get all units in this solar system */
	public Set<Unit> getUnits() {
		return units;
	}

	/** DEBUG ONLY */
	public void addSingleUnit(Unit unit) {
		units.add(unit);
	}
}
