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

package de.r2soft.space.hexcreator.utility;

public class BaseSettings {

	public static final IntVec2 SIZE_WINDOW_MINIMUM = new IntVec2(800, 550);
	public static final IntVec2 SIZE_WINDOW_DEFAULT = new IntVec2(1280, 720);

	/** User launch settings */
	public static IntVec2 SIZE_WINDOW_USER = new IntVec2();
	public static String APPLICATION_TITLE = null;
	public static boolean APPLICATION_RESIZABLE = true;
	public static boolean APPLICATION_FULLSCREEN = false;

}
