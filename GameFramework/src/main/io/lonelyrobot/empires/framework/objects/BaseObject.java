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
package io.lonelyrobot.empires.framework.objects;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.log4j.Logger;

import io.lonelyrobot.empires.framework.map.SolarSystem;

/**
 * Basic game object. Contains Name, mass and position. Is invulnerable, can't be interacted with or claimed. Use for
 * critters and stars.
 * 
 * @author ***REMOVED***
 * 
 */
public abstract class BaseObject {
  protected Logger logger = Logger.getLogger(getClass().getName());
  protected long id;

  public long getId() {
	return id;
  }

  public void setId(long id) {
	this.id = id;
  }

  private Vector2D position;
  private SolarSystem container;
  private double mass;
  private String name;
  private boolean infested;

  private Type type;

  public String getName() {
	return name;
  }

  public void setName(String name) {
	this.name = name;
  }

  /**
   * One enum to rule them all, One enum to find them, One enum to bring them all and in the darkness bind them
   */
  public static enum Type {

	/* Basic Planes */
	SHIPS_FIGHTER_I, SHIPS_FIGHTER_II, SHIPS_BOMBER_I,

	/* Stars */
	STAR_BROWN_DWARF, STAR_RED_DWARF, STAR_RED_GIANT, STAR_BLUE_DWARF, STAR_BLUE_GIANT, STAR_BLACK_HOLE,

	/* Planets */
	PLANET_EARTHY, PLANET_FLAMY, PLANET_ICY, PLANET_GASSY, PLANET_ROCKY, ASTEROID_SINGLE, ASTEROID_BELT, COMET,

	/* Moons */
	MOON_ROCKY, MOON_ICY, MOON_FLAMY,

	/** Means a habitable moon that can be colonized without great costs */
	MOON_WATERY,

	/* Structures */
	STRUC_IHUB, STRUC_FACTORY_SMALL, STRUC_FACTORY_CAPITAL, STRUC_MILITARY_SMALL, STRUC_MILITARY_LARGE, STRUC_RESEARCH_BASE_SMALL,

	/* Weapons */
	WEAPONS_LASER_I, WEAPONS_GATLING_I, WEAPONS_MISSILES_I,

	/* Propulsions */
	PROPULSION_ROCKET_ENGINE_I, PROPULSION_ION_ENGINE_I, PROPULSION_TRAVERSE_DRIVE_I, PROPULSION_STANDARD_FTL_I,

	/* Deserts */
	SPACE_KOOKIE,

	/* Slot Types */
	SLOT_HIGH, SLOT_MEDIUM, SLOT_LOW,
	// High power=Weapons, Medium power = Defense, Low power = Utility.

  }

  public Vector2D getPosition() {
	return position;
  }

  public void setPosition(Vector2D vec) {
	this.position = vec;
  }

  public double getMass() {
	return mass;
  }

  public void setMass(double mass) {
	this.mass = mass;
  }

  public Type getType() {
	return type;
  }

  public void setType(Type type) {
	this.type = type;
  }

  /** Returns the SolarSystem instance that contains this Object */
  public SolarSystem getContainer() {
	return container;
  }

  /** Call this on SolarSystem change for ships and during object creation. */
  public void setContainer(SolarSystem container) {
	this.container = container;
  }

  public void setInfested(boolean infested) {
	this.infested = infested;
  }

  public boolean isInfested() {
	return infested;
  }

}
