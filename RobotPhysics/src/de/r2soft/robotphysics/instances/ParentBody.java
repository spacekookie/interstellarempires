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

/**
 * A body that is only a parent. It has no velocity around another parent and can only hold children. Will only attract other bodies and not
 * be attracted back.
 * 
 * @author ***REMOVED***
 * 
 */
public class ParentBody extends PhysicsBody {

  private Set<OrbitalBody> orbital_children;
  private float orbital_radius;
  private Vector<Integer> orbital_position;

  public ParentBody(float mass) {
	orbital_children = new HashSet<OrbitalBody>();
  }

}
