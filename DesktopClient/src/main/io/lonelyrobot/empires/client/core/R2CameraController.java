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
package io.lonelyrobot.empires.client.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

/**
 * Basic camera controller to attach to any camera to implement scrolling and zooming. Functionality can be triggered
 * with enums
 * 
 * @author: ***REMOVED*** <***REMOVED***>
 */
public class R2CameraController extends InputAdapter {
  private boolean allowZoom = false;
  private final OrthographicCamera camera;
  private final Vector3 curr = new Vector3();
  private final Vector3 last = new Vector3(-1, -1, -1);
  private final Vector3 delta = new Vector3();
  private float sclx, scly;

  public R2CameraController(boolean allowZoom) {
	this(allowZoom, null);
  }

  @Override
  public boolean touchDown(int x, int y, int pointer, int button) {
	Vector3 tmp = new Vector3(x * sclx, y * scly, 0);
	camera.unproject(tmp);
	return false;
  }

  public R2CameraController(boolean allowZoom, OrthographicCamera camera) {
	setCameraScaling(1f, 1f);
	this.camera = camera;
	this.allowZoom = allowZoom;
  }

  /** Inject the size of the Camera Viewport if the viewport isn't by default == screen port */
  public void setCameraScaling(float x, float y) {
	this.sclx = (float) Gdx.graphics.getWidth() / x;
	this.scly = (float) Gdx.graphics.getHeight() / y;
  }

  @Override
  public boolean touchUp(int x, int y, int pointer, int button) {
	last.set(-1, -1, -1);
	return false;
  }

  @Override
  public boolean touchDragged(int x, int y, int pointer) {
	curr.set(x, y, 0);
	if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
	  delta.set(last.x, last.y, 0);
	  delta.sub(curr);
	  delta.scl(camera.zoom);
	  camera.position.add(delta.x, -delta.y, 0);
	}
	last.set(x, y, 0);

	return false;
  }

  @Override
  public boolean scrolled(int amount) {
	if (allowZoom) {
	  float newZoom = camera.zoom * (1 + (amount < 0 ? 0.05f : -0.05f));
	  changeZoom(newZoom, last.x, last.y);
	  return true;
	}
	else
	  return false;

  }

  public void changeZoom(float zoom, float x, float y) {

	Vector3 before = new Vector3(x, y, 0);
	camera.unproject(before);

	camera.zoom = zoom;
	camera.update();
	Vector3 after = new Vector3(x, y, 0);
	camera.unproject(after);

	camera.translate(before.x - after.x, before.y - after.y, 0);
  }

}
