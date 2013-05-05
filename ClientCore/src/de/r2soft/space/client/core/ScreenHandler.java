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


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

import de.r2soft.space.client.screens.LoginScreen;
import de.r2soft.space.client.screens.MenuScreen;
import de.r2soft.space.client.screens.TweenScreen;
import de.r2soft.space.client.settings.Settings;
import de.r2soft.space.client.util.ResPack;

/**
 * 
 * Called when the game is created. Handles all Screen activity for the game. Further functionality might be added in the future
 * 
 * @author: Katharina
 */
public class ScreenHandler extends Game {

	private static ScreenHandler handler;

	/**
	 * 
	 * Returns The Games screenhandler to start new screens from actors, groups and sub-classes. Accessed in a static way.
	 * 
	 * @return The main Screenhandler.
	 */
	public static ScreenHandler getInstance() {
		return handler;
	}

	private void loadAssets() {
		ResPack pack = new ResPack();
		Gdx.app.log(Settings.LOG, "Textures loaded");
	}

	@Override
	public void create() {
		handler = this;

		this.loadAssets();

		if (!Settings.skipIntro)
			setScreen(new TweenScreen(this));
		else
			setScreen(new LoginScreen(this));
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
