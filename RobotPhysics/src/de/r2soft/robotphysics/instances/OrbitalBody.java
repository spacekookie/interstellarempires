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

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import de.r2soft.robotphysics.primatives.R2Int;

public class OrbitalBody extends PhysicsBody {

  /**
   * Determines if the body can have children of it's own. If @FALSE it will act as an end-instance. If @TRUE it will initiate it's Set for
   * own children
   */
  private boolean bifunction = false;

  private PhysicsBody orbitalParent;
  private Set<OrbitalBody> children;
  private R2Int position;
  private double angle;

  public OrbitalBody(boolean bifunction) {
	super();
	position = new R2Int();
	if (bifunction)
	  children = new HashSet<OrbitalBody>();
  }

  public void update() {
	calculateAngle();
  }

  private void calculateAngle() {
	double parentX = ((ParentBody) orbitalParent).getPosition().x;
	double parentY = ((ParentBody) orbitalParent).getPosition().y;

	double tempX = Math.abs(parentX - position.x);
	double tempY = Math.abs(parentY - position.y);

	angle = Math.toDegrees(Math.atan(tempX / tempY));
	System.out.println(angle);
  }

  /** Called every tick to compute gravity for this object */
  public void computeGravity() {
	if (bifunction) {
	  // Compute gravity for children
	  for (OrbitalBody body : children) {
		body.computeGravity();
	  }
	}
	else {
	  // Mass of the object * (µ of the body)/ Radius of the ship^2
	  float forceGravity = orbitalParent.getMass() * (orbitalParent.getGravCoef() / getRadius());

	}
  }

  public boolean isBifunction() {
	return bifunction;
  }

  public PhysicsBody getOrbitaParent() {
	return orbitalParent;
  }

  public void setOrbitalParent(PhysicsBody orbitalParent) {
	this.orbitalParent = orbitalParent;
  }

  public Set<OrbitalBody> getOrbitalChildren() {
	return children;
  }

  public void addOrbitalChild(OrbitalBody child) {
	if (!children.contains(child))
	  children.add(child);
  }

  public void removeOrbitalChild(OrbitalBody child) {
	if (children.contains(child))
	  children.remove(child);
	else
	  System.out.println("No such child in orbit");
  }

  public void setOrbitalChildren(Set<OrbitalBody> children) {
	this.children = children;
  }

  public R2Int getInitialPosition() {
	return position;
  }

  public void setInitialPosition(R2Int initialPosition) {
	this.position = initialPosition;
  }

}
