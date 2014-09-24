/* #########################################################################
 * Copyright (c) 2014 RANDOM ROBOT SOFTWORKS
 * (See @authors file)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ######################################################################### */
package de.r2soft.empires.client.types;

/**
 * Advanced colour object to store hexadecimal colour values in it. It is also using the correct spelling of the word
 * "colour" that Americans have butchered for the last 250 years. You're welcome England!
 * 
 * @author: ***REMOVED*** <***REMOVED***>
 */
public class R2Colour {

  public static final R2Colour CLEAR = new R2Colour(0, 0, 0, 0);
  public static final R2Colour WHITE = new R2Colour(1, 1, 1, 1);
  public static final R2Colour BLACK = new R2Colour(0, 0, 0, 1);
  public static final R2Colour RED = new R2Colour(1, 0, 0, 1);
  public static final R2Colour GREEN = new R2Colour(0, 1, 0, 1);
  public static final R2Colour BLUE = new R2Colour(0, 0, 1, 1);
  public static final R2Colour LIGHT_GRAY = new R2Colour(0.75f, 0.75f, 0.75f, 1);
  public static final R2Colour GRAY = new R2Colour(0.5f, 0.5f, 0.5f, 1);
  public static final R2Colour DARK_GRAY = new R2Colour(0.25f, 0.25f, 0.25f, 1);
  public static final R2Colour PINK = new R2Colour(1, 0.68f, 0.68f, 1);
  public static final R2Colour ORANGE = new R2Colour(1, 0.78f, 0, 1);
  public static final R2Colour YELLOW = new R2Colour(1, 1, 0, 1);
  public static final R2Colour MAGENTA = new R2Colour(1, 0, 1, 1);
  public static final R2Colour CYAN = new R2Colour(0, 1, 1, 1);

  private float r, g, b, a;

  /**
   * Preferred constructor that uses Hex-Code value to create colour object
   * 
   * @param hexcode
   */
  public R2Colour(int hexcode) {

  }

  /**
   * Use the preferred constructor for {@link #R2Colour(int)}
   * 
   * @param r
   * @param g
   * @param b
   * @param a
   */
  @Deprecated
  public R2Colour(float r, float g, float b, float a) {
	this.r = r;
	this.g = g;
	this.b = b;
	this.a = a;
  }

}
