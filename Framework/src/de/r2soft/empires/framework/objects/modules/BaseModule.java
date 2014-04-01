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

/**
 * A basic module
 * 
 * @author Katharina
 * 
 */
public abstract class BaseModule {

  private int hp;
  private int armour;
  private boolean destroyed;

  /** @return the remaining hp of the module */
  public int getHp() {
	return hp;
  }

  /** set the remaining hp of the module */
  public void setHp(int hp) {
	this.hp = hp;
  }

  /** @return wether the module blew up or not */
  public boolean isDestroyed() {
	return hp >= 0 ? true : false;
  }

  /** THIS SHOULDN'T ACTUALLY BE CALLED ANYWHERE */
  @Deprecated
  protected void setDestroyed(boolean status) {
	if (status)
	  hp = 0;
	this.destroyed = status;
  }

  /** @return the amount of armour points the module has left */
  public int getArmour() {
	return armour;
  }

  /** Sets the new amount of armour. Either after module repairs or a combat turn */
  public void setArmour(int armour) {
	this.armour = armour;
  }

}