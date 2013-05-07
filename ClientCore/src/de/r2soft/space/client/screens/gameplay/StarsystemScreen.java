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
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.r2soft.space.client.actors.GenericMapObject;
import de.r2soft.space.client.core.ScreenHandler;
import de.r2soft.space.client.groups.SolarGroup;
import de.r2soft.space.client.settings.Resources;
import de.r2soft.space.client.util.ResPack;
import de.r2soft.space.framework.map.SolarSystem;
import de.r2soft.space.framework.objects.GameObject.SuperClass;
import de.r2soft.space.framework.objects.Unit;

public class StarsystemScreen implements Screen {

	/** Global variables */
	private ScreenHandler handler;
	private static StarsystemScreen instance;

	/** Scene 2d stuff */
	private Stage stage;

	/** Table layouts for navigation, game content and the solar map */
	private Table navigation, rightbar, resources, leftbar, solarBar;
	private TextButton backMap, refresh, requisition, details; // Top bar Navigation Buttons
	private SolarGroup sol;

	/** Solarsystem */
	private SolarSystem system;

	/** Unit logic */
	private Set<Unit> units;
	private Set<GenericMapObject> childobjects;
	private GenericMapObject focusobject;
	private SuperClass childsuper;

	/** DO NOT USE IN NORMAL CONTEXT */
	@Deprecated
	public static StarsystemScreen getInstance() {
		return instance;
	}

	public StarsystemScreen(ScreenHandler handler, SolarSystem system) {
		instance = this;
		this.handler = handler;
		this.system = system;
		this.setTitle();
		childobjects = new HashSet<GenericMapObject>();

		if (system.getUnits() != null) {
			units = system.getUnits();

			for (Unit unit : units) {
				childobjects.add(new GenericMapObject(unit));
			}
		}
	}

	/** Sets the Window title */
	private void setTitle() {
		StringBuilder s = new StringBuilder();
		s.append(Resources.SUPERTITLE);
		s.append(" - ");
		s.append(Resources.VERSION_NUMBER);
		s.append(" - ");
		s.append(Resources.SCREENTITLE_SOLAR);
		s.append(" - ");
		s.append(system.getId());
		Gdx.graphics.setTitle(s.toString());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		if (stage == null)
			stage = new Stage(width, height, true);
		stage.clear();
		Gdx.input.setInputProcessor(stage);

		this.setupSolarGroup();
		this.setupTables();
		this.setupButtons();
		this.makeListeners();
	}

	private void makeListeners() {
		refresh.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			}

		});
		backMap.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Preferences prefs = Gdx.app.getPreferences(Resources.PREFERENCE_FILE_NAME);
				handler.setScreen(new HexagonScreen(handler, prefs
						.getString(Resources.PREFERENCE_SAVED_USER_NAME)));
			}

		});

	}

	private void setupButtons() {
		backMap = new TextButton("Back to map", ResPack.UI_SKIN);
		refresh = new TextButton("Refresh Screen", ResPack.UI_SKIN);
		requisition = new TextButton("Requisition units", ResPack.UI_SKIN);
		details = new TextButton("System details", ResPack.UI_SKIN);

		/** Settings the button disabled if system has no production structures */
		requisition.setDisabled(!system.hasStructures());

		navigation.add(backMap).width(ResPack.SIZE_UI_BUTTON_NAVIGON);
		navigation.add(refresh).width(ResPack.SIZE_UI_BUTTON_NAVIGON);
		navigation.add(requisition).width(ResPack.SIZE_UI_BUTTON_NAVIGON);
		navigation.add(details).width(ResPack.SIZE_UI_BUTTON_NAVIGON);
	}

	private void setupTables() {
		navigation = new Table();
		navigation.setFillParent(true);
		navigation.top().left();

		rightbar = new Table();
		rightbar.setFillParent(true);
		rightbar.top().right();
		rightbar.setX(ResPack.SIZE_UI_GLOBAL_FRAME_OFFSET);
		rightbar.setY(ResPack.SIZE_UI_GLOBAL_FRAME_OFFSET);

		resources = new Table();
		resources.setFillParent(true);
		resources.center().left();

		leftbar = new Table();
		leftbar.setFillParent(true);
		leftbar.bottom().left();

		stage.addActor(navigation);
		stage.addActor(rightbar);
		stage.addActor(resources);
		stage.addActor(leftbar);
		stage.addActor(sol);
	}

	private void setupSolarGroup() {
		sol = new SolarGroup(system, ResPack.SIZE_HEX_MAP_X, ResPack.SIZE_HEX_MAP_Y,
				ResPack.POSITION_HEX_MAP_OFFSET);

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
