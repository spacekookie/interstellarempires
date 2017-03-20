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

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import io.lonelyrobot.empires.fw.game.obj.BaseObject;
import io.lonelyrobot.empires.fw.game.obj.Star;
import io.lonelyrobot.empires.fw.game.traits.Celestial;
import io.lonelyrobot.empires.fw.game.traits.Types;
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
  private @Getter Tree2D<Star> stars;

  /** Quick lookup tree for Celestial types */
  private @Getter Tree2D<Celestial> celestials;

  /** Quick lookup tree for player objects */
  private @Getter Tree2D<BaseObject> playerTree;

  /** The size of the jump gravity well */
  private @Getter double radius;

  { /** Intialiser block independant of constructor */
    stars = new Tree2D<>();
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
  public SolarSystem(Types.Stars stars[], double size, MapCoordinate location) {

    /** Create new stars based on the types provided */
    Set<Star> tmpStars = new HashSet<>();
    for (Types.Stars s : stars)
      tmpStars.add(new Star(s));

    // FIXME: Correctly calculate orbits for multi-star systems
    this.stars.insert(tmpStars.iterator().next(), new Vector2D(0, 0));
    this.radius = size;
    this.location = location;
  }

}
