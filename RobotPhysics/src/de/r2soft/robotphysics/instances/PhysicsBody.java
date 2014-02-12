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

import java.util.Vector;

public abstract class PhysicsBody {

  private float mass;
  private float velocity;
  private Vector<Integer> position;

  public PhysicsBody(float mass) {
	this(mass, new Vector<Integer>(0, 0));
  }

  public PhysicsBody(float mass, Vector<Integer> position) {
	this(mass, 0, new Vector<Integer>(0, 0));
  }

  public PhysicsBody(float mass, float velocity, Vector<Integer> position) {
	this.mass = mass;
	this.velocity = velocity;
	this.position = position;
  }

}
