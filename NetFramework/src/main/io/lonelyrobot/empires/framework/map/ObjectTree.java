/* #########################################################################
 * Copyright (c) 2014 Random Robot Softworks
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

package io.lonelyrobot.empires.framework.map;

import net.sf.javaml.core.kdtree.KDTree;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * A 2 dimensional version of a {@link KDTree} for easier handling.
 * 
 * @author Leander
 * 
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class ObjectTree<T> {

	private KDTree tree = new KDTree(2);

	/**
	 * Insert a node in a KD-tree.
	 * 
	 * @param position
	 * @param value
	 */
	public void insert(Vector2D position, T value) {
		tree.insert(position.toArray(), value);

	}

	/**
	 * Delete a node from a tree. Instead of actually deleting node and rebuilding tree, marks node as
	 * deleted. Hence, it is up to the caller to rebuild the tree as needed for efficiency.
	 * 
	 * @param position
	 */
	public void delete(Vector2D position) {
		tree.delete(position.toArray());
	}

	/**
	 * Find KD-tree node whose key is nearest neighbor to key.
	 * 
	 * @param position
	 */
	public T nearest(Vector2D position) {
		return (T) tree.nearest(position.toArray());
	}

	/**
	 * Find KD-tree nodes whose keys are n nearest neighbors to key. Neighbors are returned in
	 * ascending order of distance to key.
	 * 
	 * @param position
	 * @param n
	 *          Number of neighbors
	 * @return
	 */
	public T[] nearest(Vector2D position, int n) {
		return (T[]) tree.nearest(position.toArray(), n);
	}

	/**
	 * Range search in a KD-tree.
	 * 
	 * @param lowX
	 *          Lower x-Axis bound
	 * @param highX
	 *          Higher x-Axis bound
	 * @param lowY
	 *          Lower y-Axis bound
	 * @param highY
	 *          Higher y-Axis bound
	 * @return
	 */
	public T[] range(Vector2D lowerK, Vector2D upperK) {
		return (T[]) tree.range(lowerK.toArray(), upperK.toArray());
	}

	/**
	 * Find KD-tree node whose key is identical to key
	 * 
	 * @param position
	 * @return
	 */
	public T search(Vector2D position) {
		return (T) tree.search(position.toArray());
	}

	/**
	 * Move an object from an old position to a new position.
	 * 
	 * @param oldPosition
	 * @param newPosition
	 */
	public void move(Vector2D oldPosition, Vector2D newPosition) {
		Object obj = tree.search(oldPosition.toArray());
		tree.delete(oldPosition.toArray());
		tree.insert(newPosition.toArray(), obj);
	}

}
