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

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue.ValueType;

/**
 * Basic Gameobject to be extended by other objects. The superclass flag is currently 100% needed for rendering purposes. The client will
 * crash if an object doesn't have it set to something!
 * 
 * @author ***REMOVED***
 * 
 */
public abstract class GameObject {

  private float size;
  private Vector2 position;
  private GameObject orbit;
  private String name;

  /* INSANELY IMPORTANT */
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
	/*
	 * A basic fighter
	 */
	FIGHTER_I,
	/*
	 * A sample gun
	 */
	SAMPLE_GUN_I, SPACE_PUDDING_DISPENSER;
  }

  /** For the client UI to check what to display. THIS ABSOLUTELY NEEDS TO BE SET! */
  public static enum Category {
	SHIP, FLEET, PLANET, STRUCTURE, STAR, SYSTEM;
  }

  public Vector2 getPosition() {
	return position;
  }

  public void setPosition(Vector2 vec) {
	this.position = vec;
  }

  public float getSize() {
	return size;
  }

  public void setSize(float size) {
	this.size = size;
  }

  /** @return the GameObject that another object orbits around */
  public GameObject getOrbit() {
	return orbit;
  }

  /** Set the center of orbit object */
  public void setOrbit(GameObject orbit) {
	this.orbit = orbit;
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

}
