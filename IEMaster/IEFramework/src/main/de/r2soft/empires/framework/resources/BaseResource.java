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

package de.r2soft.empires.framework.resources;

/**
 * Basic resource to pay things with
 * 
 * @author ***REMOVED***
 * 
 */
public abstract class BaseResource {
  /** Scifi sounding name */
  private String name;

  /** Volume in m^3 and density in g/cm^3 */
  private double density;

  public String getName() {
	return name;
  }

  public void setName(String name) {
	this.name = name;
  }

  public double getDensity() {
	return density;
  }

  public void setDensity(double density) {
	this.density = density;
  }

}
