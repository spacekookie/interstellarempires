package de.r2soft.space.client.screens.gameplay;

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
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import de.r2soft.space.client.io.SolarCameraController;
import de.r2soft.space.client.settings.BaseSettings;
import de.r2soft.space.framework.map.SolarSystem;

/**
 * Remake of the solar system screen with new camera viewport and UI. Published for Prototype version 1.2
 * 
 * @author Katharina
 * 
 */
public class SolMapScreen implements Screen {

  /** Global scope */
  private SolarSystem system;
  private InputMultiplexer multiplexer;

  /** Solar Map */
  private OrthographicCamera mapCam, uiCam;
  private SolarCameraController mapCamController;

  /** UI Elements */
  private Stage stage;
  private Table topNav, butNav, sideBar;

  /** Debug elements */
  private ShapeRenderer shapeRenderer;

  {
	shapeRenderer = new ShapeRenderer();
	multiplexer = new InputMultiplexer();
  }

  public SolMapScreen(SolarSystem system) {
	this.system = system;
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
	// TODO: Render map

	/** Sets up stage view */
	Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	uiCam.update();

	/** Draws debug frame around map view */
	shapeRenderer.begin(ShapeType.Line);
	shapeRenderer.rect(BaseSettings.SOL_MAP_BASE_OFFSET.x, BaseSettings.SOL_MAP_BASE_OFFSET.y, BaseSettings.SOL_MAP_BASE_SIZE.x,
		BaseSettings.SOL_MAP_BASE_SIZE.y);
	shapeRenderer.end();

	// System.out.println(Gdx.input.getX() + "_" + Gdx.input.getY());

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

}
