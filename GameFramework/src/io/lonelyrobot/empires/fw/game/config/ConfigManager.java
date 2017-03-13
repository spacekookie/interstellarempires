/* #########################################################################
 * Copyright (c) 2017 Lonely Robot
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

package io.lonelyrobot.empires.fw.game.config;

import java.io.File;

/**
 * This is a static class which is responsible for reading in config files (on client and
 * server side) and providing the framework with a coherent source of stats and data to
 * populate the gameworld with.
 * 
 * It manages all configurations by types and id handles for quick map lookup. To
 * initialise this class, call {@link #setup()} before using it.
 * 
 * @author Katharina 'spacekookie' Fey <kookie@spacekookie.de>
 */
public abstract class ConfigManager {
  private static boolean setup = false;
  private static String path;

  static {
    // FIXME: Find out if run by client or server and then get real path
    path = "assets/";
  }

  public static void setup() {

    /** First check if we're already setup */
    if (ConfigManager.setup) {
      // TODO: Log a warning
      return;
    }

    /** Check that our path scoping hasn't failed us */
    if (!new File(path).exists()) {
      // TODO: Log an error
      return;
    }

    /** Aaand we're ready to go */
    ConfigManager.setup = true;
  }
}
