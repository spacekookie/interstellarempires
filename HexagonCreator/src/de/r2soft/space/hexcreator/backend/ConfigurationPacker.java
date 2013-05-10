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

package de.r2soft.space.hexcreator.backend;

import de.r2soft.space.hexcreator.utility.IntVec2;

/**
 * Set of variables that the user can use to manipulate the workings of their current application.
 * 
 * @author Katharina
 * 
 */
public class ConfigurationPacker {

	private IntVec2 size = new IntVec2();
	private String applicationTitle;
	private boolean resizable;
	private boolean fullscreen;
	private boolean packed;

	public boolean isPacked() {
		return packed;
	}

	/**
	 * Will wrap the window to it's smallest possible size based on it's content. Will wrap around a
	 * minimum size set.
	 */
	public void setPacked(boolean packed) {
		this.packed = packed;
	}

	public IntVec2 getSize() {
		return new IntVec2(size.x, size.y);
	}

	public void setSize(IntVec2 vec) {
		this.size.x = vec.x;
		this.size.y = vec.y;
	}

	public int getX() {
		return size.x;
	}

	public void setX(int sizeX) {
		this.size.x = sizeX;
	}

	public int getY() {
		return size.y;
	}

	public void setY(int sizeY) {
		this.size.y = sizeY;
	}

	public boolean isResizable() {
		return resizable;
	}

	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

	public boolean isFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	public String getApplicationTitle() {
		return applicationTitle;
	}

	public void setApplicationTitle(String applicationTitle) {
		this.applicationTitle = applicationTitle;
	}

}
