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

package io.lonelyrobot.empires.fw.game.map;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import io.lonelyrobot.empires.fw.game.obj.BaseObject;
import io.lonelyrobot.empires.fw.game.obj.Star;
import io.lonelyrobot.empires.fw.game.traits.Attackable;
import io.lonelyrobot.empires.fw.game.traits.Celestial;
import io.lonelyrobot.empires.fw.game.traits.Movable;
import io.lonelyrobot.empires.fw.game.traits.Types;
import io.lonelyrobot.empires.fw.game.utils.Logger;
import io.lonelyrobot.empires.fw.game.utils.Tree2D;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * A solarsystem is where most of the gameplay happens. It's where players build their
 * colonies, move their vessels around as well as enacting planetary movements that give
 * the game life!
 * 
 * The solar system keeps track of everything that is happening inside of itself and
 * enables both client and server to review information quickly thanks for KDTree lookup
 * structures.
 * 
 * It also provides functions to simulate orbits for celestial bodies as well as player
 * owned types inside certain regions (or in specific modes).
 * 
 * @author Katharina 'spacekookie' Fey <kookie@spacekookie.de>
 */
@ToString
@EqualsAndHashCode
public class SolarSystem {

  /** A solar system knows it's own map coordinate for quick nav lookup */
  private MapCoordinate location;

  /** A list of stars (because there are binary star systems */
  private @Getter Star star;

  /** Quick lookup tree for Celestial types */
  private @Getter Tree2D<Celestial> celestials;

  /** Quick lookup tree for player objects */
  private @Getter Tree2D<BaseObject> playerTree;

  /** The size of the jump gravity well */
  private @Getter double radius;

  { /** Intialiser block independant of constructor */
    celestials = new Tree2D<>();
    playerTree = new Tree2D<>();
  }

  /**
   * 
   * @param stars
   *          A list of star types that should be present at the centre of the system
   * @param size
   *          The size of the gravity well (in Mm) that limits FTL jumps to other systems
   * @param location
   *          The relative location in the galaxy for internal reference
   */
  public SolarSystem(Star star, MapCoordinate location) {

    /** Create new stars based on the types provided */
    this.star = star;
    this.location = location;

    /** Calculating the gravity well radius of the system */
    // FIXME: Do this **right**
    this.radius = star.getGravity();

    Logger.info("Created new solar system at " + location + " successfully...");
  }

  float angle = 0f;

  /**
   * Steps the solar system a single update cycle forward. Updates all units inside
   * itself, calculates new orbital positions and removes invalid (dead) objects from
   * object trees.
   * 
   * Is passed a delta-time between this update and the last one to smoothen certain
   * computations to take heavy server load delays into account.
   * 
   * @param delta
   */
  public void update(double delta) {
    Logger.debug("Stepping solar system " + location);

    /** First step all orbital objects around their parents. */
    star.updateOrbits(new Vector2D(0, 0));

    /** Then we update ship positions accordingly */
  }

  /**
   * Moves a unit inside the solar system and updates all references accordingly to avoid
   * future collision errors.
   * 
   * @param bo
   *          Object to be moved
   * @param offset
   *          Positional offset to be applied to the object
   */
  public void moveUnit(BaseObject bo, Vector2D offset) {

    if (!(bo instanceof Movable)) {
      Logger.error("Failed to move unmovable object!");
      return;
    }

    /** Update position reference in 2D search tree */
    Vector2D curr = playerTree.get(bo);
    playerTree.move(bo, curr.add(offset));

    /** Then update the object-own position reference */
    Logger.debug("[" + bo.getName() + "] Moving object by " + offset);
    ((Movable) bo).move(offset);
  }

  /**
   * A simple utility function that attacks a specific object inside this solar system.
   * This could either be as part of a fleet operation (large scale fight) or because of a
   * single strike or even environmental effects.
   * 
   * @param target
   *          The object to be dealt damage to
   * @param damage
   *          The amount of damage being dealt
   */
  public void attackUnit(BaseObject target, double damage) {

    if (!(target instanceof Attackable)) {
      Logger.error("Failed to attack non-attackable object!");
      return;
    }

    Logger.debug("[" + target.getName() + "] Applying " + damage + " damage");
    ((Attackable) target).attack(damage);
  }

  public void addUnit(BaseObject unit, Vector2D position) {

  }
}
