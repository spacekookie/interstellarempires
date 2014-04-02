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
package de.r2soft.empires.framework.map;

import java.util.HashSet;
import java.util.Set;

import de.r2soft.empires.framework.objects.OrbitalStructure;
import de.r2soft.empires.framework.objects.Planet;
import de.r2soft.empires.framework.objects.Ship;
import de.r2soft.empires.framework.objects.Star;
import de.r2soft.empires.framework.objects.Star.StarType;
import de.r2soft.empires.framework.players.Player;

/**
 * Object holding solar-system information.
 * 
 * TODO: Either let @SolarSystem extend @GameObject OR keep the @radius variable inside.
 * 
 * @author Katharina
 * 
 */
public class SolarSystem {

  private GalaxyPosition pos;
  private Player claim;
  private Set<Planet> planets;
  private Set<Ship> units;
  private Set<OrbitalStructure> structures;
  private Star star;
  private double radius;
  private boolean explored;

  /** @return: Systems (x,y) id on haxmap */
  public GalaxyPosition getPosition() {
	return pos;
  }

  /**
   * NEVER USE THIS ON CLIENT SIDE. NEVER NEVER NEVER! (triple-negative = negative)
   * 
   * @param id
   *          system id on map.
   */
  public void setPosition(GalaxyPosition pos) {
	this.pos = pos;
  }

  /** Empty constructor */
  public SolarSystem() {

	units = new HashSet<Ship>();
	planets = new HashSet<Planet>();
	structures = new HashSet<OrbitalStructure>();

  }

  /**
   * Master constructor for a solar system. Used by server.
   * 
   * @param id
   *          the 2d id of the solar system on the map. Center is at (0,0)
   * @param claim
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
  public SolarSystem(GalaxyPosition pos, Player claim, Set<Planet> planets, Set<Ship> units, Set<OrbitalStructure> structures, Star star) {
	this.pos = pos;
	this.claim = claim;
	this.planets = planets;
	this.units = units;
	this.structures = structures;
	this.star = star;
	if (star != null)
	  this.radius = createRadius(star.getClassification());
	else
	  System.out.println("FATAL ERROR CREATING SOLAR SYSTEM. STAR INFORMATION NEEDED!");
  }

  private float createRadius(StarType type) {

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
  public double getRadius() {
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
  public Player getClaim() {
	return claim != null ? claim : new Player("_neutral");
  }

  /**
   * Sets the owning player of a system.
   * 
   * @param p
   *          the owning player. @Null if system is neutral.
   */
  public void setClaim(Player p) {
	this.claim = p;
  }

  /**
   * To add an entire set of units into the solar system
   * 
   * @param units
   *          set of units
   */
  public void addUnits(Set<Ship> units) {
	this.units = units;
  }

  /** @return: get all units in this solar system */
  public Set<Ship> getUnits() {
	return units;
  }

  /** DEBUG ONLY */
  public void addSingleUnit(Ship unit) {
	units.add(unit);
  }

  public boolean hasUnits() {
	if (units != null)
	  return !units.isEmpty();
	else
	  return false;
  }

  public boolean hasStructures() {
	if (structures != null)
	  return !structures.isEmpty();
	else
	  return false;
  }

  public boolean hasPlanets() {
	if (units != null)
	  return !planets.isEmpty();
	else
	  return false;
  }

  public boolean isExplored() {
	return explored;
  }

  /** Set true if player has had ships in it */
  public void setExplored(boolean explored, Player player) {
	this.explored = explored;
  }

  public Set<Planet> getPlanets() {
	return planets;
  }

  public void setPlanets(Set<Planet> planets) {
	this.planets = planets;
  }

}
