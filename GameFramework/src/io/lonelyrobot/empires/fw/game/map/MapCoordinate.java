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

import lombok.Getter;

/**
 * This is a utility class which maps a tile against the broader aspect of the map around
 * it. Most notably it has a function that can get the accurate distance between any two
 * map coordinates, according to the hex tile map around it.
 * 
 * It can also take a map and thus soveregnity or other natural phenomenon into account.
 * 
 * @author Katharina 'spacekookie' Fey <kookie@spacekookie.de>
 *
 */
public class MapCoordinate {
  private @Getter Vector2D position;

  public MapCoordinate(int x, int y) {
    this.position = new Vector2D(x, y);
  }

  public String toString() {
    return "Sol [" + ((int) position.getX()) + " | " + ((int) position.getY()) + "]";
  }
}
