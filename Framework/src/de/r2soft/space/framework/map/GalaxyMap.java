/* #########################################################################
 * Copyright (c) 2013 Random Robot Softworks
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

package de.r2soft.space.framework.map;

import java.util.HashSet;
import java.util.Set;

import de.r2soft.space.framework.types.IntVec2;

/**
 * The public galaxymap that holds all "public" instances of data. Solarsystems
 * with their planets, asteroid belts, etc.
 */
public class GalaxyMap {

	private IntVec2 size;
	private Set<SolarSystem> systems;
	private int version;

	public GalaxyMap() {
		size = new IntVec2();
		systems = new HashSet<SolarSystem>();
	}

	public GalaxyMap(Set<SolarSystem> systems) {
		size = new IntVec2();
		this.systems = systems;
	}

	public IntVec2 getSize() {
		return size;
	}

	public void setSize(IntVec2 size) {
		this.size = size;
	}

	/** Gets the solarsystems */
	public Set<SolarSystem> getSystems() {
		return systems;
	}

	/** Adds a single system to the Set */
	public void addSystem(SolarSystem system) {
		systems.add(system);
	}

	/** Sets an entire Set to the set */
	public void setSystems(Set<SolarSystem> systems) {
		this.systems = systems;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	/** Gets a specific solar system with an id */
	public SolarSystem getSystemById(IntVec2 id) {
		for (SolarSystem system : systems) {
			if (id.equals(system.getId()))
				return system;
		}
		return null;
	}

}
