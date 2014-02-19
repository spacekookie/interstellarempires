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
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;

import de.r2soft.space.client.core.CoreGame;
import de.r2soft.space.client.io.OrthoCamController;
import de.r2soft.space.framework.map.SolarSystem;

/**
 * Remake of the solar system screen with new camera viewport and UI. Published for Prototype version 1.2
 * 
 * @author ***REMOVED***
 * 
 */
public class SolMapScreen implements Screen {

  /** Global scope */
  private CoreGame handler;
  private SolarSystem system;

  /** Solar Map */
  private OrthographicCamera mapCam;
  private OrthoCamController mapCamController;
  private HexagonalTiledMapRenderer hexRenderer;

  public SolMapScreen(CoreGame handler, SolarSystem system) {
	this.system = system;
	this.handler = handler;
  }

  @Override
  public void show() {

  }

  @Override
  public void resize(int arg0, int arg1) {

  }

  @Override
  public void render(float arg0) {

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
