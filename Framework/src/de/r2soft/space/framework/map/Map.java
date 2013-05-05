/* 
 * Copyright (c) 2012 Leander Sabel
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
 */

package de.r2soft.space.framework.map;

import java.util.Set;

import com.google.common.collect.Sets;

import de.r2soft.space.framework.objects.GameObject;


import net.sf.javaml.core.kdtree.KDTree;

public class Map {

  private KDTree objects;

  public Map() {
	objects = new KDTree(2);
  }

  public Set<GameObject> getObjectsAt(Location location, double range) {
	double[] lowk = { location.getX() - range, location.getY() - range };
	double[] uppk = { location.getX() + range, location.getY() + range };

	GameObject[] obj = (GameObject[]) objects.range(lowk, uppk);
	return Sets.newHashSet(obj);

  }

}
