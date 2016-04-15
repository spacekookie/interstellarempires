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
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;

import de.r2soft.empires.client.resources.Values;

/**
 * Main Launcher for the game. Calls the ScreenHandler to initialize the SplashScreen! Further functionality might be
 * added in the future
 * 
 * @author: ***REMOVED***
 */
public class MainClientLauncher {
  public static void main(String[] args) {
	LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
	cfg.title = Values.SUPERTITLE + " - " + Values.VERSION_NUMBER;

	final Options gameOptions = new Options();
	// gameOptions.addOption("fullscreen", false, "Launch as fullscreen");

	if (gameOptions.hasOption("fullscreen")) {
	  System.out.println("Attempting to launch as fullscreen Application. This is experimental as of 1.3+");
	  GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

	  cfg.width = gd.getDisplayMode().getWidth();
	  cfg.height = gd.getDisplayMode().getHeight();
	  cfg.fullscreen = true;

	  Values.NEW_WIDTH = cfg.width;
	  Values.NEW_HEIGHT = cfg.height;
	}
	else {
	  cfg.fullscreen = false;
	  cfg.width = Values.OLD_WIDTH;
	  cfg.height = Values.OLD_HEIGHT;
	}

	cfg.useGL30 = true;
	cfg.resizable = false;
	cfg.initialBackgroundColor = Color.BLACK;

	new LwjglApplication(GameCore.getInstance(), cfg);
  }
}