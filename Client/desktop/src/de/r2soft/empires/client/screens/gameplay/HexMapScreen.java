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

import org.apache.commons.math3.util.Pair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
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

import de.r2soft.space.client.core.CoreGame;
import de.r2soft.space.client.io.HexMapCameraController;
import de.r2soft.space.client.maps.hex.HexCell;
import de.r2soft.space.client.maps.hex.HexMapLayer;
import de.r2soft.space.client.maps.hex.HexMapLayers;
import de.r2soft.space.client.maps.hex.HexMapRenderer;
import de.r2soft.space.client.maps.hex.HexTileMap;
import de.r2soft.space.client.screens.utilities.LoginScreen;
import de.r2soft.space.client.screens.utilities.SettingsScreen;
import de.r2soft.space.client.settings.BaseSettings;
import de.r2soft.space.client.settings.Resources;
import de.r2soft.space.client.settings.Sizes;
import de.r2soft.space.framework.map.GalaxyMap;
import de.r2soft.space.framework.map.GalaxyPosition;
import de.r2soft.space.framework.map.SolarSystem;
import de.r2soft.space.framework.objects.Star;
import de.r2soft.space.framework.objects.Star.StarType;
import de.r2soft.space.framework.players.Player;
import de.r2soft.space.framework.types.IntVec2;
import de.r2soft.space.framework.util.MapParser;

/**
 * Re-Make of the main menu screen with new camera view port and UI. Published for Prototype version 1.2
 * 
 * @author Katharina
 * 
 */
public class HexMapScreen implements Screen {

  /** Global scope */
  private InputMultiplexer multiplexer;
  private String playerName;

  /** Hex Map */
  private GalaxyMap galaxyMap;
  private HexTileMap map;
  private OrthographicCamera mapCam;
  private ShapeRenderer shapeRenderer;
  private HexMapCameraController mapCamController;
  private HexMapRenderer hexRenderer;
  private Texture hexture;

  /** Scene2D UI */
  private OrthographicCamera uiCam;
  private Stage stage;
  private TextButton settings, quit, logout, profile, research, enterSystem;
  private Table naviRight, naviLeft, centerTop, systemInfo;
  private Label title, welcome, systemSelector;
  private Dialog profileDialog, areYouSure;

  {
	multiplexer = new InputMultiplexer();
	shapeRenderer = new ShapeRenderer();
  }

  public HexMapScreen() {
	this.setTitle();
	this.fetchGalaxyMap();
  }

  public HexMapScreen(String playerName) {
	this.setTitle();
	this.playerName = playerName;

	this.fetchGalaxyMap();
  }

  /** TODO: This should be moved to be a server call! */
  private void fetchGalaxyMap() {
	SAXReader reader = new SAXReader();
	MapParser parser = new MapParser();
	Document doc;
	try {
	  doc = reader.read("/Users/AreusAstarte/Documents/Projekte/RandomRobots/Game Development/SpaceGame/Framework/res/MapDemo.xml");
	  parser.readXML(doc.getRootElement());
	}
	catch (DocumentException e) {
	  return;
	}
	galaxyMap = parser.getGalaxyMap();
  }

  /** Sets the Window title */
  private void setTitle() {
	StringBuilder s = new StringBuilder();
	s.append(BaseSettings.SUPERTITLE);
	s.append(" - ");
	s.append(BaseSettings.VERSION_NUMBER);
	s.append(" - ");
	s.append(BaseSettings.SCREENTITLE_HOME);
	Gdx.graphics.setTitle(s.toString());
  }

  @Override
  public void show() {
	float width = Gdx.graphics.getWidth();
	float hight = Gdx.graphics.getHeight();
	stage = new Stage(width, hight);

	mapCam = new OrthographicCamera();
	mapCam.setToOrtho(false, BaseSettings.HEX_MAP_BASE_SIZE.x, BaseSettings.HEX_MAP_BASE_SIZE.y);
	mapCam.update();

	uiCam = new OrthographicCamera();
	uiCam.setToOrtho(false, width, hight);
	uiCam.update();

	HexTileMap map = new HexTileMap();
	HexMapLayers layers = map.getHexLayers();

	TiledMapTile[] tiles = new TiledMapTile[4];

	// TODO: Make this ugly go away.
	tiles[0] = new StaticTiledMapTile(Resources.TILES_BLUE);
	tiles[1] = new StaticTiledMapTile(Resources.TILES_GREEN);
	tiles[2] = new StaticTiledMapTile(Resources.TILES_RED);
	tiles[3] = new StaticTiledMapTile(Resources.TILES_WHITE);

	HexMapLayer layer = new HexMapLayer(3, 3, 112, 97);
	for (int mx = 0; mx < 3; mx++) {
	  for (int my = 0; my < 3; my++) {
		SolarSystem sys = new SolarSystem(new GalaxyPosition(mx, my), new Player("Julie"), null, null, null, new Star(
			StarType.GIANTSPACEPUDDING));
		HexCell cell = new HexCell(sys);

		if (sys != null) {
		  if (sys.getClaim().equals(BaseSettings.thisPlayer)) {
			cell.setTile(tiles[1]);
		  }
		  else if (sys.getClaim().equals(new Player("Jane"))) {
			cell.setTile(tiles[3]);
		  }
		  else {
			cell.setTile(tiles[2]);
		  }
		}
		layer.setCell(mx, my, cell);
	  }
	}
	layers.add(layer);

	hexRenderer = new HexMapRenderer(map);

	/** initializing Tables, Items and Groups */
	this.initializeFrames();

	/** initializing Tables, Items and Groups */
	this.setupLayout();

	/** Setting up the button listeners */
	this.setupListeners();

	mapCamController = new HexMapCameraController(this, mapCam, hexRenderer);
	multiplexer.addProcessor(stage);
	multiplexer.addProcessor(mapCamController);

	Gdx.input.setInputProcessor(multiplexer);
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

	/** Sets up map view */
	Gdx.gl.glViewport(BaseSettings.HEX_MAP_BASE_OFFSET.x, BaseSettings.HEX_MAP_BASE_OFFSET.y, BaseSettings.HEX_MAP_BASE_SIZE.x,
		BaseSettings.HEX_MAP_BASE_SIZE.y);
	mapCam.update();
	hexRenderer.setView(mapCam);
	hexRenderer.render();

	/** Sets up stage view */
	Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	uiCam.update();

	shapeRenderer.begin(ShapeType.Line);

	/** Draws debug frame around map view */
	shapeRenderer.rect(BaseSettings.HEX_MAP_BASE_OFFSET.x, BaseSettings.HEX_MAP_BASE_OFFSET.y, BaseSettings.HEX_MAP_BASE_SIZE.x,
		BaseSettings.HEX_MAP_BASE_SIZE.y);
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

  /** Updates the selection focus solar system */
  public void updateFocus(SolarSystem system) {
	System.out.println(system.getPosition());
  }

  private void setupProfileDialoge() {

	Table profile_leftTop = new Table();
	Image profilePicture = new Image(new Texture(Gdx.files.internal("assets/gui/users.png")));
	Label lalalal = new Label("This is a label", Resources.UI_SKIN);
	profile_leftTop.add(lalalal);

	profile_leftTop.add(profilePicture).top().center();
	profileDialog.add(profile_leftTop);

	Table profile_bottomButton = new Table();
	profile_bottomButton.setSize(BaseSettings.OLD_WIDTH / 2, BaseSettings.OLD_HEIGHT / 2);
	profileDialog.add(profile_bottomButton).right().bottom();
	TextButton closeProfile = new TextButton("Close", Resources.UI_SKIN);
	profile_bottomButton.add(closeProfile).width(Sizes.SIZE_UI_BUTTON_NAVIGON);

	closeProfile.addListener(new ClickListener() {
	  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		return true;
	  }

	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		profileDialog.remove();
	  }
	});

  }

  private void setupListeners() {

	enterSystem.addListener(new ClickListener() {
	  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		return true;
	  }

	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		CoreGame.getInstance().setScreen(new SolMapScreen(new SolarSystem()));
	  }
	});

	research.addListener(new ClickListener() {
	  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		return true;
	  }

	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	  }
	});

	profile.addListener(new ClickListener() {
	  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		return true;
	  }

	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		profileDialog = new Dialog("User Profile", Resources.UI_SKIN);
		profileDialog.setSize(450, 300);
		profileDialog.setPosition((BaseSettings.OLD_WIDTH / 2) - (BaseSettings.OLD_WIDTH / 4), (BaseSettings.OLD_HEIGHT / 2)
			- (BaseSettings.OLD_HEIGHT / 4));
		stage.addActor(profileDialog);
		setupProfileDialoge();
	  }
	});

	settings.addListener(new ClickListener() {
	  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		return true;
	  }

	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		CoreGame.getInstance().setScreen(new SettingsScreen());
	  }
	});

	quit.addListener(new ClickListener() {
	  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		return true;
	  }

	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		Gdx.app.exit();
	  }
	});

	logout.addListener(new ClickListener() {
	  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		return true;
	  }

	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

		// TODO: Request logout from server
		CoreGame.getInstance().setScreen(new LoginScreen());
	  }
	});

  }

  private void initializeFrames() {
	/** Initialize Buttons */
	profile = new TextButton("Profile", Resources.UI_SKIN);
	settings = new TextButton("Settings", Resources.UI_SKIN);
	quit = new TextButton("Logout & Quit", Resources.UI_SKIN);
	logout = new TextButton("Logout", Resources.UI_SKIN);
	research = new TextButton("Research", Resources.UI_SKIN);
	enterSystem = new TextButton("Enter Solar System", Resources.UI_SKIN);

	/** Initialize Lables */
	title = new Label("Space Game: Prototype 1.2", Resources.UI_SKIN);
	title.setAlignment(Align.center);
	title.setFontScaleX(1.2f);
	title.setFontScaleY(1.1f);
	title.setColor(Color.MAGENTA);

	welcome = new Label("Welcome: " + playerName, Resources.UI_SKIN);
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
	naviRight.add(research).width(Sizes.SIZE_UI_BUTTON_NAVIGON);
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

	tempLabel1 = new Label("Solar System", Resources.UI_SKIN);
	tempLabel1.setColor(Color.MAGENTA);

	tempLabel2 = new Label("Information", Resources.UI_SKIN);
	tempLabel2.setColor(Color.MAGENTA);

	systemInfo.add(tempLabel1);
	systemInfo.add(tempLabel2);
	systemInfo.row();
	systemInfo.add(new Label("Owner: ", Resources.UI_SKIN));
	systemInfo.add(new Label("KateTheAwesome", Resources.UI_SKIN));
	systemInfo.row();
	systemInfo.add(new Label("Size: ", Resources.UI_SKIN));
	systemInfo.add(new Label("Something", Resources.UI_SKIN));
	systemInfo.row();
	systemInfo.add(new Label("Coordinates: ", Resources.UI_SKIN));
	systemInfo.add(new Label("545-101", Resources.UI_SKIN));
	systemInfo.row();
	systemInfo.add(new Label("Units: ", Resources.UI_SKIN));
	systemInfo.add(new Label("42", Resources.UI_SKIN));
	systemInfo.row();
	systemInfo.add(new Label("Exploration: ", Resources.UI_SKIN));
	systemInfo.add(new Label("100%", Resources.UI_SKIN));
	systemInfo.row();
	systemInfo.add(enterSystem).width(Sizes.SIZE_UI_FIELD_CONTENT).colspan(2);
	systemInfo.row();

	// TODO: Make this pretty!
	setupInfoLabelBottom();

  }

  @Deprecated
  /** Hovering display for mouse on map */
  private void setupInfoLabelBottom() {
	Table selectorTable = new Table();
	systemSelector = new Label("Currently selected Solar System: " + "545-101: KateTheAwesome: Red Giant", Resources.UI_SKIN);
	selectorTable.setPosition(BaseSettings.HEX_MAP_BASE_OFFSET.x + 255, BaseSettings.HEX_MAP_BASE_OFFSET.y - 10);
	selectorTable.add(systemSelector);
	stage.addActor(selectorTable);

  }

}
