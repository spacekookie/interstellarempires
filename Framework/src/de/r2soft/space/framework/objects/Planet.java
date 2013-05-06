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

package de.r2soft.space.framework.objects;

/**
 * We might want to extend a "Orbiting object" or at least make an interface?
 * 
 * @author Katharina
 */
public class Planet extends MovingObject {

	private float radius;
	private float mass;
	private PLANETCLASS classification;

	/**
	 * Planet classification:
	 * 
	 * A: Asteroids & Rocks
	 * B: Volcanic Planet
	 * C: Desert Planet
	 * D: Earth Planet
	 * E: Ice Planet
	 * F: Gas Planets
	 * 
	 * @author Katharina
	 * 
	 */
	public static enum PLANETCLASS {
		A, B, C, D, E, F;
	}

	public Planet(SUPERCLASS superclass, float radius, float mass) {
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

	public PLANETCLASS getClassification() {
		return classification;
	}

	public void setClassification(PLANETCLASS classification) {
		this.classification = classification;
	}
}
