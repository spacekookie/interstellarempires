package io.lonelyrobot.empires.fw.game.obj;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

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
