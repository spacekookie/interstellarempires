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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sun.tools.javac.util.Pair;

import de.r2soft.space.client.core.ScreenHandler;
import de.r2soft.space.client.io.OrthoCamController;
import de.r2soft.space.client.screens.utilities.LoginScreen;
import de.r2soft.space.client.screens.utilities.SettingsScreen;
import de.r2soft.space.client.settings.Resources;
import de.r2soft.space.client.util.ResPack;
import de.r2soft.space.client.util.Sizes;
import de.r2soft.space.framework.primitives.IntVec2;

/**
 * Remake of the main menu screen with new camera viewport and UI. Published for
 * Prototype version 1.2
 * 
 * @author Katharina
 * 
 */
public class HexMapScreen implements Screen {

	/** Global scope */
	private ScreenHandler handler;
	private InputMultiplexer input;
	private String playerName;

	/** Hex Map */
	private TiledMap map;
	private OrthographicCamera mapCam;
	private ShapeRenderer shapeRenderer;
	private OrthoCamController mapCamController;
	private HexagonalTiledMapRenderer hexRenderer;
	private Texture hexture;

	/** Scene2D UI */
	private OrthographicCamera uiCam;
	private Stage stage;
	private TextButton settings, quit, logout, profile, refresh, enterSystem;
	private Table naviRight, naviLeft, centerTop, systemInfo;
	private Label title, welcome, systemSelector;
	private Dialog profileDialog, areYouSure;

	{

		input = new InputMultiplexer();
		shapeRenderer = new ShapeRenderer();

	}

	public HexMapScreen(ScreenHandler handler) {
		this.handler = handler;
		this.setTitle();
	}

	public HexMapScreen(ScreenHandler handler, String playerName) {
		this.handler = handler;
		this.setTitle();
		this.playerName = playerName;
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
		float width = Gdx.graphics.getWidth();
		float hight = Gdx.graphics.getHeight();
		stage = new Stage(width, hight);

		mapCam = new OrthographicCamera();
		mapCam.setToOrtho(false, mapDim.snd.x, mapDim.snd.y);
		mapCam.update();

		uiCam = new OrthographicCamera();
		uiCam.setToOrtho(false, width, hight);
		uiCam.update();

		mapCamController = new OrthoCamController(mapCam);
		input.addProcessor(stage);
		input.addProcessor(mapCamController);

		Gdx.input.setInputProcessor(input);

		// hexture = new Texture(Gdx.files.internal("assets/hexes2.png"));
		// TextureRegion[][] hexes = TextureRegion.split(hexture, 112, 97);
		map = new TiledMap();
		MapLayers layers = map.getLayers();
		TiledMapTile[] tiles = new TiledMapTile[4];
		// tiles[0] = new StaticTiledMapTile(new TextureRegion(hexes[0][0]));
		// tiles[1] = new StaticTiledMapTile(new TextureRegion(hexes[0][1]));
		// tiles[2] = new StaticTiledMapTile(new TextureRegion(hexes[1][0]));

		tiles[0] = new StaticTiledMapTile(ResPack.TILES_BLUE);
		tiles[1] = new StaticTiledMapTile(ResPack.TILES_GREEN);
		tiles[2] = new StaticTiledMapTile(ResPack.TILES_RED);
		tiles[3] = new StaticTiledMapTile(ResPack.TILES_WHITE);

		for (int l = 0; l < 1; l++) {
			TiledMapTileLayer layer = new TiledMapTileLayer(45, 30, 112, 97);
			for (int y = 0; y < 30; y++) {
				for (int x = 0; x < 45; x++) {
					int id = (int) (Math.random() * 4);
					Cell cell = new Cell();

					cell.setTile((TiledMapTile) tiles[id]);
					layer.setCell(x, y, cell);
				}
			}
			layers.add(layer);
		}

		hexRenderer = new HexagonalTiledMapRenderer(map);

		/** initializing Tables, Items and Groups */
		this.initializeFrames();

		/** initializing Tables, Items and Groups */
		this.setupLayout();

		/** Setting up the button listeners */
		this.setupListeners();
	}

	@Override
	public void resize(int width, int height) {
		if (stage != null)
			stage.setViewport(width, height);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		Gdx.gl.glViewport(mapDim.fst.x, mapDim.fst.y, mapDim.snd.x,
				mapDim.snd.y);
		mapCam.update();
		hexRenderer.setView(mapCam);
		hexRenderer.render();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		uiCam.update();

		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.rect(mapDim.fst.x, mapDim.fst.y, mapDim.snd.x,
				mapDim.snd.y);
		shapeRenderer.end();

		stage.act();
		stage.draw();
	}

	@Override
	public void dispose() {
		shapeRenderer.dispose();
		hexRenderer.dispose();
		hexture.dispose();
		map.dispose();
	}

	@Override
	public void resume() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void hide() {

	}

	private void setupProfileDialoge() {

		Table profile_leftTop = new Table();
		Image profilePicture = new Image(new Texture(
				Gdx.files.internal("assets/gui/users.png")));
		Label lalalal = new Label("This is a label", ResPack.UI_SKIN);
		profile_leftTop.add(lalalal);

		profile_leftTop.add(profilePicture).top().center();
		profileDialog.add(profile_leftTop);

		Table profile_bottomButton = new Table();
		profile_bottomButton.setSize(Resources.OLD_WIDTH / 2,
				Resources.OLD_HEIGHT / 2);
		profileDialog.add(profile_bottomButton).right().bottom();
		TextButton closeProfile = new TextButton("Close", ResPack.UI_SKIN);
		profile_bottomButton.add(closeProfile).width(
				Sizes.SIZE_UI_BUTTON_NAVIGON);

		closeProfile.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				profileDialog.remove();
			}
		});

	}

	private void setupListeners() {

		refresh.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			}
		});

		profile.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				profileDialog = new Dialog("User Profile", ResPack.UI_SKIN);
				profileDialog.setSize(450, 300);
				profileDialog.setPosition((Resources.OLD_WIDTH / 2)
						- (Resources.OLD_WIDTH / 4), (Resources.OLD_HEIGHT / 2)
						- (Resources.OLD_HEIGHT / 4));
				stage.addActor(profileDialog);
				setupProfileDialoge();
			}
		});

		settings.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				handler.setScreen(new SettingsScreen(handler));
			}
		});

		quit.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.exit();
			}
		});

		logout.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				// TODO: Request logout from server
				handler.setScreen(new LoginScreen(handler));
			}
		});

	}

	private void initializeFrames() {
		/** Initialize Buttons */
		profile = new TextButton("Profile", ResPack.UI_SKIN);
		settings = new TextButton("Settings", ResPack.UI_SKIN);
		quit = new TextButton("Logout & Quit", ResPack.UI_SKIN);
		logout = new TextButton("Logout", ResPack.UI_SKIN);
		refresh = new TextButton("Refresh", ResPack.UI_SKIN);
		enterSystem = new TextButton("Visit Solar System", ResPack.UI_SKIN);

		/** Initialize Lables */
		title = new Label("Space Game: Prototype 1.2", ResPack.UI_SKIN);
		title.setAlignment(Align.center);
		title.setFontScaleX(1.2f);
		title.setFontScaleY(1.1f);
		title.setColor(Color.MAGENTA);

		welcome = new Label("Welcome: " + playerName, ResPack.UI_SKIN);
		welcome.setAlignment(Align.center);

		/** Initialize right navigation */
		naviRight = new Table();
		naviRight.setFillParent(true);
		naviRight.top().right();

		/** Initialize left navigation */
		naviLeft = new Table();
		naviLeft.setFillParent(true);
		naviLeft.top().left();

		/** Initialize center top label */
		centerTop = new Table();
		centerTop.setFillParent(true);
		centerTop.center().top();
		centerTop.setX(Sizes.POSITION_HEX_MAP_OFFSET);

		/** Initialize the solar system info table */
		systemInfo = new Table();
		systemInfo.setFillParent(true);
		systemInfo.center().right();
		systemInfo.setX(-50);
		systemInfo.setY(175);

		/** Adding tables to stage */
		stage.addActor(naviLeft);
		stage.addActor(naviRight);
		stage.addActor(centerTop);
		stage.addActor(systemInfo);

	}

	private void setupLayout() {
		/** Setting up the right navigation */
		naviRight.add(refresh).width(Sizes.SIZE_UI_BUTTON_NAVIGON);
		naviRight.add(profile).width(Sizes.SIZE_UI_BUTTON_NAVIGON);
		naviRight.add(settings).width(Sizes.SIZE_UI_BUTTON_NAVIGON);
		naviRight.row();

		/** Setting up the left navigation */
		naviLeft.add(quit).width(Sizes.SIZE_UI_BUTTON_NAVIGON);
		naviLeft.add(logout).width(Sizes.SIZE_UI_BUTTON_NAVIGON);

		/** Setting up the center top label table */
		centerTop.add(title).width(Sizes.SIZE_UI_FIELD_CONTENT);
		centerTop.row();
		centerTop.add(welcome).width(Sizes.SIZE_UI_FIELD_CONTENT);
		centerTop.row();

		/** Setting up the system info table **/

		Label tempLabel1, tempLabel2;

		tempLabel1 = new Label("Solar System", ResPack.UI_SKIN);
		tempLabel1.setColor(Color.MAGENTA);

		tempLabel2 = new Label("Information", ResPack.UI_SKIN);
		tempLabel2.setColor(Color.MAGENTA);

		systemInfo.add(tempLabel1);
		systemInfo.add(tempLabel2);
		systemInfo.row();
		systemInfo.add(new Label("Owner: ", ResPack.UI_SKIN));
		systemInfo.add(new Label("KateTheAwesome", ResPack.UI_SKIN));
		systemInfo.row();
		systemInfo.add(new Label("Size: ", ResPack.UI_SKIN));
		systemInfo.add(new Label("Something", ResPack.UI_SKIN));
		systemInfo.row();
		systemInfo.add(new Label("Coordinates: ", ResPack.UI_SKIN));
		systemInfo.add(new Label("545-101", ResPack.UI_SKIN));
		systemInfo.row();
		systemInfo.add(new Label("Units: ", ResPack.UI_SKIN));
		systemInfo.add(new Label("42", ResPack.UI_SKIN));
		systemInfo.row();
		systemInfo.add(new Label("Exploration: ", ResPack.UI_SKIN));
		systemInfo.add(new Label("100%", ResPack.UI_SKIN));
		systemInfo.row();
		systemInfo.add(enterSystem).width(Sizes.SIZE_UI_FIELD_CONTENT)
				.colspan(2);
		systemInfo.row();

		// TODO: Make this pretty!
		setupInfoLabelBottom();

	}

	@Deprecated
	/** Hovering display for mouse on map */
	private void setupInfoLabelBottom() {
		Table selectorTable = new Table();
		systemSelector = new Label("Currently selected Solar System: "
				+ "545-101: KateTheAwesome: Red Giant", ResPack.UI_SKIN);
		selectorTable.setPosition(mapDim.fst.x + 255, mapDim.fst.y - 10);
		selectorTable.add(systemSelector);
		stage.addActor(selectorTable);

	}

	/** Map sizes (for Hexmap and Solar Map) */
	private static final IntVec2 mapSize = new IntVec2(900, 600);

	/** Screw around on that to position the map! */
	private static final IntVec2 mapOffset = new IntVec2(150, 25);
	/**
	 * First vector holds starting position of map (lower right corner), the
	 * other holds the actual size
	 */
	private static final Pair<IntVec2, IntVec2> mapDim = new Pair<IntVec2, IntVec2>(
			new IntVec2(((Gdx.graphics.getWidth() / 2) - mapSize.x / 2)
					- mapOffset.x,
					((Gdx.graphics.getHeight() / 2) - mapSize.y / 2)
							- mapOffset.y), mapSize);
}
