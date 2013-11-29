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

package de.r2soft.space.testspace.combat;

import de.r2soft.space.framework.ai.BasicCombat;
import de.r2soft.space.framework.objects.Ship;
import de.r2soft.space.framework.objects.GameObject.SuperType;
import de.r2soft.space.framework.objects.factory.UnitFactory.ShipType;

public class CombatTester {

  static Ship shipA = new Ship(SuperType.SHIP, ShipType.FIGHTER, "Ship A", null, null);
  static Ship shipB = new Ship(SuperType.SHIP, ShipType.FIGHTER, "Ship B", null, null);

  public static void main(String[] args) {

	System.out.println("WELCOME TO THE PROTOTYPE SHIP COMBAT SIMULATOR");

	System.out.println(BasicCombat.fight(shipA, shipB));
  }

}
