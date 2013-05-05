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
import de.r2soft.space.framework.objects.Star.STARTYPE;
import de.r2soft.space.framework.objects.Structure;
import de.r2soft.space.framework.objects.Unit;
import de.r2soft.space.framework.players.Player;

/**
 * Object holding solar-system information.
 * 
 * TODO: Either let @SolarSystem extend @GameObject OR keep the @radius variable inside.
 * 
 * @author Katharina
 * 
 */
@SuppressWarnings("unused")
public class SolarSystem {

	private IntVec2 id;
	private Player claimed;
	private Set<Planet> planets;
	private Set<Unit> units;
	private Set<Structure> structures;
	private Star star;
	private float radius;

	/** @return: Systems (x,y) id on haxmap */
	public IntVec2 getId() {
		return id;
	}

	/**
	 * NEVER USE THIS ON CLIENT SIDE. NEVER NEVER NEVER! (triple-negative = negative)
	 * 
	 * @param id
	 *          system id on map.
	 */
	public void setId(IntVec2 id) {
		this.id = id;
	}

	{
		units = new HashSet<Unit>();
		planets = new HashSet<Planet>();
		structures = new HashSet<Structure>();
	}

	/** Empty constructor */
	public SolarSystem() {
	}

	/**
	 * Master constructor for a solar system. Used by server.
	 * 
	 * @param id
	 *          the 2d id of the solar system on the map. Center is at (0,0)
	 * @param claimed
	 *          the player having claim to the solar system if exists. Else @null
	 * @param planets
	 *          The set of planets in that solar system
	 * @param units
	 *          the set of units in that solar system
	 * @param structures
	 *          the set of structures in that solar system
	 * @param star
	 *          the solar systems star
	 * @param radius
	 *          the radius of the solar system
	 */
	public SolarSystem(IntVec2 id, Player claimed, Set<Planet> planets, Set<Unit> units,
			Set<Structure> structures, Star star) {
		this.id = id;
		this.claimed = claimed;
		this.planets = planets;
		this.units = units;
		this.structures = structures;
		this.star = star;
		this.radius = createRadius(star.getType());
	}

	private float createRadius(STARTYPE type) {

		switch (type) {
		case BROWNDWARF:
			return 140f;

		case REDDWARF:
			return 230f;

		case WHITEDWARF:
			return 100f;

		case REDGIANT:
			return 280f;

		case BLUEGIANT:
			return 280f;

		case NEUTRON:
			return 100f;

		case BLACKHOLE:
			return 250f;

		case GIANTSPACEPUDDING:
			return 300f;

			/** If there was a horrible error */
		default:
			return 0;
		}
	}

	/** @return: the systems radius for rendering and calculations. */
	public float getRadius() {
		return radius;
	}

	/**
	 * Sets the radius.
	 * 
	 * @param systemSizeBlueGiant
	 *          the systems radius for rendering and calculations.
	 */
	public void setRadius(float systemSizeBlueGiant) {
		this.radius = systemSizeBlueGiant;
	}

	/** @return: the systems star details. */
	public Star getStar() {
		return star;
	}

	/**
	 * @param s
	 *          the systems star.
	 */
	public void setStar(Star s) {
		this.star = s;
	}

	/** @return: the systems owner if exists. */
	public Player getSovereignty() {
		// TODO: Impliment neutral as a player and return here.
		return claimed != null ? claimed : null;
	}

	/**
	 * Sets the owning player of a system.
	 * 
	 * @param p
	 *          the owning player. @Null if system is neutral.
	 */
	public void setSovereignty(Player p) {
		this.claimed = p;
	}

	/**
	 * To add an entire set of units into the solar system
	 * 
	 * @param units
	 *          set of units
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
