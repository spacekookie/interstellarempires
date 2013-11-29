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
package de.r2soft.space.framework.objects;

import de.r2soft.space.framework.players.Player;

/**
 * Planet implementations. Can gain or loose homeworld status by players settling their first colony on them. Capital flag can be
 * transfered.
 * 
 * @author Katharina
 * 
 */
public class Planet extends PlayerObject {

  private float radius;
  private float mass;
  private PlanetType type;
  private boolean homeworld;
  private boolean capital;

  /**
   * Planet classification:
   * 
   * A: Asteroids & Rocks && B: Volcanic Planet && C: Desert Planet && D: Earth Planet && E: Ice Planet && F: Gas Planets
   * 
   * @author Katharina
   * 
   */
  public static enum PlanetType {
	A, B, C, D, E, F;
  }

  public Planet(Category superclass, float radius, float mass) {
	super.setSuperclass(superclass);
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

  public PlanetType getType() {
	return type;
  }

  public void setType(PlanetType type) {
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
