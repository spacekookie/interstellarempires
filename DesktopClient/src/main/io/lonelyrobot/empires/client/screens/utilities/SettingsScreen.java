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
package io.lonelyrobot.empires.client.screens.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import io.lonelyrobot.empires.client.core.GameCore;
import io.lonelyrobot.empires.client.graphics.R2Screen;
import io.lonelyrobot.empires.client.resources.Assets;
import io.lonelyrobot.empires.client.resources.Values;
import io.lonelyrobot.empires.client.screens.gameplay.HexMapScreen;

/**
 * No longer use this Screen. Use the new R2Overlay implementation instead SettingsOverlay
 * 
 * This screen will enable the user to change stuff about their game client
 * 
 * @author ***REMOVED***
 * 
 */
@Deprecated
public class SettingsScreen extends R2Screen {

  /** Container and Backends */
  private Stage stage;
  private TextButton button;
  private Table table;
  private Table navigation;
  private Preferences prefs;

  private CheckBox skipIntro, playMusic;

  public SettingsScreen() {
	Gdx.graphics.setTitle(Values.SUPERTITLE + " - " + Values.VERSION_NUMBER + " - " + Values.SCREENTITLE_SETTINGS);
	prefs = Gdx.app.getPreferences(Values.PREFERENCE_FILE_NAME);

	skipIntro = new CheckBox("Skip the intro", Assets.R2_UI_SKIN);
	playMusic = new CheckBox("Play background music", Assets.R2_UI_SKIN);

	if (prefs.contains(Values.PREFERENCE_SKIP_INTRO))
	  skipIntro.setChecked(prefs.getBoolean(Values.PREFERENCE_SKIP_INTRO));

	if (prefs.contains(Values.PREFERENCE_PLAY_MUSIC))
	  playMusic.setChecked(prefs.getBoolean(Values.PREFERENCE_PLAY_MUSIC));
  }

  @Override
  public void render(float delta) {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	stage.act(delta);
	stage.draw();

	if (skipIntro.isChecked()) {
	  prefs.putBoolean(Values.PREFERENCE_SKIP_INTRO, true);
	}
	else {
	  prefs.putBoolean(Values.PREFERENCE_SKIP_INTRO, false);
	}

	if (playMusic.isChecked()) {
	  prefs.putBoolean(Values.PREFERENCE_PLAY_MUSIC, true);
	}
	else {
	  prefs.putBoolean(Values.PREFERENCE_PLAY_MUSIC, false);
	}

	skipIntro.setChecked(prefs.getBoolean(Values.PREFERENCE_SKIP_INTRO));
	playMusic.setChecked(prefs.getBoolean(Values.PREFERENCE_PLAY_MUSIC));

	/** What do we do after we're done in the bathroom? :) */
	prefs.flush();

  }

  @Override
  public void setInputFocus() {
	Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void resize(int width, int height) {
	if (stage == null)
	  stage = new Stage(new StretchViewport(width, height));
	stage.clear();

	// Collect touchdown events
	stage.getViewport().update(width, height, true);

	navigation = new Table();
	navigation.setFillParent(true);
	stage.addActor(navigation);
	button = new TextButton("Back to main screen", Assets.UI_SKIN);

	navigation.add(button).width(Values.SIZE_UI_BUTTON_NAVIGON);
	navigation.row();

	navigation.top().right();

	stage.addActor(navigation);

	button.addListener(new InputListener() {
	  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		return true;
	  }

	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		GameCore.getInstance().update();
		GameCore.getInstance().setScreen(new HexMapScreen(prefs.getString(Values.PREFERENCE_SAVED_USER_NAME)));
	  }
	});

	/** Settings Table */

	table = new Table();
	table.setFillParent(true);

	table.add(skipIntro).left();
	table.row();
	table.add(playMusic).left();
	table.row();
	table.center();
	stage.addActor(table);

	Table credits = new Table(Assets.UI_SKIN);
	credits.setBackground("default-window");
	credits.setSize(325, 125);
	credits.bottom().left();
	credits.add(new Label("Credits", Assets.UI_SKIN)).colspan(2).center();
	credits.row();
	credits.add(new Label("Leander Sabel", Assets.UI_SKIN)).left().width(150);
	credits.add(new Label("Coding", Assets.UI_SKIN)).left();
	credits.row();
	credits.add(new Label("***REMOVED***", Assets.UI_SKIN)).left().width(150);
	credits.add(new Label("Coding & Graphics", Assets.UI_SKIN)).left();
	credits.row();
	credits.add(new Label("Steve Teufel", Assets.UI_SKIN)).left().width(150);
	credits.add(new Label("Sounds & Music", Assets.UI_SKIN)).left();
	credits.row();
	credits.add(new Label("(c)2013 Random Robot Softworks", Assets.UI_SKIN)).colspan(2).left();
	credits.row();
	stage.addActor(credits);

  }

  @Override
  public void show() {
	// TODO Auto-generated method stub

  }

  @Override
  public void hide() {
	// TODO Auto-generated method stub

  }

  @Override
  public void pause() {
	// TODO Auto-generated method stub

  }

  @Override
  public void resume() {
	// TODO Auto-generated method stub

  }

  @Override
  public void dispose() {
	// TODO Auto-generated method stub

  }

}
