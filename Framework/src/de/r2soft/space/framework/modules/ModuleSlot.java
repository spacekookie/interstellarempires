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

package de.r2soft.space.framework.modules;

import com.badlogic.gdx.Gdx;

import de.r2soft.space.framework.modules.Weapon.WeaponType;

public class ModuleSlot {

	public static enum SlotType {

		/* Modules that can attack the enemy with damage or abilities */
		WEAPON,

		/* Modules that make a ship move. If destroyed can no longer move */
		PROPULSION,
		/* Modules for communication, sensors, logistics, etc. */
		UTILITY, 

		/*
		 * Modules that can defend the ship actively or passively but
		 * not deal damage to enemies.
		 */
		DEFENCE;
	}

	public static enum SlotSize {
		/** Used on frigates */
		SMALL,
		/** Used on Cruisers and Destroyers */
		MEDIUM,
		/** Used on Battle-Cruisers and Battleships */
		LARGE,
		/** Used on capital ships: Dreadnoughts, Carriers, Motherships, etc. */
		CAPITAL;
	}

	private SlotType type;
	private SlotSize size;
	private WeaponType weaponType;

	/**
	 * Creates a new Module slot for a weapon of appropriate size to be added into it.
	 */
	public ModuleSlot(SlotType type, SlotSize size, WeaponType weaponType) {
		this.type = type;
		this.size = size;
		this.weaponType = weaponType;
	}

	private Weapon w;

	/** Adds a weapon into the slot if it's not occupied yet. */
	public void addModule(Weapon w) {
		if (w == null)
			this.w = w;
		else
			logError(w);
	}

	private Propulsion p;

	/** Adds a weapon into the slot if it's not occupied yet. */
	public void addModule(Propulsion p) {
		if (p == null)
			this.p = p;
		else
			logError(p);
	}

	private void logError(BaseModule module) {
		Gdx.app.log("Framework: ", "Slot for" + module.toString() + "already occupied");
		// TODO: Promt user to clear the slot first.
	}

}