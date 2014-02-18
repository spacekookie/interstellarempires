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
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.r2soft.robotphysics.instances.PhysicsWorld;
import de.r2soft.robotphysics.tests.Body.TYPE;

public class GravityTest extends GdxTest {

  public static void main(String[] args) {
	LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
	cfg.title = "RobotPhysics Test";
	cfg.useGL20 = true;
	cfg.resizable = false;
	cfg.fullscreen = false;
	cfg.width = 1280;
	cfg.height = 720;

	new LwjglApplication(new GravityTest(), cfg);
  }

  private OrthographicCamera camera;
  private SpriteBatch batch;
  private Body planet, star;

  /** Physics */
  private PhysicsWorld world;

  @Override
  public void create() {
	planet = new Body(TYPE.PLANET);
	star = new Body(TYPE.STAR);

	world = new PhysicsWorld();
	world.addChild(planet.getBody());
	world.addChild(star.getBody());

	camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	batch = new SpriteBatch();
  }

  @Override
  public void render() {
	batch.setProjectionMatrix(camera.combined);

	planet.update(batch);
	star.update(batch);
	world.update(Gdx.graphics.getDeltaTime());
  }

}
