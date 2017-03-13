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

import java.util.Set;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import io.lonelyrobot.empires.fw.game.map.MapCoordinate;
import io.lonelyrobot.empires.fw.game.modules.ModuleSlot;
import lombok.Data;
import lombok.Getter;

/**
 * This is a base object in the game that is the parent of pretty much every parent
 * object. This does **not** apply to Modules and submodules of a ship. In fact this
 * object OWNS modules which does not allow for infinite nesting.
 * 
 * Any additional functionality beyond obvious types is handled via the Traits system.
 * 
 * @author Katharina 'spacekookie' Fey <kookie@spacekookie.de>
 */
public @Data class BaseObject {

  /** Combat variables that are important for all objects */
  protected @Getter double health;
  protected @Getter double armour;
  protected @Getter double shields;
  protected @Getter long combatID;

  /** How long can a ship deal with radiation/ boarding */
  protected @Getter int crew;
  protected @Getter double radResistence;

  /** To be able to map it to a system */
  protected @Getter MapCoordinate galaxyPos;

  /** Fields regarding movement traits */
  protected @Getter Vector2D trajectory;
  protected @Getter Vector2D solPos;
  protected @Getter double speed;
  protected @Getter double fuel;
  protected @Getter double mass;

  /** Other miscelanious fields */
  protected @Getter Set<ModuleSlot> slots;
  protected @Getter String name;
}
