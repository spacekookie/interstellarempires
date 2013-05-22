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

package de.r2soft.space.framework.navigation;

import java.util.ArrayList;
import java.util.Collection;

public class Route {

	ArrayList<Waypoint> route;
	private int travelIndex;

	public Route(Collection<Waypoint> route) {
		this.route = (ArrayList<Waypoint>) route;
		travelIndex = 0;
	}

	public Waypoint getNode(int index) {
		return route.get(index);
	}

	public Waypoint getNextNode() {
		return route.get(travelIndex++);
	}

}
