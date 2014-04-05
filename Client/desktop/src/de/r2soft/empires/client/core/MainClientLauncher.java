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
package de.r2soft.empires.client.core;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;

import com.apple.eawt.Application;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;

import de.r2soft.empires.client.resources.Values;

/**
 * Main Launcher for the game. Calls the ScreenHandler to initialise the SplashScreen! Further functionality might be added in the future
 * 
 * @author: Katharina
 */
public class MainClientLauncher {
  public static void main(String[] args) {
	LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
	cfg.title = Values.SUPERTITLE + " - " + Values.VERSION_NUMBER;

	// TODO: This throws an NPE if no argument is passed
	/** checks if game should be launched in full screen (EXPERIMENTAL AS OF ALPHA 1.3) */
	if (args[0] == "--fullscreen") {
	  cfg.fullscreen = true;
	  GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	  int width = gd.getDisplayMode().getWidth();
	  int height = gd.getDisplayMode().getHeight();

	  cfg.width = width;
	  cfg.height = height;

	  Values.NEW_WIDTH = width;
	  Values.NEW_HEIGHT = height;

	}
	else {
	  cfg.fullscreen = false;
	  cfg.width = Values.OLD_WIDTH;
	  cfg.height = Values.OLD_HEIGHT;
	}

	cfg.useGL20 = true;
	cfg.resizable = false;
	cfg.initialBackgroundColor = Color.BLACK;

	/** Sets the Application Icon for different operating systems */
	// TODO: FIX THIS
	// if (System.getProperty("os.name").equals("Mac OS X")) {
	// Application app = Application.getApplication();
	// Image image = Toolkit.getDefaultToolkit().getImage("assets/icons/launcher.png");
	// app.setDockIconImage(image);
	// }

	new LwjglApplication(GameCore.getInstance(), cfg);
  }
}