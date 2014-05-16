/* #########################################################################
 * Copyright (c) 2014 RANDOM ROBOT SOFTWORKS
 * (See @authors file)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ######################################################################### */
package de.r2soft.empires.client.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonRegionLoader;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.r2soft.empires.client.core.R2CameraController;

public class PolygonRegionTest extends R2Test {
  PolygonSpriteBatch batch;

  Texture texture;
  OrthographicCamera camera;
  PolygonRegion region;
  PolygonRegion region2;
  R2CameraController controller;

  boolean usePolygonBatch = true;

  @Override
  public void create() {
	texture = new Texture(Gdx.files.internal("tests/tree.png"));

	PolygonRegionLoader loader = new PolygonRegionLoader();
	region = loader.load(new TextureRegion(texture), Gdx.files.internal("tests/tree.psh"));

	// create a region from an arbitrary set of vertices (a triangle in this case)
	region2 = new PolygonRegion(new TextureRegion(texture), new float[] { 0, 0, 100, 100, 0, 100 }, new short[] {
		0, 1, 2 });

	camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	camera.position.x = Gdx.graphics.getWidth() / 2;
	camera.position.y = Gdx.graphics.getHeight() / 2;
	controller = new R2CameraController(false, camera);

	batch = new PolygonSpriteBatch();

	Gdx.input.setInputProcessor(controller);
  }

  @Override
  public void resize(int width, int height) {
	camera.viewportWidth = width;
	camera.viewportHeight = height;
	camera.update();
  }

  @Override
  public void render() {
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1.0f);

	camera.update();
	batch.setProjectionMatrix(camera.combined);

	batch.begin();

	// draw bot regions side-by-side
	float width = 256;
	float x = -width;
	batch.draw(region, 0, 0, 256, 256);
	// batch.draw(region2, x + width + 10, -128, 256, 256);

	batch.end();

  }

  @Override
  public void dispose() {
	texture.dispose();
	batch.dispose();
  }

}
