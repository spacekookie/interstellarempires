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

package de.r2soft.r2physics.primatives;

/**
 * A bunch of constants to be used throughout the physics engine to set global parameters for calculations and relations
 * 
 * @author Katharina
 * 
 */
public final class R2P {

  /** Relationship flag to set bifunctionality */
  public static final int R2_BODY_BIFUNCTION = 0x00001;
  /** Relationship flag to set a child */
  public static final int R2_BODY_CHILD = 0x00002;
  /** Relationship flag to set a parent */
  public static final int R2_BODY_ADULT = 0x00003;
  /** Set orbital direction to Clockwise. Default is counterclockwise. */
  public static final int R2_BODY_DIRECTION_OVERRIDE = 0x00004;

  /** Set physics calculation interval refresh rate */
  public static final int R2_WORLD_REFRESH_FAST = 0x00010;
  /** Set physics calculation interval refresh rate */
  public static final int R2_WORLD_REFRESH_MEDIUM = 0x00011;
  /** Set physics calculation interval refresh rate */
  public static final int R2_WORLD_REFRESH_SLOW = 0x00012;

  /** Gravitational constant */
  public static final double R2_PHYSICS_GRAVITY = 6.674E-11;
  /** Earth mass */
  public static final double R2_PHYSICS_MASS_EARTH = 5.97219E24;
  /** Moon mass */
  public static final double R2_PHYSICS_MASS_MOON = 7.34767309E22;
  /** FUn planet mass */
  public static final double R2_PHYSICS_MASS_FUN_ROCK = 60000;
  /** FUn planet mass */
  public static final double R2_PHYSICS_MASS_FUN_MASSIVE = 1.3E8;

  /**
   * YOU ARE ALLOWED TO CHANGE THIS VALUE. DO NOT CHANGE ANY VALUES OTHER THAN THIS ONE HERE! Pixel to RL-distance scale. This means that
   * 1AU represents "X" pixels on the screen at Camera zoom scale = 1. With zoom this is representative of WORLD COORDINATES! For Physics
   * purposes this distance will represent 149597870700 KM of distance.
   */
  public static long R2_DIST_SCALE = 650;

  /** Astronomical Unit in meters */
  public static final long R2_DIST_AU = 149597870700L;

}
