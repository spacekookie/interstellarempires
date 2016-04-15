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

package io.lonelyrobot.empires.framework.ai;

import java.util.logging.Logger;

import org.apache.commons.math3.analysis.function.Log;

import io.lonelyrobot.empires.framework.objects.Ship;
import io.lonelyrobot.empires.framework.objects.modules.Weapon;

/**
 * Only for testing and very early alpha purposes. If you call anything from
 * here past alpha I will hit you.
 */
public class BasicCombat {

	/**
	 * Lets two ships fight to the death. Returns the winning ship. If both
	 * ships are destroyed simultaneously return is null
	 */
	public static Ship fight(Ship shipA, Ship shipB) {

		int dmgA = 0;
		for (Weapon weapon : shipA.peekWeapons()) {
			dmgA += weapon.getDamagePerVolley();
		}

		int dmgB = 0;
		for (Weapon weapon : shipB.peekWeapons()) {
			dmgB += weapon.getDamagePerVolley();
		}

		int newHP_A = shipA.getHp();
		int newHP_B = shipB.getHp();

		while (newHP_A < 0 || newHP_B < 0) {
			newHP_A -= dmgB;
			newHP_B -= dmgA;

		}
		return (newHP_A < 0 && newHP_B >= 0) ? shipA : shipB;
	}
}
