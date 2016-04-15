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
package io.lonelyrobot.empires.client.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;

import io.lonelyrobot.empires.client.core.R2CameraController;

/**
 * 
 * @author: ***REMOVED*** <***REMOVED***>
 */
public class PolygonTest extends R2Test {

  private float[] vertices = new float[20];
  private float[] polyVertices = new float[8];
  private OrthographicCamera camera;
  private R2CameraController controller;
  private Texture texture;

  private SpriteBatch batch;
  private Polygon poly;

  @Override
  public void create() {
	camera = new OrthographicCamera();
	camera.setToOrtho(false);
	controller = new R2CameraController(false, camera);
	batch = new SpriteBatch();
	float color = Color
		.toFloatBits(batch.getColor().r, batch.getColor().g, batch.getColor().b, batch.getColor().a);
	texture = new Texture(Gdx.files.internal("tests/kitten.png"));

	float x1 = 0f;
	float y1 = 0f;
	float x2 = 64f;
	float y2 = 64f;

	float u1 = 0f;
	float v1 = 0f;
	float u2 = 64f;
	float v2 = 64f;

	vertices[0] = x1;
	vertices[1] = y1;
	vertices[2] = color;
	vertices[3] = u1;
	vertices[4] = v1;

	vertices[5] = x1;
	vertices[6] = y2;
	vertices[7] = color;
	vertices[8] = u1;
	vertices[9] = v2;

	vertices[10] = x2;
	vertices[11] = y2;
	vertices[12] = color;
	vertices[13] = u2;
	vertices[14] = v2;

	vertices[15] = x2;
	vertices[16] = y1;
	vertices[17] = color;
	vertices[18] = u2;
	vertices[19] = v1;

	polyVertices[0] = x1;
	polyVertices[1] = y1;

	polyVertices[2] = x1;
	polyVertices[3] = y2;

	polyVertices[4] = x2;
	polyVertices[5] = y2;

	polyVertices[6] = x2;
	polyVertices[7] = y1;

	poly = new Polygon(polyVertices);
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

	Vector3 temp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
	camera.unproject(temp);

	if (Gdx.input.isTouched())
	  if (poly.contains(temp.x, temp.y)) {
		System.out.println("BOYA");
	  }

	camera.update();
	batch.setProjectionMatrix(camera.combined);
	batch.begin();
	batch.draw(texture, vertices, 0, 20);
	batch.end();
  }

  @Override
  public void dispose() {
	batch.dispose();
	texture.dispose();
  }
}
