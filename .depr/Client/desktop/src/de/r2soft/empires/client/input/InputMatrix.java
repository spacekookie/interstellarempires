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

package de.r2soft.empires.client.input;

import de.r2soft.empires.client.core.GameCore;
import de.r2soft.empires.client.graphics.R2Screen;

/**
 * A singleton class part of the core game to manage input depending on the current game state. Will be called through main game loop.
 * 
 * @author Katharina <kookie@spacekookie.de>
 * 
 */
public class InputMatrix {
  private static InputMatrix matrix = null;
  private static GameCore master = GameCore.getInstance();
  private R2Screen last;
  private R2Screen slave;

  private InputMatrix() {

  }

  public static InputMatrix getInstance() {
	if (matrix == null)
	  matrix = new InputMatrix();
	return matrix;
  }

  public void updateSlave() {
	if (!master.isOverlaysEmpty()) {
	  if (last == null || !last.equals(master.peekOverlays()))
		master.peekOverlays().setInputFocus();
	  last = master.peekOverlays();
	}
	else {
	  if (last == null || !last.equals(master.getScreen())) {
		master.getScreen().setInputFocus();
		last = master.getScreen();
	  }

	}
  }

  public void setSlave(R2Screen slave) {
	this.slave = slave;
  }

  public R2Screen getSlave() {
	return slave;
  }
}
