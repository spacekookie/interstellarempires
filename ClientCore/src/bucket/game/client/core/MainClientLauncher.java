package bucket.game.client.core;

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

import bucket.game.client.gui.SplashScreen;
import com.badlogic.gdx.Game;

/**
 * Called when the game is created. Initializes the SplashScreen (Basically the
 * Main Menu) and also handles disposal of that screen. Further functions may be
 * added in the future
 * 
 * @author: Katharina
 */
public class MainClientLauncher extends Game {

	@Override
	public void create() {
		setScreen(new SplashScreen());
	}

	@Override
	public void dispose() {
		new SplashScreen().dispose();
	}

}