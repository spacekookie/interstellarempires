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

package de.r2soft.space.client.screens.gameplay;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.r2soft.space.client.actors.GenericMapTile;
import de.r2soft.space.client.core.ScreenHandler;
import de.r2soft.space.client.groups.HexagonGroup;
import de.r2soft.space.client.screens.utilities.LoginScreen;
import de.r2soft.space.client.screens.utilities.SettingsScreen;
import de.r2soft.space.client.settings.Resources;
import de.r2soft.space.client.util.ResPack;
import de.r2soft.space.framework.map.IntVec2;
import de.r2soft.space.framework.map.SolarSystem;
import de.r2soft.space.framework.objects.GameObject.SUPERCLASS;
import de.r2soft.space.framework.objects.GameObject.TYPE;
import de.r2soft.space.framework.objects.Star;
import de.r2soft.space.framework.objects.Star.STARCLASS;
import de.r2soft.space.framework.objects.Unit;
import de.r2soft.space.framework.players.Player;

/**
 * New implementation for Prototype 1.2 of the HexMap screen. Uses Groups instead of Actors. Until
 * it is done the @MenuScreen
 * will take it's place.
 * 
 * @author Katharina
 * 
 */
public class HexagonScreen implements Screen {

	/** Global variables */
	private ScreenHandler handler;

	/** Scene2D elements */
	private Stage stage;
	private TextButton settings, quit, logout, profile, refresh;
	private Table naviRight, naviLeft, map, centerTop;
	private Label title, welcome;
	private String name;
	private Dialog profileDialog, areYouSure;

	/** Hexagon Map */
	private HexagonGroup hex;
	private Set<SolarSystem> systems;

	public HexagonScreen(ScreenHandler handler, String name_clear) {
		this.handler = handler;
		this.setTitle();
		this.fetchData();
		// this.populateSet();
		this.name = name_clear;
	}

	/** Sets the Window title */
	private void setTitle() {
		StringBuilder s = new StringBuilder();
		s.append(Resources.SUPERTITLE);
		s.append(" - ");
		s.append(Resources.VERSION_NUMBER);
		s.append(" - ");
		s.append(Resources.SCREENTITLE_HOME);
		Gdx.graphics.setTitle(s.toString());
	}

	private void fetchData() {
		// TODO: Get Tiledata from server via Background Thread.
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		/** Show stage on screen */
		stage.act();
		stage.draw();
	}

	@Override
	@SuppressWarnings("deprecation")
	public void resize(int width, int height) {
		if (stage == null)
			stage = new Stage(width, height, true);
		stage.clear();

		/** initializing Tables, Items and Groups */
		this.initializeFrames();

		/** initializing Tables, Items and Groups */
		this.setupLayout();

		/** Populate Hexagon Map Group */
		float HEX_START_X = -275f;
		float HEX_START_Y = -105f;

		Set<Unit> units = new HashSet<Unit>(); // for Cycle through 0 to 4 for X-Axis
		units.add(new Unit(SUPERCLASS.UNIT, TYPE.FLEET, "Alpha Wing", Resources.thisPlayer, 12,
				new Vector2(150, 150)));

		for (int n = 0; n < 5; n++) {
			for (int m = 0; m < 5; m++) {
				hex.addActor(new GenericMapTile(HEX_START_X + n * (150), HEX_START_Y + m * (88),
						new SolarSystem(null, Resources._neutralplayer, null, units, null, new Star(
								STARCLASS.BROWNDWARF))));
			}
		}
		for (int n = 0; n < 8; n++) {
			for (int m = -1; m < 8; m++) {
				if (m % 2 != 0 && n % 2 != 0)
					hex.addActor(new GenericMapTile(HEX_START_X + n * (75), HEX_START_Y + m * (44),
							new SolarSystem(null, new Player("penis"), null, units, null, new Star(
									STARCLASS.REDGIANT))));
			}
		}

		this.setupListeners();
	}

	private void setupProfileDialoge() {

		Table profile_leftTop = new Table();
		Image profilePicture = new Image(new Texture(Gdx.files.internal("assets/gui/users.png")));
		Label lalalal = new Label("This is a label", ResPack.UI_SKIN);
		profile_leftTop.add(lalalal);

		profile_leftTop.add(profilePicture).top().center();
		profileDialog.add(profile_leftTop);

		Table profile_bottomButton = new Table();
		profile_bottomButton.setSize(Resources.OLD_WIDTH / 2, Resources.OLD_HEIGHT / 2);
		profileDialog.add(profile_bottomButton).right().bottom();
		TextButton closeProfile = new TextButton("Close", ResPack.UI_SKIN);
		profile_bottomButton.add(closeProfile).width(ResPack.SIZE_UI_BUTTON_NAVIGON);

		closeProfile.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				profileDialog.remove();
			}
		});

	}

	private void setupListeners() {

		refresh.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			}
		});

		profile.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				profileDialog = new Dialog("User Profile", ResPack.UI_SKIN);
				profileDialog.setSize(450, 300);
				profileDialog.setPosition((Resources.OLD_WIDTH / 2) - (Resources.OLD_WIDTH / 4),
						(Resources.OLD_HEIGHT / 2) - (Resources.OLD_HEIGHT / 4));
				stage.addActor(profileDialog);
				setupProfileDialoge();
			}
		});

		settings.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				handler.setScreen(new SettingsScreen(handler));
			}
		});

		quit.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.exit();
			}
		});

		logout.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				handler.setScreen(new LoginScreen(handler));
			}
		});

	}

	private void initializeFrames() {
		/** Initialize Buttons */
		profile = new TextButton("Profile", ResPack.UI_SKIN);
		settings = new TextButton("Settings", ResPack.UI_SKIN);
		quit = new TextButton("Logout & Quit", ResPack.UI_SKIN);
		logout = new TextButton("Logout", ResPack.UI_SKIN);
		refresh = new TextButton("Refresh", ResPack.UI_SKIN);

		/** Initialize Lables */
		title = new Label("Space Game: Prototype 1.1", ResPack.UI_SKIN);
		title.setAlignment(Align.center);
		title.setFontScaleX(1.2f);
		title.setFontScaleY(1.1f);
		title.setColor(Color.MAGENTA);

		welcome = new Label("Welcome: " + name, ResPack.UI_SKIN);
		welcome.setAlignment(Align.center);

		/** Setup group */
		hex = new HexagonGroup(ResPack.SIZE_HEX_MAP_X, ResPack.SIZE_HEX_MAP_Y,
				ResPack.POSITION_HEX_MAP_OFFSET);

		/** Initialize right navigation */
		naviRight = new Table();
		naviRight.setFillParent(true);
		naviRight.top().right();

		/** Initialize left navigation */
		naviLeft = new Table();
		naviLeft.setFillParent(true);
		naviLeft.top().left();

		/** Initialize center top label */
		centerTop = new Table();
		centerTop.setFillParent(true);
		centerTop.center().top();
		centerTop.setX(ResPack.POSITION_HEX_MAP_OFFSET);

		/** Initialize map table */
		map = new Table();
		map.setSize(ResPack.SIZE_HEX_MAP_X, ResPack.SIZE_HEX_MAP_Y);
		map.center();

		/** Adding group to table */
		map.add(hex);

		/** Adding tables to stage */
		stage.addActor(naviLeft);
		stage.addActor(naviRight);
		stage.addActor(map);
		stage.addActor(centerTop);

		/** Set stage input */
		Gdx.input.setInputProcessor(stage);
	}

	private void setupLayout() {
		/** Setting up the right navigation */
		naviRight.add(profile).width(ResPack.SIZE_UI_BUTTON_NAVIGON);
		naviRight.add(settings).width(ResPack.SIZE_UI_BUTTON_NAVIGON);
		naviRight.add(quit).width(ResPack.SIZE_UI_BUTTON_NAVIGON);
		naviRight.row();

		/** Setting up the left navigation */
		naviLeft.add(logout).width(ResPack.SIZE_UI_BUTTON_NAVIGON);
		naviLeft.add(refresh).width(ResPack.SIZE_UI_BUTTON_NAVIGON);

		/** Setting up the center top label table */
		centerTop.add(title).width(ResPack.SIZE_UI_FIELD_CONTENT);
		centerTop.row();
		centerTop.add(welcome).width(ResPack.SIZE_UI_FIELD_CONTENT);
		centerTop.row();
	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}
