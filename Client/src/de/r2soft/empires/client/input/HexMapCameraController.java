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

package de.r2soft.empires.client.input;

import org.apache.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import de.r2soft.empires.client.maps.hex.R2HexMapRenderer;
import de.r2soft.empires.client.resources.Values;
import de.r2soft.empires.client.screens.gameplay.HexMapScreen;
import de.r2soft.empires.framework.map.SolarSystem;

public class HexMapCameraController extends InputAdapter {
  private Logger logger = Logger.getLogger(getClass().getSimpleName());

  private final OrthographicCamera camera;
  private final Vector2 zoomLimits = new Vector2(0.65f, 1.45f);
  private final Vector3 curr = new Vector3();
  private final Vector3 last = new Vector3(-1, -1, -1);
  private final Vector3 delta = new Vector3();

  float sclx = (float) Gdx.graphics.getWidth() / Values.HEX_MAP_BASE_SIZE.x;
  float scly = (float) Gdx.graphics.getHeight() / Values.HEX_MAP_BASE_SIZE.y;

  private R2HexMapRenderer renderer;
  private HexMapScreen parent;

  public HexMapCameraController(HexMapScreen parent, OrthographicCamera camera, R2HexMapRenderer renderer) {
	this.camera = camera;
	this.camera.zoom = 1f;
	this.renderer = renderer;
	this.parent = parent;
  }

  public HexMapCameraController(int[] params) {
	camera = new OrthographicCamera();
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
	Vector3 tmp = new Vector3(screenX * sclx, screenY * scly, 0);
	camera.unproject(tmp);
	SolarSystem system = null;
	system = renderer.getTileWithPos(tmp.x, tmp.y);
	parent.updateHover(system);

	return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	Vector3 tmp = new Vector3(screenX * sclx, screenY * scly, 0);
	camera.unproject(tmp);
	SolarSystem system = null;

	try {
	  system = renderer.getTileWithPos(tmp.x, tmp.y);
	}
	catch (Exception e) {
	  logger.debug("Click range out of bounds of map range!");
	}
	finally {
	  parent.updateWindow(system);
	  renderer.updateFocus(system);
	}
	return true;
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

  public void changeZoom(float zoom, float x, float y) {

	Vector3 before = new Vector3(x, y, 0);
	camera.unproject(before);

	if (zoom <= zoomLimits.x || zoom >= zoomLimits.y) {
	  return;
	}

	camera.zoom = zoom;
	camera.update();
	Vector3 after = new Vector3(x, y, 0);
	camera.unproject(after);

	camera.translate(before.x - after.x, before.y - after.y, 0);
  }

  @Override
  public boolean scrolled(int amount) {
	float newZoom = camera.zoom * (1 + (amount < 0 ? 0.1f : -0.1f));
	changeZoom(newZoom, last.x, last.y);
	return true;
  }

  @Override
  public boolean touchUp(int x, int y, int pointer, int button) {
	last.set(-1, -1, -1);
	return false;
  }

  public void update() {
	float halfWidth = Values.HEX_MAP_BASE_SIZE.x / 2 * camera.zoom;
	float halfHeight = Values.HEX_MAP_BASE_SIZE.y / 2 * camera.zoom;

	float x = ((renderer.getMap().getTileWidth() / 2) * renderer.getMap().getWidth())
		+ ((renderer.getMap().getTileWidth() / 2) * renderer.getMap().getWidth() * 0.75f);
	float y = renderer.getMap().getTileHeight() * renderer.getMap().getHeight()
		+ (renderer.getMap().getTileHeight() / 2);

	camera.position.x = MathUtils.clamp(camera.position.x, halfWidth, x - halfWidth);
	camera.position.y = MathUtils.clamp(camera.position.y, halfHeight, y - halfHeight);

	camera.update();
  }
}
