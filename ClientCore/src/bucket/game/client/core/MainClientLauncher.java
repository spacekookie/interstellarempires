package bucket.game.client.core;

import java.awt.Image;
import java.awt.Toolkit;

import bucket.game.client.util.Settings;

import com.apple.eawt.Application;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;

/* 
 * Copyright (c) 2012 Katharina Fey
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
 * Main Launcher for the game. Calls the ScreenHandler to initialize the SplashScreen! Further functionality might be
 * added in the future
 * 
 * @author: Katharina
 */
public class MainClientLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = Settings.SUPERTITLE + " - " + Settings.VERSION_NUMBER;
		cfg.useGL20 = false;
		cfg.resizable = false;
		cfg.fullscreen = false;
		cfg.width = Settings.OLD_WIDTH;
		cfg.height = Settings.OLD_HEIGHT;
		cfg.initialBackgroundColor = Color.BLACK;

		/** Sets the Application Icon for different operating systems */
		if (System.getProperty("os.name").equals("Mac OS X")) {
			Application app = Application.getApplication();
			Image image = Toolkit.getDefaultToolkit().getImage("assets/icons/launcher.png");
			app.setDockIconImage(image);
		}

		new LwjglApplication(new ScreenHandler(), cfg);

	}
}
