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

import client.core.ScreenHandler;
import client.screens.SystemScreen;
import client.settings.Settings;
import client.util.Find;
import client.util.ResourcePacker;
import client.util.ResourcePacker.RENDER;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Disposable;

import framework.objects.Star.STARTYPE;
import framework.objects.Unit.TYPE;
import framework.players.Alliance;
import framework.players.Alliance.ALLEGIANCE;
import framework.players.Player;

/**
 * A generic MapObject that will be drawn onto the screen in the Solarmap. May call sub-actors for specific shapes, sizes and
 * habits of objects.
 * 
 * @author ***REMOVED***
 * 
 */
@SuppressWarnings("unused")
public class GenericMapObject extends Actor implements Disposable {

	/** The absolute position of the actor */
	private float posX, posY;

	/** Alliance of the object relative to the player */
	private Alliance alliance;

	/** Object information */
	private TYPE type;
	private String flag;
	private Player claim;
	private ALLEGIANCE allegiance;
	private boolean selected;

	private ResourcePacker res;
	private ShapeRenderer renderer;

		{
			renderer = new ShapeRenderer();
			res = new ResourcePacker();
			res.loadTextures(RENDER.FLEET);
		}

	public GenericMapObject getInstance() {
		return this;
	}

	public GenericMapObject(float x, float y) {
		this.posX = x;
		this.posY = y;
	}

	public GenericMapObject(float x, float y, TYPE type, String flag, Player claim, ALLEGIANCE allegience) {
		this.posX = x;
		this.posY = y;
		this.type = type;
		this.flag = flag;
		this.claim = claim;
		this.allegiance = allegience;
	}

	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.end();
		batch.begin();
		if (selected)
			{
				batch.draw(res.getFrame(), posX, posY, 0, 0, 64, 64, 1, 1, 0);
			}

		switch (type) {
			case FLEET:
				batch.draw(res.getFighterPlayer(), posX, posY, 0, 0, 64, 64, 1, 1, 0);
				break;

			default:
				Gdx.app.log(Settings.LOG, "Error displaying MapTile");
				break;
		}

		// TODO:
		batch.draw(res.getFrame(), posX - 3, posY - 3, 0, 0, 70, 70, 1, 1, 0);
	}

	/**
	 * This will register clicks on the corresponding tile actor.
	 * 
	 * @param x
	 *         position of mouse on screen.
	 * 
	 * @param y
	 *         position of mouse on screen.
	 * 
	 * @param touchable
	 *         Whether the actor allows touch events.
	 * 
	 * @return null
	 */
	@Override
	public Actor hit(float x, float y, boolean touchable) {
		if (touchable && getTouchable() == Touchable.enabled)
			{
				if (Gdx.input.isTouched(0))
					{
						if (x > (this.posX - 64) && x < (this.posX + 64) && y > (this.posY - 64) && y < (this.posY + 64))
							{
								selected = true;
								System.out.println("Selected: " + selected);
							}
					}
			}
		return null;
	}

	@Override
	public void dispose() {
		res.dispose();
	}

}
