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
package de.r2soft.empires.client.maps.hex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.StringBuilder;

import de.r2soft.empires.framework.map.SolarSystem;

/**
 * Own implementation of vector based Hex-Renderer
 * 
 * @author: Katharina Fey <kookie@spacekookie.de>
 */
public class R2HexMapRenderer implements Disposable {
  /** Logger instance to log debug and error information during rendering */
  private Logger logger = Logger.getLogger(getClass().getSimpleName());

  /** Debug renderer for collision shapes */
  ShapeRenderer debugRenderer;

  /** Used to express if the Y-coordinate should point downwards on the screen */
  private boolean ydown = false;

  /** The X and Y sizes of the tile hexagons on the screen. Are initialized with an X size, Y calculated. */
  private float tileX, tileY;

  /** The scale to which all objects in the renderer will be displayed */
  private float unitScale;

  /** Limits the view on the screen to a rectangular area */
  private Rectangle viewBounds;

  /** Holds the Map data to be rendered */
  private R2HexMap map;

  /** Map that links a HexCell instance that contains SolarSystem information to a polygon in the map world. */
  private Map<Polygon, R2HexCell> bounds;
  private Map<Array<Vector2>, R2HexCell> vectorBounds;

  /** Vertices for rendering */
  private float[] spaceVertex = new float[20];
  private float[] polyVertex = new float[8];

  /** SpriteBatch for rendering the tiles */
  private SpriteBatch batch;

  /** An array of integers to define the rendering parameters of the map */
  private int[] params;
  private boolean useParams = false;

  /** Initializes renderer with default scale size */
  public R2HexMapRenderer(R2HexMap map) {
	this(map, 1f);
  }

  /** Initializes renderer with new Spritebatch */
  public R2HexMapRenderer(R2HexMap map, float scale) {
	this(map, scale, new SpriteBatch());
  }

  /** Recommended constructor. Takes a map, unit scale and SpriteBatch. Default scale is 1f */
  public R2HexMapRenderer(R2HexMap map, float scale, SpriteBatch batch) {
	this.viewBounds = new Rectangle();
	bounds = new HashMap<Polygon, R2HexCell>();
	vectorBounds = new HashMap<Array<Vector2>, R2HexCell>();
	debugRenderer = new ShapeRenderer();
	this.unitScale = scale;
	this.batch = batch;
	this.map = map;
  }

  /**
   * Injects a new parameter array into the renderer, compares and logs the changes and invalidates the resources stack
   * for the next update
   */
  public void injectParameters(int[] params) {
	StringBuilder brownie = new StringBuilder();
	Arrays.sort(this.params);
	Arrays.sort(params);

	if (!Arrays.equals(this.params, params)) {
	  for (int c = 0; c < params.length; c++) {
		if (this.params[c] != params[c])
		  brownie.append("Parameter " + this.params[c] + " changed to " + params[c] + "\n ");
	  }
	  brownie.append("\n");
	  logger.debug(brownie.toString());
	  this.params = params;
	  logger.debug("Parameter swap complete. Invalidating resources and flushing rendering stack NOW!");
	  this.invalidate();
	}
	else {
	  logger.info("Parameter sets were exactly the same! WHY DID YOU DO THIS? INVALIDATION IS NOT CALLED!");
	}

  }

  /**
   * Triggers the rendering of the Hexagon-Map. Inject rendering parameters into object before calling {@link #render()}
   * to use Parameter Rendering. Try to avoid switching between vanilla to parameter rendering as it flushes all
   * resources stored in {@link #batch}
   */
  public void render() {
	// CLEARS ALL BOUNDS FROM MAP BEFORE RENDERING
	vectorBounds.clear();
	final Color bc = batch.getColor();
	final float color = Color.toFloatBits(bc.r, bc.g, bc.b, bc.a);

	this.tileX = map.getTileWidth();
	this.tileY = map.getTileHeight();

	final int mapWidth = map.getWidth();
	final int mapHeight = map.getHeight();

	final float mapTileWidth = map.getTileWidth() * unitScale;
	final float mapTileHeight = map.getTileHeight() * unitScale;

	final float mapTileWidth25 = mapTileWidth * 0.25f;
	final float mapTileWidth75 = mapTileWidth * 0.75f;

	final float mapTileHeight50 = mapTileHeight * 0.50f;
	final float mapTileHeight150 = mapTileHeight * 1.50f;

	final int colMax = Math.max(0, (int) (((viewBounds.x - mapTileWidth25) / mapTileWidth75)));
	final int colMin = Math.min(mapWidth,
		(int) ((viewBounds.x + viewBounds.width + mapTileWidth75) / mapTileWidth75));

	final int rowMax = Math.max(0, (int) ((viewBounds.y / mapTileHeight150)));
	final int rowMin = Math.min(mapHeight,
		(int) ((viewBounds.y + viewBounds.height + mapTileHeight150) / mapTileHeight));

	/** Loops through the map cells and renders the tiles at the appropriate coordinates in the world */
	for (int row = rowMax; row < rowMin; row++) {
	  for (int col = colMax; col < colMin; col++) {
		float x = mapTileWidth75 * col;
		float y = (col % 2 == (ydown ? 0 : 1) ? 0 : mapTileHeight50) + (mapTileHeight * row);

		final R2HexCell cell = (R2HexCell) map.getCell(col, row);
		if (cell == null)
		  continue;
		final TextureRegion region = cell.getRegion();

		// final boolean flipX = cell.getFlipHorizontally();
		// final boolean flipY = cell.getFlipVertically();
		// final int rotation = cell.getRotation();

		float regionWidth = region.getRegionWidth() * unitScale;
		float regionHeight = region.getRegionHeight() * unitScale;

		float x1 = x;
		float y1 = y - (regionHeight / 2);
		float x2 = x1 + regionWidth;
		float y2 = y1 + (regionHeight);

		final Vector2 a = new Vector2(x1, y1);
		final Vector2 b = new Vector2(x1, y2);
		final Vector2 c = new Vector2(x2, y2);
		final Vector2 d = new Vector2(x2, y1);

		spaceVertex[0] = x1;
		spaceVertex[1] = y1;
		spaceVertex[2] = color;
		spaceVertex[3] = 0;
		spaceVertex[4] = 0;

		spaceVertex[5] = x1;
		spaceVertex[6] = y2;
		spaceVertex[7] = color;
		spaceVertex[8] = 0;
		spaceVertex[9] = 64;

		spaceVertex[10] = x2;
		spaceVertex[11] = y2;
		spaceVertex[12] = color;
		spaceVertex[13] = 64;
		spaceVertex[14] = 64;

		spaceVertex[15] = x2;
		spaceVertex[16] = y1;
		spaceVertex[17] = color;
		spaceVertex[18] = 64;
		spaceVertex[19] = 0;

		polyVertex[0] = x1;
		polyVertex[1] = y1;

		polyVertex[2] = x1;
		polyVertex[3] = y2;

		polyVertex[4] = x2;
		polyVertex[5] = y2;

		polyVertex[6] = x2;
		polyVertex[7] = y1;

		batch.begin();
		batch.draw(region, x, y);
		batch.end();

		// final Polygon poly = new Polygon(polyVertex);
		vectorBounds.put(new Array<Vector2>(new Vector2[] { a, b, c, d }), cell);
		debugRenderer.begin(ShapeType.Line);
		debugRenderer.setColor(255, 255, 255, 255);
		debugRenderer.polygon(new float[] { a.x, a.y + (regionHeight / 2), b.x, b.y + (regionHeight / 2), c.x,
			c.y + (regionHeight / 2), d.x, d.y + (regionHeight / 2) });
		debugRenderer.end();

		// bounds.put(poly, cell);
		// TODO: use texture with vertices instead of TextureRegion?
		// batch.draw(new Texture, spriteVertices, offset, count);
	  }
	}
  }

  public HexCell getCellWithLocation(float xi, float yi) {

	return null;
  }

  /** Invalidates batch and flushes all resources */
  private void invalidate() {
	logger.info("Invalidating batch resources!");
	Gdx.gl.glEnable(GL20.GL_BLEND);
	batch.flush();
	batch.dispose();
	batch = new SpriteBatch();
  }

  public boolean isYdown() {
	return ydown;
  }

  public void setYdown(boolean ydown) {
	this.ydown = ydown;
  }

  public float getUnitScale() {
	return unitScale;
  }

  public void setUnitScale(float unitScale) {
	this.unitScale = unitScale;
  }

  public boolean isUseParams() {
	return useParams;
  }

  public void setUseParams(boolean useParams) {
	this.useParams = useParams;
  }

  public void setTileSize(float x, float y) {
	logger.warn("Changing tile size mid-render. Is this right?");
	this.tileX = x;
	this.tileY = y;
	this.invalidate();
  }

  @Override
  public void dispose() {
	batch.dispose();
	debugRenderer.dispose();
	Runtime.getRuntime().gc();
  }

  /** Returns a tile from the render collection With collision bound checks */
  public SolarSystem getTileWithPos(float x, float y) {
	Map<Array<Vector2>, R2HexCell> temp = vectorBounds;

	for (Array<Vector2> array : temp.keySet()) {
	  if (Intersector.isPointInPolygon(array, new Vector2(x, y))) {
		System.out.println(temp.get(array).getSystem().getPosition().toString());
		return temp.get(array).getSystem();
	  }
	}

	return null;
  }

  // for (Polygon p : temp.keySet()) {
  // // Intersector.isPointInPolygon(new Vector2(x, y), p);
  // if (p.contains(x, y)) {
  // System.out.println("Found it");
  // return temp.get(p).getSystem();
  // }
  // }
  // return null;
  // }

  public void setView(OrthographicCamera camera) {
	batch.setProjectionMatrix(camera.combined);
	debugRenderer.setProjectionMatrix(camera.combined);
	float width = camera.viewportWidth * camera.zoom;
	float height = camera.viewportHeight * camera.zoom;
	viewBounds.set(camera.position.x - width / 2, camera.position.y - height / 2, width, height);
  }
}