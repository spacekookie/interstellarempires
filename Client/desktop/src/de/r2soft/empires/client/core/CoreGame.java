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

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;

import de.r2soft.empires.client.screens.utilities.LoginScreen;
import de.r2soft.empires.client.settings.BaseSettings;

/**
 * 
 * Called when the game is created. Handles all Screen activity for the game. Further functionality might be added in the future
 * 
 * @author: Katharina
 */
public class CoreGame extends R2Game {

  private static CoreGame handler;
  private Music music;
  private Preferences prefs;

  /**
   * 
   * Returns The Games screenhandler to start new screens from actors, groups and sub-classes. Accessed in a static way.
   * 
   * @return The main Screenhandler.
   */
  public static CoreGame getInstance() {
	return handler;
  }

  /**
   * Called every time something major is being updated such as screen resolution, settings or big server syncs.
   * 
   * @author Katharina
   */
  public void onUpdate() {
	if (prefs.getBoolean(BaseSettings.PREFERENCE_PLAY_MUSIC)) {
	  if (!music.isPlaying()) {
		music.play();
		music.setLooping(true);
		music.setVolume(0.95f);
	  }
	}
	else {
	  if (music.isPlaying())
		music.stop();
	}
  }

  @Override
  public void create() {
	handler = this;

	music = Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/music/intro_music.mp3"));
	prefs = Gdx.app.getPreferences(BaseSettings.PREFERENCE_FILE_NAME);

	if (!prefs.contains(BaseSettings.PREFERENCE_PLAY_MUSIC))
	  prefs.putBoolean(BaseSettings.PREFERENCE_PLAY_MUSIC, true);

	if (!prefs.contains(BaseSettings.PREFERENCE_SKIP_INTRO))
	  prefs.putBoolean(BaseSettings.PREFERENCE_SKIP_INTRO, true);

	onUpdate();

	// setScreen(new SolMapScreen(null));
	setScreen(new LoginScreen());

	// if (!prefs.getBoolean(BaseSettings.PREFERENCE_SKIP_INTRO))
	// setScreen(new IntroductionScreen());
	// else
  }

  @Override
  public void dispose() {
	super.dispose();
	if (prefs.getBoolean(BaseSettings.PREFERENCE_PLAY_MUSIC))
	  music.stop();
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

	if (prefs.getBoolean(BaseSettings.PREFERENCE_PLAY_MUSIC))
	  music.pause();
  }

  @Override
  public void resume() {
	super.resume();
	if (prefs.getBoolean(BaseSettings.PREFERENCE_PLAY_MUSIC))
	  music.play();
  }

}
