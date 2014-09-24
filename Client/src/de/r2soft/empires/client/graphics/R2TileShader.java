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
package de.r2soft.empires.client.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;

/**
 * 
 * @author: Katharina Fey <kookie@spacekookie.de>
 */
public class R2TileShader implements Shader {

  @Override
  public void dispose() {

  }

  @Override
  public void init() {

  }

  @Override
  public int compareTo(Shader other) {
	return 0;
  }

  @Override
  public boolean canRender(Renderable instance) {
	return false;
  }

  @Override
  public void begin(Camera camera, RenderContext context) {

  }

  @Override
  public void render(Renderable renderable) {

  }

  @Override
  public void end() {

  }

}
