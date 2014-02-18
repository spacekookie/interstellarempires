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

package de.r2soft.robotphysics.primatives;

public class R2Int {
  /** the x-component of this vector */
  public int x;
  /** the y-component of this vector */
  public int y;

  /** Constructs a new vector at (0,0) */
  public R2Int() {
  }

  /**
   * Constructs a vector with the given components
   * 
   * @param x
   *          The x-component
   * @param y
   *          The y-component
   */
  public R2Int(int x, int y) {
	this.x = x;
	this.y = y;
  }

  /**
   * Constructs a vector from the given vector
   * 
   * @param v
   *          The vector
   */
  public R2Int(R2Int v) {
	set(v);
  }

  /** @return a copy of this vector */
  public R2Int copy() {
	return new R2Int(this);
  }

  /** @return The euclidian length */
  public float length() {
	return (int) Math.sqrt(x * x + y * y);
  }

  /**
   * Sets this vector from the given vector
   * 
   * @param v
   *          The vector
   * @return This vector for chaining
   */
  public R2Int set(R2Int v) {
	x = v.x;
	y = v.y;
	return this;
  }

  /**
   * Sets the components of this vector
   * 
   * @param x
   *          The x-component
   * @param y
   *          The y-component
   * @return This vector for chaining
   */
  public R2Int set(int x, int y) {
	this.x = x;
	this.y = y;
	return this;
  }

  /**
   * Substracts the given vector from this vector.
   * 
   * @param v
   *          The vector
   * @return This vector for chaining
   */
  public R2Int substract(R2Int v) {
	x -= v.x;
	y -= v.y;
	return this;
  }

  /**
   * Adds the given vector to this vector
   * 
   * @param v
   *          The vector
   * @return This vector for chaining
   */
  public R2Int add(R2Int v) {
	x += v.x;
	y += v.y;
	return this;
  }

  /**
   * Adds the given components to this vector
   * 
   * @param x
   *          The x-component
   * @param y
   *          The y-component
   * @return This vector for chaining
   */
  public R2Int add(int x, int y) {
	this.x += x;
	this.y += y;
	return this;
  }

  /**
   * @param v
   *          The other vector
   * @return The dot product between this and the other vector
   */
  public float dot(R2Int v) {
	return x * v.x + y * v.y;
  }

  /**
   * Multiplies this vector by a scalar
   * 
   * @param scalar
   *          The scalar
   * @return This vector for chaining
   */
  public R2Int mult(int scalar) {
	x *= scalar;
	y *= scalar;
	return this;
  }

  /**
   * Multiplies this vector by a scalar
   * 
   * @return This vector for chaining
   */
  public R2Int mult(int x, int y) {
	this.x *= x;
	this.y *= y;
	return this;
  }

  public R2Int div(int value) {
	return this.mult(1 / value);
  }

  public R2Int div(int vx, int vy) {
	return this.mult(1 / vx, 1 / vy);
  }

  public R2Int div(R2Int other) {
	return this.mult(1 / other.x, 1 / other.y);
  }

  public String toString() {
	return "(" + x + "," + y + ")";
  }

  /**
   * Substracts the other vector from this vector.
   * 
   * @param x
   *          The x-component of the other vector
   * @param y
   *          The y-component of the other vector
   * @return This vector for chaining
   */
  public R2Int substract(int x, int y) {
	this.x -= x;
	this.y -= y;
	return this;
  }

  /**
   * Calculates the cross product between this and the given vector.
   * 
   * @param v
   *          the other vector
   * @return the cross product
   */
  public float cross(R2Int v) {
	return this.x * v.y - this.y * v.x;
  }

  /**
   * Calculates the cross product between this and the given vector.
   * 
   * @param x
   *          the x-coordinate of the other vector
   * @param y
   *          the y-coordinate of the other vector
   * @return the cross product
   */
  public float cross(int x, int y) {
	return this.x * y - this.y * x;
  }

  @Override
  public boolean equals(Object obj) {
	if (obj == null)
	  return false;
	if (getClass() != obj.getClass())
	  return false;

	R2Double other = (R2Double) obj;

	return (this.x == other.x && this.y == other.y) ? true : false;
  }
}
