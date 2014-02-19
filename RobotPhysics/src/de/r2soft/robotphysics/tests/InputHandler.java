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

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import de.r2soft.robotphysics.primatives.R2Float;

public class InputHandler extends InputAdapter {

  private Body body;
  private OrthographicCamera camera;

  private boolean drag;

  /** Creates an input handler for a single object. Self-entitled piece of shit. I know... */
  public InputHandler(Body body, OrthographicCamera camera) {
	this.body = body;
	this.camera = camera;
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
  public boolean touchDown(int x, int y, int pointer, int button) {

	Vector3 values = new Vector3(x, y, 0);
	camera.unproject(values);

	// System.out.println("Mouse: " + x + " " + y + " Aim: " + body.getPosition().x + " " + body.getPosition().y);
	if ((values.x > body.getPosition().x && values.x < body.getPosition().x + body.spriteSize.x)
		&& (values.y > body.getPosition().y && values.y < body.getPosition().y + body.spriteSize.y)) {
	  drag = true;
	  body.updatePosition(new R2Float(values.x, values.y));
	}
	return false;
  }

  @Override
  public boolean touchUp(int x, int y, int pointer, int button) {
	if (drag)
	  drag = false;
	return false;
  }

  @Override
  public boolean touchDragged(int x, int y, int pointer) {
	Vector3 values = new Vector3(x, y, 0);
	camera.unproject(values);

	if (drag) {
	  body.updatePosition(new R2Float(values.x, values.y));
	}

	return false;
  }

  @Override
  public boolean scrolled(int amount) {
	return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
	return false;
  }

  public boolean isClicked() {
	return drag;
  }
}
