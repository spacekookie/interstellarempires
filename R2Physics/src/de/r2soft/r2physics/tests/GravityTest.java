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

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;

import de.r2soft.r2physics.instances.OrbitalBody;
import de.r2soft.r2physics.instances.ParentBody;
import de.r2soft.r2physics.instances.PhysicsWorld;
import de.r2soft.r2physics.primatives.R2Float;
import de.r2soft.r2physics.primatives.R2Int;
import de.r2soft.r2physics.primatives.R2P;
import de.r2soft.r2physics.tests.Body.TYPE;

public class GravityTest extends Game {

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
  private Body planet, planet2, star;
  private InputHandler handler;
  private GestureHandler gestures;
  private CameraController cam;
  private InputMultiplexer plex;

  /** Physics */
  private PhysicsWorld world;

  @Override
  public void create() {
	planet = new Body(TYPE.PLANET, new R2Float(250, 300));
	planet2 = new Body(TYPE.PLANET, new R2Float(300, 300));
	star = new Body(TYPE.STAR, new R2Float(600, 300));

	camera = new OrthographicCamera();
	camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	batch = new SpriteBatch();
	handler = new InputHandler(planet, camera);

	plex = new InputMultiplexer();
	plex.addProcessor(handler);

	if (Gdx.app.getType().equals(ApplicationType.Android)) {
	  gestures = new GestureHandler(camera, handler);
	  plex.addProcessor(new GestureDetector(gestures));
	}
	else {
	  cam = new CameraController(camera, handler);
	  plex.addProcessor(cam);

	}

	Gdx.input.setInputProcessor(plex);

	world = new PhysicsWorld(handler, camera);
	((ParentBody) star.getBody()).addChild((OrbitalBody) planet.getBody());
	((ParentBody) star.getBody()).addChild((OrbitalBody) planet2.getBody());

	((OrbitalBody) planet.getBody()).setOrbitalParent((ParentBody) star.getBody());
	((OrbitalBody) planet2.getBody()).setOrbitalParent((ParentBody) star.getBody());
	star.getBody().setMass(R2P.R2_PHYSICS_MASS_FUN_MASSIVE);
	planet.getBody().setMass(R2P.R2_PHYSICS_MASS_FUN_ROCK);
	planet2.getBody().setMass(R2P.R2_PHYSICS_MASS_FUN_ROCK);
	world.addChild(planet.getBody());
	world.addChild(planet2.getBody());
	world.addChild(star.getBody());

  }

  @Override
  public void render() {
	Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

	batch.setProjectionMatrix(camera.combined);
	camera.update();

	planet.update(batch);
	star.update(batch);
	world.update(Gdx.graphics.getDeltaTime());
  }

}
