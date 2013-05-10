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

/**
 * Basic interface for the application context or screens. Implementing objects are called
 * Paintings. The same interface is being used by the {@link BackendManager}
 * 
 * @author ***REMOVED***
 * 
 */
public interface Paintings {

	/** Called when Window is first created. Initialize your assets here */
	public void onCreate();

	/** Called every time the window size changes. Should trigger the rebuild of the UI */
	public void onResize(int x, int y);

	/** Called in the render loop. Should contain all dynamic rendering */
	public void onPaint();

	/** Called when Window is closed. Dispose of all your assets here */
	public void onDestroy();

}
