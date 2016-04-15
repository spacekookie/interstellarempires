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
package io.lonelyrobot.empires.client.types;

import com.badlogic.gdx.graphics.Color;

/**
 * Advanced colour object to store hexadecimal colour values in it. It is also using the correct spelling of the word
 * "colour" that Americans have butchered for the last 250 years. You're welcome England!
 * 
 * @author: Katharina Fey <kookie@spacekookie.de>
 */
public class Colour extends Color {

  public static final Colour CLEAR = new Colour(0, 0, 0, 0);
  public static final Colour WHITE = new Colour(1, 1, 1, 1);
  public static final Colour BLACK = new Colour(0, 0, 0, 1);
  public static final Colour RED = new Colour(1, 0, 0, 1);
  public static final Colour GREEN = new Colour(0, 1, 0, 1);
  public static final Colour BLUE = new Colour(0, 0, 1, 1);
  public static final Colour LIGHT_GRAY = new Colour(0.75f, 0.75f, 0.75f, 1);
  public static final Colour GRAY = new Colour(0.5f, 0.5f, 0.5f, 1);
  public static final Colour DARK_GRAY = new Colour(0.25f, 0.25f, 0.25f, 1);
  public static final Colour PINK = new Colour(1, 0.68f, 0.68f, 1);
  public static final Colour ORANGE = new Colour(1, 0.78f, 0, 1);
  public static final Colour YELLOW = new Colour(1, 1, 0, 1);
  public static final Colour MAGENTA = new Colour(1, 0, 1, 1);
  public static final Colour CYAN = new Colour(0, 1, 1, 1);

  private float r, g, b, a;

  /**
   * Preferred constructor that uses Hex-Code value to create colour object
   * 
   * @param hexcode
   */
  @Deprecated
  public Colour(int hexcode) {
	super(hexcode);
  }

  /**
   * Use the preferred constructor for {@link #R2Colour(int)}
   * 
   * @param r
   * @param g
   * @param b
   * @param a
   */
  public Colour(float r, float g, float b, float a) {
	super(r, g, b, a);
	this.r = r;
	this.g = g;
	this.b = b;
	this.a = a;
  }

}
