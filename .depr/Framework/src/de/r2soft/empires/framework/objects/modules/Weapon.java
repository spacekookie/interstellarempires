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

package de.r2soft.empires.framework.objects.modules;

import de.r2soft.empires.framework.ai.CombatAI;
import de.r2soft.empires.framework.objects.Ship.ShipType;

/**
 * Basic implementation of a weapon. Two constructors, one for weapon module, one for hard-wired into ships frame for sub-frigate ships.
 * 
 * @author Katharina
 * 
 */
public class Weapon extends AdvancedModule {

  public static enum WeaponType {
	/* Will spawn child projectile */
	MISSLE_SMALL, MISSLE_MED, MISSLE_LARGE, MISSLE_CAPITAL,

	RAILS_SMALL, RAILS_MEDIUM, RAILS_LARGE, RAILS_CAPITAL,

	AA_SMALL, AA_MEDIUM, AA_LARGE, AA_CAPITALS;
  }

  private ModuleSlot parent;
  private WeaponType type;
  private long aimID;
  private CombatAI combatAI;

  /**
   * How many turns does it take to charge this weapon. 1 means it is ready every turn. 0.5 means it can fire twice per turn. 3 means that
   * it needs to cool down and is ready for combat again 3 turns after the original one.
   */
  private int rateOfFire;
  /**
   * The volley damage of this weapon delt in an entire combat round which will be simulated as 60 seconds on the server. Damage can be
   * spread out onto different targets and doesn't need to remain focused.
   */
  private int damagePerVolley;
  /**
   * a constant that decides what armour thickness this weapon can penetrate. If the enemy armour is too thick the weapon only does minor
   * armour damage but not structural. Enemy armour values can decrease over long combat making it more vulnerable against smaller ships.
   */
  private int armourPunch;

  public Weapon(ModuleSlot slot, WeaponType weapontype) {
	this.parent = slot;
	this.type = weapontype;
	combatAI = new CombatAI(this);
  }

  // TODO: Get actual data from xml

  ShipType Depr_type;
  int Depr_rate, Depr_damage, Depr_punch;

  /**
   * Only use for debugging. Will be taken out after basic functionality has been proven.
   * 
   * @param type
   *          the type of ship that uses this weapon
   * @param ROF
   *          the rate of fire of that specific weapon
   * @param DPR
   *          the damage per combat round delt with this weapon
   * 
   * @param PUNCH
   *          a constant that decides what armour thickness this weapon can penetrate
   */
  @Deprecated
  public Weapon(ShipType type, int ROF, int DPR, int PUNCH) {
	this.Depr_type = type;
	this.Depr_rate = ROF;
	this.Depr_damage = DPR;
	this.Depr_punch = PUNCH;
  }

  public long getAimCombatId() {
	return aimID;
  }

  public void setAimCombatId(long id) {
	this.aimID = id;
  }

  public int getRateOfFire() {
	return rateOfFire;
  }

  public void setRateOfFire(int rateOfFire) {
	this.rateOfFire = rateOfFire;
  }

  public int getDamagePerVolley() {
	return damagePerVolley;
  }

  public void setDamagePerVolley(int damagePerVolley) {
	this.damagePerVolley = damagePerVolley;
  }

  public int getArmourPunch() {
	return armourPunch;
  }

  public void setArmourPunch(int armourPunch) {
	this.armourPunch = armourPunch;
  }

}
