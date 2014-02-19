/* #########################################################################
 * Copyright (c) 2014 Random Robot Softworks
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

package de.r2soft.robotphysics.primatives;

/**
 * A bunch of constants to be used throughout the physics engine to set stuff
 * 
 * @author ***REMOVED***
 * 
 */
public final class R2P {

  /** Relationship flag to set bifunctionality */
  public static final int R2_BODY_BIFUNCTION = 0x000001;
  /** Relationship flag to set a child */
  public static final int R2_BODY_CHILD = 0x00002;
  /** Relationship flag to set a parent */
  public static final int R2_BODY_ADULT = 0x00003;

  /** Set physics calculation interval refresh rate */
  public static final int R2_WORLD_REFRESH_FAST = 0x00010;
  /** Set physics calculation interval refresh rate */
  public static final int R2_WORLD_REFRESH_MEDIUM = 0x00011;
  /** Set physics calculation interval refresh rate */
  public static final int R2_WORLD_REFRESH_SLOW = 0x00012;

}
