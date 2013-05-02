/* 
 * Copyright (c) 2013 Katharina Fey
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

package bucket.game.client.actors;

import bucket.game.client.util.Ally;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Disposable;

@SuppressWarnings("unused")
public class GenericMapTile extends Actor implements Disposable {

	private TextureAtlas atlas; // Holds all Tile textures
	private TextureRegion hosTile, friendTile, neuTile, playTile;
	private float tileX, tileY;
	private float sizeX, sizeY;
	private int tileID;

	/**
	 * The constructor will set up the coordinates on which the tile will then be drawn. It's
	 * not so difficult O.o. Also includes the alliance of the tile.
	 * 
	 * @param x
	 *         coordinate of the requested tile.
	 * @param y
	 *         coordinate of the requested tile.
	 * @param alliance
	 *         of the tile: player, hostile, neutral and friendly.
	 */
	public GenericMapTile(float x, float y, Ally a, int id) {
		loadTextures();
		tileX = x;
		tileY = y;
		tileID = id;
	}

	/**
	 * Will draw the map. At some point
	 */
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.end();
		batch.begin();
		batch.draw(friendTile, tileX, tileY, 0, 0, 100, 100, 1, 1, 0);
	}

	/**
	 * Loads the Tile TextureRegions from the atlas. Moved to the @ResourcePacker in
	 * Prototype 1.0.5. Will eventually take care of all resource management.
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
	 * @return Actor to be displayed.
	 */
	@Override
	public Actor hit(float x, float y, boolean touchable) {

		if (touchable && getTouchable() == Touchable.enabled) {
			if (Gdx.input.isTouched(0)) {

				System.out.println("TileID: " + tileID);
			}
		}

		return null;
	}

	@Override
	public void dispose() {
	}

}
