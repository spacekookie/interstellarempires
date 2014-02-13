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

public class OrbitalBody extends PhysicsBody {

  /**
   * Determines if the body can have children of it's own. If @FALSE it will act as an end-instance. If @TRUE it will initiate it's Set for
   * own children
   */
  public boolean bifunction = false;

  private PhysicsBody orbital_parent;
  private Set<OrbitalBody> orbital_children;

  public OrbitalBody(boolean bifunction) {
	super();
	if (bifunction)
	  orbital_children = new HashSet<OrbitalBody>();
  }

  public void computeGravity() {
	if (bifunction) {

	}
	else {

	}
  }

}
