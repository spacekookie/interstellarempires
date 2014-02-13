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

  private float mass;
  private float density;
  private float grav_radius, grav_coef;

  /** TODO: Change in something useful */
  public PhysicsBody() {
	this(0, 0, 0);
  }

  public PhysicsBody(float mass, float density, float grav_coef) {

  }

}
