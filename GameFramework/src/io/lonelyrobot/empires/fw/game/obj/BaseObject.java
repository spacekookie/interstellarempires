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
import io.lonelyrobot.empires.fw.game.map.SolarSystem;
import io.lonelyrobot.empires.fw.game.modules.ModuleSlot;
import io.lonelyrobot.empires.fw.game.traits.Orbitable;
import io.lonelyrobot.empires.fw.game.utils.Tools;
import lombok.Data;

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
  protected double health;
  protected double armour;
  protected double shields;
  protected long combatID;

  /** How long can a ship deal with radiation/ boarding */
  protected int crew;
  protected double radResistence;

  /** To be able to map it to a system */
  protected MapCoordinate galaxyPos;
  protected SolarSystem parent;

  /** Fields regarding movement traits */
  protected BaseObject orbitalParent;
  protected Set<BaseObject> orbitalChildren;

  protected Vector2D trajectory;
  protected boolean anchored;
  protected Vector2D solPos;
  protected double speed;
  protected double fuel;
  protected double mass;

  /** Other miscelanious fields */
  protected Set<ModuleSlot> slots;
  protected String name;

  /**
   * This function is called recursively from the {@link Star}, updating it's children
   * with an offset of {0, 0}. These children then move themselves according to the
   * position of their parent and then pass this offset to their children. These children
   * move themselves by the offset and then calculate their additional offset.
   * 
   * @param parentOffset
   */
  public void updateOrbits(Vector2D parentOffset, double radius, double step) {

    /** Calculate own offset */
    Vector2D offset = Tools.computeOrbit(orbitalParent.getSolPos(), radius, solPos, step);

    /** Add the parent offset on top */
    offset.add(parentOffset);

    /** Apply position to self */
    this.setSolPos(offset);

    /** Only propagate orbital offsets if self is Orbitable */
    if (!(this instanceof Orbitable))
      return;

    /** Call function for all children with the new (accumilated) offset */
    Tools.safe(orbitalChildren).forEach((i) -> i.updateOrbits(offset, radius, step));
  }
}
