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
package de.r2soft.empires.client.settings;

import de.r2soft.empires.framework.players.Player;
import de.r2soft.empires.framework.types.IntVec2;

/**
 * Initializes the settings for the game. Also responsible for setting new Settings.
 * 
 * @author: Katharina
 */
public class BaseSettings {

  /** Just going with 720p game resolution here */
  public static int OLD_WIDTH = 1280;
  public static int OLD_HEIGHT = 720;
  public static int NEW_WIDTH = OLD_WIDTH;
  public static int NEW_HEIGHT = OLD_HEIGHT;
  public static String SUPERTITLE = "Interstellar Empires";
  public static String VERSION_NUMBER = "Alpha 1";
  public static final String SCREENTITLE_SETTINGS = "Settings";
  public static final String SCREENTITLE_HOME = "Home";
  public static final String SCREENTITLE_LOGIN = "Login";
  public static final String SCREENTITLE_SOLAR = "Solar System";

  public static final String PREFERENCE_FILE_NAME = "de.r2.space.client.main-prefereces";
  public static final String PREFERENCE_SKIP_INTRO = "skip_intro";
  public static final String PREFERENCE_PLAY_MUSIC = "play_background_music";
  public static final String PREFERENCE_SAVE_USERNAME = "save_login_user_name";
  public static final String PREFERENCE_SAVED_USER_NAME = "saved_user_name";

  @Deprecated
  /** Needs to be made dynamic and fetched from server */
  public static final Player thisPlayer = new Player("KateTheAwesome");
  public static final Player _neutralplayer = new Player("_no_owning_player_");

  /**
   * ############### SOLAR SYSTEM MAP VALUES ###############
   */
  public static final IntVec2 SOL_MAP_BASE_OFFSET = new IntVec2(2, 32);
  public static final IntVec2 SOL_MAP_BASE_SIZE = new IntVec2(950, 650);

  /**
   * ############### HEXAGON GALAXY MAP VALUES ###############
   */
  public static final IntVec2 HEX_MAP_BASE_OFFSET = new IntVec2(2, 32);
  public static final IntVec2 HEX_MAP_BASE_SIZE = new IntVec2(950, 640);
}
