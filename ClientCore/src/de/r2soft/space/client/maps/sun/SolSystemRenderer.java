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

package de.r2soft.space.client.maps.sun;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

import de.r2soft.space.client.settings.Resources;
import de.r2soft.space.client.settings.Sizes;
import de.r2soft.space.client.util.Find;
import de.r2soft.space.framework.map.SolarSystem;

/**
 * A custom renderer that renders a solar system with a star in the middle and planets orbiting on circular paths around the star.
 * 
 * @author Katharina
 * 
 */
public class SolSystemRenderer implements MapRenderer, Disposable {
  private boolean yDown = false;
  private float scale;
  private SpriteBatch batch;
  private Rectangle viewBounds;

  private SolarSystem system;

  public SolSystemRenderer(SolarSystem system) {
	this.system = system;
	batch = new SpriteBatch();
  }

  public boolean isYdown() {
	return yDown;
  }

  public void setYDown(boolean yDown) {
	this.yDown = yDown;
  }

  public void setAUScale(float scale) {
	this.scale = scale;
  }

  public float getAUScale() {
	return scale;
  }

  @Override
  public void dispose() {
	batch.dispose();
  }

  @Override
  public void setView(OrthographicCamera camera) {
	batch.setProjectionMatrix(camera.combined);
	float width = camera.viewportWidth * camera.zoom;
	float height = camera.viewportHeight * camera.zoom;
	viewBounds.set(camera.position.x - width / 2, camera.position.y - height / 2, width, height);
  }

  @Override
  public void setView(Matrix4 projection, float x, float y, float width, float height) {
	batch.setProjectionMatrix(projection);
	viewBounds.set(x, y, width, height);
  }

  @Override
  public void render() {
	batch.begin();
	// TODO: Rendering magic here
	batch.draw(Resources.STARS_RED_DWARF, Find.getCenter().x - (Sizes.SIZE_CELESTIAL_RED_DWARF / 2), Find.getCenter().y
		- (Sizes.SIZE_CELESTIAL_RED_DWARF / 2), 0, 0, Sizes.SIZE_CELESTIAL_RED_DWARF, Sizes.SIZE_CELESTIAL_RED_DWARF, 1, 1, 0);
	batch.end();
  }

  @Override
  public void render(int[] layers) {

  }

}
