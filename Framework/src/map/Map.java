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

package map;

import java.util.Set;

import objects.GameObject;
import datastructures.Interval;
import datastructures.Interval2D;
import datastructures.QuadTree;

public class Map {

  private QuadTree<Double, GameObject> objects;

  public Map() {
	objects = new QuadTree<Double, GameObject>();
  }

  /**
   * Get all game objects from a given position and range. This will query the map for game objects
   * from Position - range to Position + range.
   * 
   * @param pos
   * @param radius
   * @return
   */
  public Set<GameObject> getObjects(Location pos, Double range) {
	Interval<Double> xIntervall = new Interval<Double>(pos.getX() - range, pos.getX() + range);
	Interval<Double> yIntervall = new Interval<Double>(pos.getY() - range, pos.getY() + range);
	Interval2D<Double> intervall = new Interval2D<Double>(xIntervall, yIntervall);
	return objects.query2D(intervall);
  }
}
