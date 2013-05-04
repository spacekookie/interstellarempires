/* 
 * Copyright (c) 2013 Katharina Fey
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

package client.types;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.utils.NumberUtils;

/**
 * My own integer implementation of the Vector2 class from LibGDX. And my professors told me my vector skills were insufficiant
 * :')
 * 
 * @author Katharina
 * 
 */
public class IntVec2 {

	/** the x-component of this vector **/
	public int x;
	/** the y-component of this vector **/
	public int y;

	/** Constructs a new vector at (0,0) */
	public IntVec2() {
	}

	/**
	 * Constructs a vector with the given components
	 * 
	 * @param x
	 *         The x-component
	 * @param y
	 *         The y-component
	 */
	public IntVec2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructs a vector from the given vector
	 * 
	 * @param v
	 *         The vector
	 */
	public IntVec2(IntVec2 v) {
		set(v);
	}

	/** @return a copy of this vector */
	public IntVec2 copy() {
		return new IntVec2(this);
	}

	/** @return The euclidian length */
	public float length() {
		return (int) Math.sqrt(x * x + y * y);
	}

	/**
	 * Sets this vector from the given vector
	 * 
	 * @param v
	 *         The vector
	 * @return This vector for chaining
	 */
	public IntVec2 set(IntVec2 v) {
		x = v.x;
		y = v.y;
		return this;
	}

	/**
	 * Sets the components of this vector
	 * 
	 * @param x
	 *         The x-component
	 * @param y
	 *         The y-component
	 * @return This vector for chaining
	 */
	public IntVec2 set(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

	/**
	 * Substracts the given vector from this vector.
	 * 
	 * @param v
	 *         The vector
	 * @return This vector for chaining
	 */
	public IntVec2 substract(IntVec2 v) {
		x -= v.x;
		y -= v.y;
		return this;
	}

	/**
	 * Normalizes this vector
	 * 
	 * @return This vector for chaining
	 */
	public IntVec2 normalize() {
		int length = (int) length();
		if (length != 0)
			{
				x /= length;
				y /= length;
			}
		return this;
	}

	/**
	 * Adds the given vector to this vector
	 * 
	 * @param v
	 *         The vector
	 * @return This vector for chaining
	 */
	public IntVec2 add(IntVec2 v) {
		x += v.x;
		y += v.y;
		return this;
	}

	/**
	 * Adds the given components to this vector
	 * 
	 * @param x
	 *         The x-component
	 * @param y
	 *         The y-component
	 * @return This vector for chaining
	 */
	public IntVec2 add(int x, int y) {
		this.x += x;
		this.y += y;
		return this;
	}

	/**
	 * @param v
	 *         The other vector
	 * @return The dot product between this and the other vector
	 */
	public float dot(IntVec2 v) {
		return x * v.x + y * v.y;
	}

	/**
	 * Multiplies this vector by a scalar
	 * 
	 * @param scalar
	 *         The scalar
	 * @return This vector for chaining
	 */
	public IntVec2 mult(int scalar) {
		x *= scalar;
		y *= scalar;
		return this;
	}

	/**
	 * Multiplies this vector by a scalar
	 * 
	 * @return This vector for chaining
	 */
	public IntVec2 mult(int x, int y) {
		this.x *= x;
		this.y *= y;
		return this;
	}

	public IntVec2 div(int value) {
		return this.mult(1 / value);
	}

	public IntVec2 div(int vx, int vy) {
		return this.mult(1 / vx, 1 / vy);
	}

	public IntVec2 div(IntVec2 other) {
		return this.mult(1 / other.x, 1 / other.y);
	}

	public String toString() {
		return "[" + x + " | " + y + "]";
	}

	/**
	 * Substracts the other vector from this vector.
	 * 
	 * @param x
	 *         The x-component of the other vector
	 * @param y
	 *         The y-component of the other vector
	 * @return This vector for chaining
	 */
	public IntVec2 substract(float x, float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	/**
	 * Multiplies this vector by the given matrix
	 * 
	 * @param mat
	 *         the matrix
	 * @return this vector
	 */
	public IntVec2 mult(Matrix3 mat) {
		int x = (int) (this.x * mat.val[0] + this.y * mat.val[3] + mat.val[6]);
		int y = (int) (this.x * mat.val[1] + this.y * mat.val[4] + mat.val[7]);
		this.x = x;
		this.y = y;
		return this;
	}

	/**
	 * Calculates the cross product between this and the given vector.
	 * 
	 * @param v
	 *         the other vector
	 * @return the cross product
	 */
	public float cross(IntVec2 v) {
		return this.x * v.y - this.y * v.x;
	}

	/**
	 * Calculates the cross product between this and the given vector.
	 * 
	 * @param x
	 *         the x-coordinate of the other vector
	 * @param y
	 *         the y-coordinate of the other vector
	 * @return the cross product
	 */
	public float cross(int x, int y) {
		return this.x * y - this.y * x;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntVec2 other = (IntVec2) obj;
		if (NumberUtils.floatToIntBits(x) != NumberUtils.floatToIntBits(other.x))
			return false;
		if (NumberUtils.floatToIntBits(y) != NumberUtils.floatToIntBits(other.y))
			return false;
		return true;
	}
}