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

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Polygon;
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
import de.r2soft.empires.client.maps.hex.R2HexCell;
import de.r2soft.empires.client.maps.hex.R2HexMap;
import de.r2soft.empires.client.maps.hex.R2HexMapRenderer;
import de.r2soft.empires.client.maps.hex.HexTileMap;
import de.r2soft.empires.client.resources.Assets;
import de.r2soft.empires.client.resources.Values;
import de.r2soft.empires.client.screens.overlay.MainMenuOverlay;
import de.r2soft.empires.client.tests.Statics;
import de.r2soft.empires.framework.map.GalaxyMap;
import de.r2soft.empires.framework.map.GalaxyPosition;
import de.r2soft.empires.framework.map.SolarSystem;
import de.r2soft.empires.framework.objects.BaseObject.Type;
import de.r2soft.empires.framework.objects.Star;
import de.r2soft.empires.framework.players.Player;

/**
 * Re-Make of the main menu screen with new camera view port and UI. Published for Prototype version 1.2
 * 
 * @author ***REMOVED***
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
  private ShapeRenderer shapeRenderer, penis;
  private HexMapCameraController mapCamController;
  // private HexMapRenderer hexRenderer;
  private R2HexMapRenderer r2HexRenderer;

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
	penis = new ShapeRenderer();
  }

  public HexMapScreen() {
	this("Jane");
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
	stage = new Stage();

	mapCam = new OrthographicCamera();
	mapCam.setToOrtho(false, Values.HEX_MAP_BASE_SIZE.x, Values.HEX_MAP_BASE_SIZE.y);
	mapCam.update();

	uiCam = new OrthographicCamera();
	uiCam.setToOrtho(false, width, hight);
	uiCam.update();

	Statics sweet = new Statics();
	Random r = new Random(System.currentTimeMillis());
	TextureRegion[] regions = new TextureRegion[5];
	regions[0] = Assets.R2_TILES_WHITE;
	regions[1] = Assets.R2_TILES_RED;
	regions[2] = Assets.R2_TILES_GREEN;
	regions[3] = Assets.R2_TILES_BLUE;
	regions[4] = Assets.R2_TILES_PURPLE;

	R2HexMap r2map = new R2HexMap(16, 16, 112, 97);

	for (int mx = 0; mx < r2map.getWidth(); mx++) {
	  for (int my = 0; my < r2map.getHeight(); my++) {
		System.out.println("Making tile: " + mx + " " + my);

		SolarSystem sys = new SolarSystem(new Star(Type.STAR_RED_DWARF));
		sys.setClaim(Values.thisPlayer);
		sys.setPosition(new GalaxyPosition(mx, my));

		R2HexCell cell = new R2HexCell(sys, regions[r.nextInt(5)]);
		r2map.setCell(mx, my, cell);
	  }
	}
	r2HexRenderer = new R2HexMapRenderer(r2map);

	/** initializing Tables, Items and Groups */
	this.initializeFrames();

	/** initializing Tables, Items and Groups */
	this.setupLayout();

	/** Setting up the button listeners */
	this.setupListeners();

	mapCamController = new HexMapCameraController(this, mapCam, r2HexRenderer);
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
	  stage.getViewport().update(width, height);
  }

  @Override
  public void render(float delta) {
	// TODO: Move this into the game core?
	Gdx.gl20.glEnable(GL20.GL_BLEND);
	shapeRenderer.setProjectionMatrix(uiCam.combined);
	shapeRenderer.begin(ShapeType.Filled);
	shapeRenderer.setColor(0, 0, 0, 0.75f);
	shapeRenderer.rect(Values.HEX_MAP_BASE_OFFSET.x, Values.HEX_MAP_BASE_OFFSET.y, Values.HEX_MAP_BASE_SIZE.x,
		Values.HEX_MAP_BASE_SIZE.y);
	shapeRenderer.end();

	/** Sets up map view */
	Gdx.gl.glViewport(Values.HEX_MAP_BASE_OFFSET.x, Values.HEX_MAP_BASE_OFFSET.y, Values.HEX_MAP_BASE_SIZE.x,
		Values.HEX_MAP_BASE_SIZE.y);

	mapCam.update();
	r2HexRenderer.setView(mapCam);
	r2HexRenderer.checkDebugRendering();
	r2HexRenderer.render();

	/** Sets up stage view */
	Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	uiCam.update();

	shapeRenderer.begin(ShapeType.Line);
	shapeRenderer.setColor(1, 1, 1, 0.5f);
	/** Draws debug frame around map view */
	shapeRenderer.rect(Values.HEX_MAP_BASE_OFFSET.x, Values.HEX_MAP_BASE_OFFSET.y, Values.HEX_MAP_BASE_SIZE.x,
		Values.HEX_MAP_BASE_SIZE.y);
	shapeRenderer.end();

	stage.act();
	stage.draw();
  }

  @Override
  public void dispose() {
	super.dispose();
	shapeRenderer.dispose();
	r2HexRenderer.dispose();
  }

  /** Updates the selection focus solar system */
  public void updateFocus(SolarSystem system) {
	StringBuilder sb = new StringBuilder();

	sb.append("Currently selected System: ");
	if (system.getPosition() != null)
	  sb.append(system.getPosition());
	else
	  sb.append("Unknown");

	sb.append(" — Owner: ");

	if (system.getClaim() != null)
	  sb.append(system.getClaim().getName());
	else
	  sb.append("Unknown");

	sb.append(" — Star Type: ");

	if (system.getStar() != null)
	  if (system.getStar().getType() != null)
		sb.append(system.getStar().getType());
	  else
		sb.append("Unknown");
	else
	  sb.append("Unknown");

	systemSelector.setText(sb.toString());
  }

  private void setupProfileDialoge() {

	Table profile_leftTop = new Table();
	// TODO: KILL THIS WITH FIRE IN A BURNING BLAZE OF DESTRUCTION AND HORROR!!!
	Image profilePicture = new Image(new Texture(Gdx.files.internal("gui/users.png")));
	Label lalalal = new Label("This is a label", Assets.R2_UI_SKIN);
	profile_leftTop.add(lalalal);

	profile_leftTop.add(profilePicture).top().center();
	profileDialog.add(profile_leftTop);

	Table profile_bottomButton = new Table();
	profile_bottomButton.setSize(Values.OLD_WIDTH / 2, Values.OLD_HEIGHT / 2);
	profileDialog.add(profile_bottomButton).right().bottom();
	TextButton closeProfile = new TextButton("Close", Assets.R2_UI_SKIN);
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
		Statics stats = new Statics();
		GameCore.getInstance().setScreen(new SolMapScreen(stats.getSystem()));
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
		profileDialog.setPosition((Values.NEW_WIDTH / 2) - (Values.NEW_WIDTH / 4), (Values.NEW_HEIGHT / 2)
			- (Values.NEW_HEIGHT / 4));
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
	profile = new TextButton("Profile", Assets.R2_UI_SKIN);
	menu = new TextButton("Main Menu", Assets.R2_UI_SKIN);

	research = new TextButton("Research", Assets.R2_UI_SKIN);
	enterSystem = new TextButton("Enter Solar System", Assets.R2_UI_SKIN);

	// /** Initialize Lables */
	// title = new Label(Values.SUPERTITLE + ": " + Values.VERSION_NUMBER, Assets.R2_UI_SKIN);
	// title.setAlignment(Align.center);
	// title.setFontScaleX(1.2f);
	// title.setFontScaleY(1.1f);
	// title.setColor(Color.MAGENTA);

	welcome = new Label("Welcome: " + playerName, Assets.R2_UI_SKIN);
	welcome.setFontScale(1.5f);
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
	naviLeft.add(menu).width(Values.R2_UI_SIZES_BUTTON_WIDTH_CONTENT)
		.height(Values.R2_UI_SIZES_BUTTON_HEIGHT_CONTENT);

	/** Setting up the center top label table */
	// centerTop.add(title).width(Values.SIZE_UI_FIELD_CONTENT);
	// centerTop.row();
	centerTop.add(welcome).width(Values.SIZE_UI_FIELD_CONTENT);
	centerTop.row();

	/** Setting up the system info table **/

	Label tempLabel1, tempLabel2;

	tempLabel1 = new Label("Solar System", Assets.R2_UI_SKIN);
	tempLabel1.setColor(Color.MAGENTA);

	tempLabel2 = new Label("Information", Assets.R2_UI_SKIN);
	tempLabel2.setColor(Color.MAGENTA);

	systemInfo.add(tempLabel1);
	systemInfo.add(tempLabel2);
	systemInfo.row();
	systemInfo.add(new Label("Owner: ", Assets.R2_UI_SKIN));
	systemInfo.add(new Label("KateTheAwesome", Assets.R2_UI_SKIN));
	systemInfo.row();
	systemInfo.add(new Label("Size: ", Assets.R2_UI_SKIN));
	systemInfo.add(new Label("Something", Assets.R2_UI_SKIN));
	systemInfo.row();
	systemInfo.add(new Label("Coordinates: ", Assets.R2_UI_SKIN));
	systemInfo.add(new Label("545-101", Assets.R2_UI_SKIN));
	systemInfo.row();
	systemInfo.add(new Label("Units: ", Assets.R2_UI_SKIN));
	systemInfo.add(new Label("42", Assets.R2_UI_SKIN));
	systemInfo.row();
	systemInfo.add(new Label("Exploration: ", Assets.R2_UI_SKIN));
	systemInfo.add(new Label("100%", Assets.R2_UI_SKIN));
	systemInfo.row();
	systemInfo.add(enterSystem).width(Values.SIZE_UI_FIELD_CONTENT).colspan(2);
	systemInfo.row();

	// TODO: Make this pretty!
	Table selectorTable = new Table();
	systemSelector = new Label("", Assets.R2_UI_SKIN);
	selectorTable.setPosition(Values.HEX_MAP_BASE_OFFSET.x + 300, Values.HEX_MAP_BASE_OFFSET.y - 10);
	selectorTable.add(systemSelector);
	stage.addActor(selectorTable);

  }

}
