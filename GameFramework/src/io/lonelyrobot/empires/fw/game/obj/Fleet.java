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

}
