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

package de.r2soft.empires.framework.objects;

import de.r2soft.empires.framework.planetary.Orbit;
import de.r2soft.empires.framework.planetary.Orbit.ORBITALTYPE;

/**
 * A basic moon that orbits a parent planet
 * 
 * @author ***REMOVED***
 * 
 */
public class Moon extends OrbitalObject {
	private Planet parent;

	@Deprecated
	public Moon(Planet parent) {
		super.setOrbitalParent(parent);
		super.setOrbit(new Orbit(ORBITALTYPE.CIRCULAR, getOrbitalR(), getOrbitalParent()));
	}

	public Moon(Orbit orbit) {
		super.setOrbit(orbit);
		super.getOrbit().setSelf(this);
		this.parent = (Planet) orbit.getParent();
	}

	public Planet getParent() {
		return parent;
	}

	public void setParent(Planet parent) {
		this.parent = parent;
	}

}
