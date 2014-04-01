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

package de.r2soft.empires.framework.objects;

import java.util.Set;

import de.r2soft.empires.framework.ai.Admiral;
import de.r2soft.empires.framework.ai.Admiral.CommandType;

/**
 * Fleet object to hold Sets of units and admirals.
 * 
 * @author Katharina
 * 
 */
public class Fleet extends MovableObject {

  public static enum FleetSize {
	TINY, SMALL, MEDIUM, LARGE, MASSIVE;
  }

  private int count;
  private Set<Ship> units;
  private Admiral admiral;

  public Fleet(Set<Ship> units) {
	this.count = units.size();
	this.units = units;
  }

  /**
   * Determines what icon size will be used for rendering.
   * 
   * @return enum for fleet SIZE.
   */
  public FleetSize getFleetSize() {
	if (count < 10)
	  return FleetSize.TINY;
	if (count < 25)
	  return FleetSize.SMALL;
	if (count < 50)
	  return FleetSize.MEDIUM;
	if (count < 100)
	  return FleetSize.LARGE;
	if (count < 500)
	  return FleetSize.MASSIVE;
	else
	  return null;
  }

  /** Add existing fleet to this one */
  public void addUnits(Set<Ship> newUnits) {

	for (Ship u : newUnits) {
	  units.add(u);
	  count++;
	}
  }

  /** Add single unit to this fleet */
  public void addUnit(Ship unit) {
	units.add(unit);
	count++;
  }

  /** Remove a specific unit from the fleet */
  public void removeUnit(Ship unit) {
	units.remove(unit);
	count--;
  }

  public void removeUnits(Set<Ship> units) {
	for (Ship u : units) {
	  this.units.remove(u);
	  count--;
	}
  }

  public int getCount() {
	return count;
  }

  public void setCount(int count) {
	this.count = count;
  }

  public Set<Ship> getUnits() {
	return units;
  }

  public boolean hasAdmiral() {
	return admiral == null ? false : true;
  }

  public Admiral getAdmiral() {
	return admiral;
  }

  public void setAdmiral(String name, CommandType type) {
	if (admiral == null)
	  admiral = new Admiral(name, type);
  }
}
