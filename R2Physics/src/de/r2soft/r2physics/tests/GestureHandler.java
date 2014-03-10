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

package de.r2soft.r2physics.tests;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class GestureHandler implements GestureListener {

  private OrthographicCamera camera;
  private InputHandler handler;

  float scale = 1;
  final Vector3 curr = new Vector3();
  final Vector3 last = new Vector3(-1, -1, -1);
  final Vector3 delta = new Vector3();

  public GestureHandler(OrthographicCamera camera, InputHandler handler) {
	this.camera = camera;
	this.handler = handler;
  }

  @Override
  public boolean touchDown(float x, float y, int pointer, int button) {
	return false;
  }

  @Override
  public boolean tap(float x, float y, int count, int button) {
	return false;
  }

  @Override
  public boolean longPress(float x, float y) {
	return false;
  }

  @Override
  public boolean fling(float velocityX, float velocityY, int button) {
	return false;
  }

  @Override
  public boolean pan(float x, float y, float deltaX, float deltaY) {
	if (handler.isDragged())
	  return false;

	delta.set(deltaX, deltaY, 0);
	delta.scl(camera.zoom);
	camera.position.sub(delta.x, delta.y, 0);
	return true;

  }

  @Override
  public boolean panStop(float x, float y, int pointer, int button) {
	return false;
  }

  @Override
  public boolean zoom(float initialDistance, float distance) {
	if (handler.isDragged())
	  return false;

	float ratio = initialDistance / distance;
	camera.zoom = scale * ratio;

	return true;
  }

  public void changeZoom(float zoom) {

	camera.zoom = zoom;
	camera.update();

  }

  @Override
  public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
	return false;
  }

}
