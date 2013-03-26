/* 
 * Copyright (c) 2013 Leander Sabel
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

package datastructures;

/**
 * A 2D interval representing a rectangle in Cartesian space.
 * 
 * @author Leander Sabel
 * @author http://algs4.cs.princeton.edu/91primitives/Interval2D.java
 * 
 * @param <Key>
 */
public class Interval2D<Key extends Comparable<Key>> {
  public final Interval<Key> intervalX;   // x-interval
  public final Interval<Key> intervalY;   // y-interval

  
  public Interval2D(Interval<Key> intervalX, Interval<Key> intervalY) {
	this.intervalX = intervalX;
	this.intervalY = intervalY;
  }
  
  public Interval2D(Key x1, Key y1, Key x2, Key y2) {
	intervalX = new Interval<Key>(x1, x2);
	intervalY = new Interval<Key>(y1, y2);
  }

  /**
   * Does this 2D interval a intersect b?
   * @param b
   * @return
   */
  public boolean intersects(Interval2D<Key> b) {
	if (intervalX.intersects(b.intervalX))
	  return true;
	if (intervalY.intersects(b.intervalY))
	  return true;
	return false;
  }

  /**
   * Does this 2D interval contain (x, y)?
   * @param x
   * @param y
   * @return
   */
  public boolean contains(Key x, Key y) {
	return intervalX.contains(x) && intervalY.contains(y);
  }

  /**
   * Format this Interval2D as a String.
   */
  public String toString() {
	return intervalX + " x " + intervalY;
  }
}
