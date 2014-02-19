/* #########################################################################
 * Copyright (c) 2014 Random Robot Softworks
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

package de.r2soft.robotphysics.instances;

/**
 * Basic physics body with mass, density, gravity coefficients and range.
 * 
 * @author AreusAstarte
 * 
 */
public abstract class PhysicsBody {

  private double mass, density;
  private float radius;
  private float gravRadius, gravCoef;

  /** TODO: Change in something useful */
  public PhysicsBody() {
	this(0, 0, 0);
  }

  public PhysicsBody(double mass, float radius, float gravCoef) {
	this.mass = mass;
	this.radius = radius;
	this.gravCoef = gravCoef;
	initValues();
  }

  private void initValues() {
	density = mass / radius;
  }

  public double getMass() {
	return mass;
  }

  public void setMass(double mass) {
	this.mass = mass;
  }

  public double getDensity() {
	return density;
  }

  public void setDensity(float density) {
	this.density = density;
  }

  public float getRadius() {
	return radius;
  }

  public void setRadius(float radius) {
	this.radius = radius;
  }

  public float getGravRadius() {
	return gravRadius;
  }

  public void setGravRadius(float gravRadius) {
	this.gravRadius = gravRadius;
  }

  public float getGravCoef() {
	return gravCoef;
  }

  public void setGravCoef(float gravCoef) {
	this.gravCoef = gravCoef;
  }

}
