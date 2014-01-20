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
package de.r2soft.space.client.settings;

import com.badlogic.gdx.Gdx;
import com.sun.tools.javac.util.Pair;

import de.r2soft.space.framework.players.Player;
import de.r2soft.space.framework.types.IntVec2;

/**
 * Initializes the settings for the game. Also responsible for setting new Settings.
 * 
 * @author: ***REMOVED***
 */
public class BaseSettings {

  /** Just going with 720p game resolution here */
  public static int OLD_WIDTH = 1280;
  public static int OLD_HEIGHT = 720;
  public static int NEW_WIDTH = OLD_WIDTH;
  public static int NEW_HEIGHT = OLD_HEIGHT;
  public static String SUPERTITLE = "Game Client";
  public static String VERSION_NUMBER = "Prototype 1.2";
  public static final String SCREENTITLE_SETTINGS = "SETTINGS";
  public static final String SCREENTITLE_HOME = "HOME";
  public static final String SCREENTITLE_LOGIN = "LOGIN";
  public static final String SCREENTITLE_SOLAR = "SOL SYSTEM";

  /** Log sings */
  public final static String LOG_GLOBAL = "From Client";
  public final static String LOG_HEXMAP = "From HexMap";
  public final static String LOG_HEX_TILE = "From HexMap";
  public final static String LOG_SOLAR_MAP = "From System";
  public final static String LOG_MAP_OBJECT = "From Object";

  public static final String PREFERENCE_FILE_NAME = "de.r2.space.client.main-prefereces";
  public static final String PREFERENCE_SKIP_INTRO = "skip_intro";
  public static final String PREFERENCE_PLAY_MUSIC = "play_background_music";
  public static final String PREFERENCE_SAVE_USERNAME = "save_login_user_name";
  public static final String PREFERENCE_SAVED_USER_NAME = "saved_user_name";

  /** Map Sub-sizes */
  public static final IntVec2 mapSize = new IntVec2(900, 600);

  @Deprecated
  /** Needs to be made dynamic and fetched from server */
  public static final Player thisPlayer = new Player("KateTheAwesome");
  public static final Player _neutralplayer = new Player("_no_owning_player_");

  /** Hexmap formulas */
  @Deprecated
  public static final float HEX_START_X = -275f;
  @Deprecated
  public static final float HEX_START_Y = -100f;
  @Deprecated
  public static final float HEX_RADIUS = 64f;
  @Deprecated
  public static final float HEX_COORD_X = (1 / 2) * 64f;
  @Deprecated
  public static final float HEX_COORD_Y = (float) ((Math.sqrt(3) * 64f) / 2);

}
