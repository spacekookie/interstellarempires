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

import bucket.game.client.screens.MenuScreen;
import bucket.game.client.screens.TweenScreen;
import bucket.game.client.settings.Settings;

import com.badlogic.gdx.Game;

/**
 * 
 * Called when the game is created. Handles all Screen activity for the game. Further
 * functionality might be added in the future
 * 
 * @author: Katharina
 */
public class ScreenHandler extends Game {

	static ScreenHandler handler;

	/**
	 * 
	 * Returns The Games screenhandler to start new screens from actors, groups and
	 * sub-classes. Accessed in a static way.
	 * 
	 * @return The main Screenhandler
	 */
	public static ScreenHandler getHandler() {
		return handler;
	}

	@Override
	public void create() {
		handler = this;

		if (!Settings.skipIntro)
			setScreen(new TweenScreen(this));
		else
			setScreen(new MenuScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

}
