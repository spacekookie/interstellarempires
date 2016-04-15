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

package io.lonelyrobot.empires.framework.objects.factory;

import java.util.Set;

import io.lonelyrobot.empires.framework.map.GalaxyMap;
import io.lonelyrobot.empires.framework.types.IntVec2;

public class MapFactory {

  public static enum MapParameters {
	/*
	 * Create a funny map
	 */
	FUNNY,
	/*
	 * Create a rather doomy map
	 */
	DOOMY;
  }

  public static GalaxyMap generateMap(IntVec2 size, Set<MapParameters> parameters) {
	GalaxyMap map = new GalaxyMap();

	// TODO: Work Magic here and create a cool galaxy map

	return map;
  }

}
