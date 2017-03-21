/* #########################################################################
 * Copyright (c) 2017 Lonely Robot
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

package io.lonelyrobot.empires.fw.game.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import net.sf.javaml.core.kdtree.KDTree;

/**
 * This is a KDTree generic object storage class that maps objects against 2D coordinates.
 * This is primarily used for object storage within the
 * 
 * @author Katharina 'spacekookie' Fey <kookie@spacekookie.de>
 *
 * @param <T>
 */
public class Tree2D<T> implements Iterable<T> {

  private Map<T, Vector2D> map;
  private KDTree tree;

  {
    map = new HashMap<>();
    tree = new KDTree(2);
  }

  /**
   * 
   * @param item
   * @param pos
   */
  public void insert(T item, Vector2D pos) {
    map.put(item, pos);
    tree.insert(pos.toArray(), item);
  }

  /**
   * 
   * @param item
   */
  public void remove(T item) {
    Vector2D pos = map.get(item);
    map.remove(item);
    tree.delete(pos.toArray());
  }

  public Vector2D get(T item) {
    return map.get(item);
  }

  /**
   * 
   * @param item
   * @param pos
   */
  public void move(T item, Vector2D pos) {
    this.remove(item);
    this.insert(item, pos);
  }

  /**
   * 
   * @param pos
   * @return
   */
  public T search(Vector2D pos) {
    return (T) tree.search(pos.toArray());
  }

  /**
   * 
   * @param lowerK
   * @param upperK
   * @return
   */
  public Set<T> rangeSearch(Vector2D lowerK, Vector2D upperK) {
    Set<T> found = new HashSet<>();
    T[] range = (T[]) tree.range(lowerK.toArray(), upperK.toArray());

    /** Transpose the data from array to Set class */
    for (T t : range)
      found.add(t);

    /** Return our glorious findings */
    return found;
  }

  /**
   * 
   * @param item
   * @return
   */
  public T nearest(T item) {
    Vector2D pos = map.get(item);
    if (pos == null)
      return null;

    return (T) tree.nearest(pos.toArray());
  }

  public Set<T> nearest(T item, int count) {
    Vector2D pos = map.get(item);
    if (pos == null)
      return null;

    Set<T> found = new HashSet<>();
    T[] nearest = (T[]) tree.nearest(pos.toArray(), count);
    for (T t : nearest)
      found.add(t);

    return found;
  }

  @Override
  public Iterator<T> iterator() {
    return new KDTreeIterator();
  }

  /**
   * A private iterator type that maps all {@link #ObjectTree} iterations against the map
   * keyset which contains the actual objects that are being stored.
   * 
   * This is not advised but sometimes useful...
   */
  private class KDTreeIterator implements Iterator<T> {

    private Iterator m;

    public KDTreeIterator() {
      m = map.keySet().iterator();
    }

    public boolean hasNext() {
      return m.hasNext();
    }

    public T next() {
      return (T) m.next();
    }

    public void remove() {
      m.remove();
    }
  }
}