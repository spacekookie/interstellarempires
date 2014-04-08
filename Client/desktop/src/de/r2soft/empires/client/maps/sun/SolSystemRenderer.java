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

package de.r2soft.empires.client.maps.sun;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

import de.r2soft.empires.client.resources.Assets;
import de.r2soft.empires.client.resources.Values;
import de.r2soft.empires.framework.map.SolarSystem;
import de.r2soft.empires.framework.objects.BaseObject;
import de.r2soft.empires.framework.objects.Fleet;
import de.r2soft.empires.framework.objects.Moon;
import de.r2soft.empires.framework.objects.Planet;

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
	viewBounds = new Rectangle();
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
	this.renderPlanets();
	this.renderShips();
	this.renderStar();
	this.renderStructures();
	this.renderOrbits();
	batch.end();
  }

  private void renderStructures() {

  }

  private void renderStar() {
	// TODO: Check for star type here
	batch.draw(Assets.R2_SOLAR_STAR_REDDWARF, -(Values.R2_SOLAR_CELESTIAL_STAR_REDDWARF / 2),
		-(Values.R2_SOLAR_CELESTIAL_STAR_REDDWARF / 2), 0, 0, Values.R2_SOLAR_CELESTIAL_STAR_REDDWARF,
		Values.R2_SOLAR_CELESTIAL_STAR_REDDWARF, 1, 1, 0);
  }

  private void renderPlanets() {

	for (Planet p : system.getPlanets()) {
	  // TODO: Check planet type.

	  System.out.println("I'm already rendering.");

	  batch.draw(Assets.R2_SOLAR_PLANET_EARTHY, (float) p.getPosition().getX(), (float) p.getPosition().getY(),
		  -(Values.R2_SOLAR_CELESTIAL_PLANET_EARTHY), -(Values.R2_SOLAR_CELESTIAL_PLANET_EARTHY), Values.R2_SOLAR_CELESTIAL_PLANET_EARTHY,
		  Values.R2_SOLAR_CELESTIAL_PLANET_EARTHY, 1, 1, 0);
	  if (p.hasMoons()) {
		for (Moon m : p.getMoons()) {
		  // TODO: Check for moon type.
		  batch.draw(Assets.R2_SOLAR_MOON_ROCKY, (float) m.getPosition().getX(), (float) m.getPosition().getY(),
			  -(Values.R2_SOLAR_CELESTIAL_PLANET_EARTHY), -(Values.R2_SOLAR_CELESTIAL_PLANET_EARTHY),
			  Values.R2_SOLAR_CELESTIAL_PLANET_EARTHY, Values.R2_SOLAR_CELESTIAL_PLANET_EARTHY, 1, 1, 0);
		}
	  }
	}

  }

  private void renderShips() {
	// for (Fleet f : system.getUnits()) {
	// if (f.getCount() == 1) {
	// // TODO: Check for ship types.
	// batch.draw(Assets.UNITS_FIGHTER_BASIC, (float) f.getPosition().getX(), (float) f.getPosition().getY(),
	// -(Values.R2_SOLAR_SHIP_NORMAL / 2), -(Values.R2_SOLAR_SHIP_NORMAL / 2), Values.R2_SOLAR_SHIP_NORMAL,
	// Values.R2_SOLAR_SHIP_NORMAL, 1, 1, 0);
	// }
	// else {
	// // TODO: Change icon size according to fleet size
	// batch.draw(Assets.FLEET_FIGHTER_FRIEND, (float) f.getPosition().getX(), (float) f.getPosition().getY(),
	// -(Values.R2_SOLAR_FLEET_MEDIUM / 2), -(Values.R2_SOLAR_FLEET_MEDIUM / 2), Values.R2_SOLAR_FLEET_MEDIUM,
	// Values.R2_SOLAR_FLEET_MEDIUM, 1, 1, 0);
	// }
	//
	// }

  }

  private void renderOrbits() {

  }

  /**
   * This method returns whatever object the player has clicked on. See TODO file inside.
   * 
   * @param x
   *          coordinate on the screen
   * @param y
   *          coordinate on the screen
   * @return Returns any instance of a BaseObject child, depending on what was actually clicked.
   */
  public BaseObject getUnitAtCoordinates(float x, float y) {
	/* TODO: I'm not yet done writing this algorithm but I can already see it's gonna be a big mess. Fix it later. */

	BaseObject temp = null;

	// for (Fleet f : system.getUnits())
	// if (f.getPosition().equals(new Vector2D(x, y))) {
	// temp = f;
	// return temp;
	// }
	// for (Planet p : system.getPlanets()) {
	// if (p.getPosition().equals(new Vector2D(x, y))) {
	// temp = p;
	// return temp;
	// }
	// else
	// for (Moon m : p.getMoons())
	// if (m.getPosition().equals(new Vector2D(x, y))) {
	// temp = m;
	// return temp;
	// }
	// }
	// for (Fleet f : system.getUnits())
	// if (f.getPosition().equals(new Vector2D(x, y))) {
	// temp = f;
	// return temp;
	// }

	return temp;
  }

  @Override
  public void render(int[] layers) {

  }

}
