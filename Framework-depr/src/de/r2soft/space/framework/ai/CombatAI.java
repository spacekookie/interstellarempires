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

package de.r2soft.space.framework.ai;

import java.util.Set;
import java.util.Vector;

import de.r2soft.space.framework.objects.Ship;
import de.r2soft.space.framework.objects.modules.Weapon;

/**
 * A very basic AI to handle combat on ships for each module
 * 
 * @author Katharina
 * 
 */
public class CombatAI extends BasicAI {

	private long aimID;
	private Weapon parent;

	/** Will attach an AI to each weapon at the beginning of a fight */
	public CombatAI(Weapon parent) {
		this.parent = parent;
	}

	/**
	 * Will be called for each combat turn determining what optimal target
	 * should be picked.
	 */
	public Ship onCombat(Set<Ship> targets) {
		aimID = parent.getAimCombatId();
		long min = aimID; // arbitrarily set something here
		int minindex = 0;

		Vector<Long> targetIDs = new Vector<Long>();
		Vector<Ship> ships = new Vector<Ship>();

		/** Add combat IDs to Collection */
		for (Ship ship : targets) {
			targetIDs.add(ship.getCombatID());
			ships.add(ship);
		}

		// TODO: Check BinaryHeap first if optimal target exists

		for (int i = 0; i < targetIDs.size(); i++) {
			if (Math.abs(targetIDs.get(i) - aimID) < min) {
				min = targetIDs.get(i);
				minindex = i;
			}
		}

		// TODO: Add minidex to BinaryHeap

		return ships.get(minindex);
	}
}
