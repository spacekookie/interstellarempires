/* #########################################################################
 * Copyright (c) 2017 Lonely Robot
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

package io.lonelyrobot.empires.cl.gl;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import io.lonelyrobot.empires.cl.gl.obj.SolObject;
import io.lonelyrobot.empires.fw.game.map.MapCoordinate;
import io.lonelyrobot.empires.fw.game.map.SolarSystem;
import io.lonelyrobot.empires.fw.game.obj.BaseObject;
import io.lonelyrobot.empires.fw.game.obj.Planet;
import io.lonelyrobot.empires.fw.game.obj.Star;
import io.lonelyrobot.empires.fw.game.traits.Types;
import io.lonelyrobot.empires.fw.game.utils.Logger;

public class CoreClient extends Game {

  private SolObject star, planet, pl2;

  /** This holds all of the game data */
  private SolarSystem system;

  ShapeRenderer renderer;
  OrthographicCamera camera;

  @Override
  public void create() {

    /** Initialise the logger */
    Logger.setup((msg, lvl) -> System.out.println("[" + lvl + "]: " + msg));
    Logger.validate();

    /** Create GameFramework objects */
    Star s = new Star(Types.Stars.RED_DWARF);
    s.setSolPos(new Vector2D(0, 0));

    Planet p = new Planet(s, 150, new Vector2D(150, 0));
    p.getOrbit().setRadius(150);
    p.getOrbit().setStepSpeed(0.025f);

    Planet p2 = new Planet(s, 250, new Vector2D(250, 0));
    p2.getOrbit().setRadius(250);
    p2.getOrbit().setStepSpeed(0.0075f);

    Set<BaseObject> children = new HashSet<>();
    children.add(p);
    children.add(p2);
    s.getOrbit().setChildren(children);

    system = new SolarSystem(s, new MapCoordinate(0, 0));

    // TODO: Add a nicer way to do this (we only do this a few times so it's fine
    system.getCelestials().insert(p, new Vector2D(150, 0));

    camera = new OrthographicCamera(1280, 720);
    // camera.position.x -= 150;
    // camera.position.y -= 150;
    renderer = new ShapeRenderer();

    star = new SolObject(s);
    planet = new SolObject(p);
    pl2 = new SolObject(p2);
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    Gdx.gl.glEnable(GL20.GL_BLEND);
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glDisable(GL20.GL_BLEND);

    // camera.position.z = -50;
    // camera.update();

    system.update(0);

    renderer.setProjectionMatrix(camera.combined);
    renderer.setColor(Color.YELLOW);
    renderer.begin(ShapeType.Filled);
    // renderer.circle(0, 0, 25);

    star.draw(renderer, 75);

    renderer.setColor(Color.GREEN);
    planet.draw(renderer, 25);

    renderer.setColor(Color.BROWN);
    pl2.draw(renderer, 30);

    renderer.end();

    // renderer.setColor(Color.WHITE);
    //
    // camera.position.x = 0;
    // camera.position.y = 0;
    // camera.position.z = 0;
    //
    // camera.update();
    // renderer.setProjectionMatrix(camera.combined);
    //
    // renderer.begin(ShapeType.Filled);
    // renderer.circle(250, 250, 500);
    //

    // renderer.end();
    //
    // try {
    // Thread.sleep(150);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
  }
}
