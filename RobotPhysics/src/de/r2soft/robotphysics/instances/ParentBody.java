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

public class ParentBody extends PhysicsBody {

  /** The type of this parent body. Version 0.0.2 adds nested parent bodies */
  public static enum PARENT_BODY_TYPE {
	PLANET, MOON, CELESTIAL_CENTER;
  }

  private PARENT_BODY_TYPE type;
  private Set<OrbitalBody> orbitalChildren;

  /** Do not use! */
  public ParentBody(float mass) {
	this(mass, null);
  }

  /** Constructor to overwrite {@link #PARENT_BODY_TYPE} */
  public ParentBody(float mass, PARENT_BODY_TYPE type) {
	super(mass);
	this.type = type;
	orbitalChildren = new HashSet<OrbitalBody>();
  }

}
