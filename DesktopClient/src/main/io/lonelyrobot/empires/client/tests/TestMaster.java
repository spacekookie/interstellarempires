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

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;

/**
 * 
 * @author: Katharina Fey <kookie@spacekookie.de>
 */
public class TestMaster {

  public static void main(String[] args) {
	LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
	cfg.title = "Interstellar Empires Client Test Suite 0.1";
	cfg.width = 1280;
	cfg.height = 720;
	cfg.useGL30 = true;
	cfg.fullscreen = false;
	cfg.resizable = true;

	new LwjglApplication(new PolygonTest(), cfg);
  }

}
