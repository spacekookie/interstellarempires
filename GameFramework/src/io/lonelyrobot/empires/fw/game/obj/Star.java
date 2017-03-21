/* #########################################################################
 * Copyright (c) 2017 Lonely Robot
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

package io.lonelyrobot.empires.fw.game.obj;

import java.util.HashSet;
import java.util.Set;

import io.lonelyrobot.empires.fw.game.map.OrbitData;
import io.lonelyrobot.empires.fw.game.traits.Celestial;
import io.lonelyrobot.empires.fw.game.traits.Orbitable;
import io.lonelyrobot.empires.fw.game.traits.Ownable;
import io.lonelyrobot.empires.fw.game.traits.Types;
import lombok.Data;

/**
 * A star is at the center of a solar system although not neccessarily at location (0, 0).
 * It has an impact on the gravity of it's system as well as output different radiation
 * zones into the core of the system.
 * 
 * Stars are classified as different types that are defined in {@link Types.Stars} with
 * further comments attached to them.
 * 
 * @author Katharina 'spacekookie' Fey <kookie@spacekookie.de>
 */
public @Data class Star extends BaseObject implements Celestial, Ownable, Orbitable {

  public Star(Types.Stars type) {
    super.orbit = new OrbitData();
  }

  /**
   * This function calculates the range impact on the gravity well of a solarsystem from
   * this star alone. Calculations can be done in this function to benefit game balance
   * without making too much physical sense.
   * 
   * In the end, a double number in Mm is returned to be used elsewhere.
   * 
   * @return double in Mm
   */
  public double getGravity() {
    return mass;
  }
}
