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

package io.lonelyrobot.empires.framework.players;

import java.util.HashSet;

public class Role {

  /** Determines the level of access a player has in an alliance */
  private enum Right {
	RESOURCES, PROMOTE_DEMOTE, SETTINGS, HANGAR_MANAGEMENT, FLEET_MANAGEMENT, DIPLOMACY;
  }

  private String title;
  private HashSet<Right> rights;

  public Role(HashSet<Right> rights) {
	this.rights = rights;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
	if (this == obj)
	  return true;
	if (obj == null)
	  return false;
	if (getClass() != obj.getClass())
	  return false;
	Role other = (Role) obj;
	if (title == null) {
	  if (other.title != null)
		return false;
	}
	else if (!title.equals(other.title))
	  return false;
	return true;
  }

  /**
   * @return the title
   */
  public String getTitle() {
	return title;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((title == null) ? 0 : title.hashCode());
	return result;
  }

  /**
   * @param title
   *          the title to set
   */
  public void setTitle(String title) {
	this.title = title;
  }

  public HashSet<Right> getRights() {
	return rights;
  }

  public boolean isSpecificRight(Right right) {
	return this.rights.contains(right);
  }

  public void addSpecificRight(Right right) {
	if (!this.rights.contains(right))
	  this.rights.add(right);
  }

  public void setRights(HashSet<Right> rights) {
	this.rights = rights;
  }
}
