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
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import de.r2soft.empires.client.core.R2CameraController;

/**
 * 
 * @author: Katharina Fey <kookie@spacekookie.de>
 */
public class ShaderTest extends R2Test {
  private OrthographicCamera camera;
  private R2CameraController controller;
  private ShaderProgram shader;
  private SpriteBatch batch;

  public void create() {
	camera = new OrthographicCamera();
	controller = new R2CameraController(false, camera);

	ShaderProgram.pedantic = false;
	shader = new ShaderProgram(Gdx.files.internal(""), Gdx.files.internal(""));

	System.out.println(shader.isCompiled() ? "Shader compiled, yay" : "Boooo");
	batch.setShader(shader);
  }

  public void render() {
	Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
	Gdx.gl.glClearColor(0, 0, 0, 1);

	camera.update();

  }

}
