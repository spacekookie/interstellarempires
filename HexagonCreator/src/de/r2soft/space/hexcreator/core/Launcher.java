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

package de.r2soft.space.hexcreator.core;

import de.r2soft.space.hexcreator.backend.ConfigurationPacker;
import de.r2soft.space.hexcreator.backend.RobotApplication;
import de.r2soft.space.hexcreator.utility.BaseSettings;
import de.r2soft.space.hexcreator.utility.IntVec2;

/**
 * Front application launcher working with my AWT based application layout.
 * 
 * @author Katharina
 * 
 */
public class Launcher {

	public static void main(String[] args) {

		/** Create configuration object */
		ConfigurationPacker cfp = new ConfigurationPacker();
		cfp.setSize(new IntVec2(BaseSettings.SIZE_WINDOW_DEFAULT.x, BaseSettings.SIZE_WINDOW_DEFAULT.y));
		cfp.setResizable(true);
		cfp.setFullscreen(false);
		cfp.setPacked(true);
		cfp.setApplicationTitle("Random Robot Softworks: Hexagon Editor");

		new RobotApplication(new AppManager(), cfp);

	}
}