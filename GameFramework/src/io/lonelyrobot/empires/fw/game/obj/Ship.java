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

import io.lonelyrobot.empires.fw.game.config.ShipConfig;
import io.lonelyrobot.empires.fw.game.traits.Attackable;
import io.lonelyrobot.empires.fw.game.traits.Movable;
import io.lonelyrobot.empires.fw.game.traits.Ownable;

/**
 * This is a ship object. It holds data about a single ship either by itself or as part of
 * a fleet. When it is a part of a fleet the movable trait needs to become inactive to
 * avoid collisions on movement operations (the fleet moves as one entity).
 * 
 * Attackable and Ownable flags are still important in fleet combat and the movable trait
 * needs to be kept on the ship because single ships can break off from a fleet.
 * 
 * @author Katharina 'spacekookie' Fey <kookie@spacekookie.de>
 */
public class Ship extends BaseObject implements Ownable, Movable, Attackable {

  public Ship(ShipConfig cfg) {

  }

  @Override
  public void attack(double damage) {
    Attackable.attack(this, damage);
  }

  @Override
  public void move(Vector2D offset) {
    Movable.move(this, offset);
  }

  /*************************
   * 
   * Functions that map interface accessors to the {@link #BaseObject} owned super fields.
   * 
   *************************/

  public double speed() {
    return super.speed;
  }

  public void speed(double s) {
    super.speed = s;
  }

  public double fuel() {
    return super.fuel;
  }

  public void fuel(double f) {
    super.fuel = f;
  }

  public Vector2D trajectory() {
    return super.trajectory;
  }

  public void trajectory(Vector2D t) {
    super.trajectory = t;
  }

  public long combatID() {
    return super.combatID;
  }

}
