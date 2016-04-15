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

package io.lonelyrobot.empires.client.input;

import java.util.Set;

import org.apache.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import io.lonelyrobot.empires.client.maps.sun.SolSystemRenderer;
import io.lonelyrobot.empires.client.resources.Values;
import io.lonelyrobot.empires.client.screens.gameplay.SolMapScreen;
import io.lonelyrobot.empires.framework.map.SolarSystem;
import io.lonelyrobot.empires.framework.objects.BaseObject;

public class SolarCameraController extends InputAdapter {
	private Logger logger = Logger.getLogger(getClass().getSimpleName());
	final OrthographicCamera camera;
	final Vector3 curr = new Vector3();
	final Vector3 last = new Vector3(-1, -1, -1);
	final Vector3 delta = new Vector3();

	private SolSystemRenderer renderer;
	private SolMapScreen parent;
	float sclx, scly;

	public SolarCameraController(SolMapScreen parent, OrthographicCamera camera,
			SolSystemRenderer renderer) {
		this.camera = camera;
		this.renderer = renderer;
		this.parent = parent;

		sclx = (float) Gdx.graphics.getWidth() / Values.SOL_MAP_BASE_SIZE.x;
		scly = (float) Gdx.graphics.getHeight() / Values.SOL_MAP_BASE_SIZE.y;

	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 tmp = new Vector3(screenX * sclx, screenY * scly, 0);
		camera.unproject(tmp);

		// BaseObject target = null;
		// try {
		// Set<BaseObject> objects = renderer.getObjectsAtCoordinates(tmp.x, tmp.y);
		// for (BaseObject o : objects) {
		// // TODO: Some magic here
		// }
		// }
		// catch (Exception e) {
		// logger.info("Nothing to select at: " + tmp);
		// return false;
		// }
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

		camera.zoom = zoom;
		camera.update();
		Vector3 after = new Vector3(x, y, 0);
		camera.unproject(after);

		camera.translate(before.x - after.x, before.y - after.y, 0);
	}

	@Override
	public boolean scrolled(int amount) {
		float newZoom = camera.zoom * (1 + (amount < 0 ? 0.05f : -0.05f));
		changeZoom(newZoom, last.x, last.y);
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		last.set(-1, -1, -1);
		return false;
	}
}
