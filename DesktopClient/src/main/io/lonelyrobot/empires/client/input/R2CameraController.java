/* #########################################################################
 * Copyright (c) 2014 RANDOM ROBOT SOFTWORKS
 * (See @authors file)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ######################################################################### */
package io.lonelyrobot.empires.client.input;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * The super type to all camera instances. Handles camera options as well as zoom bounds, scrolling and clamping.
 * 
 * @author: ***REMOVED*** <***REMOVED***>
 */
public abstract class R2CameraController implements InputProcessor {
  private final OrthographicCamera camera;
  private final Logger logger = Logger.getLogger(getClass().getSimpleName());
  private List<Integer> params;

  /** Initialises the Camera controller with a default set of parameters */
  public R2CameraController(OrthographicCamera camera) {
	this(camera, null);
  }

  /**
   * Initialises the camera controller with a user-defined set of parameters. Check the API docs for R2DEngine to see
   * possible controller parameters
   */
  public R2CameraController(OrthographicCamera camera, int[] params) {
	this.camera = camera;
	this.injectParams(params);
  }

  public void injectParams(int[] params) {
	Integer[] temp = new Integer[params.length];
	int c = 0;
	for (int i : params) {
	  temp[c++] = Integer.valueOf(i);
	}
	this.params = Arrays.asList(temp);
	updateControllerSettings();
  }

  @Override
  public boolean keyDown(int keycode) {
	return false;
  }

  @Override
  public boolean keyUp(int keycode) {
	return false;
  }

  @Override
  public boolean keyTyped(char character) {
	return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	return false;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
	return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
	return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
	return false;
  }

  @Override
  public boolean scrolled(int amount) {
	return false;
  }

  private void updateControllerSettings() {

  }

  /**
   * Should be called every frame to clamp the camera to predefined boundries.
   */
  public void update() {

  }

}
