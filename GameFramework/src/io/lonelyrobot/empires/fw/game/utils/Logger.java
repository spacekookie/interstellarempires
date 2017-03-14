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

package io.lonelyrobot.empires.fw.game.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * This logger class is used all over the game framework to inform the user (or developer
 * :) ) of any problems that occur during the runtime of the game. This is done without
 * creating a logger in the framework itself and having to pass it either between objects
 * in the net framework or having platform specific code in the framework as clients and
 * servers might want to log things differently.
 * 
 * Therefore there is a setup function that needs to be provided with runnable lambdas
 * that hand off the messages provided to the log utility functions to whatever
 * implementation was provided to the setup function.
 * 
 * @author Katharina 'spacekookie' Fey <kookie@spacekookie.de>
 */
public abstract class Logger {
  public static enum LEVEL {
    DEBUG, INFO, WARN, ERROR, FATAL, APOCALYPSE
  }

  /** A callback that provides both message and level parameters */
  public interface CombineCallback {
    void log(String msg, LEVEL lvl);
  }

  public interface SingleCallback {
    void log(String msg);
  }

  private static boolean setup = false;
  private static boolean combined = true;
  private static CombineCallback combine;
  private static Map<LEVEL, SingleCallback> mapping;

  static {
    mapping = new HashMap<>();

  }

  /**
   * This setup function can be used to register a single runnable that will be given a
   * message string and a log level while the function provided then needs to handle
   * different log levels by itself without help of this Logger class.
   * 
   * @param cb
   *          Callback lambda provided from user
   */
  public static int setup(CombineCallback cb) {
    int ret = 0;

    if (cb == null)
      return 1337;

    /** Store the combined callback function */
    combined = true;
    combine = cb;

    if (ret == 0)
      Logger.setup = true;
    return ret;
  }

  /**
   * This setup function uses a single callback system where each log level is registered
   * against a different callback function where this Logger class will handle the
   * splitting of log streams to the parent caller.
   * 
   * Make sure to register ALL levels before calling {@link #validate()}
   * 
   * @param sb
   *          Callback lambda provided from user
   */
  public static int setup(SingleCallback sb, LEVEL lvl) {
    int ret = 0;

    if (sb == null || lvl == null)
      return 1337;

    combined = false;
    mapping.put(lvl, sb);
    return ret;
  }

  /**
   * This utility function validates the state of the logger. This function can be called
   * at an time to check if the logger was setup correctly and completely. It will also
   * setup the setup state correctly if validation passes successfully. This is required
   * when using SingleCallback functionality.
   * 
   * @return
   */
  public static boolean validate() {
    if (combined) {
      if (combine != null) {
        setup = true;
        return true;
      }
    } else {

      if (mapping != null) {
        int error = 0;

        if (mapping.get(LEVEL.DEBUG) == null)
          error++;

        if (mapping.get(LEVEL.INFO) == null)
          error++;

        if (mapping.get(LEVEL.WARN) == null)
          error++;

        if (mapping.get(LEVEL.ERROR) == null)
          error++;

        if (mapping.get(LEVEL.FATAL) == null)
          error++;

        if (mapping.get(LEVEL.APOCALYPSE) == null)
          error++;

        if (error == 0) {
          setup = true;
          return true;
        }
      }
    }

    return false;
  }

  /*****************************************************************/

  public void debug(String msg) {
    if (!setup)
      return;

    if (combined)
      combine.log(msg, LEVEL.DEBUG);
    else
      mapping.get(LEVEL.DEBUG).log(msg);
  }

  public void info(String msg) {
    if (!setup)
      return;

    if (combined)
      combine.log(msg, LEVEL.INFO);
    else
      mapping.get(LEVEL.INFO).log(msg);
  }

  public void warn(String msg) {
    if (!setup)
      return;

    if (combined)
      combine.log(msg, LEVEL.WARN);
    else
      mapping.get(LEVEL.WARN).log(msg);
  }

  public void error(String msg) {
    if (!setup)
      return;

    if (combined)
      combine.log(msg, LEVEL.ERROR);
    else
      mapping.get(LEVEL.ERROR).log(msg);
  }

  public void fatal(String msg) {
    if (!setup)
      return;

    if (combined)
      combine.log(msg, LEVEL.FATAL);
    else
      mapping.get(LEVEL.FATAL).log(msg);
  }

  public void apocalypse(String msg) {
    if (!setup)
      return;

    if (combined)
      combine.log(msg, LEVEL.APOCALYPSE);
    else
      mapping.get(LEVEL.APOCALYPSE).log(msg);
  }

}
