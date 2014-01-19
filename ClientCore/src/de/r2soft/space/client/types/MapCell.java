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

package de.r2soft.space.client.types;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import de.r2soft.space.framework.map.SolarSystem;

@Deprecated
public class MapCell extends Cell {

  private SolarSystem system;

  public MapCell(SolarSystem system) {
	this.system = system;
  }

  public SolarSystem getSystem() {
	return system;
  }

  public void setSystem(SolarSystem system) {
	this.system = system;
  }

}
