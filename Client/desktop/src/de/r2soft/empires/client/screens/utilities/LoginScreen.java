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
package de.r2soft.empires.client.screens.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.r2soft.empires.client.core.GameCore;
import de.r2soft.empires.client.graphics.R2Screen;
import de.r2soft.empires.client.resources.Assets;
import de.r2soft.empires.client.resources.Values;
import de.r2soft.empires.client.screens.gameplay.HexMapScreen;

public class LoginScreen extends R2Screen {

  /** UI elements */
  private Stage stage;
  private Table intro, outro;
  private TextField userField, passField;
  private TextButton login, exit, back;
  private CheckBox saveUser;
  private Preferences prefs;

  /** Background stuff */
  private String name_clear, password_clear;

  public LoginScreen() {
	prefs = Gdx.app.getPreferences(Values.PREFERENCE_FILE_NAME);
	login = new TextButton("LOGIN", Assets.UI_SKIN);
	passField = new TextField("", Assets.UI_SKIN);
	userField = new TextField("", Assets.UI_SKIN);
	saveUser = new CheckBox("Save username?", Assets.UI_SKIN);
	back = new TextButton("Back to Intro", Assets.UI_SKIN);

	if (prefs.contains(Values.PREFERENCE_SAVE_USERNAME)) {
	  userField.setText(prefs.getString(Values.PREFERENCE_SAVED_USER_NAME));
	  saveUser.setChecked(prefs.getBoolean(Values.PREFERENCE_SAVE_USERNAME));
	}
  }

  @Override
  public void resize(int w, int h) {
	if (stage == null)
	  stage = new Stage(w, h, true);
	stage.clear();

	intro = new Table();
	intro.setFillParent(true);
	outro = new Table();
	outro.setFillParent(true);

	// Exiting the game
	exit = new TextButton("Exit Game", Assets.UI_SKIN);
	outro.add(exit).width(Values.SIZE_UI_BUTTON_NAVIGON);
	outro.row();
	outro.add(back).width(Values.SIZE_UI_BUTTON_NAVIGON);
	outro.top().left();

	userField.setMessageText("Your username");
	passField.setMessageText("Your password");
	passField.setPasswordCharacter('*');
	passField.setPasswordMode(true);

	intro.add(userField).width(Values.SIZE_UI_FIELD_CONTENT);
	intro.row();
	intro.add(passField).width(Values.SIZE_UI_FIELD_CONTENT);
	intro.row();
	intro.add(login).width(Values.SIZE_UI_FIELD_CONTENT);
	intro.row();
	intro.add(saveUser);
	intro.row();

	login.addListener(new ClickListener() {
	  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		return true;
	  }

	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		scheduleLogin();
	  }
	});

	exit.addListener(new InputListener() {
	  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		return true;
	  }

	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		Gdx.app.exit();
	  }
	});

	back.addListener(new InputListener() {
	  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		return true;
	  }

	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		GameCore.getInstance().setScreen(new IntroductionScreen());
	  }
	});

	stage.addActor(outro);
	stage.addActor(intro);
  }

  public void render(float delta) {

	Gdx.gl.glClearColor(0, 0, 0, 1); // Paint it black
	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

	stage.act(delta);
	stage.draw();

	if (saveUser.isChecked()) {
	  prefs.putBoolean(Values.PREFERENCE_SAVE_USERNAME, true);
	}
	else {
	  prefs.putBoolean(Values.PREFERENCE_SAVE_USERNAME, false);
	}

	saveUser.setChecked(prefs.getBoolean(Values.PREFERENCE_SAVE_USERNAME));

	/** What do we do after we're done in the bathroom? :) */
	prefs.flush();

  }

  private void scheduleLogin() {
	name_clear = userField.getText().toString();
	password_clear = passField.getText().toString();

	if (prefs.getBoolean(Values.PREFERENCE_SAVE_USERNAME))
	  prefs.putString(Values.PREFERENCE_SAVED_USER_NAME, name_clear);
	if (!prefs.getBoolean(Values.PREFERENCE_SAVE_USERNAME))
	  prefs.putString(Values.PREFERENCE_SAVED_USER_NAME, "");

	prefs.flush();

	GameCore.getInstance().setScreen(new HexMapScreen(name_clear));

  }

  @Override
  public void setInputPrimary() {
	Gdx.input.setInputProcessor(stage);
  }

}
