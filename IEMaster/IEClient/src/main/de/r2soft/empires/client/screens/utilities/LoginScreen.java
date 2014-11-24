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

import org.apache.log4j.Logger;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi.MD5;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi.SHA1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import de.r2soft.empires.client.core.GameCore;
import de.r2soft.empires.client.graphics.R2Overlay;
import de.r2soft.empires.client.graphics.R2Screen;
import de.r2soft.empires.client.resources.Assets;
import de.r2soft.empires.client.resources.SettingsInterface;
import de.r2soft.empires.client.resources.Values;
import de.r2soft.empires.client.screens.gameplay.HexMapScreen;
import de.r2soft.empires.client.screens.overlay.MainMenuOverlay;
import de.r2soft.empires.client.screens.overlay.ServerManageOverlay;
import de.r2soft.empires.client.util.Server;

public class LoginScreen extends R2Screen {
  private Logger logger = Logger.getLogger(getClass().getName());

  /** UI elements */
  private Stage stage;
  private Label serverTitle;
  private Table intro, outro, server;
  private TextField userField, passField;
  private TextButton login, menu, back, manageServers;
  private CheckBox saveUser, savePw;

  /** Background stuff */
  private String name_clear, password_clear;

  public LoginScreen() {
	login = new TextButton("LOGIN", Assets.R2_UI_SKIN);
	passField = new TextField("", Assets.R2_UI_SKIN);
	userField = new TextField("", Assets.R2_UI_SKIN);
	saveUser = new CheckBox("Save username?", Assets.R2_UI_SKIN);
	savePw = new CheckBox("Save Password?", Assets.R2_UI_SKIN);
	back = new TextButton("Back to Intro", Assets.R2_UI_SKIN);
	manageServers = new TextButton("Manage Servers", Assets.R2_UI_SKIN);

	if (SettingsInterface.getInstance().contains(Values.PREFERENCE_SAVE_USERNAME)) {
	  userField.setText(SettingsInterface.getInstance().getString(Values.PREFERENCE_SAVED_USER_NAME));
	  saveUser.setChecked(SettingsInterface.getInstance().getBoolean(Values.PREFERENCE_SAVE_USERNAME));
	}
	else if (SettingsInterface.getInstance().contains(Values.PREFERENCE_SAVE_LOGINPW)) {
	  passField.setText(SettingsInterface.getInstance().getString(Values.PREFERENCE_SAVED_USER_PW));
	  savePw.setChecked(SettingsInterface.getInstance().getBoolean(Values.PREFERENCE_SAVE_USERNAME));
	}
  }

  @Override
  public void show() {

  }

  @Override
  public void resize(int width, int height) {
	if (stage == null)
	  stage = new Stage(new StretchViewport(width, height));
	this.redraw();
  }

  /**
   * Loads servers from the preferences database and converts them into Server objects to be handled by the SelectBox on
   * the Login screen.
   * 
   * @return
   */
  private Server[] fetchServers() {
	String[] tmp = SettingsInterface.getInstance().getList(Values.PREFERENCE_LIST_SERVERS);
	Server[] servers = new Server[tmp.length];

	for (int n = 0; n < servers.length; n++) {
	  servers[n] = new Server(tmp[n]);
	}
	return servers;
  }

  private void setupListeners() {
	manageServers.addListener(new ClickListener() {
	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		GameCore.getInstance().addOverlay(new ServerManageOverlay());

	  }
	});

	savePw.addListener(new ChangeListener() {

	  @Override
	  public void changed(ChangeEvent arg0, Actor arg1) {
		if (savePw.isChecked())
		  logger.info("Saving password can be considered insecure");
	  }
	});

	login.addListener(new ClickListener() {
	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		scheduleLogin();
	  }
	});

	menu.addListener(new ClickListener() {
	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		MainMenuOverlay overlay = new MainMenuOverlay();
		GameCore.getInstance().addOverlay(overlay);

	  }
	});

	back.addListener(new ClickListener() {
	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		GameCore.getInstance().setScreen(new IntroductionScreen());
	  }
	});

  }

  public void render(float delta) {
	stage.act(delta);
	stage.draw();

	/* Disables savePW if saveUser is off */
	if (!saveUser.isChecked())
	  savePw.setDisabled(true);
	else
	  savePw.setDisabled(false);

	/* This updates the Preferences DB accordingly */
	if (saveUser.isChecked()) {
	  SettingsInterface.getInstance().putBoolean(Values.PREFERENCE_SAVE_USERNAME, true);
	}
	else
	  SettingsInterface.getInstance().putBoolean(Values.PREFERENCE_SAVE_USERNAME, false);

	if (savePw.isChecked() && saveUser.isChecked()) {
	  SettingsInterface.getInstance().putBoolean(Values.PREFERENCE_SAVE_LOGINPW, true);
	}
	else
	  SettingsInterface.getInstance().putBoolean(Values.PREFERENCE_SAVE_LOGINPW, false);

	/** What do we do after we're done in the bathroom? :) */
	SettingsInterface.getInstance().flush();

  }

  private void scheduleLogin() {
	name_clear = userField.getText().toString();
	password_clear = passField.getText().toString();

	String hash = password_clear;

	if (SettingsInterface.getInstance().getBoolean(Values.PREFERENCE_SAVE_USERNAME))
	  SettingsInterface.getInstance().putString(Values.PREFERENCE_SAVED_USER_NAME, name_clear);

	else if (!SettingsInterface.getInstance().getBoolean(Values.PREFERENCE_SAVE_USERNAME))
	  SettingsInterface.getInstance().putString(Values.PREFERENCE_SAVED_USER_NAME, "");

	if (SettingsInterface.getInstance().getBoolean(Values.PREFERENCE_SAVE_LOGINPW))
	  SettingsInterface.getInstance().putString(Values.PREFERENCE_SAVED_USER_PW, hash);

	else if (!SettingsInterface.getInstance().getBoolean(Values.PREFERENCE_SAVE_LOGINPW))
	  SettingsInterface.getInstance().putString(Values.PREFERENCE_SAVED_USER_PW, "");

	SettingsInterface.getInstance().flush();

	Values.initPlayer(name_clear);

	GameCore.getInstance().setScreen(new HexMapScreen(name_clear));
  }

  @Override
  public void setInputFocus() {
	Gdx.input.setInputProcessor(stage);
	setupListeners();
  }

  public void redraw() {
	stage.clear();

	intro = new Table();
	intro.setFillParent(true);

	outro = new Table();
	outro.setFillParent(true);

	server = new Table();
	server.setFillParent(true);

	// Exiting the game
	menu = new TextButton("Main Menu", Assets.R2_UI_SKIN);
	outro.add(menu).width(Values.SIZE_UI_BUTTON_NAVIGON);
	outro.row();
	outro.add(back).width(Values.SIZE_UI_BUTTON_NAVIGON);
	outro.top().left();

	userField.setMessageText(" Your username");
	passField.setMessageText(" Your password");
	passField.setPasswordCharacter('*');
	passField.setPasswordMode(true);

	intro.add(userField).width(Values.SIZE_UI_FIELD_CONTENT);
	intro.row();
	intro.add(passField).width(Values.SIZE_UI_FIELD_CONTENT);
	intro.row();
	intro.add(login).width(Values.SIZE_UI_FIELD_CONTENT);
	intro.row();
	intro.add(saveUser).left();
	intro.row();
	intro.add(savePw).left();
	intro.row();

	/** Populate the server list */
	SelectBox<Server> serverList = new SelectBox<Server>(Assets.R2_UI_SKIN);
	serverList.setItems(fetchServers());

	serverTitle = new Label("Select a server:", Assets.R2_UI_SKIN);

	server.add(serverTitle).left();
	server.add(manageServers).right();
	server.row();
	server.add(serverList).left().colspan(2).width(575f);

	server.center().bottom();
	server.setY(Gdx.graphics.getHeight() / 6);

	stage.addActor(outro);
	stage.addActor(intro);
	stage.addActor(server);

  }

}
