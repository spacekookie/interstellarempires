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

/**
 * 
 */
package de.r2soft.space.framework.objects;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * An object that can be moved by a player
 * 
 */
public abstract class MovingObject extends PlayerObject {

	private Vector2D trajectory;
	private float speed;

	/**
	 * @return the trajectory
	 */
	public Vector2D getTrajectory() {
		return trajectory;
	}

	/**
	 * @param trajectory
	 *          the trajectory to set
	 */
	public void setTrajectory(Vector2D trajectory) {
		this.trajectory = trajectory;
	}

	/**
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * @param speed
	 *          the speed to set
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
