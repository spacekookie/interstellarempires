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

import de.r2soft.space.framework.objects.Star.STARCLASS;

/**
 * @author Leander
 * 
 */
public abstract class GameObject {

	private float size;
	private Vector2 position;
	private GameObject orbit;

	private TYPE type;
	private SUPERCLASS superclass;

	public static enum TYPE {
		SHIP, DEBRIS, FLEET, /** @Unit */
		PLANET, MOON, ASTEROID, /** @Planet */
		STATION_ORBITAL, RESEARCH_STATION, MILITARY_STATION, /** @Structure */
		STATION_FACILITY_SMALL, STATION_FACILITY_CAPITAL, STATION_MINING;
	}

	/** For the client UI to check what to display. THIS ABSOLUTELY NEEDS TO BE SET! */
	public static enum SUPERCLASS {
		UNIT, PLANET, STRUCTURE, STAR, SYSTEM;
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

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

	/** @return MUST NOT BE NULL */
	public SUPERCLASS getSuperclass() {
		return superclass;
	}

	public void setSuperclass(SUPERCLASS superclass) {
		this.superclass = superclass;
	}

}
