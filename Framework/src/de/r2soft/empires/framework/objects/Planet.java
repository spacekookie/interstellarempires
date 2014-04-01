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
package de.r2soft.empires.framework.objects;

import java.util.HashSet;
import java.util.Set;

import de.r2soft.empires.framework.planetary.Orbit;
import de.r2soft.empires.framework.planetary.Orbit.ORBIT_TYPE;
import de.r2soft.empires.framework.players.Player;

/**
 * Planet object that can be claimed and raided
 * 
 * @author Katharina
 * 
 */
public class Planet extends OrbitalObject {

  private float radius;
  private float mass;
  private PlanetType type;
  private boolean homeworld;
  private boolean capital;

  private Set<Moon> moons;

  /**
   * Planet classification:
   * 
   * A: Asteroids & Rocks && B: Volcanic Planet && C: Desert Planet && D: Earth Planet && E: Ice Planet && F: Gas Planets
   * 
   * @author Katharina
   * 
   */
  public static enum PlanetType {
	ASTEROIDS, VOLCANIC, DESERT, EARTHY, ICY, GASSY;
  }

  @Deprecated
  public Planet(Category c, float radius, BaseObject parent) {
	// Why does this exist?
  }

  public Planet(Category category, float radius, float mass) {
	super.setCategory(category);
	super.orbit = new Orbit(ORBIT_TYPE.CIRCULAR, getOrbitalR(), this, getParent());
	moons = new HashSet<Moon>();
  }

  public void addMoon(Moon moon) {
	if (!moons.contains(moon))
	  moons.add(moon);
  }

  public Set<Moon> getMoons() {
	return moons;
  }

  public boolean hasMoon(Moon moon) {
	return (moons.contains(moon)) ? true : false;
  }

  public boolean hasMoons() {
	return (moons.isEmpty()) ? false : true;
  }

  public float getRadius() {
	return radius;
  }

  public void setRadius(float radius) {
	this.radius = radius;
  }

  public float getMass() {
	return mass;
  }

  public void setMass(float mass) {
	this.mass = mass;
  }

  @Deprecated
  public PlanetType getPlanetType() {
	return type;
  }

  @Deprecated
  public void setPlanetType(PlanetType type) {
	this.type = type;
  }

  /** Set from server side! Homeworld can not be transfered */
  protected void setHomeworld(Player p) {
	if (p.hasPlanets())
	  this.homeworld = true;
	else
	  this.homeworld = false;
  }

  public boolean isCapital() {
	return capital;
  }

  /**
   * Sets the new capital of the empire. Checks for the old capital and if exists revokes capital status from that planet.
   */
  public void setCapital(boolean capital) {
	Planet old = super.getClaim().getCapital();
	if (old != null)
	  old.setCapital(false);
	this.capital = capital;
  }

  public boolean isHomeworld() {
	return homeworld;
  }
}
