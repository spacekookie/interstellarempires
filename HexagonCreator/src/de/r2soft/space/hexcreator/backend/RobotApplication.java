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

import de.r2soft.space.hexcreator.utility.BaseSettings;

/**
 * Back-end manager to call methods in the {@link Paintings interface}
 * 
 * @author Katharina
 * 
 */
public class RobotApplication {

	BackendManager app;

	/** Main application constructor */
	public RobotApplication(BackendManager app, ConfigurationPacker cfp) {
		BaseSettings.SIZE_WINDOW_USER.x = cfp.getX();
		BaseSettings.SIZE_WINDOW_USER.y = cfp.getY();
		BaseSettings.APPLICATION_TITLE = cfp.getApplicationTitle();
		BaseSettings.APPLICATION_FULLSCREEN = cfp.isFullscreen();
		BaseSettings.APPLICATION_RESIZABLE = cfp.isResizable();
		this.app = app;
		app.onCreate();
		app.onResize(BaseSettings.SIZE_WINDOW_USER.x, BaseSettings.SIZE_WINDOW_USER.y);

		if (cfp.isPacked())
			app.getShell().pack();

		painting();
	}

	/**
	 * Main application loop. Will call the painting method in a loop. Will @break when {@link dying
	 * == true}. Will end the application.
	 * 
	 * 
	 * @author Katharina
	 */
	public void painting() {
		while (!app.getShell().isDisposed()) {
			if (!app.getDisplay().readAndDispatch())
				app.getDisplay().sleep();
			app.onPaint();

			if (app.isDying()) {
				app.onDestroy();
				break;
			}
		}

	}

}
