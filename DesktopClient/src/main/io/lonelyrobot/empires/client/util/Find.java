/* #########################################################################
 * Copyright (c) 2013 Random Robot Softworks
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
package io.lonelyrobot.empires.client.util;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import io.lonelyrobot.empires.framework.types.IntVec2;

/**
 * 
 * Pixel coordination utility class.
 * 
 * @author ***REMOVED***
 * 
 */
public class Find {

  public static HashMap<IntVec2, Vector2> hexmap;

  /** @return: the screens center position */
  public static Vector2 getCenter() {
	return new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
  }

  /**
   * Sets the standard pixel coordinates for tiles on the HexMap
   * 
   * @param size
   *          Tiles pixel size to adjust pixel coordinates.
   */
  public static void setHexMap(float size) {
	hexmap.put(new IntVec2(0, 0), new Vector2());
  }
}
