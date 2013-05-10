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

import java.util.Set;

import com.badlogic.gdx.math.Vector2;

import de.r2soft.space.framework.modules.ModuleSlot;
import de.r2soft.space.framework.modules.Propulsion;
import de.r2soft.space.framework.modules.Weapon;
import de.r2soft.space.framework.objects.factory.UnitFactory.ShipType;
import de.r2soft.space.framework.players.Alliance.ALLEGIANCE;
import de.r2soft.space.framework.players.Player;

/**
 * Common game unit. Can include single ships, ex-ships (debris), fleets and even rainbow ponies.
 * Rainbow ponies have
 * infinite shields, speed and damage.
 * 
 * @author ***REMOVED***
 * 
 */
public class Unit extends MovingObject {

	private Player claim;
	private ShipType type;
	private Set<Weapon> weapons;
	private Set<ModuleSlot> slots;
	private Propulsion engine;

	/** Master constructor for units */
	public Unit(SuperClass superclass, ShipType type, String name, Player claim, Vector2 position) {
		this.type = type;
		this.claim = claim;
		super.setName(name);
		super.setPosition(position);
		super.setSuperclass(superclass);
	}

	/** DO NOT USE THIS. @SuperClass must not be null */
	@Deprecated
	public Unit() {
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
}
