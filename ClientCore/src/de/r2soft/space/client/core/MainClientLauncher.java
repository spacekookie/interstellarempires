/* 
 * Copyright (c) 2013 Katharina Fey
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
package de.r2soft.space.client.core;

import java.awt.Image;
import java.awt.Toolkit;

import com.apple.eawt.Application;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;

import de.r2soft.space.client.settings.Settings;
import de.r2soft.space.client.types.IntVec2;
import de.r2soft.space.client.util.ResPack;
import de.r2soft.space.framework.map.SolarSystem;
import de.r2soft.space.framework.objects.Star;
import de.r2soft.space.framework.objects.Unit;
import de.r2soft.space.framework.objects.Star.STARTYPE;
import de.r2soft.space.framework.objects.Unit.TYPE;
import de.r2soft.space.framework.players.Player;


/**
 * Main Launcher for the game. Calls the ScreenHandler to initialise the
 * SplashScreen! Further functionality might be added in the future
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
		if (System.getProperty("os.name").equals("Mac OS X"))
			{
				Application app = Application.getApplication();
				Image image = Toolkit.getDefaultToolkit().getImage("assets/icons/launcher.png");
				app.setDockIconImage(image);
			}

		new LwjglApplication(new ScreenHandler(), cfg);
		MainClientLauncher m = new MainClientLauncher();
		m.initSystems();
	}

	/** Creating two example solar systems */

	static SolarSystem s1;
	static SolarSystem s2;
	static SolarSystem s3;

	static Unit sampleFleet;

	void initSystems() {
		s1 = new SolarSystem();
		s1.setStar(new Star());
		s1.getStar().setType(STARTYPE.BROWNDWARF);
		s1.setRadius(ResPack.SIZE_SYSTEM_BROWN_DWARF);
		s1.setSovereignty(null);

		/** 2nd system */

		s2 = new SolarSystem();
		s2.setStar(new Star());
		s2.getStar().setType(STARTYPE.BLUEGIANT);
		s2.setRadius(ResPack.SIZE_SYSTEM_BLUE_GIANT);
		s2.setSovereignty(null);

		/** 3nd system */

		s3 = new SolarSystem();
		s3.setStar(new Star());
		s3.getStar().setType(STARTYPE.REDDWARF);
		s3.setRadius(ResPack.SIZE_SYSTEM_RED_DWARF);
		s3.setSovereignty(null);

		/** Sample fleet */
		sampleFleet = new Unit();
		sampleFleet.setType(TYPE.FLEET);
		sampleFleet.setSpeed(50.0);
		sampleFleet.setFlag("SampleFleet");
		sampleFleet.setClaim(new Player("KateTheAwesome"));

		s2.addSingleUnit(sampleFleet);

	}

	public static SolarSystem getSystemWithID(IntVec2 systemID) {
		if (systemID.equals(new IntVec2(0, 0)))
			{
				return s1;
			} else if (systemID.equals(new IntVec2(1, 0)))
			{
				return s2;
			} else
			{
				return s3;
			}
	}
}