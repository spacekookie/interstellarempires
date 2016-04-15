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

package io.lonelyrobot.empires.framework.objects.modules;

/**
 * A more advanced module that allows for shields and in the future maybe other stuff.
 * 
 * @author Katharina
 * 
 */
public abstract class AdvancedModule extends BaseModule {

  /** Defensive values for the modules */
  private int shields;

  /** @return the amount of shields the unit has left */
  public int getShields() {
	return shields;
  }

  /** Sets the new or initial amount of shields. Should be called in unit creation and after combat */
  public void setShields(int shields) {
	this.shields = shields;
  }

}