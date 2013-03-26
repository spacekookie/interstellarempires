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

import java.util.HashSet;
import java.util.Set;

import objects.GameObject;
import map.Location;

/**
 * 
 * @author Leander Sabel
 * @author http://algs4.cs.princeton.edu/92search/QuadTree.java.html
 * 
 * @param <Key>
 * @param <Value>
 */
public class QuadTree<Key extends Comparable<Key>, Value> {
  private Node root;

  /**
   * Insert (x, y) into the tree.
   * 
   * @param x
   * @param y
   * @param value
   */
  public void insert(Key x, Key y, Value value) {
	root = insert(root, x, y, value);
  }

  /**
   * Insert (x, y) into the subtree of a node.
   * 
   * @param h
   * @param x
   * @param y
   * @param value
   * @return
   */
  private Node insert(Node h, Key x, Key y, Value value) {
	if (h == null)
	  return new Node(x, y, value);
	// // if (eq(x, h.x) && eq(y, h.y)) h.value = value; // duplicate
	else if (less(x, h.x) && less(y, h.y))
	  h.SW = insert(h.SW, x, y, value);
	else if (less(x, h.x) && !less(y, h.y))
	  h.NW = insert(h.NW, x, y, value);
	else if (!less(x, h.x) && less(y, h.y))
	  h.SE = insert(h.SE, x, y, value);
	else if (!less(x, h.x) && !less(y, h.y))
	  h.NE = insert(h.NE, x, y, value);
	return h;
  }

  /**
   * Search for a region in the entire tree.
   * 
   * @param rect
   * @return
   */
  public Set<Value> query2D(Interval2D<Key> rect) {
	return query2D(root, rect);
  }

  /**
   * Search for a region from a given node downwards.
   * 
   * @param h
   * @param rect
   */
  private Set<Value> query2D(Node h, Interval2D<Key> rect) {
	Set<Value> values = new HashSet<Value>();

	if (h == null)
	  return values;

	Key xmin = rect.intervalX.low;
	Key ymin = rect.intervalY.low;
	Key xmax = rect.intervalX.high;
	Key ymax = rect.intervalY.high;
	if (rect.contains(h.x, h.y))
	  values.add(h.value);
	if (less(xmin, h.x) && less(ymin, h.y))
	  values.addAll(query2D(h.SW, rect));
	if (less(xmin, h.x) && !less(ymax, h.y))
	  values.addAll(query2D(h.NW, rect));
	if (!less(xmax, h.x) && less(ymin, h.y))
	  values.addAll(query2D(h.SE, rect));
	if (!less(xmax, h.x) && !less(ymax, h.y))
	  values.addAll(query2D(h.NE, rect));

	return values;
  }

  /**
   * Is key 1 less than key 2?
   * 
   * @param k1
   * @param k2
   * @return
   */
  private boolean less(Key k1, Key k2) {
	return k1.compareTo(k2) < 0;
  }

  /**
   * Compare two keys.
   * 
   * @param k1
   * @param k2
   * @return
   */
  private boolean eq(Key k1, Key k2) {
	return k1.compareTo(k2) == 0;
  }

  /**
   * Contents of a single node.
   * 
   */
  private class Node {
	Key x, y;              // x- and y- coordinates
	Node NW, NE, SE, SW;   // four subtrees
	Value value;           // associated data

	Node(Key x, Key y, Value value) {
	  this.x = x;
	  this.y = y;
	  this.value = value;
	}
  }

}
