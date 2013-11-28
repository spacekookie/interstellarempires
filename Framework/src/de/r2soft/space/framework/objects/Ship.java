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

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Vector2;

import de.r2soft.space.framework.objects.factory.UnitFactory.ShipType;
import de.r2soft.space.framework.objects.modules.BaseModule;
import de.r2soft.space.framework.objects.modules.ModuleSlot;
import de.r2soft.space.framework.objects.modules.Propulsion;
import de.r2soft.space.framework.objects.modules.Weapon;
import de.r2soft.space.framework.players.Alliance.ALLEGIANCE;
import de.r2soft.space.framework.players.Player;

/**
 * Common game unit. Can include single ships, ex-ships (debris), fleets and
 * even rainbow ponies. Rainbow ponies have infinite shields, speed and damage.
 * 
 * @author Katharina
 * 
 */
public class Ship extends MovingObject {

	private Player claim;
	private ShipType type;
	private Set<ModuleSlot> slots;
	private Propulsion engine;

	/** Constructor for ships without modules */
	public Ship(SuperClass superclass, ShipType type, String name,
			Player claim, Vector2 position) {
		this.type = type;
		this.claim = claim;
		super.setName(name);
		super.setPosition(position);
		super.setSuperclass(superclass);
	}

	/** Constructor for ships with modules */
	public Ship(SuperClass superclass, ShipType type, String name,
			Player claim, Vector2 position, Set<ModuleSlot> slots) {
		this.type = type;
		this.claim = claim;
		super.setName(name);
		super.setPosition(position);
		super.setSuperclass(superclass);
		this.slots = slots;
	}

	/** DO NOT USE THIS. @SuperClass must not be null */
	@Deprecated
	public Ship() {
		super.setSuperclass(SuperClass.SHIP);
	}

	public Player getClaim() {
		return claim;
	}

	public void setClaim(Player claim) {
		this.claim = claim;
	}

	public ShipType getType() {
		return type;
	}

	public void setType(ShipType type) {
		this.type = type;
	}

	public ALLEGIANCE getAllegiance(Player p) {

		if (p.getAlliance().equals(claim.getAlliance())) {
			return ALLEGIANCE.FRIENDLY;
		}
		return p.equals(this.claim) ? ALLEGIANCE.PLAYER : ALLEGIANCE.HOSTILE;
	}

	/** Strips all modules from their slots */
	public void stripShip() {
		Set<BaseModule> stripped = new HashSet<BaseModule>();
		for (ModuleSlot slot : slots) {
			stripped.add(slot.getModule());
		}
		/**
		 * TODO: @stripped needs to be returned to the players module bay. TBI =
		 * To be implemented
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
	 * Get a quick overview of all weapons currently installed on this ship.
	 * TODO: Change this to have lower runtime and make it more efficient.
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
