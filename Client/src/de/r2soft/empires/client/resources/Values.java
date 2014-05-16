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
package de.r2soft.empires.client.resources;

import de.r2soft.empires.framework.players.Player;
import de.r2soft.empires.framework.types.IntVec2;

/**
 * Initializes the settings for the game. Also responsible for setting new Settings.
 * 
 * @author: ***REMOVED***
 */
public class Values {

  /* Global Client Settings */
  public static int OLD_WIDTH = 1280;
  public static int OLD_HEIGHT = 720;
  public static int NEW_WIDTH = OLD_WIDTH;
  public static int NEW_HEIGHT = OLD_HEIGHT;
  public static String SUPERTITLE = "Interstellar Empires";
  public static String VERSION_NUMBER = "Alpha 1.3";
  public static final String SCREENTITLE_SETTINGS = "Settings";
  public static final String SCREENTITLE_HOME = "Galaxy Map";
  public static final String SCREENTITLE_LOGIN = "Login";
  public static final String SCREENTITLE_SOLAR = "Solar System";

  /* Preference Key Strings */
  public static final String PREFERENCE_FILE_NAME = "de.r2.space.client.main-prefereces";
  public static final String PREFERENCE_SKIP_INTRO = "skip_intro";
  public static final String PREFERENCE_PLAY_MUSIC = "play_background_music";
  public static final String PREFERENCE_SAVE_USERNAME = "save_login_user_name";
  public static final String PREFERENCE_SAVE_LOGINPW = "save_login_user_password";
  public static final String PREFERENCE_SAVED_USER_NAME = "saved_user_name";
  public static final String PREFERENCE_SAVED_USER_PW = "saved_user_pw_hash";
  public static final String PREFERENCE_LAUNCH_FULLSCREEN = "launch_as_fullscreen_app";
  public static final String PREFERENCE_USE_HEXAGON_DEBUGGING = "use-hexagons-as-debugging-sample-batch";

  /* Local player data from login */

  /** The player currently logged into the client */
  public static Player PLAYER;

  /**
   * Call this at start of session. Uses stored player information and throws a critical error of no information is
   * stored.
   */
  public static void initPlayer() {
	PLAYER = new Player();
  }

  /**
   * Initializes a new player with a name
   * 
   * @param name
   *          Name of the player to be initialized.
   */
  public static void initPlayer(String name) {
	Values.PLAYER = new Player(name);
  }

  @Deprecated
  public static final Player thisPlayer = new Player("KateTheAwesome");

  /*
   * ############### SOLAR SYSTEM MAP VALUES ###############
   */
  public static final IntVec2 SOL_MAP_BASE_OFFSET = new IntVec2(2, 32);
  public static final IntVec2 SOL_MAP_BASE_SIZE = new IntVec2(950, 650);

  /*
   * ############### HEXAGON GALAXY MAP VALUES ###############
   */
  public static final IntVec2 HEX_MAP_BASE_OFFSET = new IntVec2(2, 32);
  public static final IntVec2 HEX_MAP_BASE_SIZE = new IntVec2(950, 640);

  /*
   * ############### UI ELEMENT PADDING SIZES ###############
   */

  /** Padding size around UI elements */
  public static final float R2_UI_PIXEL_PAD_TINY = 5f;
  /** Padding size around UI elements */
  public static final float R2_UI_PIXEL_PAD_SMALL = 20f;
  /** Padding size around UI elements */
  public static final float R2_UI_PIXEL_PAD_MEDIUM = 45f;
  /** Padding size around UI elements */
  public static final float R2_UI_PIXEL_PAD_LARGE = 65f;
  /** Padding size around UI elements */
  public static final float R2_UI_PIXEL_PAD_MASSIVE = 100f;

  /*
   * ############### UI ELEMENT SIZES ###############
   */
  public static final float R2_UI_SIZES_BUTTON_HEIGHT_PRIME = 45f;
  public static final float R2_UI_SIZES_BUTTON_WIDTH_PRIME = 250f;

  public static final float R2_UI_SIZES_BUTTON_HEIGHT_CONTENT = 30f;
  public static final float R2_UI_SIZES_BUTTON_WIDTH_CONTENT = 150f;

  /*
   * ############### SOLAR MAP ICON SIZES ###############
   */

  /* Stars and System Center Bodies */

  /** Size for a red-dwarf star in the map */
  public static final float R2_SOLAR_CELESTIAL_STAR_REDDWARF = 150;
  /** Size for a red-giant star in the map */
  public static final float R2_SOLAR_CELESTIAL_STAR_REDGIANT = 300;
  /** Size for a blue-dwarf (Neutron) star in the map */
  public static final float R2_SOLAR_CELESTIAL_STAR_BLUEDWARF = 20;
  /** Size for a blue-giant (Class O Supergiant) star in the map */
  public static final float R2_SOLAR_CELESTIAL_STAR_BLUEGIANT = 275;
  /** Size for a brown-dwarf star in the map */
  public static final float R2_SOLAR_CELESTIAL_STAR_BROWNDWARF = 100;
  /** Size for a black hole/ wormhole "star" in the map */
  public static final float R2_SOLAR_CELESTIAL_STAR_BLACKHOLE = 75;

  /* Planets and Moons */

  /** Size for an Earth-Like planet (0.8-1.5 earth masses) */
  public static final float R2_SOLAR_CELESTIAL_PLANET_EARTHY = 25;
  /** Size for an icy dwarf planet (0.3-0.7 earth masses) */
  public static final float R2_SOLAR_CELESTIAL_PLANET_ICY = 20;
  /** Size for a vulcanic planet (0.05-0.3 earth masses) */
  public static final float R2_SOLAR_CELESTIAL_PLANET_FLAMY = 20;
  /** Size for a gas giant (10-100 earth masses) */
  public static final float R2_SOLAR_CELESTIAL_PLANET_GASSY = 75;
  /** Size for a standard earth-type rock moon (0.5-1 earth moon masses) */
  public static final float R2_SOLAR_CELESTIAL_MOON_ROCKY = 10;

  /* Ships, Fleet and Stations */
  /** Size for a standarf Fleet/Ship/Station Icon */
  public static final float R2_SOLAR_PLAYER_FLEET = 25;
  /** Size for a standarf Fleet/Ship/Station Icon */
  public static final float R2_SOLAR_PLAYER_SHIP = 25;
  /** Size for a standarf Fleet/Ship/Station Icon */
  public static final float R2_SOLAR_PLAYER_STATION = 25;

  /* FROM HERE ON IS OLD AND LAME SHIT! */

  /** Star sizes */

  /** Brown Dwarf */
  public static final float SIZE_CELESTIAL_BROWN_DWARF = 25;
  /** Red Dwarf */
  public static final float SIZE_CELESTIAL_RED_DWARF = 70;
  /** Red Giant */
  public static final float SIZE_CELESTIAL_RED_GIANT = 120;
  /** Neutron Star */
  public static final float SIZE_CELESTIAL_BLUE_DWARF = 20;
  /** Blue Supergiant */
  public static final float SIZE_CELESTIAL_BLUE_GIANT = 140;
  /** Small black hole */
  public static final float SIZE_CELESTIAL_BLACK_DWARF = 10;
  /** Medium black hole */
  public static final float SIZE_CELESTIAL_BLACK_MODERATE = 40;
  /** Large black hole */
  public static final float SIZE_CELESTIAL_BLACK_GIANT = 120;

  /** Fleet sizes */

  /** < 10 ships */
  @Deprecated
  public static final float SIZE_FLEET_TINY = 30;
  /** < 25 ships */
  @Deprecated
  public static final float SIZE_FLEET_SMALL = 40;
  /** < 50 ships */
  @Deprecated
  public static final float SIZE_FLEET_MEDIUM = 50;
  /** < 100 ships */
  @Deprecated
  public static final float SIZE_FLEET_LARGE = 60;
  /** < 250 ships */
  @Deprecated
  public static final float SIZE_FLEET_GIANT = 70;

  /** GUI sizes */

  /** Selection box sizes */

  @Deprecated
  public static final float SIZE_GUI_SELECTION_BOX_TINY = SIZE_FLEET_TINY + 4;
  @Deprecated
  public static final float SIZE_GUI_SELECTION_BOX_SMALL = SIZE_FLEET_SMALL + 4;
  @Deprecated
  public static final float SIZE_GUI_SELECTION_BOX_MEDIUM = SIZE_FLEET_MEDIUM + 4;
  @Deprecated
  public static final float SIZE_GUI_SELECTION_BOX_LARGE = SIZE_FLEET_LARGE + 4;
  @Deprecated
  public static final float SIZE_GUI_SELECTION_BOX_GIANT = SIZE_FLEET_GIANT + 4;

  /** Map tile sizes */
  @Deprecated
  public static final float SIZE_GUI_HEXAGON_TILE = 100;

  /** GUI ELEMENTS */

  /** How group elements (unwrapped) will be offset from one another */
  @Deprecated
  public static final float SIZE_UI_GROUP_OFFSET = -20;
  @Deprecated
  public static final float SIZE_SOLAR_GROUP_OFFSET_INITIAL = -30;

  /** Navigation button: exit, logout, settings, etc. */
  public static final float SIZE_UI_BUTTON_NAVIGON = 150;
  /** Content button: Build units, colonise planets, attack, destroy, etc. */
  public static final float SIZE_UI_BUTTON_CONTENT = 100;
  /** Login fields & explanations, tooltips, etc. */
  public static final float SIZE_UI_FIELD_CONTENT = 200;
  public static final float SIZE_UI_FIELD_CONTENT_SMALL = 100;
  /** Frame offset */
  public static final float SIZE_UI_GLOBAL_FRAME_OFFSET = -35;

  /** Planet sizes */

  /** Asteroids & Rocks */
  public static final float SIZE_CELESTIAL_PLANET_CLASS_A = 0;
  /** Volcanic Planet */
  public static final float SIZE_CELESTIAL_PLANET_CLASS_B = 0;
  /** Desert Planet */
  public static final float SIZE_CELESTIAL_PLANET_CLASS_C = 0;
  /** Earth Planet */
  public static final float SIZE_CELESTIAL_PLANET_CLASS_D = 0;
  /** Ice Planet */
  public static final float SIZE_CELESTIAL_PLANET_CLASS_E = 0;
  /** Gas Planets */
  public static final float SIZE_CELESTIAL_PLANET_CLASS_F = 0;

  /** System sizes */

  /** Brown dwarf solar system */
  public static final float SIZE_SYSTEM_BROWN_DWARF = 140;
  /** Red dwarf solar system */
  public static final float SIZE_SYSTEM_RED_DWARF = 230;
  /** Red giant solar system */
  public static final float SIZE_SYSTEM_RED_GIANT = 280;
  /** Neutron star solar system */
  public static final float SIZE_SYSTEM_WHITE_DWARF = 100;
  /** Blue giant solar system */
  public static final float SIZE_SYSTEM_BLUE_GIANT = 280;
  /** Black hole solar system */
  public static final float SIZE_SYSTEM_BLACK_HOLE = 250;

}
