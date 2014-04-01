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

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.log4j.Logger;

/**
 * Basic game object. Contains Name, mass and position. Is invulnerable, can't be interacted with or claimed. Use for critters and stars.
 * 
 * @author Katharina
 * 
 */
public abstract class BaseObject {

  protected Logger logger = Logger.getLogger(getClass().getName());

  private Vector2D position;
  private float mass;
  private String name;

  @Deprecated
  private Category category;
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
	FIGHTER_I, FIGHTER_II, BOMBER_I,

	/* Stars */
	STAR_BROWN_DWARF, STAR_RED_DWARF, STAR_RED_GIANT, STAR_YELLOW_DWARF, STAR_WHITE_DWARF, STAR_BLUE_DWARF, STAR_BLUE_GIANT, STAR_BLACK_HOLE,

	/* Planets */
	EARTH, VULCANIC, ICE, GAS_GIANT, GAS_DWARF, ROCK, ASTEROID_SINGLE, ASTEROID_BELT, METEOR,
	// TODO: Something to store orbit radii with? Or should we at all?

	/* Structures */
	IHUB, FACTORY_SMALL, FACTORY_CAPITAL, MILITARY_SMALL, MILITARY_LARGE, RESEARCH_BASE_SMALL,

	/* Weapons */
	LASER_I, GATLING_I, MISSILES_I,

	/* Propulsions */
	ROCKET_ENGINE_I, ION_ENGINE_I, TRAVERSE_DRIVE_I,

	/* Deserts */
	SPACE_PUDDING,

	/* Fleet Sizes */
	FLEET_TINY, FLEET_SMALL, FLEET_MEDIUM, FLEET_LARGE, FLEET_CAPITAL,

	/* Slot Types */
	SLOT_HIGH, SLOT_MEDIUM, SLOT_LOW,
	// High power=Weapons, Medium power = Defense, Low power = Utility.
  }

  /**
   * For the client UI to check what to display. THIS ABSOLUTELY NEEDS TO BE SET!
   */
  public static enum Category {
	SHIP, FLEET, PLANET, STRUCTURE, STAR, SYSTEM;
  }

  public Vector2D getPosition() {
	return position;
  }

  public void setPosition(Vector2D vec) {
	this.position = vec;
  }

  public float getMass() {
	return mass;
  }

  public void setMass(float mass) {
	this.mass = mass;
  }

  public Type getType() {
	return type;
  }

  public void setType(Type type) {
	this.type = type;
  }

  public void setCategory(Category category) {
	this.category = category;
  }

  public Category getCategory() {
	return category;
  }

}
