/* #########################################################################
 * Copyright (c) 2014 Random Robot Softworks
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

package de.r2soft.empires.client.screens.overlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.r2soft.empires.client.core.GameCore;
import de.r2soft.empires.client.graphics.R2Overlay;
import de.r2soft.empires.client.resources.Assets;
import de.r2soft.empires.client.screens.utilities.LoginScreen;
import de.r2soft.empires.client.screens.utilities.SettingsScreen;

/**
 * 
 * @author ***REMOVED*** <***REMOVED***>
 * 
 */
public class MainMenuOverlay extends R2Overlay {
  private Button exit, logout, options, cancel;
  private Label title;
  private Table main;

  public MainMenuOverlay() {
	super(new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
  }

  @Override
  public void build() {
	System.out.println("Building Overlay screen!!!");
	exit = new TextButton("End Session & Quit", Assets.UI_SKIN);
	logout = new TextButton("Logout & Change User", Assets.UI_SKIN);
	options = new TextButton("Options", Assets.UI_SKIN);
	cancel = new TextButton("Cancel", Assets.UI_SKIN);

	title = new Label("GAME MAIN MENU", Assets.UI_SKIN);
	title.setFontScale(2.5f);

	main = new Table(Assets.UI_SKIN);
	main.setFillParent(true);
	main.center();

	main.add(title).center().pad(25f);
	main.row().height(75);
	main.add(cancel).center().width(500).pad(5f);
	main.row().height(75);
	main.add(options).center().width(500).pad(5f);
	main.row().height(75);
	main.add(logout).center().width(500).pad(5f);
	main.row().height(75);
	main.add(exit).center().width(500).pad(5f);

	stage.addActor(main);
	this.makeListeners();
  }

  private void makeListeners() {
	exit.addListener(new ClickListener() {

	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		Gdx.app.exit();
		logout();
	  }
	});

	logout.addListener(new ClickListener() {
	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		GameCore.getInstance().removeOverlay();
		GameCore.getInstance().setScreen(new LoginScreen());
		logout();
	  }
	});

	cancel.addListener(new ClickListener() {
	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		GameCore.getInstance().removeOverlay();
	  }
	});

  }

  private void logout() {
	// TODO: Request server logout.
  }

  @Override
  public void setInputFocus() {
	Gdx.input.setInputProcessor(stage);

	/**
	 * TODO: I don't understand this. I will now call this the "Mystery Button" and move on. It works like this, though it shouldn't be
	 * necessary! All the other buttons work. I LITERALLY have no idea anymore what it could be. Maybe somebody else *wink wink* has more
	 * luck with this thing than me.
	 */
	options.addListener(new ClickListener() {
	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		GameCore.getInstance().addOverlay(new PreferencesOverlay());
	  }
	});

  }
}
