package bucket.game.client.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Disposable;

/**
 * HexMap implementation...when it grows up some day
 * 
 * @author ***REMOVED***
 * 
 */

public class HexMap extends Actor implements Disposable {

	private float sizeX, sizeY; // Size of the map-actor on screen
	private Vector2 tileID; // Tile ID on the global map.
	private float tileX, tileY; // Tile size to let the view know when to stop drawing tiles

	/** Actor Stuff here */

	private TextureAtlas atlas;
	private TextureRegion hosTile, friendTile, neuTile, playTile;
	private ShapeRenderer shapeRenderer;

	/**
	 * Will draw the map. At some point
	 */
	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.end();
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.translate(getX() / 2, getY() / 2, 0);

		shapeRenderer.begin(ShapeType.Rectangle);
		shapeRenderer.rect(0, 0, sizeX + 20, sizeY);
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

	private float vTile, hTile; // Number of tiles possible on the screen

	public HexMap(float x, float y) {
		this.sizeX = x;
		this.sizeY = y;
		this.tileX = 150;
		this.tileY = 85;

		shapeRenderer = new ShapeRenderer();

		loadTextures();
	}

	/**
	 * Loads the Tile TextureRegions from the atlas. Moved to the @ResourcePacker
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
	 * Detects if an actor has been clicked
	 */
	public Actor hit(float x, float y, boolean touchable) {
		if (touchable && getTouchable() != Touchable.enabled)
			return null;
		return x >= 0 && x < getWidth() && y >= 0 && y < getHeight() ? this : null;
	}

	/**
	 * Usually only called once when creating the application. Players with custom skins and graphic packs may end up
	 * changing these values. Checking for tile size is done outside this class.
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
	 * @return Vector2 object with tile size as x and y paramenters
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
	 *          Vector ID of the tile in question
	 * 
	 * @return true: The tile is on screen and needs to be displayed. false: The tile is no on screen and shouldn't be
	 *         displayed.
	 */
	public boolean isTileOnScreen(Vector2 tileID) {

		boolean result = false;

		// TODO: Get top left tile (Somehow? :s)

		// TODO: Then check
		// if(dist(topleft --> tileID > hTile || vTile)
		// ### result --> false
		// ### else result --> true

		return result;
	}

	public void getTileWithID(Vector2 tileID) {

		// TODO: This will check the QuadTree for tile info

	}

	/**
	 * Am I supposed to do something with this stub? :|
	 */
	@Override
	public void dispose() {

	}
}