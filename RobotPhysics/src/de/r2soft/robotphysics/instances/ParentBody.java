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

import de.r2soft.robotphysics.primatives.R2Int;

/**
 * A body that is only a parent. It has no velocity around another parent and can only hold children. Will only attract other bodies and not
 * be attracted back.
 * 
 * @author Katharina
 * 
 */
public class ParentBody extends PhysicsBody {

  private Set<OrbitalBody> children;
  private float radius;
  private R2Int position;
  private float mass;

  /** Takes mass in Kilograms */
  public ParentBody(float mass) {

	children = new HashSet<OrbitalBody>();
  }

  public void addChild(OrbitalBody body) {
	if (children.contains(body))
	  children.add(body);
  }

  public float getRadius() {
	return radius;
  }

  public void setRadius(float orbitalRadius) {
	this.radius = orbitalRadius;
  }

  public R2Int getPosition() {
	return position;
  }

  public void setPosition(R2Int position) {
	this.position = position;
  }

}
