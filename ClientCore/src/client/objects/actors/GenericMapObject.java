/* 
 * Copyright (c) 2013 ***REMOVED***
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
 */

package client.objects.actors;

import client.util.ResourcePacker;
import client.util.ResourcePacker.RENDER;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

import framework.objects.Unit.TYPE;
import framework.players.Alliance;
import framework.players.Player;

/**
 * A generic MapObject that will be drawn onto the screen in the Solarmap. May call sub-actors for specific shapes, sizes and
 * habits of objects.
 * 
 * @author ***REMOVED***
 * 
 */
public class GenericMapObject extends Actor implements Disposable {

	/** The absolute position of the actor */
	private float posX, posY;

	/** Alliance of the object relative to the player */
	private Alliance alliance;

	private ResourcePacker res;

	public GenericMapObject(float x, float y, Alliance alliance) {
		res = new ResourcePacker();
		res.loadTextures(RENDER.FLEET);
	}

	public GenericMapObject(float x, float y, TYPE type, String flag, Player claim) {

	}

	@Override
	public void dispose() {
		res.dispose();
	}

}
