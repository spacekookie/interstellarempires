package de.r2soft.empires.client.screens.gameplay;

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
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import de.r2soft.empires.client.core.GameCore;
import de.r2soft.empires.client.graphics.R2Screen;
import de.r2soft.empires.client.input.SolarCameraController;
import de.r2soft.empires.client.maps.sun.SolSystemRenderer;
import de.r2soft.empires.client.settings.BaseSettings;
import de.r2soft.empires.client.settings.Resources;
import de.r2soft.empires.framework.map.SolarSystem;

/**
 * Remake of the solar system screen with new camera viewport and UI. Published for Prototype version 1.2
 * 
 * @author ***REMOVED***
 * 
 */
public class SolMapScreen extends R2Screen {

  /** Global scope */
  private SolarSystem system;
  private InputMultiplexer multiplexer;
  private Preferences prefs;

  /** Solar Map */
  private OrthographicCamera mapCam, uiCam;
  private SolarCameraController mapCamController;
  private SolSystemRenderer solRenderer;

  /** UI Elements */
  private Stage stage;
  private Table topNav, butNav, sideBar;

  /** Top Navigation Elements */
  private TextButton button_viewPlanets, button_viewUnits, button_requisition, button_research, button_diplomacy, button_galaxyMap,
	  button_quit;

  /** Debug elements */
  private ShapeRenderer shapeRenderer;

  {
	shapeRenderer = new ShapeRenderer();
	multiplexer = new InputMultiplexer();
  }

  public SolMapScreen(SolarSystem system) {
	this.system = system;
	prefs = Gdx.app.getPreferences(BaseSettings.PREFERENCE_FILE_NAME);
  }

  @Override
  public void show() {
	float width = Gdx.graphics.getWidth();
	float hight = Gdx.graphics.getHeight();
	stage = new Stage(width, hight);

	mapCam = new OrthographicCamera();
	mapCam.setToOrtho(false, BaseSettings.SOL_MAP_BASE_SIZE.x, BaseSettings.SOL_MAP_BASE_SIZE.y);
	mapCam.update();

	uiCam = new OrthographicCamera();
	uiCam.setToOrtho(false, width, hight);
	uiCam.update();

	mapCamController = new SolarCameraController(null, mapCam, null);

	multiplexer.addProcessor(stage);
	multiplexer.addProcessor(mapCamController);

	setupTopNavigation();
	setupButtonListeners();

	solRenderer = new SolSystemRenderer(system);
  }

  @Override
  public void setInputPrimary() {
	Gdx.input.setInputProcessor(multiplexer);
  }

  @Override
  public void resize(int width, int height) {
	if (stage != null)
	  stage.setViewport(width, height);
  }

  @Override
  public void render(float arg0) {
	Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

	Gdx.gl.glViewport(BaseSettings.SOL_MAP_BASE_OFFSET.x, BaseSettings.SOL_MAP_BASE_OFFSET.y, BaseSettings.SOL_MAP_BASE_SIZE.x,
		BaseSettings.SOL_MAP_BASE_SIZE.y);
	mapCam.update();
	solRenderer.setView(mapCam);
	solRenderer.render();

	/** Sets up stage view */
	Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	uiCam.update();

	/** Draws debug frame around map view */
	shapeRenderer.begin(ShapeType.Line);
	shapeRenderer.rect(BaseSettings.SOL_MAP_BASE_OFFSET.x, BaseSettings.SOL_MAP_BASE_OFFSET.y, BaseSettings.SOL_MAP_BASE_SIZE.x,
		BaseSettings.SOL_MAP_BASE_SIZE.y);
	shapeRenderer.end();

	stage.act();
	stage.draw();
  }

  @Override
  public void dispose() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void pause() {

  }

  @Override
  public void hide() {

  }

  private void setupTopNavigation() {
	topNav = new Table(Resources.UI_SKIN);
	topNav.setFillParent(true);
	topNav.top();

	button_viewPlanets = new TextButton("View Planets", Resources.UI_SKIN);
	button_viewUnits = new TextButton("View Units", Resources.UI_SKIN);
	button_requisition = new TextButton("Requisition Units", Resources.UI_SKIN);
	button_research = new TextButton("Research", Resources.UI_SKIN);
	button_diplomacy = new TextButton("Diplomacy", Resources.UI_SKIN);
	button_galaxyMap = new TextButton("Galaxy Map", Resources.UI_SKIN);
	button_quit = new TextButton("Logout & Quit", Resources.UI_SKIN);

	topNav.add(button_viewPlanets);
	topNav.add(button_viewUnits);
	topNav.add(button_requisition);
	topNav.add(button_research);
	topNav.add(button_diplomacy);
	topNav.add(button_galaxyMap);
	topNav.add(button_quit);

	stage.addActor(topNav);
  }

  private void setupButtonListeners() {
	button_galaxyMap.addListener(new InputListener() {
	  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		return true;
	  }

	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		GameCore.getInstance().onUpdate();
		GameCore.getInstance().setScreen(new HexMapScreen(prefs.getString(BaseSettings.PREFERENCE_SAVED_USER_NAME)));
	  }
	});

  }

}
