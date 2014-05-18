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

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.log4j.Logger;

import de.r2soft.empires.framework.objects.BaseObject;
import de.r2soft.empires.framework.objects.Fleet;
import de.r2soft.empires.framework.objects.Moon;
import de.r2soft.empires.framework.objects.OrbitalStructure;
import de.r2soft.empires.framework.objects.Planet;
import de.r2soft.empires.framework.objects.Ship;
import de.r2soft.empires.framework.objects.Star;
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
  private Logger logger = Logger.getLogger(getClass().getSimpleName());

  private GalaxyPosition pos;
  private Player claim;
  private Set<Planet> planets;
  private Set<Fleet> units;
  private Set<Moon> moons;

  private Set<OrbitalStructure> structures;
  private Star star;
  private double radius;
  private boolean explored;
  private int exploration = 0; // default is 0%
  private ObjectTree<BaseObject> objectPositions;

  /** @return: Systems (x,y) id on haxmap */
  public GalaxyPosition getPosition() {
	return pos;
  }

  /**
   * Sets the galaxy position of a solarsystem during creation. MUST NOT BE CALLED OUTSIDE MAP GENERATION
   * 
   * @param id
   *          system id on map.
   */
  public void setPosition(GalaxyPosition pos) {
	this.pos = pos;
  }

  /** Minimalistic constructor that only takes a Star and a claim. Claim can be null. */
  public SolarSystem(Star star, Player claim) {
	this(star);
	if (claim == null)
	  logger.info("Why not use the base constructor next time?");
	this.claim = claim;

	// Tree Demo
	objectPositions.insert(new Vector2D(10, 10), new Fleet(new HashSet<Ship>()));
	BaseObject object = objectPositions.nearest(new Vector2D(10, 10));
	objectPositions.move(new Vector2D(10, 10), new Vector2D(20, 20));

  }

  /** Constructor ONLY taking in a star. Raw system */
  public SolarSystem(Star star) {
	if (star == null)
	  logger.error("Star was NULL!");

	units = new HashSet<Fleet>();
	planets = new HashSet<Planet>();
	structures = new HashSet<OrbitalStructure>();
	logger.warn("Don't forget to set the rest of the values!");
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
   *          the set of fleets in that solar system
   * @param structures
   *          the set of structures in that solar system
   * @param star
   *          the solar systems star
   * @param radius
   *          the radius of the solar system
   */
  public SolarSystem(GalaxyPosition pos, Player claim, Set<Planet> planets, Set<Fleet> units,
	  Set<OrbitalStructure> structures, Star star) {
	this.pos = pos;
	this.claim = claim;
	this.planets = planets;
	this.units = units;
	this.structures = structures;
	this.star = star;
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
  public void addUnits(Set<Fleet> units) {
	this.units = units;
  }

  /** @return: get all fleets in the solar system. A single ship is fleet with size 1 */
  public Set<Fleet> getUnits() {
	return units;
  }

  public void addSingleFleet(Fleet fleet) {
	if (units == null) {
	  units = new HashSet<Fleet>();
	  units.add(fleet);
	  return;
	}
	units.add(fleet);
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

  public Set<OrbitalStructure> getStructures() {
	return structures;
  }

  public void setStructures(Set<OrbitalStructure> structures) {
	this.structures = structures;
  }

  public Set<Moon> getMoons() {
	return moons;
  }

  /** Redo this */
  public void setMoons(Set<Moon> moons) {
	this.moons = moons;
  }

  /** Not like this will be called very often. Will you be able to create and destroy moons? */
  public void updateMoons() {
	for (Planet planet : planets)
	  for (Moon moon : planet.getMoons())
		if (!moons.contains(moon))
		  moons.add(moon);
  }

  public void setExploration(int ex) {
	if (ex <= 100)
	  this.exploration = ex;
  }

  public void incrementExploration(int factor) {
	int temp = this.exploration;
	if (factor == 0)
	  temp++;
	else
	  temp += factor;
	if (temp <= 100)
	  this.exploration = temp;
	else
	  return;

  }

  public int getExploration() {
	return exploration;
  }
}
