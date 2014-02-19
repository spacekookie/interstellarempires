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

package de.r2soft.robotphysics.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class CameraController extends InputAdapter {
  final OrthographicCamera camera;
  final Vector3 curr = new Vector3();
  final Vector3 last = new Vector3(-1, -1, -1);
  final Vector3 delta = new Vector3();

  public CameraController(OrthographicCamera camera) {
	this.camera = camera;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {

	float sclx = (float) Gdx.graphics.getWidth();
	float scly = (float) Gdx.graphics.getHeight();

	Vector3 tmp = new Vector3(screenX * sclx, screenY * scly, 0);
	camera.unproject(tmp);

	return false;
  }

  @Override
  public boolean touchDragged(int x, int y, int pointer) {
	curr.set(x, y, 0);
	if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
	  delta.set(last.x, last.y, 0);
	  delta.scl(camera.zoom);
	  delta.sub(curr);
	  camera.position.add(delta.x, delta.y, 0);
	}
	last.set(x, y, 0);
	return false;
  }

  @Override
  public boolean scrolled(int amount) {
	return true;
  }

  @Override
  public boolean touchUp(int x, int y, int pointer, int button) {
	last.set(-1, -1, -1);
	return false;
  }
}