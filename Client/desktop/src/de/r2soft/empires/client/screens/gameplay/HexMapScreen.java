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

package de.r2soft.empires.client.screens.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
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

import de.r2soft.empires.client.core.GameCore;
import de.r2soft.empires.client.graphics.R2Screen;
import de.r2soft.empires.client.input.HexMapCameraController;
import de.r2soft.empires.client.maps.hex.HexCell;
import de.r2soft.empires.client.maps.hex.HexMapLayer;
import de.r2soft.empires.client.maps.hex.HexMapLayers;
import de.r2soft.empires.client.maps.hex.HexMapRenderer;
import de.r2soft.empires.client.maps.hex.HexTileMap;
import de.r2soft.empires.client.resources.Assets;
import de.r2soft.empires.client.resources.Values;
import de.r2soft.empires.client.screens.overlay.MainMenuOverlay;
import de.r2soft.empires.client.screens.utilities.SettingsScreen;
import de.r2soft.empires.framework.map.GalaxyMap;
import de.r2soft.empires.framework.map.SolarSystem;
import de.r2soft.empires.framework.players.Player;

/**
 * Re-Make of the main menu screen with new camera view port and UI. Published for Prototype version 1.2
 * 
 * @author Katharina
 * 
 */
public class HexMapScreen extends R2Screen {

  /** Global scope */
  private InputMultiplexer multiplexer;
  private String playerName;

  /** Hex Map */
  private GalaxyMap galaxyMap;
  // private HexTileMap map;
  private OrthographicCamera mapCam;
  private ShapeRenderer shapeRenderer;
  private HexMapCameraController mapCamController;
  private HexMapRenderer hexRenderer;

  /** Scene2D UI */
  private OrthographicCamera uiCam;
  private Stage stage;
  private TextButton menu, profile, research, enterSystem;
  private Table naviRight, naviLeft, centerTop, systemInfo;
  private Label title, welcome, systemSelector;
  private Dialog profileDialog, areYouSure;

  {
	multiplexer = new InputMultiplexer();
	shapeRenderer = new ShapeRenderer();
  }

  public HexMapScreen() {
	this.setTitle();
	// this.fetchGalaxyMap();
  }

  public HexMapScreen(String playerName) {
	this.setTitle();
	this.playerName = playerName;

	// this.fetchGalaxyMap();
  }

  /** TODO: This should be moved to be a server call! */
  // private void fetchGalaxyMap() {
  // SAXReader reader = new SAXReader();
  // MapParser parser = new MapParser();
  // Document doc;
  // try {
  // doc = reader
  // .read("/Users/AreusAstarte/Documents/Projekte/RandomRobots/Game Development/SpaceGame/Framework/res/MapDemo.xml");
  // parser.readXML(doc.getRootElement());
  // } catch (DocumentException e) {
  // return;
  // }
  // galaxyMap = parser.getGalaxyMap();
  // }

  /** Sets the Window title */
  private void setTitle() {
	StringBuilder s = new StringBuilder();
	s.append(Values.SUPERTITLE);
	s.append(" - ");
	s.append(Values.VERSION_NUMBER);
	s.append(" - ");
	s.append(Values.SCREENTITLE_HOME);
	Gdx.graphics.setTitle(s.toString());
  }

  @Override
  public void show() {
	float width = Gdx.graphics.getWidth();
	float hight = Gdx.graphics.getHeight();
	stage = new Stage(width, hight);

	mapCam = new OrthographicCamera();
	mapCam.setToOrtho(false, Values.HEX_MAP_BASE_SIZE.x, Values.HEX_MAP_BASE_SIZE.y);
	mapCam.update();

	uiCam = new OrthographicCamera();
	uiCam.setToOrtho(false, width, hight);
	uiCam.update();

	HexTileMap map = new HexTileMap();
	HexMapLayers layers = map.getHexLayers();

	TiledMapTile[] tiles = new TiledMapTile[4];

	// TODO: Make this ugly go away.
	tiles[0] = new StaticTiledMapTile(Assets.R2_TILES_BLUE);
	tiles[1] = new StaticTiledMapTile(Assets.R2_TILES_GREEN);
	tiles[2] = new StaticTiledMapTile(Assets.R2_TILES_RED);
	tiles[3] = new StaticTiledMapTile(Assets.R2_TILES_WHITE);

	HexMapLayer layer = new HexMapLayer(3, 3, 112, 97);
	for (int mx = 0; mx < 3; mx++) {
	  for (int my = 0; my < 3; my++) {
		SolarSystem sys = new SolarSystem();
		sys.setClaim(new Player("Jane"));
		// SolarSystem sys = new SolarSystem(new GalaxyPosition(mx, my), new Player("Julie"), null, null, null, new Star(
		// StarType.GIANTSPACEPUDDING));
		HexCell cell = new HexCell(sys);

		if (sys != null) {
		  if (sys.getClaim().equals(Values.thisPlayer)) {
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

  }

  @Override
  public void setInputFocus() {
	Gdx.input.setInputProcessor(multiplexer);
  }

  @Override
  public void resize(int width, int height) {
	if (stage != null)
	  stage.setViewport(width, height);
  }

  @Override
  public void render(float delta) {
	// TODO: Move this into the game core?
	Gdx.gl20.glEnable(GL20.GL_BLEND);
	shapeRenderer.setProjectionMatrix(uiCam.combined);
	shapeRenderer.begin(ShapeType.Filled);
	shapeRenderer.setColor(0, 0, 0, 0.75f);
	shapeRenderer.rect(Values.HEX_MAP_BASE_OFFSET.x, Values.HEX_MAP_BASE_OFFSET.y, Values.HEX_MAP_BASE_SIZE.x, Values.HEX_MAP_BASE_SIZE.y);
	shapeRenderer.end();

	/** Sets up map view */
	Gdx.gl.glViewport(Values.HEX_MAP_BASE_OFFSET.x, Values.HEX_MAP_BASE_OFFSET.y, Values.HEX_MAP_BASE_SIZE.x, Values.HEX_MAP_BASE_SIZE.y);

	mapCam.update();
	hexRenderer.setView(mapCam);
	hexRenderer.render();

	/** Sets up stage view */
	Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	uiCam.update();

	shapeRenderer.begin(ShapeType.Line);

	/** Draws debug frame around map view */
	shapeRenderer.rect(Values.HEX_MAP_BASE_OFFSET.x, Values.HEX_MAP_BASE_OFFSET.y, Values.HEX_MAP_BASE_SIZE.x, Values.HEX_MAP_BASE_SIZE.y);
	shapeRenderer.end();

	stage.act();
	stage.draw();
  }

  @Override
  public void dispose() {
	super.dispose();
	shapeRenderer.dispose();
	hexRenderer.dispose();
  }

  /** Updates the selection focus solar system */
  public void updateFocus(SolarSystem system) {
	System.out.println(system.getPosition());
  }

  private void setupProfileDialoge() {

	Table profile_leftTop = new Table();
	// TODO: KILL THIS WITH FIRE IN A BURNING BLAZE OF DESTRUCTION AND HORROR!!!
	Image profilePicture = new Image(new Texture(Gdx.files.internal("gui/users.png")));
	Label lalalal = new Label("This is a label", Assets.UI_SKIN);
	profile_leftTop.add(lalalal);

	profile_leftTop.add(profilePicture).top().center();
	profileDialog.add(profile_leftTop);

	Table profile_bottomButton = new Table();
	profile_bottomButton.setSize(Values.OLD_WIDTH / 2, Values.OLD_HEIGHT / 2);
	profileDialog.add(profile_bottomButton).right().bottom();
	TextButton closeProfile = new TextButton("Close", Assets.UI_SKIN);
	profile_bottomButton.add(closeProfile).width(Values.SIZE_UI_BUTTON_NAVIGON);

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
		GameCore.getInstance().setScreen(new SolMapScreen(new SolarSystem()));
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
		profileDialog = new Dialog("User Profile", Assets.UI_SKIN);
		profileDialog.setSize(450, 300);
		profileDialog.setPosition((Values.NEW_WIDTH / 2) - (Values.NEW_WIDTH / 4), (Values.NEW_HEIGHT / 2) - (Values.NEW_HEIGHT / 4));
		stage.addActor(profileDialog);
		setupProfileDialoge();
	  }
	});

	menu.addListener(new ClickListener() {
	  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		return true;
	  }

	  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		MainMenuOverlay overlay = new MainMenuOverlay();
		GameCore.getInstance().addOverlay(overlay);
	  }
	});

  }

  private void initializeFrames() {
	/** Initialize Buttons */
	profile = new TextButton("Profile", Assets.UI_SKIN);
	menu = new TextButton("Main Menu", Assets.UI_SKIN);

	research = new TextButton("Research", Assets.UI_SKIN);
	enterSystem = new TextButton("Enter Solar System", Assets.UI_SKIN);

	/** Initialize Lables */
	title = new Label(Values.SUPERTITLE + ": " + Values.VERSION_NUMBER, Assets.UI_SKIN);
	title.setAlignment(Align.center);
	title.setFontScaleX(1.2f);
	title.setFontScaleY(1.1f);
	title.setColor(Color.MAGENTA);

	welcome = new Label("Welcome: " + playerName, Assets.UI_SKIN);
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
	naviRight.add(research).width(Values.SIZE_UI_BUTTON_NAVIGON);
	naviRight.add(profile).width(Values.SIZE_UI_BUTTON_NAVIGON);
	naviRight.row();

	/** Setting up the left navigation */
	naviLeft.add(menu).width(Values.R2_UI_SIZES_BUTTON_WIDTH_CONTENT).height(Values.R2_UI_SIZES_BUTTON_HEIGHT_CONTENT);

	/** Setting up the center top label table */
	centerTop.add(title).width(Values.SIZE_UI_FIELD_CONTENT);
	centerTop.row();
	centerTop.add(welcome).width(Values.SIZE_UI_FIELD_CONTENT);
	centerTop.row();

	/** Setting up the system info table **/

	Label tempLabel1, tempLabel2;

	tempLabel1 = new Label("Solar System", Assets.UI_SKIN);
	tempLabel1.setColor(Color.MAGENTA);

	tempLabel2 = new Label("Information", Assets.UI_SKIN);
	tempLabel2.setColor(Color.MAGENTA);

	systemInfo.add(tempLabel1);
	systemInfo.add(tempLabel2);
	systemInfo.row();
	systemInfo.add(new Label("Owner: ", Assets.UI_SKIN));
	systemInfo.add(new Label("KateTheAwesome", Assets.UI_SKIN));
	systemInfo.row();
	systemInfo.add(new Label("Size: ", Assets.UI_SKIN));
	systemInfo.add(new Label("Something", Assets.UI_SKIN));
	systemInfo.row();
	systemInfo.add(new Label("Coordinates: ", Assets.UI_SKIN));
	systemInfo.add(new Label("545-101", Assets.UI_SKIN));
	systemInfo.row();
	systemInfo.add(new Label("Units: ", Assets.UI_SKIN));
	systemInfo.add(new Label("42", Assets.UI_SKIN));
	systemInfo.row();
	systemInfo.add(new Label("Exploration: ", Assets.UI_SKIN));
	systemInfo.add(new Label("100%", Assets.UI_SKIN));
	systemInfo.row();
	systemInfo.add(enterSystem).width(Values.SIZE_UI_FIELD_CONTENT).colspan(2);
	systemInfo.row();

	// TODO: Make this pretty!
	setupInfoLabelBottom();

  }

  @Deprecated
  /** Hovering display for mouse on map */
  private void setupInfoLabelBottom() {
	Table selectorTable = new Table();
	systemSelector = new Label("Currently selected Solar System: " + "545-101: KateTheAwesome: Red Giant", Assets.UI_SKIN);
	selectorTable.setPosition(Values.HEX_MAP_BASE_OFFSET.x + 255, Values.HEX_MAP_BASE_OFFSET.y - 10);
	selectorTable.add(systemSelector);
	stage.addActor(selectorTable);

  }

}
