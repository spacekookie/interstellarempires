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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import de.r2soft.empires.client.core.GameCore;
import de.r2soft.empires.client.graphics.R2Screen;
import de.r2soft.empires.client.input.SolarCameraController;
import de.r2soft.empires.client.maps.sun.SolSystemRenderer;
import de.r2soft.empires.client.resources.Assets;
import de.r2soft.empires.client.resources.Values;
import de.r2soft.empires.client.screens.overlay.MainMenuOverlay;
import de.r2soft.empires.framework.map.SolarSystem;
import de.r2soft.empires.framework.objects.BaseObject;

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
  private Table meta;

  /** Top Navigation Elements */
  private TextButton menu;

  /** Debug elements */
  private ShapeRenderer shapeRenderer;

  {
	shapeRenderer = new ShapeRenderer();
	multiplexer = new InputMultiplexer();
  }

  public SolMapScreen(SolarSystem system) {
	this.system = system;
	prefs = Gdx.app.getPreferences(Values.PREFERENCE_FILE_NAME);
  }

  @Override
  public void show() {
	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	stage = new Stage(new StretchViewport(width, height));

	mapCam = new OrthographicCamera();
	mapCam.setToOrtho(false, Values.SOL_MAP_BASE_SIZE.x, Values.SOL_MAP_BASE_SIZE.y);
	mapCam.update();

	uiCam = new OrthographicCamera();
	uiCam.setToOrtho(false, width, height);
	uiCam.update();

	mapCamController = new SolarCameraController(null, mapCam, null);

	multiplexer.addProcessor(stage);
	multiplexer.addProcessor(mapCamController);

	setupTopNavigation();
	solRenderer = new SolSystemRenderer(system, mapCam);
  }

  @Override
  public void resize(int width, int height) {
	if (stage != null)
	  stage.getViewport().update(width, height);

  }

  @Override
  public void render(float arg0) {
	Gdx.gl20.glEnable(GL20.GL_BLEND);

	/* Background colour drop for map */
	shapeRenderer.setProjectionMatrix(uiCam.combined);
	shapeRenderer.begin(ShapeType.Filled);
	shapeRenderer.setColor(0, 0, 0, 0.75f);
	shapeRenderer.rect(Values.HEX_MAP_BASE_OFFSET.x, Values.HEX_MAP_BASE_OFFSET.y, Values.HEX_MAP_BASE_SIZE.x,
		Values.HEX_MAP_BASE_SIZE.y);
	shapeRenderer.end();

	Gdx.gl.glViewport(Values.SOL_MAP_BASE_OFFSET.x, Values.SOL_MAP_BASE_OFFSET.y, Values.SOL_MAP_BASE_SIZE.x,
		Values.SOL_MAP_BASE_SIZE.y);
	mapCam.update();
	solRenderer.setView(mapCam);
	solRenderer.render();

	/** Sets up stage view */
	Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	uiCam.update();

	/* Draws debug frame around map view */
	shapeRenderer.begin(ShapeType.Line);
	shapeRenderer.rect(Values.SOL_MAP_BASE_OFFSET.x, Values.SOL_MAP_BASE_OFFSET.y, Values.SOL_MAP_BASE_SIZE.x,
		Values.SOL_MAP_BASE_SIZE.y);
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
	meta = new Table(Assets.UI_SKIN);
	meta.setFillParent(true);
	meta.top().left();
	meta.defaults().width(Values.R2_UI_SIZES_BUTTON_WIDTH_CONTENT)
		.height(Values.R2_UI_SIZES_BUTTON_HEIGHT_CONTENT);

	menu = new TextButton("Main Menu", Assets.UI_SKIN);
	meta.add(menu);

	// button_viewUnits = new TextButton("View Units", Assets.UI_SKIN);
	// button_requisition = new TextButton("Requisition Units", Assets.UI_SKIN);
	// button_research = new TextButton("Research", Assets.UI_SKIN);
	// button_diplomacy = new TextButton("Diplomacy", Assets.UI_SKIN);
	// button_galaxyMap = new TextButton("Galaxy Map", Assets.UI_SKIN);
	// button_quit = new TextButton("Logout & Quit", Assets.UI_SKIN);
	//
	// topNav.add(button_viewPlanets);
	// topNav.add(button_viewUnits);
	// topNav.add(button_requisition);
	// topNav.add(button_research);
	// topNav.add(button_diplomacy);
	// topNav.add(button_galaxyMap);
	// topNav.add(button_quit);

	stage.addActor(meta);
  }

  private void setupButtonListeners() {
	meta.addListener(new ClickListener() {
	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		GameCore.getInstance().addOverlay(new MainMenuOverlay());
	  }
	});
  }

  @Override
  public void setInputFocus() {
	Gdx.input.setInputProcessor(multiplexer);
	setupButtonListeners();
  }

  /** Updates the UI with the new focus information */
  public void updateFocus(BaseObject target) {

  }

}
