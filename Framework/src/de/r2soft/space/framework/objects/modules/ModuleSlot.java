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

import com.badlogic.gdx.Gdx;

import de.r2soft.space.framework.objects.modules.Weapon.WeaponType;

public class ModuleSlot {

  public static enum SlotType {

	/* Modules that can attack the enemy with damage or abilities */
	WEAPON,

	/* Modules that make a ship move. If destroyed can no longer move */
	PROPULSION,

	/* Modules for communication, sensors, logistics, etc. */
	UTILITY,

	/*
	 * Modules that can defend the ship actively or passively but not deal damage to enemies.
	 */
	DEFENCE;
  }

  public static enum SlotSize {

	/* Used on frigates */
	SMALL,

	/* Used on Cruisers and Destroyers */
	MEDIUM,

	/* Used on Battle-Cruisers and Battleships */
	LARGE,

	/* Used on capital ships: Dreadnoughts, Carriers, Motherships, etc. */
	CAPITAL;
  }

  private SlotType type;
  private SlotSize size;
  private WeaponType weaponType;
  private BaseModule child;

  /** Creates an empty slot to be filled later */
  public ModuleSlot() {

  }

  /**
   * Creates a new Module slot for a weapon of appropriate size to be added into it.
   */
  public ModuleSlot(SlotType type, SlotSize size, WeaponType weaponType) {
	this.type = type;
	this.size = size;
	this.weaponType = weaponType;
  }

  public void addModule(BaseModule module) {
	if (child == null)
	  this.child = module;
	else
	  logError(child);
  }

  private Weapon w;

  /** Adds a weapon into the slot if it's not occupied yet. */
  @Deprecated
  public void addModule(Weapon w) {
	if (w == null)
	  this.w = w;
	else
	  logError(w);
  }

  private Propulsion p;

  /** Adds engines into the slot if it's not occupied yet. */
  @Deprecated
  public void addModule(Propulsion p) {
	if (p == null)
	  this.p = p;
	else
	  logError(p);
  }

  /** Returns the fitted module. Must be cast to specific type */
  public BaseModule getBaseModule() {
	return child;
  }

  /**
   * Returns the fitted module inside the slot TODO: This needs to be changed in the future!
   */
  @Deprecated
  public BaseModule getModule() {
	return (w != null) ? w : p;
  }

  private void logError(BaseModule module) {
	Gdx.app.log("Framework: ", "Slot for" + module.toString() + "already occupied");
	// TODO: Promt user to clear the slot first.
  }

  public SlotType getType() {
	return type;
  }

  /** Should never be called */
  private void setType(SlotType type) {
	this.type = type;
  }

  public SlotSize getSize() {
	return size;
  }

  /** Should never be called */
  private void setSize(SlotSize size) {
	this.size = size;
  }

  public WeaponType getWeaponType() {
	return weaponType;
  }

  public void setWeaponType(WeaponType weaponType) {
	this.weaponType = weaponType;
  }

  public BaseModule getChild() {
	return child;
  }

  public void setChild(BaseModule child) {
	this.child = child;
  }

}