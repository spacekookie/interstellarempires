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

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import io.lonelyrobot.empires.fw.game.traits.Attackable;
import io.lonelyrobot.empires.fw.game.traits.Movable;
import io.lonelyrobot.empires.fw.game.traits.Ownable;

/**
 * This is a fleet object. It represents a collection of ships in space that travel, warp
 * and fight together. As such all behaviour implemented by traits needs to be ignored and
 * passed onto each member of the fleet while the interface is the same regardless of
 * combat is done on a single ship or a fleet.
 * 
 * @author Katharina 'spacekookie' Fey <kookie@spacekookie.de>
 */
public class Fleet extends BaseObject implements Ownable, Movable, Attackable {

  public Fleet() {

  }

  @Override
  public long combatID() {
    return 0;
  }

  @Override
  public void attack(double damage) {}

  @Override
  public Vector2D trajectory() {
    return null;
  }

  @Override
  public double speed() {
    return 0;
  }

  @Override
  public double fuel() {
    return 0;
  }

  @Override
  public void move(Vector2D offset) {}

  @Override
  public void trajectory(Vector2D t) {}

  @Override
  public void speed(double s) {}

  @Override
  public void fuel(double f) {}

}
