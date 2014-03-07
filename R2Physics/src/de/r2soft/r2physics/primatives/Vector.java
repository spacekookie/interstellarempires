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

package de.r2soft.r2physics.primatives;

/**
 * Encapsulates a general vector. Allows chaining operations by returning a reference to itself in all modification methods. See
 * {@link Vector2} and {@link Vector3} for specific implementations.
 * 
 * @author Xoppa
 */
public interface Vector<T extends Vector<T>> {
  /** @return a copy of this vector */
  T cpy();

  /** @return The euclidian length */
  float len();

  /** @return The squared euclidian length */
  float len2();

  /**
   * Limits this vector's length to given value
   * 
   * @return This vector for chaining
   */
  T limit(float limit);

  /**
   * Clamps this vector's length to given value
   * 
   * @param min
   *          Min length
   * @param max
   *          Max length
   * @return This vector for chaining
   */
  T clamp(float min, float max);

  /**
   * Sets this vector from the given vector
   * 
   * @param v
   *          The vector
   * @return This vector for chaining
   */
  T set(T v);

  /**
   * Substracts the given vector from this vector.
   * 
   * @param v
   *          The vector
   * @return This vector for chaining
   */
  T sub(T v);

  /**
   * Normalizes this vector. Does nothing if it is zero.
   * 
   * @return This vector for chaining
   */
  T nor();

  /**
   * Adds the given vector to this vector
   * 
   * @param v
   *          The vector
   * @return This vector for chaining
   */
  T add(T v);

  /**
   * @param v
   *          The other vector
   * @return The dot product between this and the other vector
   */
  float dot(T v);

  /**
   * Scales this vector by a scalar
   * 
   * @param scalar
   *          The scalar
   * @return This vector for chaining
   */
  T scl(float scalar);

  /**
   * Scales this vector by another vector
   * 
   * @return This vector for chaining
   */
  T scl(T v);

  /**
   * @param v
   *          The other vector
   * @return the distance between this and the other vector
   */
  float dst(T v);

  /**
   * This is much faster to calculate than {@link Vector#dst(Vector)} It avoids a calculating square root, so it is mostly useful for
   * comparisons
   * 
   * @param v
   *          The other vector
   * @return the squared distance between this and the other vector
   */
  float dst2(T v);

  /**
   * Linearly interpolates between this vector and the target vector by alpha which is in the range [0,1]. The result is stored in this
   * vector.
   * 
   * @param target
   *          The target vector
   * @param alpha
   *          The interpolation coefficient
   * @return This vector for chaining.
   */
  T lerp(T target, float alpha);

  // TODO: T crs(T v);
}
