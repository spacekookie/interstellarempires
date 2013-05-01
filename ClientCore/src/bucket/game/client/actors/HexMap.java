package bucket.game.client.actors;

/* 
 * Copyright (c) 2012 ***REMOVED***
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

import org.lwjgl.Sys;

import bucket.game.client.core.ScreenHandler;
import bucket.game.client.gui.SystemScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Disposable;

import framework.map.SolarSystem;

/**
 * HexMap implementation.
 * 
 * @author ***REMOVED***
 * 
 */

public class HexMap extends Actor implements Disposable {

	protected ScreenHandler handler;

	/*
	 * Size of the map-actor on screen
	 */
	private float sizeX, sizeY;

	/*
	 * Tile ID on the global map.
	 */
	private Vector2 tileID;

	/*
	 * Tile size to let the view know when to stop drawing tiles
	 */
	private float tileX, tileY;

	/*
	 * Number of tiles possible on the screen
	 */
	private float vTile, hTile;

	/*
	 * Absolute position of map origin on screen.
	 */
	private float positionX, positionY;

	/** Actor Stuff here */

	private TextureAtlas atlas; // Holds all Tile textures
	private TextureRegion hosTile, friendTile, neuTile, playTile;
	private ShapeRenderer shapeRenderer;

	/** New Render stuff here */
	private OrthographicCamera camera;

	/**
	 * Will draw the map. At some point
	 */
	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.end();
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX() / 2, getY() / 2, 0);

		shapeRenderer.begin(ShapeType.Rectangle);
		shapeRenderer.rect(0, 0, sizeX, sizeY);
		shapeRenderer.end();
		batch.begin();

		// Drawing a few tiles. Not mathematically correct but sue me!
		for (int n = 0; n <= 3; n++) {

			for (int m = 0; m <= 3; m++)
				batch.draw(neuTile, (getX() / 2) + (n * tileX), (getY() / 2) + (m * tileY), 0, 0, 100, 100, 1, 1, 0);
		}

		for (int n = 0; n <= 3; n++) {

			for (int m = 0; m <= 2; m++)
				batch.draw(neuTile, (getX() / 2) + (n * tileX + 75), (getY() / 2) + (m * tileY + 42.5f), 0, 0, 100, 100, 1, 1,
						0);
		}

	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public HexMap(float x, float y, ScreenHandler handler) {
		this.sizeX = x;
		this.sizeY = y;
		this.tileX = 150;
		this.tileY = 85;

		this.handler = handler;

		shapeRenderer = new ShapeRenderer();

		loadTextures();
	}

	/**
	 * Loads the Tile TextureRegions from the atlas. Moved to the @ResourcePacker in
	 * P-1.0.3.
	 */
	@Deprecated
	private void loadTextures() {

		this.atlas = new TextureAtlas(Gdx.files.internal("assets/map/prot-map-tiles.pack"));

		// See what I did there? ;)
		hosTile = atlas.findRegion("prot-map-tile-hostile");
		friendTile = atlas.findRegion("prot-map-tile-friend");
		neuTile = atlas.findRegion("prot-map-tile-neutral");
		playTile = atlas.findRegion("prot-map-tile-player");

	}

	/**
	 * This will register click and touch events on the actor and return the corresponding
	 * tile to something that wants it.
	 * 
	 * @param x
	 *          position of mouse on screen.
	 * 
	 * @param y
	 *          position of mouse on screen.
	 * 
	 * @param touchable
	 *          Whether the actor allows touch events.
	 * 
	 * @return Actor to be displayed.
	 */
	@Override
	public Actor hit(float x, float y, boolean touchable) {

		if (touchable && getTouchable() == Touchable.enabled) {
			if (Gdx.input.isTouched(0)) {

				/**
				 * If click is on map and not outside Actor view.
				 */
				if (x > (positionX - (sizeX / 2)) && x < (positionX + (sizeX / 2)) && y > (positionY - (sizeY / 2))
						&& y < (positionY + (sizeY / 2))) {
					System.out.println("SUCCESS");
					handler.setScreen(new SystemScreen(handler, new Vector2(0, 0)));
				} else {
					System.out.println("ERROR");
				}

				return x >= 0 && x < getWidth() && y >= 0 && y < getHeight() ? this : null;
			}
		}
		return null;
	}

	/**
	 * Usually only called once when creating the application. Players with custom skins and
	 * graphic packs may end up changing these values. Checking for tile size is done
	 * outside this class.
	 * 
	 * @param x
	 * @param y
	 */
	public void setTileSize(float x, float y) {

		this.tileX = x;
		this.tileY = y;

		this.hTile = sizeX / tileX;
		this.vTile = sizeY / tileY;

	}

	/**
	 * Gets the size of a generic tile as a vector.
	 * 
	 * @return Vector2 object with tile size as x and y Parameters.
	 */
	public Vector2 getTileSize() {

		Vector2 size = new Vector2();
		size.x = tileX;
		size.y = tileY;

		return size;
	}

	/**
	 * Checks if the requested tile should even be displayed or if it is outside the
	 * requested view.
	 * 
	 * @param tileID
	 *          Vector ID of the tile in question
	 * 
	 * @return true: The tile is on screen and needs to be displayed. false: The tile is no
	 *         on screen and shouldn't be displayed.
	 */
	public boolean isTileOnScreen(Vector2 tileID) {

		boolean result = false;

		// TODO: Get top left tile (Somehow? :s)

		// TODO: Then check
		// if(dist(topleft --> tileID) > hTile || vTile)
		// ### result --> false
		// ### else result --> true

		return result;
	}

	public SolarSystem getTileWithID(Vector2 tileID) {
		return null;

		// TODO: This will check the QuadTree for tile info

	}

	/**
	 * Am I supposed to do something with this stub? :|
	 */
	@Override
	public void dispose() {

	}

	public void setScreenPosition(float x, float y) {
	}
}
