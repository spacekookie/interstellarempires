package io.lonelyrobot.empires.fw.game.traits;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

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

  /** @return current speed */
  public double speed();

  /** @return current fuel levels */
  public double fuel();

  /**
   * Moves the object by an offset to the current position.
   * 
   * @param offset
   */
  public void move(Vector2D offset);

  static void move(Object o, Vector2D offset) {

  }
}
