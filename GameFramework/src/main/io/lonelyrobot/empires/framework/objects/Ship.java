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
package io.lonelyrobot.empires.framework.objects;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import io.lonelyrobot.empires.framework.objects.factory.ValueManager;
import io.lonelyrobot.empires.framework.objects.modules.BaseModule;
import io.lonelyrobot.empires.framework.objects.modules.ModuleSlot;
import io.lonelyrobot.empires.framework.objects.modules.Propulsion;
import io.lonelyrobot.empires.framework.objects.modules.Weapon;
import io.lonelyrobot.empires.framework.players.Player;
import io.lonelyrobot.empires.framework.players.Sociable;

/**
 * Common game unit. Can include single ships, ex-ships (debris), fleets and even rainbow ponies.
 * Rainbow ponies have infinite shields, speed and damage.
 * 
 * @author ***REMOVED***
 * 
 */
public class Ship extends MovableObject {

	@Deprecated
	public static enum ShipType {
		FIGHTER, CARGO_SMALL, MOTHERSHIP;
	}

	private ShipType type;
	private Set<ModuleSlot> slots;

	public Ship(Type type, Sociable claim) {
		super.setType(type);
		super.setClaim((Player) claim);
		// this.slots = ValueManager.getInstance().getSomethingShiny(type);
	}

	/** Constructor for ships without modules */
	@Deprecated
	public Ship(ShipType type, String name, Player claim, Vector2D position) {
		this.type = type;
		super.setClaim(claim);
		super.setName(name);
		super.setPosition(position);
	}

	/** Constructor for ships with modules */
	@Deprecated
	public Ship(ShipType type, String name, Player claim, Vector2D position, Set<ModuleSlot> slots) {
		this.type = type;
		super.setClaim(claim);
		super.setName(name);
		super.setPosition(position);
		this.slots = slots;
	}

	/** DO NOT USE THIS. @SuperClass must not be null */
	@Deprecated
	public Ship() {
	}

	@Deprecated
	public ShipType getShipType() {
		return type;
	}

	public void setType(ShipType type) {
		this.type = type;
	}

	/** Strips all modules from their slots */
	public void stripShip() {
		Set<BaseModule> stripped = new HashSet<BaseModule>();
		for (ModuleSlot slot : slots) {
			stripped.add(slot.getModule());
		}
		/**
		 * TODO: @stripped needs to be returned to the players module bay. TBI = To be implemented
		 */
	}

	/** Get a quick overview of all modules currently installed on this ship */
	public Set<BaseModule> peekModules() {
		Set<BaseModule> children = new HashSet<BaseModule>();
		for (ModuleSlot slot : slots)
			children.add(slot.getBaseModule());
		return children;
	}

	/**
	 * Get a quick overview of all weapons currently installed on this ship. TODO: Change this to have
	 * lower runtime and make it more efficient.
	 * */
	public Set<Weapon> peekWeapons() {
		Set<Weapon> weapons = new HashSet<Weapon>();
		for (BaseModule module : peekModules()) {
			if (module instanceof Weapon)
				weapons.add((Weapon) module);

		}
		return weapons;
	}
}
