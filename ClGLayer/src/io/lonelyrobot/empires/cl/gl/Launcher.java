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

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import io.lonelyrobot.empires.fw.game.obj.BaseObject;
import io.lonelyrobot.empires.fw.game.obj.Ship;
import io.lonelyrobot.empires.fw.game.utils.Tree2D;

/**
 * Main launcher class that handles platform dependant code like setting icons, getting
 * screen resolution and setting up backend and front-end components to create the game.
 * 
 * This is only one of possible (future) clients.
 * 
 * @author Katharina 'spacekookie' Fey
 */
public class Launcher {

  public static void main(String args[]) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.width = 1280;
    config.height = 720;

    new LwjglApplication(new CoreClient(), config);
  }
}
