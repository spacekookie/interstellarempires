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
package de.r2soft.space.framework.objects.factory;

import java.util.HashSet;
import java.util.Set;

import de.r2soft.space.framework.objects.Fleet;
import de.r2soft.space.framework.objects.GameObject.SuperClass;
import de.r2soft.space.framework.objects.Structure;
import de.r2soft.space.framework.objects.Unit;
import de.r2soft.space.framework.players.Player;

/**
 * This class provides static methods to build default units.
 * 
 * @author Leander
 * 
 */
public class UnitFactory {

	public static enum ShipType {
		FIGHTER, CARGO_SMALL;
	}

	private Player owner;
	private Structure parent;

	/** Constructor to be called from unit producing structures and planets. */
	public UnitFactory(Player owner, Structure parent) {
		this.owner = owner;
		this.parent = parent;
	}

	/**
	 * Requsition a single unit by the Unit object
	 * 
	 * @param type
	 * @return
	 * 
	 * @author ***REMOVED***
	 */
	public Unit requisitionUnit(ShipType type) {
		Unit ship = new Unit(SuperClass.UNIT, type, null, owner, parent.getPosition());
		return ship;
	}

	/**
	 * Requisitions a fleet of units from a HashSet of shiptypes.
	 * 
	 * @param types
	 *          of ships wanted. Multiple entries mean multiple instances of that requested unit.
	 * @return Fleet object with requested ships. Base constructor without admiral.
	 * 
	 * @author ***REMOVED***
	 */
	public Fleet requisitionFleet(Set<ShipType> types) {

		Set<Unit> requested = new HashSet<Unit>();

		for (ShipType type : types) {
			requested.add(new Unit(SuperClass.UNIT, type, null, owner, parent.getPosition()));
		}

		Fleet fleet = new Fleet(requested);
		return fleet;
	}

	/**
	 * Build a new default unit.
	 * 
	 * @param type
	 * @return
	 */
	@Deprecated
	public static Unit buildUnit(ShipType type) {
		if (type == ShipType.FIGHTER) {
			Unit fighter = new Unit();
			return fighter;
		}
		else {
			// Log the error
			return null;
		}
	}

}
