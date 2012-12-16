package bucket.game.client.util;

/* 
 * Copyright (c) 2012 ***REMOVED***
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
 */

/**
 * Initializes the settings for the game. Also responsible for setting new
 * Settings.
 * 
 * @author: ***REMOVED***
 */
public class Settings {

	public static int oldWidth = 800;
	public static int oldHeight = 600;
	public static int newHeight;

	public static int getOldHeight() {
		return oldHeight;
	}

	public static void setOldHeight(int oldHeight) {
		Settings.oldHeight = oldHeight;
	}

	public static int getOldWidth() {
		return oldWidth;
	}

	public static void setOldWidth(int oldWidth) {
		Settings.oldWidth = oldWidth;
	}

	public static int getNewHeight() {
		return newHeight;
	}

	public static void setNewHeight(int newHeight) {
		Settings.newHeight = newHeight;
	}

	public static int getNewWidth() {
		return newWidth;
	}

	public static void setNewWidth(int newWidth) {
		Settings.newWidth = newWidth;
	}

	public static int newWidth;

}
