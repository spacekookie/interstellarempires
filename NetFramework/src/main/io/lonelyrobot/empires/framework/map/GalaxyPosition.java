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

package io.lonelyrobot.empires.framework.map;

import io.lonelyrobot.empires.framework.types.IntVec2;

public class GalaxyPosition {

  private IntVec2 pos;

  public GalaxyPosition() {
	pos = new IntVec2();
  }

  public GalaxyPosition(int x, int y) {
	pos = new IntVec2(x, y);
  }

  public GalaxyPosition(IntVec2 pos) {
	this.pos = pos;
  }

  public IntVec2 getPos() {
	return pos;
  }

  public void setPos(IntVec2 pos) {
	this.pos = pos;
  }

  @Override
  public boolean equals(Object obj) {
	if (obj == null)
	  return false;
	if (obj instanceof GalaxyPosition)
	  return (this.pos.x == ((GalaxyPosition) obj).getPos().x && this.pos.y == ((GalaxyPosition) obj).getPos().y);
	else
	  return false;
  }

  @Override
  public String toString() {
	return pos.x + "-" + pos.y;
  }
}
