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
 * A data structure that holds a low and a high point.
 * 
 * @author Leander Sabel
 * @author http://algs4.cs.princeton.edu/92search/Interval.java.html
 * 
 * @param <Key>
 */
public class Interval<Key extends Comparable<Key>> {
  public final Key low;
  public final Key high;

  /**
   * Create a new interval with two points. If the low point is higher than the high point it will
   * exchange the points to ensure that low < high.
   * 
   * @param low
   * @param high
   */
  public Interval(Key low, Key high) {
	if (less(high, low)) {
	  this.low = high;
	  this.high = low;
	}
	else {
	  this.low = low;
	  this.high = high;
	}
  }

  /**
   * Is x between low and high?
   * 
   * @param x
   * @return
   */
  public boolean contains(Key x) {
	return !less(x, low) && !less(high, x);
  }

  /**
   * Does this interval intersect that interval?
   * 
   * @param that
   * @return
   */
  public boolean intersects(Interval<Key> that) {
	if (less(this.high, that.low))
	  return false;
	if (less(that.high, this.low))
	  return false;
	return true;
  }

  /**
   * Does this interval equal that interval?
   * 
   * @param that
   * @return
   */
  public boolean equals(Interval<Key> that) {
	return this.low.equals(that.low) && this.high.equals(that.high);
  }

  /**
   * Is x smaller than y?
   * 
   * @param x
   * @param y
   * @return
   */
  private boolean less(Key x, Key y) {
	return x.compareTo(y) < 0;
  }

  /**
   * Format this interval as a string.
   */
  public String toString() {
	return "[" + low + ", " + high + "]";
  }
}
