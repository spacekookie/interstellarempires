package client.objects.groups;

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

import client.core.ScreenHandler;
import client.objects.actors.GenericMapTile;
import client.screens.MenuScreen;
import client.types.IntVec2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

import framework.map.SolarSystem;
import framework.players.Alliance.ALLEGIANCE;

/**
 * Hexmap implementation as a ViewGroup. Will be added to MapTable on Screen. Holds @GenericMapTile actors.
 * 
 * @author ***REMOVED***
 * 
 */

public class HexMap extends Group implements Disposable {

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

	private TextureAtlas atlas; // Holds all Tile textures
	private ShapeRenderer shapeRenderer;
	private MenuScreen parent;

	private Stage stage;

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
	}

	/**
	 * Will draw the map. At some point
	 */
	public void draw(SpriteBatch batch, float parentAlpha) {

		// Debug frame.
		batch.end();
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX() / 2, getY() / 2, 0);

		shapeRenderer.begin(ShapeType.Rectangle);
		shapeRenderer.rect(0, 0, sizeX, sizeY);
		shapeRenderer.end();
		batch.begin();

		if (stage == null)
			stage = new Stage();

		stage.clear();

		stage.addActor(new GenericMapTile((getX() / 2) + (0 * tileX), (getY() / 2) + (0 * tileY), ALLEGIANCE.PLAYER, new IntVec2(0, 0)));
		stage.addActor(new GenericMapTile((getX() / 2) + (0 * tileX + 75), (getY() / 2) + (0 * tileY + 42.5f), ALLEGIANCE.NEUTRAL, new IntVec2(1, 0)));
		stage.addActor(new GenericMapTile((getX() / 2) + (1 * tileX), (getY() / 2) + (0 * tileY), ALLEGIANCE.HOSTILE, new IntVec2(2, 0)));
		stage.draw();

	}

	public void setInputToChild() {
		Gdx.input.setInputProcessor(stage);
	}

	/**
	 * Usually only called once when creating the application. Players with custom skins and graphic packs may end up changing these
	 * values. Checking for tile size is done outside this class.
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
	 * Checks if the requested tile should even be displayed or if it is outside the requested view.
	 * 
	 * @param tileID
	 *         Vector ID of the tile in question
	 * 
	 * @return true: The tile is on screen and needs to be displayed. false: The tile is no on screen and shouldn't be displayed.
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

	/**
	 * MIGHT be used later on.
	 * 
	 * @param tileID
	 * @return
	 */
	@Deprecated
	public SolarSystem getTileWithID(Vector2 tileID) {
		return null;

		// TODO: This will check the QuadTree for tile info

	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	public void setScreenPosition(float x, float y) {
	}
}
