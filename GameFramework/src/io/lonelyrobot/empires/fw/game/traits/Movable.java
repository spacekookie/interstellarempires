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

package io.lonelyrobot.empires.fw.game.traits;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import io.lonelyrobot.empires.fw.game.obj.BaseObject;

/**
 * This is a trait that marks an object as movable. That means that any child object has
 * functions to be moved from external inputs (not the internal movement of, say, a
 * physics based object orbiting or being "on rails".
 * 
 * It also implements a static move function that can be called by child objects that
 * don't want to duplicate functionality
 * 
 * @author Katharina 'spacekookie' Fey <kookie@spacekookie.de>
 */
public interface Movable {

  /** @return current trajectory */
  public Vector2D trajectory();

  public void trajectory(Vector2D t);

  /** @return current speed */
  public double speed();

  public void speed(double s);

  /** @return current fuel levels */
  public double fuel();

  public void fuel(double f);

  /**
   * Moves the object by an offset to the current position.
   * 
   * @param offset
   */
  public void move(Vector2D offset);

  public static void move(BaseObject object, Vector2D offset) {

    /** Check if not called with invalid payload */
    if (!(object instanceof Movable))
      return;

    /** Position is actually held on BaseObject */
    Vector2D pos = object.getSolPos();
    object.setSolPos(pos.add(offset));
  }
}
