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

package io.lonelyrobot.empires.client.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Singleton settings handler to interface the entire application with the settings database.
 * 
 * @author ***REMOVED***
 * 
 */
public class SettingsInterface {
  private static SettingsInterface self;

  private Preferences prefs;

  private SettingsInterface() {
	prefs = Gdx.app.getPreferences(Values.PREFERENCE_FILE_NAME);
  }

  public boolean contains(String flag) {
	return prefs.contains(flag);
  }

  public boolean containsList(String flag) {
	return prefs.contains(flag + "_exists");
  }

  public static SettingsInterface getInstance() {
	if (self == null)
	  self = new SettingsInterface();
	return self;
  }

  public SettingsInterface putBoolean(String flag, boolean value) {
	prefs.putBoolean(flag, value);
	return this;
  }

  public boolean getBoolean(String flag) {
	if (prefs.contains(flag))
	  return prefs.getBoolean(flag);
	return false;
  }

  public SettingsInterface putList(String[] values, String flag) {
	String[] names = generateNames(flag, values.length);

	prefs.putInteger(flag, values.length);
	prefs.putBoolean(flag + "_exists", true);
	for (int n = 0; n < values.length; n++) {
	  prefs.putString(names[n], values[n]);
	}
	return this;
  }

  public String[] getList(String flag) {
	int size = prefs.getInteger(flag);
	String[] results = new String[size];
	String[] names = generateNames(flag, size);
	for (int n = 0; n < size; n++) {
	  results[n] = prefs.getString(names[n]);
	}
	return results;
  }

  private String[] generateNames(String flag, int size) {
	String[] names = new String[size];
	for (int n = 0; n < size; n++) {
	  names[n] = flag + n + "th";
	}
	return names;
  }

  /**
   * ABSOLUTELY CALL THIS BEFORE ENDING WRITING TO THE SETTINGS DATABASE!
   * 
   * Flushing after every write action is wasteful thus to minimise database writing please flush after the last action
   * only!
   */
  public void flush() {
	prefs.flush();
  }

  public void putString(String flag, String value) {
	prefs.putString(flag, value);
  }

  public String getString(String flag) {
	return prefs.getString(flag);
  }
}
