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
package de.r2soft.space.framework.objects;

import de.r2soft.space.framework.players.Player;

/**
 * 
 * Basic player object that can be claimed and interacted with. Not move. Has Shields and armour and can engage in combat. Is extended by
 * Planetary Object and OrbitalObject
 * 
 * @author ***REMOVED***
 * 
 *         #
 * 
 *         Basic player object that can be manipulated by the player, however not always moved
 * 
 * @author Leander
 * 
 */
public abstract class PlayerObject extends BaseObject {

  private Player claim;
  private long combatID;
  private int hp, shields, armour;

  /**
   * The amount of armour that the units attack can punch through. If the enemy armour value is too high this unit will not do any damage.
   */
  private int punch;

  public Player getClaim() {
	return claim;
  }

  public void setClaim(Player claim) {
	this.claim = claim;
  }

  public long getCombatID() {
	return combatID;
  }

  public void setCombatID(long id) {
	this.combatID = id;
  }

  public int getHp() {
	return hp;
  }

  public void setHp(int hp) {
	this.hp = hp;
  }

  public int getArmour() {
	return armour;
  }

  public void setArmour(int armour) {
	this.armour = armour;
  }

  public int getPunch() {
	return punch;
  }

  public void setPunch(int punch) {
	this.punch = punch;
  }

}
