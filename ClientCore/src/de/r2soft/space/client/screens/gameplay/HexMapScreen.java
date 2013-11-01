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

package de.r2soft.space.client.screens.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import de.r2soft.space.client.actors.DebugFrame;
import de.r2soft.space.client.core.ScreenHandler;
import de.r2soft.space.client.settings.Resources;
import de.r2soft.space.client.testspace.OrthoCamController;
import de.r2soft.space.client.util.ResPack;

/**
 * Remake of the main menu screen with new camera viewport and UI. Published for
 * Prototype version 1.2
 * 
 * @author ***REMOVED***
 * 
 */
public class HexMapScreen implements Screen {

	/** Global scope */
	private ScreenHandler handler;
	private InputMultiplexer input;

	/** Shapes */
	private ShapeRenderer shapeRenderer;

	/** Hex Map */
	private TiledMap map;
	private OrthographicCamera mapCam;
	private OrthoCamController mapCamController;
	private HexagonalTiledMapRenderer hexRenderer;
	private Texture hexture;

	/** Scene2D UI */
	private OrthographicCamera uiCam;
	private Stage stage;

	{

		input = new InputMultiplexer();
		shapeRenderer = new ShapeRenderer();

	}

	public HexMapScreen(ScreenHandler handler) {
		this.handler = handler;
		this.setTitle();
	}

	/** Sets the Window title */
	private void setTitle() {
		StringBuilder s = new StringBuilder();
		s.append(Resources.SUPERTITLE);
		s.append(" - ");
		s.append(Resources.VERSION_NUMBER);
		s.append(" - ");
		s.append(Resources.SCREENTITLE_HOME);
		Gdx.graphics.setTitle(s.toString());
	}

	@Override
	public void show() {
		mapCam = new OrthographicCamera();
		mapCam.setToOrtho(false, 921, 518); // TODO: Replace with variables

		uiCam = new OrthographicCamera();
		uiCam.setToOrtho(false, 1280, 720);

		input.addProcessor(stage);
		input.addProcessor(mapCamController);

		hexture = new Texture(Gdx.files.internal("assets/hexes.png"));
		TextureRegion[][] hexes = TextureRegion.split(hexture, 112, 97);
		map = new TiledMap();
		MapLayers layers = map.getLayers();
		TiledMapTile[] tiles = new TiledMapTile[3];
		// TODO: Change the hextile setup.
		tiles[0] = new StaticTiledMapTile(new TextureRegion(hexes[0][0]));
		tiles[1] = new StaticTiledMapTile(new TextureRegion(hexes[0][1]));
		tiles[2] = new StaticTiledMapTile(new TextureRegion(hexes[1][0]));

		for (int l = 0; l < 1; l++) {
			TiledMapTileLayer layer = new TiledMapTileLayer(45, 30, 112, 97);
			for (int y = 0; y < 30; y++) {
				for (int x = 0; x < 45; x++) {
					int id = (int) (Math.random() * 3);
					Cell cell = new Cell();
					cell.setTile((TiledMapTile) tiles[id]);
					layer.setCell(x, y, cell);
				}
			}
			layers.add(layer);
		}

		hexRenderer = new HexagonalTiledMapRenderer(map);

	}

	@Override
	public void resize(int width, int height) {
		if (stage == null)
			stage = new Stage(width, height);
		else
			stage.setViewport(width, height);

		Button but = new TextButton("SUCK ON THAT YOU ******* CODE!!!",
				ResPack.UI_SKIN);
		but.setPosition(0, 0);
		stage.addActor(but);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		Gdx.gl.glViewport((Gdx.graphics.getWidth() / 2) - 461,
				(Gdx.graphics.getHeight() / 2) - 259, 921, 518);

		mapCam.update();
		hexRenderer.setView(mapCam);
		hexRenderer.render();

		Gdx.gl.glViewport(0, 0, 1280, 720);
		uiCam.update();

		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.rect((Gdx.graphics.getWidth() / 2) - 461,
				(Gdx.graphics.getHeight() / 2) - 259, 921, 518);
		shapeRenderer.end();

		stage.act();
		stage.draw();
	}

	@Override
	public void dispose() {
		shapeRenderer.dispose();
		// hexRenderer.dispose();
		// hexture.dispose();
		// map.dispose();
	}

	// SpriteBatch batch;
	/** Render */

	@Override
	public void resume() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void hide() {

	}

}
