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
import client.types.IntVec2;
import client.util.ResourcePacker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Disposable;

import framework.players.Alliance.Allegiance;

public class GenericMapTile extends Actor implements Disposable {

	private TextureAtlas atlas; // Holds all Tile textures
	private TextureRegion hosTile, friendTile, neuTile, playTile;
	private float posX, posY;
	private float sizeX, sizeY;
	private IntVec2 tileID;
	private Allegiance ally;
	private ShapeRenderer renderer;
	private ResourcePacker res;

	protected IntVec2 id = null;

		/** Loads textures */
		{
			res = new ResourcePacker();
			res.loadTextures();
		}

	/**
	 * The constructor will set up the coordinates on which the tile will then be drawn. It's not so difficult O.o. Also includes the
	 * alliance of the tile.
	 * 
	 * @param x
	 *         coordinate of the requested tile.
	 * @param y
	 *         coordinate of the requested tile.
	 * @param alliance
	 *         of the tile: player, hostile, neutral and friendly.
	 */
	public GenericMapTile(float x, float y, Allegiance a, IntVec2 id) {
		loadTextures();
		posX = x;
		posY = y;
		tileID = id;
		sizeX = sizeY = 100; // Tile size.
		ally = a;
		renderer = new ShapeRenderer();
		// System.out.println("Position: " + posX + " " + posY);
	}

	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.end();
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
		renderer.translate(getX() / 2, getY() / 2, 0);

		renderer.begin(ShapeType.Rectangle);
		renderer.rect(posX, posY, sizeX, sizeY);
		renderer.end();

		batch.begin();

		switch (ally) {
			case FRIENDLY:
				batch.draw(res.getFriendTile(), posX, posY, 0, 0, sizeX, sizeY, 1, 1, 0);
				break;

			case HOSTILE:
				batch.draw(res.getHosTile(), posX, posY, 0, 0, sizeX, sizeY, 1, 1, 0);
				break;

			case NEUTRAL:
				batch.draw(res.getNeuTile(), posX, posY, 0, 0, sizeX, sizeY, 1, 1, 0);
				break;

			case PLAYER:
				batch.draw(res.getPlayTile(), posX, posY, 0, 0, sizeX, sizeY, 1, 1, 0);
				break;

			default:
				Gdx.app.log(Settings.LOG, "Error displaying MapTile");
				break;
		}
	}

	/**
	 * Moved to the @ResourcePacker with Version P1.0.6
	 */
	@Deprecated
	private void loadTextures() {

		this.atlas = new TextureAtlas(Gdx.files.internal("assets/map/prot-map-tiles.pack"));
		hosTile = atlas.findRegion("prot-map-tile-hostile");
		friendTile = atlas.findRegion("prot-map-tile-friend");
		neuTile = atlas.findRegion("prot-map-tile-neutral");
		playTile = atlas.findRegion("prot-map-tile-player");
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
						if (x > (this.posX - (this.sizeX)) && x < (this.posX + (this.sizeX)) && y > (this.posY - (this.sizeY)) && y < (this.posY + (this.sizeY)))
							{
								System.out.println(tileID);
								ScreenHandler.getInstance().setScreen(new SystemScreen(ScreenHandler.getInstance(), tileID));
							}
					}
			}
		return null;
	}

	@Override
	public void dispose() {

	}

}
