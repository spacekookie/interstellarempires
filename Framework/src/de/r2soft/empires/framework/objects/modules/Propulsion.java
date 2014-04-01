/* #########################################################################
 * Copyright (c) 2013 Random Robot Softworks
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

package de.r2soft.space.framework.objects.modules;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Extends BaseModule because Propulsion shouldn't have special shielding
 * 
 * @author Katharina
 * 
 */
public class Propulsion extends BaseModule {

  private double strength;

  public Propulsion(double strength) {
	this.strength = strength;
  }

  public void move(Vector2D target) {
	// TODO: magic here to make ships fly
  }

  /** @return the absolute output of the drive. Not taking ship mass into account */
  public double getStrength() {
	return strength;
  }

  /** set new drive strength. Called on drive overload and after engineering upgrades */
  public void setStrength(float strength) {
	this.strength = strength;
  }

}
