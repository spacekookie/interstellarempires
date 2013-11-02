/* #########################################################################
 * Copyright (c) 2013 Random Robot Softworks
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

package de.r2soft.space.client.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

/**
 * A debug frame actor for the stage that draws a rectangle around the given
 * coordinates.
 * 
 * @author ***REMOVED***
 * 
 */
public class DebugFrame extends Actor implements Disposable {

	private Vector2 size, pos;
	private Color colour;
	private ShapeRenderer renderer;

	{
		size = new Vector2();
		pos = new Vector2();
		renderer = new ShapeRenderer();
	}

	/** Creating new debug frame on screen with standard colour (white) */
	public DebugFrame(float width, float hight, float x, float y) {
		this.size.x = width;
		this.size.y = hight;
		this.pos.x = x;
		this.pos.y = y;
	}

	/** Creating new debug frame on screen with custom colour */
	public DebugFrame(float width, float hight, float x, float y, Color colour) {
		this.size.x = width;
		this.size.y = hight;
		this.pos.x = x;
		this.pos.y = y;
		this.colour = colour;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.end();
		batch.begin();

	}

	@Override
	public void dispose() {

	}

}
