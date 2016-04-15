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
package io.lonelyrobot.empires.client.graphics;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Intro animation.
 * 
 * @author: Katharina
 */
public class IntroAnimator implements TweenAccessor<Sprite> {

  public static final int ALPHA = 1;

  @Override
  public int getValues(Sprite target, int tweenType, float[] returnValues) {

	switch (tweenType) {
	case ALPHA:
	  returnValues[0] = target.getColor().a;
	  return 1;

	default:
	  return 0;
	}

  }

  @Override
  public void setValues(Sprite target, int tweenType, float[] newValues) {

	switch (tweenType) {
	case ALPHA:
	  target.setColor(1, 1, 1, newValues[0]);
	  break;

	default:
	  break;
	}

  }

}
